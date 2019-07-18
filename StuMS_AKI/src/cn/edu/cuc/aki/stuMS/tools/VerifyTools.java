package cn.edu.cuc.aki.stuMS.tools;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.cuc.aki.stuMS.exception.TeacherNotExistException;

public class VerifyTools {
	
	/**
	 * 验证方法
	 */
	
	//判断是否存在此tid的教师
	public static boolean isTeacherExist(String tid) {
		String sql = "select * from course where nteacher = '" + tid +"';";
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
	
	//判断是否存在此cid的课程
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
	
	//判断课程是否与教师相匹配
	public static boolean isCourseMatchTeacher(String sid, String tid) {
		String sql = "select * from ct where sid = '" + sid +"' and tid ='" + tid +"';";
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
	
	//判断是否存在相应sid的学生
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
	
	//判断学生是否选了相应课程
	public static boolean isCourseMatchStudent(String kkid,String sid) {
		String sql = "select * from sc where kkid = '" + kkid +"'and sid='" + sid +"';";
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
