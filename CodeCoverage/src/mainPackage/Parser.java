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
import com.github.javaparser.ast.stmt.SwitchEntryStmt;
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

				switch (s.getClass().getSimpleName()) {
				case "ExpressionStmt":
					block.addStatement(s);
					block.addStatement(print(s));
					n.setBody(block);
					break;

				case "ReturnStmt":
					block.addStatement(print(s));
					block.addStatement(s);
					n.setBody(block);
					break;

				case "IfStmt":
					IfStmt f = (IfStmt) s;
					f = modifyIfStatement(f);
					block.addStatement(f);
					block.addStatement(print(f));
					n.setBody(block);
					break;

				case "ForStmt":
					ForStmt fs = (ForStmt) s;
					fs = modifyForStatement(fs);
					block.addStatement(fs);
					block.addStatement(print(fs));
					n.setBody(block);
					break;

				case "WhileStmt":
					WhileStmt ws = (WhileStmt) s;
					ws = modifyWhileStatement(ws);
					block.addStatement(ws);
					block.addStatement(print(ws));
					n.setBody(block);
					break;
				case "SwitchStmt":
					SwitchStmt sws = (SwitchStmt) s;
					sws = modifiedSwitchStatement(sws);
					block.addStatement(sws);
					block.addStatement(print(sws));
					n.setBody(block);
					break;
				// System.out.println(sws.getEntries().getClass().getSimpleName());
				}
			}
		}

		// works with List<Node> also.
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
					fs = modifyForStatement(fs);
					newBlock.addStatement(fs);
					newBlock.addStatement(print(fs));
				} else if (s.getClass().getSimpleName().equals("WhileStmt")) {
					WhileStmt ws = (WhileStmt) s;
					ws = modifyWhileStatement(ws);
					newBlock.addStatement(ws);
					newBlock.addStatement(print(ws));
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
			modifiedIfStatement.setThenStmt(modifyBlockStatement(modifiedIfStatement.getChildrenNodes().get(1).getChildrenNodes()));
			if (modifiedIfStatement.getElseStmt() != null) {
				modifiedIfStatement.setElseStmt(modifyBlockStatement(modifiedIfStatement.getElseStmt().getChildrenNodes()));
			}
			return modifiedIfStatement;
		}

		public ForStmt modifyForStatement(ForStmt forStatement) {
			ForStmt modifiedForStatement = forStatement;
			modifiedForStatement.setBody(modifyBlockStatement(modifiedForStatement.getChildrenNodes().get(3).getChildrenNodes()));
			return modifiedForStatement;
		}

		public WhileStmt modifyWhileStatement(WhileStmt whileStatement) {
			WhileStmt modifiedWhileStatement = whileStatement;
			modifiedWhileStatement.setBody(modifyBlockStatement(modifiedWhileStatement.getChildrenNodes().get(1).getChildrenNodes()));
			return modifiedWhileStatement;
		}

		public SwitchStmt modifiedSwitchStatement(SwitchStmt switchStatement) {
			SwitchStmt modifiedSwitchStatement = switchStatement;
			modifiedSwitchStatement.setEntries(modifySwitchEntryStatement((modifiedSwitchStatement.getChildrenNodes())));
			return modifiedSwitchStatement;
		}

		public List<SwitchEntryStmt> modifySwitchEntryStatement(List<Node> n) {
			SwitchEntryStmt modifiedSwitchEntry = null;
			List<SwitchEntryStmt> newEntryList = new ArrayList<>();
			List<Node> nodeList = new ArrayList<>(n);
			for (Node s : nodeList) {
				if (s.getClass().getSimpleName().equals("SwitchEntryStmt")) {
					SwitchEntryStmt sw = (SwitchEntryStmt) s;
					System.out.println(sw);
					modifiedSwitchEntry = new SwitchEntryStmt();
					modifiedSwitchEntry.setLabel(sw.getLabel());
					for (Statement statement : sw.getStmts()) {
						modifiedSwitchEntry.addStatement(print(statement));
						modifiedSwitchEntry.addStatement(statement);
					}
					newEntryList.add(modifiedSwitchEntry);
				}
			}
			return newEntryList;
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
