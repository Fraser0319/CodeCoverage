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
import java.util.Scanner;

import mainPackage.CodeTracker;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntryStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.WhileStmt;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Parser {
	
	private static String fileName;

	public String getFileName() {
		return fileName;
	}

	public static void main(String[] args) throws Exception {

		System.out.println("------ Code Parser ------");
		System.out.println("Enter the path to your src folder you wish to parse: ");
		Scanner scan = new Scanner(System.in);
		String path = scan.nextLine();
		File[] files = new File(path).listFiles();
		

		for (File f : files) {
			fileName = f.getName();
			System.out.println("Parsing: " + f.getName());

			FileInputStream in = new FileInputStream(f);
			CompilationUnit cu;
			Parser p = new Parser();
			if (new File("codeTracker.ser").isFile()) {
				List<Line> oldList = p.deserialise();
				List<Line> updatedList = new ArrayList<>(oldList);
				// removing old parsing for the input file in the list
				for (Line l : oldList) {
					if (l.getFileName().equals(f.getName())) {
						updatedList.remove(l);
					}
					CodeTracker.setCoverageRecord(updatedList);
				}
			}

			try {
				cu = JavaParser.parse(in);
			} finally {
				in.close();
			}

			new ModifierVisitor().visit(cu, null);
			//new ConstructorVisitor().visit(cu, null);
			byte[] modfile = cu.toString().getBytes();
			Path file = Paths.get("src/mainPackage/" + f.getName());
			Files.write(file, modfile);
			p.serialise();
			System.out.println("------ Parsing "+ f.getName() + " Complete" + "------");
			System.out.println();
		}
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

			e.printStackTrace();
		}

	}

	public List<Line> deserialise() {

		try {
			FileInputStream fileIn = new FileInputStream("codeTracker.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			CodeTracker.setCoverageRecord((List<Line>) in.readObject());
			fileIn.close();
		} catch (IOException | ClassNotFoundException e) {

			e.printStackTrace();
		}
		return CodeTracker.getCoverageRecord();
	}

	private static class ModifierVisitor extends VoidVisitorAdapter {
		
		public void visit(ConstructorDeclaration n, Object a){
			n.setBody(modifyBlockStatement(n.getBody().getChildrenNodes()));
		}

		public void visit(MethodDeclaration n, Object a) {
			// System.out.println(n.getChildrenNodes().get(3).getChildrenNodes());
			n.setBody(modifyBlockStatement(n.getBody().getChildrenNodes()));
		}

		// works with List<Node> also.
		public BlockStmt modifyBlockStatement(List<? extends Node> n) {

			BlockStmt newBlock = new BlockStmt();
			List<? extends Node> newList = new ArrayList<>(n);
			for (Node s : newList) {

				if (s.getClass().getSimpleName().equals("LineComment")) {
					break;
				}
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
				} else if (s.getClass().getSimpleName().equals("DoStmt")) {
					DoStmt dos = (DoStmt) s;
					dos = modifyDo(dos);
					newBlock.addStatement(dos);
					newBlock.addStatement(print(dos));
				} else if (s.getClass().getSimpleName().equals("TryStmt")) {
					TryStmt tryst = (TryStmt) s;
					tryst = modifiedTry(tryst);
					newBlock.addStatement(tryst);
					newBlock.addStatement(print(tryst));
				} else {
					if (s instanceof Statement) {
						Statement newStatement = (Statement) s;
						newBlock.addStatement(print(newStatement));
						newBlock.addStatement(newStatement);

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
			modifiedForStatement.setBody(modifyBlockStatement(modifiedForStatement.getBody().getChildrenNodes()));
			return modifiedForStatement;
		}

		public DoStmt modifyDo(DoStmt doStatement) {
			DoStmt modifiedDoStatement = doStatement;
			modifiedDoStatement.setBody(modifyBlockStatement(modifiedDoStatement.getBody().getChildrenNodes()));
			return modifiedDoStatement;
		}

		public WhileStmt modifyWhile(WhileStmt whileStatement) {
			WhileStmt modifiedWhileStatement = whileStatement;
			modifiedWhileStatement.setBody(modifyBlockStatement(modifiedWhileStatement.getBody().getChildrenNodes()));
			return modifiedWhileStatement;
		}

		public SwitchStmt modifiedSwitch(SwitchStmt switchStatement) {
			SwitchStmt modifiedSwitchStatement = switchStatement;
			modifiedSwitchStatement
					.setEntries(modifySwitchEntryStatement((modifiedSwitchStatement.getChildrenNodes())));
			return modifiedSwitchStatement;
		}

		public TryStmt modifiedTry(TryStmt tryStatement) {
			TryStmt modifiedTry = tryStatement;
			modifiedTry.setTryBlock(modifyBlockStatement(modifiedTry.getTryBlock().getChildrenNodes()));
			return modifiedTry;
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
			modifiedForech.setBody(modifyBlockStatement(modifiedForech.getBody().getChildrenNodes()));
			return modifiedForech;
		}

		public Statement print(Statement e) {

			MethodCallExpr newCall = new MethodCallExpr(new NameExpr("mainPackage.CodeTracker"), "markExecuted");
			newCall.addArgument(new StringLiteralExpr(Parser.fileName));
			newCall.addArgument(new StringLiteralExpr("" + e.getBegin().line));
			CodeTracker.addCode(Parser.fileName, "" + e.getBegin().line);
			return new ExpressionStmt(newCall);
		}

		public Statement print(Expression e) {

			MethodCallExpr newCall = new MethodCallExpr(new NameExpr("mainPackage.CodeTracker"), "markExecuted");
			newCall.addArgument(new StringLiteralExpr(Parser.fileName));
			newCall.addArgument(new StringLiteralExpr("" + e.getBegin().line));
			CodeTracker.addCode(Parser.fileName, "" + e.getBegin().line);
			return new ExpressionStmt(newCall);
		}
	}
}
