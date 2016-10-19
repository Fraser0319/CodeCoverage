package mainPackage;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.visitor.ModifierVisitorAdapter;

public class CodeTracker {

	private static List<Triple> coverageRecord = new ArrayList<Triple>();

	public static List<Triple> getCoverageRecord() {
		return coverageRecord;
	}

	public static void markExecuted(String fileName, String lineNumber) {

		for (Triple t : coverageRecord) {
			if (t.getFileName() == fileName && t.getLineExecuted() == false) {
				t.setLineExecuted(true);
			}
		}
	}

	public static void addCode(String fileName, String lineNumber) {
		//System.out.println(coverageRecord.size());
		if (coverageRecord.size() == 0) {
			coverageRecord.add(new Triple(fileName, lineNumber, false));
		}else{
			coverageRecord.add(new Triple(fileName, lineNumber, false));
		}
	}
}
