package cn.edu.cuc.aki.stuMS.tools;

import java.sql.SQLException;

import cn.edu.cuc.aki.stuMS.exception.IDNotExistException;

public class UserTools {
	
	//�����޸��˻����룬�����ڵ�¼����ĺ��������û������У�idΪ��¼���洫���id������newPasswordΪ�û����õ�������
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
