package cn.edu.cuc.aki.stuMS.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StuTools {
	
	
	/**
	 * ѧ��id�ķ���
	 */
	
	//��ѯѧ�����˵���Ϣ
	public static Map<String,String> selectStuInfo(String sid) { 
		String sql = "select * from stu where sid = '" + sid +"';";
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sql);
			Map<String,String> stuInfo = new HashMap<String,String>();
			if(rsSet.next()) {
				String ssid = rsSet.getString("sid");
				stuInfo.put("sid", ssid);
				String name = rsSet.getString("name");
				stuInfo.put("name", name);
				int sex = rsSet.getInt("sex");
				String sexString = Integer.toString(sex);
				stuInfo.put("sex", sexString);
				int age = rsSet.getInt("age");
				String ageString = Integer.toString(age);
				stuInfo.put("age", ageString);
				String major = rsSet.getString("major");
				stuInfo.put("major", major);
				return stuInfo;
			}else {
				return null;
			}
		}catch (SQLException se) {
			// TODO: handle exception
			
		 	  System.out.println(se);
		 	  return null;
		}finally {
			 // ��ɺ�ر�
	        MySQLConnector.disconnect();
		}
	}
	
	//��ѯѧ�����˵����пγ̳ɼ�
	public static ArrayList<String[]> selectStuGrade(String sid) { 
		String sql = "select sc.kkid, course.cname, nteacher.tid, nteacher.tname, sc.grade from ct, sc ,nteacher,course where ct.kkid = sc.kkid and ct.cid = course.cid and ct.cid = course.cid and ct.tid=nteacher.tid and sc.sid = '"+ sid +"';";
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sql);
			ArrayList<String[]> data = new ArrayList<String[]>();
			while(rsSet.next()) {
				String kkid = rsSet.getString("kkid");
				String cname = rsSet.getString("cname");
				String tid = rsSet.getString("tid");
				String tname = rsSet.getString("tname");
				String grade = rsSet.getString("grade");
				String[] info = {kkid , cname, tid, tname, grade};
				data.add(info);
			}
			return data;
		}catch (SQLException se) {
			// TODO: handle exception
			
		 	  System.out.println(se);
		 	  return null;
		}finally {
			 // ��ɺ�ر�
	        MySQLConnector.disconnect();
		}
	}
	
	
	
}