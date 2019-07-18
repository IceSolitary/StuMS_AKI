package cn.edu.cuc.aki.stuMS.exception;

public class CourseNotExistException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public CourseNotExistException() {
		super("不存在该课程！");
	}
	
	public CourseNotExistException(String s) {
		super(s);
	}

}
