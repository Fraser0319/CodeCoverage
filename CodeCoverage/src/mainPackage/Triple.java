package mainPackage;

public class Triple {

	private String fileName;
	private int lineNumber;
	private boolean lineExecuted;

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public void setLineExecuted(boolean lineExecuted) {
		this.lineExecuted = lineExecuted;
	}

	public String getFileName() {
		return fileName;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public boolean getLineExecuted() {
		return lineExecuted;
	}

	public Triple(String fileName, int lineNumber, boolean lineExecuted) {
		this.fileName = fileName;
		this.lineNumber = lineNumber;
		this.lineExecuted = lineExecuted;
	}

}
