package cn.edu.cuc.aki.stuMS.exception;

public class CourseNotMatchStudentException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public CourseNotMatchStudentException() {
		super("学生未选此课程！");
	}
	
	public CourseNotMatchStudentException(String s) {
		super(s);
	}

}
