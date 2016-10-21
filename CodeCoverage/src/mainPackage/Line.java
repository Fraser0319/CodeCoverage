package mainPackage;

import java.io.Serializable;

/**
 * @author Fraser
 *
 */
public class Line implements Serializable {

	private String fileName;
	private String  lineNumber;
	private boolean lineExecuted;

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setLineNumber(String  lineNumber) {
		this.lineNumber = lineNumber;
	}

	public void setLineExecuted(boolean lineExecuted) {
		this.lineExecuted = lineExecuted;
	}

	public String getFileName() {
		return fileName;
	}

	public String  getLineNumber() {
		return lineNumber;
	}

	public boolean getLineExecuted() {
		return lineExecuted;
	}

	public Line(String fileName, String lineNumber, boolean lineExecuted) {
		this.fileName = fileName;
		this.lineNumber = lineNumber;
		this.lineExecuted = lineExecuted;
	}

	@Override
	public String toString() {
		return "fileName: " + fileName + " " + "lineNumber: " + lineNumber + " " + "lineExecuted: " + lineExecuted;
	}

}
