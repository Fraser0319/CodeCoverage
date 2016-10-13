
import java.beans.Expression;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.github.javaparser.JavaParser;
import com.github.javaparser.Position;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.ModifierVisitorAdapter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class SimpleJPmod {

	public static void main(String[] args) throws Exception {
		FileInputStream in = new FileInputStream("PracticalTwo.java");

		CompilationUnit cu;
		try {
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}
		//new MethodModVisitor().visit(cu,null);
		new ModifierVisitor().visit(cu, null);
		//cu.accept(new ModifierVisitor(), null);
		// write the modified cu back...
		//System.out.println(cu.toString());

		// Write modified AST to a file
		byte[] modfile = cu.toString().getBytes();
		Path file = Paths.get("newFile.java");
		Files.write(file, modfile);
	}

	/**
	 * Example of how to modify code and insert instrumentation Just print out
	 * name of class after execution
	 */
	private static class MethodModVisitor extends VoidVisitorAdapter {
		
		public void visit(IfStmt n, Object a){
			MethodCallExpr newCall = new MethodCallExpr(new NameExpr("System.out"), "println");
			newCall.addArgument(n.getCondition());
			BlockStmt block = new BlockStmt();
			block.addStatement(newCall);
			
			//System.out.println(block.getStmts());
			for(Statement s : block.getStmts()){
				System.out.println(s);
			}
		}
		
		@Override
		public void visit(MethodDeclaration n, Object arg) {
			
			// Step 1 - create a new node
			MethodCallExpr call = new MethodCallExpr(new NameExpr("System.out"), "println");
			// Add in the argument - name of method visited
			call.addArgument(new StringLiteralExpr(n.getName()));
			
			// Step 2 - Modify block
			BlockStmt block = new BlockStmt();
			block.addStatement(call);
			block.addStatement(n.getBody());
			n.setBody(block);
		}
	}
	
	private static class ModifierVisitor extends ModifierVisitorAdapter{
		
		@Override
		public Node visit(MethodDeclaration n, Object arg) {
			n.setLineComment("this is a method");
			return super.visit(n, arg);
		}
		
		public Node visit(ExpressionStmt n, Object a){
			
			BlockStmt block = new BlockStmt();
			block.addStatement(printCondition(n.getExpression(), n.getBegin().line));
			
			return block;
		}
		
//		public Node visit(IfStmt n, Object a){
//			n.setLineComment("this is an if statment");
//			
//			BlockStmt b = new BlockStmt();
//			b.addStatement(n);
//			b.addStatement(printCondition(n.getCondition(),n.getBegin().line));
//			BlockStmt bb = new BlockStmt();
//			bb.addStatement(n.getCondition());
//			bb.addStatement(n.getThenStmt());
//			bb.addStatement(printStatment(n.getThenStmt().getEnd().line));
//			bb.addStatement(n.getElseStmt());
//			System.out.println(n.getThenStmt().getEnd().line);
//			
//			return bb;
//		}
		
		
		private Statement printCondition(com.github.javaparser.ast.expr.Expression e, int line){
			
			MethodCallExpr newCall = new MethodCallExpr(new NameExpr("System.out"), "println");
			newCall.addArgument(new StringLiteralExpr("executed line: " + line));
			
			return new ExpressionStmt(newCall);
			
		}
		
		private Statement printStatment( int line){
			
			MethodCallExpr newCall = new MethodCallExpr(new NameExpr("System.out"), "println");
			newCall.addArgument(new StringLiteralExpr("Then statement executed"));
			
			return new ExpressionStmt(newCall);
			
		}
		
	}
}