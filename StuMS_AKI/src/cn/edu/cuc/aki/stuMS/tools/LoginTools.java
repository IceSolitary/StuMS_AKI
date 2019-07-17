package cn.edu.cuc.aki.stuMS.tools;

import java.sql.ResultSet;
import java.sql.SQLException;



import cn.edu.cuc.aki.stuMS.exception.IDNotExistException;
import cn.edu.cuc.aki.stuMS.exception.PasswordErrorException;

public class LoginTools {
	
	public static int login(String id, String password) throws Exception {
		String sql = "select password from user where id = '" + id + "';";
		try {
			ResultSet rSet = MySQLConnector.connect(sql);
			if(rSet.next()) {
				String getPassword = rSet.getString("password"); 
				if(password.equals(getPassword)) {
					int role = rSet.getInt("role");
					return role;
				}else {
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
