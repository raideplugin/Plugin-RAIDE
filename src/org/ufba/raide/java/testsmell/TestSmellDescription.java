package org.ufba.raide.java.testsmell;

public class TestSmellDescription {
	
	String testSmellType;
	String testSmellRefactoring;
	String filePath;
	String className;
	String methodName;
	String linePosition;
	int beginMethod;
	int endMethod;
	
	public int getBeginMethod() {
		return beginMethod;
	}
	public void setBeginMethod(int beginMethod) {
		this.beginMethod = beginMethod;
	}
	public int getEndMethod() {
		return endMethod;
	}
	public void setEndMethod(int endMethod) {
		this.endMethod = endMethod;
	}
	public TestSmellDescription(String testSmellType, String testSmellRefactoring, String filePath, 
			String className, String methodName, String linePosition, int beginMethod, int endMethod) {
		
		this.testSmellType = testSmellType;
		this.testSmellRefactoring = testSmellRefactoring;
		this.filePath = filePath;
		this.className = className;
		this.methodName = methodName;
		this.linePosition = linePosition;
		this.beginMethod = beginMethod;
		this.endMethod = endMethod;
	}
	public TestSmellDescription() {
		new TestSmellDescription("", "", "", "", "", "", 0, 0);		
	}	
	
	
	public String getTestSmellType() {
		return testSmellType;
	}
	public void setTestSmellType(String testSmellType) {
		this.testSmellType = testSmellType;
	}
	public String getTestSmellRefactoring() {
		return testSmellRefactoring;
	}
	public void setTestSmellRefactoring(String testSmellRefactoring) {
		this.testSmellRefactoring = testSmellRefactoring;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getLinePosition() {
		return linePosition;
	}
	public void setLinePosition(String linePosition) {
		this.linePosition = linePosition;
	}
	
	
}
