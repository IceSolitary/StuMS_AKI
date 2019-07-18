package cn.edu.cuc.aki.stuMS.tools;

import java.sql.SQLException;

import cn.edu.cuc.aki.stuMS.exception.IDNotExistException;
import cn.edu.cuc.aki.stuMS.tools.log.LogIplm;

public class UserTools {
	
	/**
	 * 用户方法
	 */
	
	//用于修改账户密码，存在于登录界面的后续各个用户界面中，id为登录界面传入的id参数，newPassword为用户设置的新密码
	public static void alterPassword(String newPassword,String id) {
		String sql = "update user set password = '"+newPassword+"' where id = '" + id + "';";
		try {
			MySQLConnector.connect(sql);
			String logContent = "id : " + id + " role:unknown \nUser alter his or her password.";
			LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
		}catch (Exception e) {
			String logContent = "id : " + id + " role:unknown \nUser alter his or her password, but error.";
			LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
			e.printStackTrace();
		}
	}

}
