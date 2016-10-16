package mainPackage;

import java.util.ArrayList;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.visitor.ModifierVisitorAdapter;

public class CodeTracker {

	static ArrayList<Triple> coverageRecord = new ArrayList<Triple>();

	public static void markExecuted(String fileName, String lineNumber) {
		for (Triple t : coverageRecord) {
			if (t.getFileName() == fileName) {
				coverageRecord.add(new Triple(fileName, lineNumber, true));
			}
		}
		coverageRecord.add(new Triple(fileName, lineNumber, true));
	}

}
