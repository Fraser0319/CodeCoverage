package mainPackage;

import java.util.ArrayList;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.visitor.ModifierVisitorAdapter;

public class CodeTracker {

	ArrayList<Triple> coverageRecord = new ArrayList<Triple>();

	public void markExecuted(String fileName, String lineNumber) {

		for (Triple t : coverageRecord) {
			if (t.getFileName() == fileName && t.getLineExecuted() == false) {
				t.setLineExecuted(true);
			}
		}
	}


	public boolean addCode(String fileName, String lineNumber) {
		if(coverageRecord.size() == 0){
			coverageRecord.add(new Triple(fileName, lineNumber, false));
		}
		for (Triple t : coverageRecord) {
			if (coverageRecord.contains(t)) {
				return false;
			}
			coverageRecord.add(new Triple(fileName, lineNumber, false));
		}
		//System.out.println(coverageRecord);
		return true;
	}
}
