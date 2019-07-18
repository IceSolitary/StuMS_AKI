package cn.edu.cuc.aki.stuMS.exception;

public class CourseNotMatchTeacherException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public CourseNotMatchTeacherException() {
		super("所选课程与授课教师不匹配！");
	}
	
	public CourseNotMatchTeacherException(String s) {
		super(s);
	}


}
