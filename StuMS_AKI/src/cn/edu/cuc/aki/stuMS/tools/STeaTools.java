package cn.edu.cuc.aki.stuMS.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class STeaTools {
	
	public static Map<String,String> selectStuInfo(String tid) { 
		String sql = "select * from steacher where tid = '" + tid +"';";
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sql);
			Map<String,String> stuInfo = new HashMap<String,String>();
			if(rsSet.next()) {
				String ssid = rsSet.getString("tid");
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
			 // 完成后关闭
	        MySQLConnector.disconnect();
		}
	}

}
