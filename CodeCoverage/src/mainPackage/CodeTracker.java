package mainPackage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CodeTracker implements Serializable {
	
	private static List<Triple> coverageRecord = new ArrayList<Triple>();
	static Parser p = new Parser();
	
	public static void start() throws ClassNotFoundException, IOException{
		p.deserialise();
	}
	
	public static List<Triple> getCoverageRecord() {
		return coverageRecord;
	}

	public static void setCoverageRecord(List<Triple> coverageRecord) {
		CodeTracker.coverageRecord = coverageRecord;
	}

	public static void markExecuted(String fileName, String lineNumber) throws ClassNotFoundException, IOException {
		//System.out.println(lineNumber);
		for (Triple t : coverageRecord) {
			//System.out.println(t.getLineNumber());
			if (t.getLineNumber().equals(lineNumber) && t.getLineExecuted() == false) {
				t.setLineExecuted(true);
				System.out.println("lineExecuted: " + t.getLineNumber());
			}
		}
	}

	public static void addCode(String fileName, String lineNumber) {
		
		if (coverageRecord.size() == 0) {
			coverageRecord.add(new Triple(fileName, lineNumber, false));
		}else{
			coverageRecord.add(new Triple(fileName, lineNumber, false));
		}
	}
}
