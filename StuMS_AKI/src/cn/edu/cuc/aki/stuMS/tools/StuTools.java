package cn.edu.cuc.aki.stuMS.tools;

import java.sql.ResultSet;

public class StuTools {
	
	
	/**
	 * ѧ��id�ķ���
	 */
	
	//��ѯѧ�����˵���Ϣ
	public static void selectStuInfo(int sid) { 
		String sql = "select * from stu where sid = " + Integer.toString(sid);
		ResultSet rSet= MySQLConnector.connect(sql);
		MySQLConnector.disconnect();
	}
	
	//��ѯѧ�����˵����пγ̳ɼ�
	public static void selectStuGrade(int sid) { 
		String sql = "select cname,grade from stu , c ,sc where stu.sid = sc.sid and c.cid = sc.cid and sty.sid = " + Integer.toString(sid);
		ResultSet rSet= MySQLConnector.connect(sql);
		MySQLConnector.disconnect();
	}
	
	
	
}
