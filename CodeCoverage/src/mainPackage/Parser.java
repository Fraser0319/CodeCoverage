package mainPackage;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.ModifierVisitorAdapter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Parser {

	public static void main(String[] args) throws Exception {
		FileInputStream in = new FileInputStream("PracticalTwo.java");

		CompilationUnit cu;
		try {
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}
		// new MethodModVisitor().visit(cu,null);
		new ModifierVisitor().visit(cu, null);
		// cu.accept(new ModifierVisitor(), null);
		// write the modified cu back...
		// System.out.println(cu.toString());

		// Write modified AST to a file
		byte[] modfile = cu.toString().getBytes();
		Path file = Paths.get("newFile.java");
		Files.write(file, modfile);
	}

	private static class ModifierVisitor extends VoidVisitorAdapter {

		public void visit(MethodDeclaration n, Object a) {
			BlockStmt block = new BlockStmt();
			for (Statement s : n.getBody().getStmts()) {

				switch (s.getClass().getName()) {
				case "com.github.javaparser.ast.stmt.ExpressionStmt":
					block.addStatement(s);
					block.addStatement(print(s));
					n.setBody(block);
					break;
				case "com.github.javaparser.ast.stmt.ReturnStmt":
					block.addStatement(print(s));
					block.addStatement(s);
					n.setBody(block);
					break;
				case "com.github.javaparser.ast.stmt.IfStmt":
					IfStmt f = (IfStmt) s;
					block.addStatement(print(f.getCondition()));
					block.addStatement(f);
					n.setBody(block);
					break;

				case "com.github.javaparser.ast.stmt.ForStmt":
					ForStmt fs = (ForStmt) s;
					block.addStatement(fs);
					System.out.println(fs.getBody());
					n.setBody(block);
					break;

				case "com.github.javaparser.ast.stmt.WhileStmt":
					WhileStmt ws = (WhileStmt) s;
					block.addStatement(ws);
					n.setBody(block);
					break;
				}
			}
		}

		public Statement print(Statement e) {

			MethodCallExpr newCall = new MethodCallExpr(new NameExpr("mainPackage.CodeTracker"), "markExecuted");
			newCall.addArgument(new StringLiteralExpr("PracticalTwo"));
			newCall.addArgument(new StringLiteralExpr("" + e.getBegin().line));

			return new ExpressionStmt(newCall);
		}

		public Statement print(Expression e) {

			MethodCallExpr newCall = new MethodCallExpr(new NameExpr("mainPackage.CodeTracker"), "markExecuted");
			newCall.addArgument(new StringLiteralExpr("PracticalTwo"));
			newCall.addArgument(new StringLiteralExpr("" + e.getBegin().line));

			return new ExpressionStmt(newCall);
		}

	}

}
