package cn.edu.cuc.aki.stuMS.exception;

public class CourseNotMatchTeacherException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public CourseNotMatchTeacherException() {
		super("��ѡ�γ����ڿν�ʦ��ƥ�䣡");
	}
	
	public CourseNotMatchTeacherException(String s) {
		super(s);
	}


}
