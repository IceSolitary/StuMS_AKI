package cn.edu.cuc.aki.stuMS.exception;

public class IDNotExistException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public IDNotExistException() {
		super("�����ڸ��˺ţ�");
	}
	
	public IDNotExistException(String s) {
		super(s);
	}
}
