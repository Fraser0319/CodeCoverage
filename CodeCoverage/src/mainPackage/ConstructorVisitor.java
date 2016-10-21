package mainPackage;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ConstructorVisitor extends VoidVisitorAdapter {

	public void visit(ConstructorDeclaration n, Object a){
		BlockStmt block = (BlockStmt) n.getChildrenNodes().get(1);
		BlockStmt newBlock = new BlockStmt();
		for(Statement b : block.getStmts()){
			newBlock.addStatement(b);
			newBlock.addStatement(print(b));
		}
		newBlock = block;
	}
	
	
	
	public Statement print(Statement e) {

		MethodCallExpr newCall = new MethodCallExpr(new NameExpr("mainPackage.CodeTracker"), "markExecuted");
		newCall.addArgument(new StringLiteralExpr("PracticalOne"));
		newCall.addArgument(new StringLiteralExpr("" + e.getBegin().line));
		CodeTracker.addCode("PracticalOne", "" + e.getBegin().line);
		return new ExpressionStmt(newCall);
	}

	public Statement print(Expression e) {

		MethodCallExpr newCall = new MethodCallExpr(new NameExpr("mainPackage.CodeTracker"), "markExecuted");
		newCall.addArgument(new StringLiteralExpr("PracticalOne"));
		newCall.addArgument(new StringLiteralExpr("" + e.getBegin().line));
		CodeTracker.addCode("PracticalOne", "" + e.getBegin().line);
		return new ExpressionStmt(newCall);
	}
}
