package cn.edu.cuc.aki.stuMS.exception;

public class StudentNotExistException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public StudentNotExistException() {
		super("学生不存在！");
	}
	
	public StudentNotExistException(String s) {
		super(s);
	}

}
