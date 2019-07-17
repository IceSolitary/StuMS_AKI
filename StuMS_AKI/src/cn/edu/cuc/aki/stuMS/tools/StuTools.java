package cn.edu.cuc.aki.stuMS.tools;

import java.sql.ResultSet;

public class StuTools {
	
	
	/**
	 * 学生id的方法
	 */
	
	//查询学生本人的信息
	public static void selectStuInfo(int sid) { 
		String sql = "select * from stu where sid = " + Integer.toString(sid);
		ResultSet rSet= MySQLConnector.connect(sql);
		MySQLConnector.disconnect();
	}
	
	//查询学生本人的所有课程成绩
	public static void selectStuGrade(int sid) { 
		String sql = "select cname,grade from stu , c ,sc where stu.sid = sc.sid and c.cid = sc.cid and sty.sid = " + Integer.toString(sid);
		ResultSet rSet= MySQLConnector.connect(sql);
		MySQLConnector.disconnect();
	}
	
	
	
}
