package cn.edu.cuc.aki.stuMS.exception;

public class TeacherNotExistException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TeacherNotExistException() {
		super("�����ڸ���ʦ��");
	}
	
	public TeacherNotExistException(String s) {
		super(s);
	}

}
