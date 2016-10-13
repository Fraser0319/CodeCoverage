package mainPackage;

import java.util.ArrayList;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.visitor.ModifierVisitorAdapter;

public class CodeTracker {

	private ArrayList<Triple> coverageRecord = new ArrayList<Triple>();
	
	public boolean markExecuted(String fileName, int lineNumber) {
		for (Triple t : coverageRecord) {
			if (t.getFileName() == fileName) {
				t.setLineNumber(lineNumber);
				t.setLineExecuted(true);
				return true;
			}
		}
		coverageRecord.add(new Triple(fileName, lineNumber, false));
		return false;
	}

}
