package mainPackage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CodeTracker implements Serializable {
	
	private static List<Line> coverageRecord = new ArrayList<Line>();
	static Parser p = new Parser();
	
	public static void start(){
		p.deserialise();
	}
	
	public static List<Line> getCoverageRecord() {
		return coverageRecord;
	}

	public static void setCoverageRecord(List<Line> coverageRecord) {
		CodeTracker.coverageRecord = coverageRecord;
	}
	
	public void getStatistics(){
		// to-do
	}

	public static void markExecuted(String fileName, String lineNumber) {
		for (Line l : coverageRecord) {
			if (l.getFileName().equals(fileName) && l.getLineNumber().equals(lineNumber) && l.getLineExecuted() == false) {
				l.setLineExecuted(true);
				System.out.println();
				System.out.println("lineExecuted: " + l.getLineNumber() + "\n" + "fileName: " + l.getFileName());
			}
		}
	}

	public static void addCode(String fileName, String lineNumber) {
		if (coverageRecord.size() == 0) {
			coverageRecord.add(new Line(fileName, lineNumber, false));
		}else{
			coverageRecord.add(new Line(fileName, lineNumber, false));
		}
	}
}
