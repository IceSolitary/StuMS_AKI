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
	 * 锟斤拷通锟斤拷师锟斤拷锟斤拷
	 */

	/**
	 * 锟斤拷取锟皆硷拷锟斤拷锟斤拷息
	 * 
	 * @param tid 锟斤拷师id
	 * @return 锟街凤拷锟斤拷锟斤拷锟斤拷息锟街碉拷
	 */
	public static Map<String, String> selectNTInfo(String tid) {
		String sql = "select * from nteacher where tid = '" + tid + "';";
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sql);
			Map<String, String> stuInfo = new HashMap<String, String>();
			if (rsSet.next()) {
				String ssid = rsSet.getString("tid");
				stuInfo.put("tid", ssid);
				String name = rsSet.getString("tname");
				stuInfo.put("name", name);
				int sex = rsSet.getInt("sex");
				String sexString;
				if(sex==1){
					sexString = "男";
				}
				else if(sex==2) {
					sexString = "女";
				}
				else {
					sexString = "未知";
				}
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
			// 锟斤拷珊锟截憋拷
			MySQLConnector.disconnect();
		}
	}

	/**
	 * 锟斤拷询锟皆硷拷锟侥课筹拷
	 * 
	 * @param tid 锟斤拷师锟皆硷拷锟斤拷id
	 * @return 锟轿筹拷锟斤拷息锟叫憋拷
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
			String logContent = "id : " + tid + " role:NT \nUser inquire own course information.";
			LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
			return data;
		} catch (SQLException se) {
			String logContent = "id : " + tid + " role:NT \nUser inquire own course information, but error.";
			LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
			System.out.println(se);
			return null;
		} finally {
			// 锟斤拷珊锟截憋拷
			MySQLConnector.disconnect();
		}
	}

	/**
	 * 锟斤拷询锟皆硷拷锟斤拷锟斤拷学锟斤拷锟侥成硷拷
	 * 
	 * @param tid 锟斤拷师锟皆硷拷锟斤拷id
	 * @return 锟斤拷锟斤拷学锟斤拷锟侥成硷拷锟斤拷息锟叫憋拷
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
				String sname = rsSet.getString("name");
				String grade = rsSet.getString("grade");
				if(grade.equals("-1")) {
					grade="烫烫烫";
				}
				String[] info = { kkid, cname, sid, sname, grade };
				data.add(info);
			}
			String logContent = "id : " + tid + " role:NT \nUser inquire his or her all grades information of all students.";
			LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
			return data;
		} catch (SQLException se) {
			System.out.println(se);
			String logContent = "id : " + tid + " role:NT \nUser inquire his or her all grades information of all students,but error.";
			LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
			return null;
		} finally {
			// 锟斤拷珊锟截憋拷
			MySQLConnector.disconnect();
		}
	}

	/**
	 * 锟睫革拷学锟斤拷锟斤拷应锟轿程的成硷拷
	 * @param tid  锟斤拷锟斤拷锟斤拷师锟斤拷id
	 * @param sid  学锟斤拷id
	 * @param kkid  锟斤拷应锟轿筹拷id
	 * @param grade  锟睫改猴拷锟斤拷鲁杉锟�
	 * @throws CourseNotMatchStudentException
	 */
	public static void alterStuGrade(String tid, String sid, String kkid, int grade) throws CourseNotMatchStudentException {
		if (!VerifyTools.isCourseMatchStudent(kkid,sid)) {
			throw new CourseNotMatchStudentException();
		}
		String sql = "update sc set grade = " + grade + " where kkid = '" + kkid + "'and sid = '" + sid + "';";
		try {
			MySQLConnector.connect(sql);
			String logContent = "id : " + tid + " role:NT \nUser alter student whose sid =" + sid + " and courseid = "+ kkid + " alter grade = " + grade +".";
			LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
		} catch (Exception e) {
			String logContent = "id : " + tid + " role:NT \nUser alter student whose sid =" + sid + " and courseid = "+ kkid + " alter grade = " + grade +",but error.";
			LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
			System.out.println(e);
		} finally {
			// 锟斤拷珊锟截憋拷
			MySQLConnector.disconnect();
		}
	}

//
//	// 锟睫革拷锟斤拷应锟斤拷师锟轿筹拷
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
//			// 锟斤拷珊锟截憋拷
//			MySQLConnector.disconnect();
//		}
//	}
//
//	// 锟斤拷询锟斤拷应锟斤拷师锟斤拷锟节匡拷
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
//			// 锟斤拷珊锟截憋拷
//			MySQLConnector.disconnect();
//		}
//	}
//
//	// 锟斤拷锟斤拷学锟斤拷锟斤拷应锟轿程成硷拷
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
//			// 锟斤拷珊锟截憋拷
//			MySQLConnector.disconnect();
//		}
//	}
//
//	// 删锟斤拷锟斤拷应学锟斤拷锟斤拷应锟轿程的成硷拷
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
//			// 锟斤拷珊锟截憋拷
//			MySQLConnector.disconnect();
//		}
//	}
//
//	// 锟睫革拷锟斤拷应学锟斤拷锟斤拷应锟轿程的成硷拷
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
//			// 锟斤拷珊锟截憋拷
//			MySQLConnector.disconnect();
//		}
//	}
//
//

}
