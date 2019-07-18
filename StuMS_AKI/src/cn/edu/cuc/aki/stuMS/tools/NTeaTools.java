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
	 * 教师方法
	 */

	/**
	 * 获取自己的信息
	 * 
	 * @param tid 教师id
	 * @return 字符串型信息字典
	 */
	public static Map<String, String> selectStuInfo(String tid) {
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
				return stuInfo;
			} else {
				return null;
			}
		} catch (SQLException se) {
			// TODO: handle exception

			System.out.println(se);
			return null;
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}

	/**
	 * 查询自己的课程
	 * 
	 * @param tid 教师自己的id
	 * @return 课程信息列表
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
			return data;
		} catch (SQLException se) {
			System.out.println(se);
			return null;
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}

	/**
	 * 查询自己所有学生的成绩
	 * 
	 * @param tid 教师自己的id
	 * @return 所有学生的成绩信息列表
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
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}

	/**
	 * 修改学生相应课程的成绩
	 * @param tid  操作老师的id
	 * @param sid  学生id
	 * @param kkid  相应课程id
	 * @param grade  修改后的新成绩
	 * @throws CourseNotMatchStudentException
	 */
	public static void alterStuGrade(String tid, String sid, String kkid, int grade)
			throws CourseNotMatchStudentException {
		if (!VerifyTools.isCourseMatchStudent(cid, sid)) {
			throw new CourseNotMatchStudentException();
		}
		String sql = "upadate course set grade = " + grade + " where cid = '" + cid + "'and sid = '" + sid + "';";
		try {
			MySQLConnector.connect(sql);

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e);
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}

	public static void addTCourse(String tid, String cid, String cname) throws TeacherNotExistException {
		if (!VerifyTools.isTeacherExist(tid)) {
			throw new TeacherNotExistException();
		}

		String sql = "insert into course (cid,cname,tid) values('" + cid + "','" + cname + "','" + tid + "');";
		try {
			MySQLConnector.connect(sql);
			String logContent = "id:" + cid + " 用户向教师课程表中添加了课程号为" + cid + "课程名为：" + cname + "教师id为：";
			LogIplm.addLog(TYPE.INFORMATION, logContent);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}

	}

	// 删除相应教师的课程
	public static void deleteTCourse(String tid, String cid) throws CourseNotMatchTeacherException {

		if (!VerifyTools.isCourseMatchTeacher(cid, tid)) {
			throw new CourseNotMatchTeacherException();
		}

		String sql = "delete from course where cid = '" + cid + "';";
		try {
			MySQLConnector.connect(sql);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}

	// 修改相应教师课程
	public static void alterTCourse(String cid, String tid, String cname) throws CourseNotMatchTeacherException {
		if (!VerifyTools.isCourseMatchTeacher(cid, tid)) {
			throw new CourseNotMatchTeacherException();
		}
		String sql = "upadate course set cname = '" + cname + "' where cid = '" + cid + "'and tid = '" + tid + "';";
		try {
			MySQLConnector.connect(sql);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}

//	// 查询相应教师所授课
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
//			// 完成后关闭
//			MySQLConnector.disconnect();
//		}
//	}

	// 增加学生相应课程成绩
	public static void addStuGrade(String sid, String cid, int grade)
			throws StudentNotExistException, CourseNotMatchTeacherException {
		if (!VerifyTools.isStudentExist(sid)) {
			throw new StudentNotExistException();
		}
		if (!VerifyTools.isCourseExist(cid)) {
			throw new CourseNotMatchTeacherException();
		}
		String sql = "insert into sc (sid,cid,grade) values('" + sid + "','" + cid + "'," + grade + ");";
		try {
			MySQLConnector.connect(sql);
		} catch (Exception e) {

			System.out.println(e);
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}

	// 删除相应学生相应课程的成绩
	public static void deleteStuGrade(String sid, String cid) throws CourseNotMatchStudentException {
		if (!VerifyTools.isCourseMatchStudent(cid, sid)) {
			throw new CourseNotMatchStudentException();
		}
		String sql = "delete from sc where cid = '" + cid + "'and sid = '" + sid + "';";
		try {
			MySQLConnector.connect(sql);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}

//	// 修改相应学生相应课程的成绩
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
//			// 完成后关闭
//			MySQLConnector.disconnect();
//		}
//	}

	// 查询学生课程成绩

	public static ArrayList[] searchStuGrade(String sid, String cid) throws StudentNotExistException {
		if (!VerifyTools.isStudentExist(sid)) {
			throw new StudentNotExistException();
		}
		String sql = "select sname , cname ,grade from course,stu,sc where stu.sid = sc.sid and sc.cid = course.cid and cid = '"
				+ cid + "'and sid='" + sid + "';";
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sql);
			ArrayList[] data = new ArrayList[3];
			data[0].add("sname");
			data[1].add("cname");
			data[2].add("grade");
			while (rsSet.next()) {
				String sname = rsSet.getString("sname");
				data[0].add(sname);
				String cname = rsSet.getString("cname");
				data[0].add(cname);
				int grade = rsSet.getInt("grade");
				data[0].add(grade);
			}
			return data;
		} catch (SQLException se) {
			// TODO: handle exception

			System.out.println(se);
			return null;
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}

	// 修改学生信息
	public static void alterStuInfo(String sid, String name, int sex, int age, String major)
			throws StudentNotExistException {
		if (!VerifyTools.isStudentExist(sid)) {
			throw new StudentNotExistException();
		}
		String sql = "upadate stu set name = '" + name + "',sex = " + sex + ",age = " + age + ",major='" + major
				+ "', where sid = '" + sid + "';";
		try {
			MySQLConnector.connect(sql);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}

}
