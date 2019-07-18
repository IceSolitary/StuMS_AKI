package cn.edu.cuc.aki.stuMS.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.crypto.Data;

import cn.edu.cuc.aki.stuMS.exception.CourseNotMatchStudentException;
import cn.edu.cuc.aki.stuMS.exception.CourseNotMatchTeacherException;
import cn.edu.cuc.aki.stuMS.exception.StudentNotExistException;
import cn.edu.cuc.aki.stuMS.exception.TeacherNotExistException;
import cn.edu.cuc.aki.stuMS.tools.log.LogIplm;

public class NTeaTools implements LogIplm {

	/**
	 * ��ͨ��ʦ����
	 */

	/**
	 * ��ȡ�Լ�����Ϣ
	 * 
	 * @param tid ��ʦid
	 * @return �ַ�������Ϣ�ֵ�
	 */
	public static Map<String, String> selectNTInfo(String tid) {
		String sql = "select * from nteacher where tid = '" + tid + "';";
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sql);
			Map<String, String> stuInfo = new HashMap<String, String>();
			if (rsSet.next()) {
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
				String logContent = "id : " + tid + " role:NT \nUser inquire own information.";
				LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
				return stuInfo;	
			} else {
				String logContent = "id : " + tid + " role:NT \nUser inquire own information,but null.";
				LogIplm.addLog(LogIplm.TYPE.WARNING, logContent);
				return stuInfo;
			}
		} catch (SQLException se) {
			// TODO: handle exception
			String logContent = "id : " + tid + " role:NT \nUser inquire own information,but error.";
			LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
			System.out.println(se);
			return null;
		} finally {
			// ��ɺ�ر�
			MySQLConnector.disconnect();
		}
	}

	/**
	 * ��ѯ�Լ��Ŀγ�
	 * 
	 * @param tid ��ʦ�Լ���id
	 * @return �γ���Ϣ�б�
	 */
	public static ArrayList<String[]> selectTCourse(String tid) {

		String sql = "select ct.kkid,course.cname from ct,course  where ct.cid = course.cid and tid= '" + tid + "';";
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sql);
			ArrayList<String[]> data = new ArrayList<String[]>();
			while (rsSet.next()) {
				String kkid = rsSet.getString("kkid");
				String cname = rsSet.getString("cname");

				String[] info = { kkid, cname };
				data.add(info);
			}
			String logContent = "id : " + tid + " role:NT \nUser inquire own information.";
			LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
			return data;
		} catch (SQLException se) {
			System.out.println(se);
			return null;
		} finally {
			// ��ɺ�ر�
			MySQLConnector.disconnect();
		}
	}

	/**
	 * ��ѯ�Լ�����ѧ���ĳɼ�
	 * 
	 * @param tid ��ʦ�Լ���id
	 * @return ����ѧ���ĳɼ���Ϣ�б�
	 */
	public static ArrayList<String[]> selectStuGrade(String tid) {
		String sql = "select sc.kkid, course.cname, stu.sid, stu.name, sc.grade \r\n" + "from ct, sc ,stu,course \r\n"
				+ "where ct.kkid = sc.kkid and ct.cid = course.cid and ct.cid = course.cid and stu.sid = sc.sid and ct.tid='"
				+ tid + "'\r\n" + "and sc.sid in( select sc.sid\r\n" + "from sc,ct\r\n"
				+ "where sc.kkid = ct.kkid and ct.tid ='" + tid + "');";
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sql);
			ArrayList<String[]> data = new ArrayList<String[]>();
			while (rsSet.next()) {
				String kkid = rsSet.getString("kkid");
				String cname = rsSet.getString("cname");
				String sid = rsSet.getString("sid");
				String sname = rsSet.getString("sname");
				String grade = rsSet.getString("grade");
				String[] info = { kkid, cname, sid, sname, grade };
				data.add(info);
			}
			return data;
		} catch (SQLException se) {
			// TODO: handle exception

			System.out.println(se);
			return null;
		} finally {
			// ��ɺ�ر�
			MySQLConnector.disconnect();
		}
	}

	/**
	 * �޸�ѧ����Ӧ�γ̵ĳɼ�
	 * @param tid  ������ʦ��id
	 * @param sid  ѧ��id
	 * @param kkid  ��Ӧ�γ�id
	 * @param grade  �޸ĺ���³ɼ�
	 * @throws CourseNotMatchStudentException
	 */
<<<<<<< HEAD
//	public static void alterStuGrade(String tid, String sid, String kkid, int grade)
//			throws CourseNotMatchStudentException {
//		if (!VerifyTools.isCourseMatchStudent(cid, sid)) {
//			throw new CourseNotMatchStudentException();
//		}
//		String sql = "upadate course set grade = " + grade + " where cid = '" + cid + "'and sid = '" + sid + "';";
//		try {
//			MySQLConnector.connect(sql);
//
//		} catch (Exception e) {
//			// TODO: handle exception
//
//			System.out.println(e);
//		} finally {
//			// ��ɺ�ر�
//			MySQLConnector.disconnect();
//		}
//	}

	public static void addTCourse(String tid, String cid, String cname) throws TeacherNotExistException {
		if (!VerifyTools.isTeacherExist(tid)) {
			throw new TeacherNotExistException();
		}

		String sql = "insert into course (cid,cname,tid) values('" + cid + "','" + cname + "','" + tid + "');";
		try {
			MySQLConnector.connect(sql);
			String logContent = "id:" + cid + " �û����ʦ�γ̱�������˿γ̺�Ϊ" + cid + "�γ���Ϊ��" + cname + "��ʦidΪ��";
			LogIplm.addLog(TYPE.INFORMATION, logContent);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// ��ɺ�ر�
			MySQLConnector.disconnect();
		}

	}

	// ɾ����Ӧ��ʦ�Ŀγ�
	public static void deleteTCourse(String tid, String cid) throws CourseNotMatchTeacherException {

		if (!VerifyTools.isCourseMatchTeacher(cid, tid)) {
			throw new CourseNotMatchTeacherException();
		}

		String sql = "delete from course where cid = '" + cid + "';";
=======
	public static void alterStuGrade(String tid, String sid, String kkid, int grade) throws CourseNotMatchStudentException {
		if (!VerifyTools.isCourseMatchStudent(kkid,sid)) {
			throw new CourseNotMatchStudentException();
		}
		String sql = "upadate course set grade = " + grade + " where kkid = '" + kkid + "'and sid = '" + sid + "';";
>>>>>>> fdad9f7aa72079b4baec5fbdc3af3c26bd340a26
		try {
			MySQLConnector.connect(sql);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// ��ɺ�ر�
			MySQLConnector.disconnect();
		}
	}

//
//	// �޸���Ӧ��ʦ�γ�
//	public static void alterTCourse(String cid, String tid, String cname) throws CourseNotMatchTeacherException {
//		if (!VerifyTools.isCourseMatchTeacher(cid, tid)) {
//			throw new CourseNotMatchTeacherException();
//		}
//		String sql = "upadate course set cname = '" + cname + "' where cid = '" + cid + "'and tid = '" + tid + "';";
//		try {
//			MySQLConnector.connect(sql);
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			// ��ɺ�ر�
//			MySQLConnector.disconnect();
//		}
//	}
//
//	// ��ѯ��Ӧ��ʦ���ڿ�
//	public static ArrayList[] selectTCourse(String tid) throws TeacherNotExistException {
//		if(! VerifyTools.isTeacherExist(tid)) {
//			throw new TeacherNotExistException();
//		}
//		
//		String sql = "select cname from course where tid= '" + tid + "';";
//		try {
//			ResultSet rsSet = MySQLConnector.returnConnect(sql);
//			ArrayList[] data = new ArrayList[1];
//			data[0].add("cname");
//			while (rsSet.next()) {
//				String cname = rsSet.getString("cname");
//				data[0].add(cname);
//			}
//			return data;
//		} catch (SQLException se) {
//			// TODO: handle exception
//
//			System.out.println(se);
//			return null;
//		} finally {
//			// ��ɺ�ر�
//			MySQLConnector.disconnect();
//		}
//	}
//
//	// ����ѧ����Ӧ�γ̳ɼ�
//	public static void addStuGrade(String sid, String cid, int grade)
//			throws StudentNotExistException, CourseNotMatchTeacherException {
//		if (!VerifyTools.isStudentExist(sid)) {
//			throw new StudentNotExistException();
//		}
//		if (!VerifyTools.isCourseExist(cid)) {
//			throw new CourseNotMatchTeacherException();
//		}
//		String sql = "insert into sc (sid,cid,grade) values('" + sid + "','" + cid + "'," + grade + ");";
//		try {
//			MySQLConnector.connect(sql);
//		} catch (Exception e) {
//
//			System.out.println(e);
//		} finally {
//			// ��ɺ�ر�
//			MySQLConnector.disconnect();
//		}
//	}
//
//	// ɾ����Ӧѧ����Ӧ�γ̵ĳɼ�
//	public static void deleteStuGrade(String sid, String cid) throws CourseNotMatchStudentException {
//		if (!VerifyTools.isCourseMatchStudent(cid, sid)) {
//			throw new CourseNotMatchStudentException();
//		}
//		String sql = "delete from sc where cid = '" + cid + "'and sid = '" + sid + "';";
//		try {
//			MySQLConnector.connect(sql);
//
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			// ��ɺ�ر�
//			MySQLConnector.disconnect();
//		}
//	}
//
//	// �޸���Ӧѧ����Ӧ�γ̵ĳɼ�
//	public static void alterStuGrade(String sid, String cid, int grade) throws CourseNotMatchStudentException {
//		if(!VerifyTools.isCourseMatchStudent(cid, sid)) {
//			throw new CourseNotMatchStudentException();
//		}
//		String sql = "upadate course set grade = " + grade + " where cid = '" + cid + "'and sid = '" + sid + "';";
//		try {
//			MySQLConnector.connect(sql);
//
//		} catch (Exception e) {
//			// TODO: handle exception
//
//			System.out.println(e);
//		} finally {
//			// ��ɺ�ر�
//			MySQLConnector.disconnect();
//		}
//	}
//
//

}
