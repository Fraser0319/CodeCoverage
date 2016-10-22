package mainPackage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CodeTracker implements Serializable {

	private static List<Line> coverageRecord = new ArrayList<Line>();
	static Parser p = new Parser();

	public static void start() {
		p.deserialise();
	}

	public static List<Line> getCoverageRecord() {
		return coverageRecord;
	}

	public static void setCoverageRecord(List<Line> coverageRecord) {
		CodeTracker.coverageRecord = coverageRecord;
	}

	public static void getStatistics() {
		int executed = 0;
		int notExecuted = 0;
		Map<String,Integer> fileStats = new HashMap<String,Integer>();
		for (Line l : coverageRecord) {
			if (l.getLineExecuted() == true) {
				if(fileStats.containsKey(l.getFileName())){
					fileStats.put(l.getFileName(), fileStats.get(l.getFileName()) + 1 );
					executed++;
				}else{
					int count = 0;
					fileStats.put(l.getFileName(), count);
					executed++;
				}
				
			} else {
				notExecuted++;
			}
		}
		System.out.println("map"+ fileStats);
		System.out.println();
		System.out.println("------ Statistics ------ ");
		System.out.println("number of lines executed: " + executed);
		System.out.println("number of lines not executed: " + notExecuted);
		int total = (executed + notExecuted);
		float percentage = (float) ((executed * 100.00) / total);
		for(String file : fileStats.keySet()){
			System.out.println(file + ": " + fileStats.get(file) + " lines executed");
		}
		System.out.println("percentage code covered: " + Math.round(percentage) + "%");
	}

	public static void markExecuted(String fileName, String lineNumber) {
		for (Line l : coverageRecord) {
			if (l.getFileName().equals(fileName) && l.getLineNumber().equals(lineNumber)
					&& l.getLineExecuted() == false) {
				l.setLineExecuted(true);
				System.out.println();
				System.out.println("lineExecuted: " + l.getLineNumber() + "\n" + "fileName: " + l.getFileName());
			}
		}
	} 

	public static void addCode(String fileName, String lineNumber) {
		coverageRecord.add(new Line(fileName, lineNumber, false));
	}
}
