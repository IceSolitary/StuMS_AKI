package cn.edu.cuc.aki.stuMS.tools;

import java.sql.SQLException;

import cn.edu.cuc.aki.stuMS.exception.IDNotExistException;
import cn.edu.cuc.aki.stuMS.tools.log.LogIplm;

public class UserTools {
	
	/**
	 * �û�����
	 */
	
	//�����޸��˻����룬�����ڵ�¼����ĺ��������û������У�idΪ��¼���洫���id������newPasswordΪ�û����õ�������
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
