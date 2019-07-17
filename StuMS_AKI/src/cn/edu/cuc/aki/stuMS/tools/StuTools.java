package cn.edu.cuc.aki.stuMS.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StuTools {
	
	
	/**
	 * 学生id的方法
	 */
	
	//查询学生本人的信息
	public static ArrayList[] selectStuInfo(String sid) { 
		String sql = "select * from stu where sid = '" + sid +"';";
		try {
			ResultSet rsSet = MySQLConnector.connect(sql);
			ArrayList[] data = new ArrayList[5];
			data[0].add("sid");
			data[1].add("name");
			data[2].add("sex");
			data[3].add("age");
			data[4].add("major");
			while(rsSet.next()) {
				String ssid = rsSet.getString("sid");
				data[0].add(ssid);
				String name = rsSet.getString("name");
				data[1].add(name);
				int sex = rsSet.getInt("sex");
				data[2].add(sex);
				int age = rsSet.getInt("age");
				data[3].add(age);
				String major = rsSet.getString("major");
				data[4].add(major);
				
			}
			return data;
		}catch (SQLException se) {
			// TODO: handle exception
			
		 	  System.out.println(se);
		 	  return null;
		}finally {
			 // 完成后关闭
	        MySQLConnector.disconnect();
		}
	}
	
	//查询学生本人的所有课程成绩
	public static ArrayList[] selectStuGrade(String sid) { 
		String sql = "select cname,grade from stu , c ,sc where stu.sid = sc.sid and c.cid = sc.cid and sty.sid = '" + sid +"';";
		
		try {
			ResultSet rsSet = MySQLConnector.connect(sql);
			ArrayList[] data = new ArrayList[2];
			data[0].add("cname");
			data[1].add("grade");
			while(rsSet.next()) {
				String cname = rsSet.getString("cname");
				data[0].add(cname);
				int grade = rsSet.getInt("cname");
				data[0].add(grade);
			}
			return data;
		}catch (SQLException se) {
			// TODO: handle exception
			
		 	  System.out.println(se);
		 	  return null;
		}finally {
			 // 完成后关闭
	        MySQLConnector.disconnect();
		}
	}
	
	
	
}
