package cn.edu.cuc.aki.stuMS.tools;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.cuc.aki.stuMS.exception.IDNotExistException;
import cn.edu.cuc.aki.stuMS.exception.PasswordErrorException;
import cn.edu.cuc.aki.stuMS.tools.log.LogIplm;

public class LoginTools {
	/**
	 * 登陆方法
	 */
	
	/**
	 * 登陆函数
	 * @param id  登陆id
	 * @param password  密码
	 * @return  登陆后的角色，以此选择进入何种界面
	 * @throws PasswordErrorException  密码错误异常
	 * @throws IDNotExistException  用户不存在异常
	 */
	public static int login(String id, String password) throws PasswordErrorException, IDNotExistException {
		String sql = "select password,role from user where id = '" + id + "';";
		try {
			ResultSet rSet = MySQLConnector.returnConnect(sql);
			if(rSet.next()) {
				String getPassword = rSet.getString("password"); 
				if(password.equals(getPassword)) {
					int role = rSet.getInt("role");
					String logContent = "id : " + id + " user logged in successfully.";
					LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
					return role;
				}else {
					String logContent = "id : " + id + " PasswordError";
					LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
					throw new PasswordErrorException();
				}
			}
			else {
				throw new IDNotExistException();
			}
		}catch (SQLException se) {
			// TODO: handle exception
			se.printStackTrace();
			return 0;
		}
	}
}
