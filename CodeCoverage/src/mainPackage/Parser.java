package mainPackage;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.core.Is;
import mainPackage.CodeTracker;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
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

		System.out.println(CodeTracker.getCoverageRecord());
		// Write modified AST to a file
		byte[] modfile = cu.toString().getBytes();
		Path file = Paths.get("newFile.java");
		Files.write(file, modfile);
	}

	/***
	 * ***Notes***
	 * 
	 * Convert the for each loops to use streams later to clean up the code
	 * 
	 * @author fraserbeaton
	 *
	 */

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
					f = modifyIfStatement(f);
					block.addStatement(f);
					block.addStatement(print(f));
					n.setBody(block);
					break;

				case "com.github.javaparser.ast.stmt.ForStmt":
					ForStmt fs = (ForStmt) s;
					fs = modifForStatement(fs);
					block.addStatement(fs);
					block.addStatement(print(fs));
					n.setBody(block);
					break;

				case "com.github.javaparser.ast.stmt.WhileStmt":
					WhileStmt ws = (WhileStmt) s;
					// System.out.println(ws.getBody());
					block.addStatement(ws);
					n.setBody(block);
					break;
				}
			}
		}

		public BlockStmt modifyBlockStatement(List<? extends Node> n) {

			BlockStmt newBlock = new BlockStmt();
			List<? extends Node> newList = new ArrayList<>(n);
			for (Node s : newList) {
				if (s.getClass().getSimpleName().equals("IfStmt")) {
					IfStmt f = (IfStmt) s;
					f = modifyIfStatement(f);
					newBlock.addStatement(f);
					newBlock.addStatement(print(f));
				} else if (s.getClass().getSimpleName().equals("ForStmt")) {
					ForStmt fs = (ForStmt) s;
					fs = modifForStatement(fs);
					newBlock.addStatement(fs);
					newBlock.addStatement(print(fs));
					//newBlock.addStatement(fs.setBody(modifyBlockStatement(fs.getBody().getChildrenNodes())));

				} else if (s.getClass().getSimpleName().equals("WhileStmt")) {
					WhileStmt ws = (WhileStmt) s;
				} else {
					if (s instanceof Statement) {
						Statement newStatement = (Statement) s;
						newBlock.addStatement(newStatement);
						newBlock.addStatement(print(newStatement));
					} else {
						Expression newExpr = (Expression) s;
						newBlock.addStatement(newExpr);
						newBlock.addStatement(print(newExpr));
					}
				}
			}
			return newBlock;
		}

		public IfStmt modifyIfStatement(IfStmt ifStatement) {

			IfStmt modifiedIfStatement = ifStatement;
			modifiedIfStatement.setThenStmt(
					modifyBlockStatement(modifiedIfStatement.getChildrenNodes().get(1).getChildrenNodes()));
			return modifiedIfStatement;

		}

		public ForStmt modifForStatement(ForStmt forStatement) {
			ForStmt modifiedForStatement = forStatement;
			modifiedForStatement
					.setBody(modifyBlockStatement(modifiedForStatement.getChildrenNodes().get(3).getChildrenNodes()));
			return modifiedForStatement;
		}

		public Statement print(Statement e) {

			MethodCallExpr newCall = new MethodCallExpr(new NameExpr("mainPackage.CodeTracker"), "markExecuted");
			newCall.addArgument(new StringLiteralExpr("PracticalTwo"));
			newCall.addArgument(new StringLiteralExpr("" + e.getBegin().line));
			// newCall.addArgument(new BooleanLiteralExpr(true));
			CodeTracker.addCode("PracticalTwo", "" + e.getBegin().line);
			// System.out.println(CodeTracker.getCoverageRecord());
			return new ExpressionStmt(newCall);
		}

		public Statement print(Expression e) {

			MethodCallExpr newCall = new MethodCallExpr(new NameExpr("mainPackage.CodeTracker"), "markExecuted");
			newCall.addArgument(new StringLiteralExpr("PracticalTwo"));
			newCall.addArgument(new StringLiteralExpr("" + e.getBegin().line));
			// newCall.addArgument(new BooleanLiteralExpr(true));
			CodeTracker.addCode("PracticalTwo", "" + e.getBegin().line);
			// System.out.println(CodeTracker.getCoverageRecord());
			return new ExpressionStmt(newCall);
		}
	}
}
