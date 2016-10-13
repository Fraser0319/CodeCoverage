package mainPackage;

import java.util.ArrayList;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.visitor.ModifierVisitorAdapter;

public class CodeTracker extends ModifierVisitorAdapter {
	
	private ArrayList<Triple> coverageRecord = new ArrayList<Triple>();
	
	
	public boolean markExecuted(Expression e, String fileName, int lineNumber){
		for(Triple t : coverageRecord){
			if(t.getFileName() == fileName){
				t.setLineNumber(lineNumber);
				t.setLineExecuted(true);
				return true;
			}
		}
		coverageRecord.add(new Triple(fileName, lineNumber, false));
		return false;
	}
	
	
	

	@Override
	public Node visit(ExpressionStmt n, Object a) {
		

		return super.visit(n, a);
	}

	@Override
	public Node visit(MethodDeclaration n, Object a) {


		return super.visit(n, a);
	}
	
	
	
	

}
