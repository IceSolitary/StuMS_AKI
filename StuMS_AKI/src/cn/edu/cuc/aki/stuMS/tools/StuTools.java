package cn.edu.cuc.aki.stuMS.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.edu.cuc.aki.stuMS.tools.log.LogIplm;

public class StuTools {

	/**
	 * 学生id的方法
	 */

	// 查询学生本人的信息
	public static Map<String, String> selectStuInfo(String sid) {
		String sql = "select * from stu where sid = '" + sid + "';";
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sql);
			Map<String, String> stuInfo = new HashMap<String, String>();
			if (rsSet.next()) {
				String ssid = rsSet.getString("sid");
				stuInfo.put("sid", ssid);
				String name = rsSet.getString("name");
				stuInfo.put("name", name);
				int sex = rsSet.getInt("sex");
				String sexString;
				if(sex==1){
					sexString = "男";
				}
				else if(sex==2) {
					sexString = "女";
				}
				else {
					sexString = "未知";
				}
				stuInfo.put("sex", sexString);
				int age = rsSet.getInt("age");
				String ageString = Integer.toString(age);
				stuInfo.put("age", ageString);
				String major = rsSet.getString("major");
				stuInfo.put("major", major);
				String logContent = "id : " + sid + " role:STU \nUser inquire own information.";
				LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
				return stuInfo;
			} else {
				String logContent = "id : " + sid + " role:STU \nUser inquire own information, but null.";
				LogIplm.addLog(LogIplm.TYPE.WARNING, logContent);
				return null;
			}
		} catch (SQLException se) {
			String logContent = "id : " + sid + " role:STU \nUser inquire own information, but error.";
			LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
			System.out.println(se);
			return null;
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}

	// 查询学生本人的所有课程成绩
	public static ArrayList<String[]> selectStuGrade(String sid) {
		String sql = "select sc.kkid, course.cname, nteacher.tid, nteacher.tname, sc.grade from ct, sc ,nteacher,course where ct.kkid = sc.kkid and ct.cid = course.cid and ct.cid = course.cid and ct.tid=nteacher.tid and sc.sid = '"
				+ sid + "';";
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sql);
			ArrayList<String[]> data = new ArrayList<String[]>();
			while (rsSet.next()) {
				String kkid = rsSet.getString("kkid");
				String cname = rsSet.getString("cname");
				String tid = rsSet.getString("tid");
				String tname = rsSet.getString("tname");
				String grade = rsSet.getString("grade");
				if(grade=="-1") {
					grade=null;
				}
				String[] info = { kkid, cname, tid, tname, grade };
				data.add(info);
			}
			String logContent = "id : " + sid + " role:STU \nUser inquire own course grade information.";
			LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
			return data;
		} catch (SQLException se) {
			String logContent = "id : " + sid + " role:STU \nUser inquire own course grade information, but error.";
			LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
			System.out.println(se);
			return null;
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}

}
