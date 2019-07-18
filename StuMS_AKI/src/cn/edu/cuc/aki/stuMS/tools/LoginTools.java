package cn.edu.cuc.aki.stuMS.tools;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.cuc.aki.stuMS.exception.IDNotExistException;
import cn.edu.cuc.aki.stuMS.exception.PasswordErrorException;
import cn.edu.cuc.aki.stuMS.tools.log.LogIplm;

public class LoginTools {
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
