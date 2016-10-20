package mainPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mainPackage.CodeTracker;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntryStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Parser {

	public static void main(String[] args) throws Exception {
		FileInputStream in = new FileInputStream("PracticalTwo.java");
		CompilationUnit cu;
		Parser p = new Parser();
		p.deserialise();
		try {

			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}
		new ModifierVisitor().visit(cu, null);
		byte[] modfile = cu.toString().getBytes();
		Path file = Paths.get("src/mainPackage/PracticalTwo.java");
		Files.write(file, modfile);
		p.serialise();
	}

	public void serialise() {

		try {
			FileOutputStream writeOut = new FileOutputStream("codeTracker.ser");
			ObjectOutputStream out = new ObjectOutputStream(writeOut);
			out.writeObject(CodeTracker.getCoverageRecord());
			out.close();
			writeOut.close();
			System.out.println("list saved in codeTracker.ser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deserialise() {

		try {
			FileInputStream fileIn = new FileInputStream("codeTracker.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			CodeTracker.setCoverageRecord((List<Triple>) in.readObject());
			fileIn.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static class ModifierVisitor extends VoidVisitorAdapter {

		public void visit(IfStmt n, Object a) {
			n = modifyIf(n);
		}

		public void visit(ForStmt n, Object a) {
			n = modifyForLoop(n);
		}

		public void visit(WhileStmt n, Object a) {
			n = modifyWhile(n);
		}

		public void visit(SwitchStmt n, Object a) {
			n = modifiedSwitch(n);
		}

		public void visit(ForeachStmt n, Object a) {
			n = modifyForEach(n);
		}

		// public void visit(MethodDeclaration n, Object a) {
		// BlockStmt block = new BlockStmt();
		// for (Statement s : n.getBody().getStmts()) {
		//
		// switch (s.getClass().getSimpleName()) {
		// case "ExpressionStmt":
		// block.addStatement(s);
		// block.addStatement(print(s));
		// n.setBody(block);
		// break;
		//
		// case "ReturnStmt":
		// block.addStatement(print(s));
		// block.addStatement(s);
		// n.setBody(block);
		// break;
		//
		// case "IfStmt":
		// IfStmt f = (IfStmt) s;
		// f = modifyIf(f);
		// block.addStatement(f);
		// block.addStatement(print(f));
		// n.setBody(block);
		// break;
		//
		// case "ForStmt":
		// ForStmt fs = (ForStmt) s;
		// fs = modifyForLoop(fs);
		// block.addStatement(fs);
		// block.addStatement(print(fs));
		// n.setBody(block);
		// break;
		//
		// case "WhileStmt":
		// WhileStmt ws = (WhileStmt) s;
		// ws = modifyWhile(ws);
		// block.addStatement(ws);
		// block.addStatement(print(ws));
		// n.setBody(block);
		// break;
		//
		// case "SwitchStmt":
		// SwitchStmt sws = (SwitchStmt) s;
		// sws = modifiedSwitch(sws);
		// block.addStatement(sws);
		// block.addStatement(print(sws));
		// n.setBody(block);
		// break;
		//
		// case "ForeachStmt":
		// ForeachStmt fes = (ForeachStmt) s;
		// fes = modifyForEach(fes);
		// block.addStatement(fes);
		// block.addStatement(print(fes));
		// n.setBody(block);
		// break;
		// }
		// }
		// }

		// works with List<Node> also.
		public BlockStmt modifyBlockStatement(List<? extends Node> n) {

			BlockStmt newBlock = new BlockStmt();
			List<? extends Node> newList = new ArrayList<>(n);
			for (Node s : newList) {
				if (s.getClass().getSimpleName().equals("IfStmt")) {
					IfStmt f = (IfStmt) s;
					f = modifyIf(f);
					newBlock.addStatement(f);
					newBlock.addStatement(print(f));
				} else if (s.getClass().getSimpleName().equals("ForStmt")) {
					ForStmt fs = (ForStmt) s;
					fs = modifyForLoop(fs);
					newBlock.addStatement(fs);
					newBlock.addStatement(print(fs));
				} else if (s.getClass().getSimpleName().equals("WhileStmt")) {
					WhileStmt ws = (WhileStmt) s;
					ws = modifyWhile(ws);
					newBlock.addStatement(ws);
					newBlock.addStatement(print(ws));
				} else if (s.getClass().getSimpleName().equals("SwitchStmt")) {
					SwitchStmt sws = (SwitchStmt) s;
					sws = modifiedSwitch(sws);
					newBlock.addStatement(sws);
					newBlock.addStatement(print(sws));
				} else if (s.getClass().getSimpleName().equals("ForeachStmt")) {
					ForeachStmt fes = (ForeachStmt) s;
					fes = modifyForEach(fes);
					newBlock.addStatement(fes);
					newBlock.addStatement(print(fes));
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

		public IfStmt modifyIf(IfStmt ifStatement) {
			IfStmt modifiedIfStatement = ifStatement;
			modifiedIfStatement.setThenStmt(
					modifyBlockStatement(modifiedIfStatement.getChildrenNodes().get(1).getChildrenNodes()));
			if (modifiedIfStatement.getElseStmt() != null) {
				modifiedIfStatement
						.setElseStmt(modifyBlockStatement(modifiedIfStatement.getElseStmt().getChildrenNodes()));
			}
			return modifiedIfStatement;
		}

		public ForStmt modifyForLoop(ForStmt forStatement) {
			ForStmt modifiedForStatement = forStatement;
			modifiedForStatement
					.setBody(modifyBlockStatement(modifiedForStatement.getChildrenNodes().get(3).getChildrenNodes()));
			return modifiedForStatement;
		}

		public WhileStmt modifyWhile(WhileStmt whileStatement) {
			WhileStmt modifiedWhileStatement = whileStatement;
			modifiedWhileStatement
					.setBody(modifyBlockStatement(modifiedWhileStatement.getChildrenNodes().get(1).getChildrenNodes()));
			return modifiedWhileStatement;
		}

		public SwitchStmt modifiedSwitch(SwitchStmt switchStatement) {
			SwitchStmt modifiedSwitchStatement = switchStatement;
			modifiedSwitchStatement
					.setEntries(modifySwitchEntryStatement((modifiedSwitchStatement.getChildrenNodes())));
			return modifiedSwitchStatement;
		}

		public List<SwitchEntryStmt> modifySwitchEntryStatement(List<Node> n) {
			SwitchEntryStmt modifiedSwitchEntry = null;
			List<SwitchEntryStmt> newEntryList = new ArrayList<>();
			List<Node> nodeList = new ArrayList<>(n);
			for (Node s : nodeList) {
				if (s.getClass().getSimpleName().equals("SwitchEntryStmt")) {
					SwitchEntryStmt sw = (SwitchEntryStmt) s;
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

		public ForeachStmt modifyForEach(ForeachStmt forEachStatement) {

			ForeachStmt modifiedForech = forEachStatement;
			modifiedForech.setBody(modifyBlockStatement(modifiedForech.getChildrenNodes().get(2).getChildrenNodes()));
			return modifiedForech;
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
