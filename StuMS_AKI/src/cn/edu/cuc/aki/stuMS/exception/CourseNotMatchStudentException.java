package cn.edu.cuc.aki.stuMS.exception;

public class CourseNotMatchStudentException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public CourseNotMatchStudentException() {
		super("ѧ��δѡ�˿γ̣�");
	}
	
	public CourseNotMatchStudentException(String s) {
		super(s);
	}

}
