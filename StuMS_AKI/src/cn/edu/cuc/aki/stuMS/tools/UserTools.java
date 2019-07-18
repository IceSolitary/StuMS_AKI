package cn.edu.cuc.aki.stuMS.tools;

import java.sql.SQLException;

import cn.edu.cuc.aki.stuMS.exception.IDNotExistException;

public class UserTools {
	
	//用于修改账户密码，存在于登录界面的后续各个用户界面中，id为登录界面传入的id参数，newPassword为用户设置的新密码
	public static void alterPassword(String newPassword,String id) {
		String sql = "update user set password = '"+newPassword+"' where id = '" + id + "';";
		try {
			MySQLConnector.connect(sql);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
