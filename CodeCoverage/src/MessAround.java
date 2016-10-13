import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MessAround {

	public static void main(String[] args) throws Exception {
		FileInputStream in = new FileInputStream("SimpleUMLCandI.java");

		CompilationUnit cu;
		try {
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}
		//new MethodModVisitor().visit(cu, null);
		//new ExpressionVisitor().visit(cu, null);
		new ifForForEachVisitor().visit(cu, null);
		// write the modified cu back...
		//System.out.println(cu.toString());

		// Write modified AST to a file
		byte[] modfile = cu.toString().getBytes();
		Path file = Paths.get("testParse.java");
		Files.write(file, modfile);
	}

	/**
	 * Example of how to modify code and insert instrumentation Just print out
	 * name of class after execution
	 */
	private static class MethodModVisitor extends VoidVisitorAdapter {
		@Override
		public void visit(MethodDeclaration n, Object arg) {

			// Step 1 - create a new node
			// (Various options)
			// NameExpr systemOut = NameExpr.name("System.out");
			// MethodCallExpr call = new MethodCallExpr(systemOut, "println");
			// MethodCallExpr call = new
			// MethodCallExpr(NameExpr.name("System.out"), "println");
			MethodCallExpr call = new MethodCallExpr(new NameExpr("System.out"), "println");
			call.addArgument(new IntegerLiteralExpr(String.valueOf(n.getBegin()))); // gets
																					// a
																					// line
																					// number
			// Add in the argument - name of method visited
			call.addArgument(new StringLiteralExpr(n.getName()));

			// Step 2 - Add this statement to the method (adds to end)
			// n.getBody().addStatement(call);

			// Step 3 - Modify block
			BlockStmt block = new BlockStmt();
			block.addStatement(call);
			block.addStatement(n.getBody());
			n.setBody(block);
		}

	}

	private static class ExpressionVisitor extends VoidVisitorAdapter {

		@Override
		public void visit(ExpressionStmt n, Object arg) {			
			System.out.println(new IntegerLiteralExpr(String.valueOf(n.getBegin().line)));
			System.out.println((new StringLiteralExpr(n.getExpression().toString())));
		}
	}
	
	private static class ifForForEachVisitor extends VoidVisitorAdapter {
		
		ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("js");

//		@Override
//		public void visit(ForeachStmt n, Object arg) {
//			System.out.println(new IntegerLiteralExpr(String.valueOf(n.getBegin().line)));
//			System.out.println("Variable :" + n.getVariable());
//			System.out.println("iterable :" + n.getIterable());
//			System.out.println(n.getBody());
//			
//		}
//		
//		@Override
//		public void visit(BooleanLiteralExpr n, Object arg) {
//			System.out.println(n.getValue());
//		}


//		@Override
//		public void visit(IfStmt n, Object arg) {
//			//int statmentSize = n.getEnd().line -  n.getBegin().line;
//			System.out.println(new IntegerLiteralExpr(String.valueOf(n.getBegin().line)));
//			try {
//				System.out.println("Condition: " + n.getCondition());
//				System.out.println(engine.eval(n.getCondition().toString()));
//				if(!(boolean)engine.eval(n.getCondition().toString())){
//					System.out.println("ElseCondition : " + n.getElseStmt());
//				} else {
//					System.out.println("ThenStatment: " + n.getThenStmt());
//				}
//			} catch (ScriptException e) {
//				e.printStackTrace();
//			}
//				
//			//System.out.println(new IntegerLiteralExpr(String.valueOf(n.getEnd().line)));
//			//System.out.println(statmentSize + 1);
//		}
		
		public void visit(BinaryExpr n, Object a){
			
			System.out.println(n.getLeft() + " " + n.getBegin().line);
		}
		
//		@Override
//		public void visit(MethodDeclaration n, Object arg) {
//			
//			
//			
//			
//		}
//		
//		
//		public void visit(IfStmt n, Object arg) {
//			
//			
//			
//			
//			
//			
//			
//		}
		
		
		
	}

}