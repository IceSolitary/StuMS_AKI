package cn.edu.cuc.aki.stuMS.tools;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.cuc.aki.stuMS.exception.TeacherNotExistException;

public class VerifyTools {
	
	//������֤����
	
	//�ж��Ƿ���ڴ�tid�Ľ�ʦ
	public static boolean isTeacherExist(String tid) {
		String sql = "select * from course where tid = '" + tid +"';";
		try {
			ResultSet rSet = MySQLConnector.returnConnect(sql);
			if(rSet.next()) {
				return true;
			}
			else {
				return false;
			}
		}catch (SQLException se) {
			se.printStackTrace();
			return false;
		}finally {
			MySQLConnector.disconnect();
		}
	}
	
	//�ж��Ƿ���ڴ�cid�Ŀγ�
	public static boolean isCourseExist(String cid) {
		String sql = "select * from course where cid = '" + cid +"';";
		try {
			ResultSet rSet = MySQLConnector.returnConnect(sql);
			if(rSet.next()) {
				return true;
			}
			else {
				return false;
			}
		}catch (SQLException se) {
			se.printStackTrace();
			return false;
		}finally {
			MySQLConnector.disconnect();
		}
	}
	
	//�жϿγ��Ƿ����ʦ��ƥ��
	public static boolean isCourseMatchTeacher(String cid,String tid) {
		String sql = "select * from course where cid = '" + cid +"'and tid='" + tid +"';";
		try {
			ResultSet rSet = MySQLConnector.returnConnect(sql);
			if(rSet.next()) {
				return true;
			}
			else {
				return false;
			}
		}catch (SQLException se) {
			se.printStackTrace();
			return false;
		}finally {
			MySQLConnector.disconnect();
		}
	}
	
	//�ж��Ƿ������Ӧsid��ѧ��
	public static boolean isStudentExist(String sid) {
		String sql = "select * from stu where sid = '" + sid +"';";
		try {
			ResultSet rSet = MySQLConnector.returnConnect(sql);
			if(rSet.next()) {
				return true;
			}
			else {
				return false;
			}
		}catch (SQLException se) {
			se.printStackTrace();
			return false;
		}finally {
			MySQLConnector.disconnect();
		}
	}
	
	//�ж�ѧ���Ƿ�ѡ����Ӧ�γ�
	public static boolean isCourseMatchStudent(String cid,String sid) {
		String sql = "select * from sc where cid = '" + cid +"'and sid='" + sid +"';";
		try {
			ResultSet rSet = MySQLConnector.returnConnect(sql);
			if(rSet.next()) {
				return true;
			}
			else {
				return false;
			}
		}catch (SQLException se) {
			se.printStackTrace();
			return false;
		}finally {
			MySQLConnector.disconnect();
		}
	}

}
