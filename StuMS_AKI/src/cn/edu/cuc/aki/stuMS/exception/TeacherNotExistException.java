package cn.edu.cuc.aki.stuMS.exception;

public class TeacherNotExistException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TeacherNotExistException() {
		super("不存在该老师！");
	}
	
	public TeacherNotExistException(String s) {
		super(s);
	}

}
