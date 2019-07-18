package cn.edu.cuc.aki.stuMS.exception;

public class PasswordErrorException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public PasswordErrorException() {
		super("密码错误！");
	}
	
	public PasswordErrorException(String s) {
		super(s);
	}

}
