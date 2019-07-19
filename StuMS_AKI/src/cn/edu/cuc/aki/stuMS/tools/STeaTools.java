package cn.edu.cuc.aki.stuMS.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.edu.cuc.aki.stuMS.exception.CourseNotExistException;
import cn.edu.cuc.aki.stuMS.exception.CourseNotMatchTeacherException;
import cn.edu.cuc.aki.stuMS.exception.StudentNotExistException;
import cn.edu.cuc.aki.stuMS.exception.TeacherNotExistException;
import cn.edu.cuc.aki.stuMS.tools.log.LogIplm;
import cn.edu.cuc.aki.stuMS.tools.log.LogIplm.TYPE;

public class STeaTools {
	
	/**
	 * 教务处老师方法
	 */

	/**
	 * 查询教务处老师的个人信息
	 * 
	 * @param tid 教务处老师的id
	 * @return 教务处老师的个人信息字典
	 */
	public static Map<String, String> selectSTInfo(String tid) {
		String sql = "select * from steacher where tid = '" + tid + "';";
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sql);
			Map<String, String> stuInfo = new HashMap<String, String>();
			if (rsSet.next()) {
				String ssid = rsSet.getString("tid");
				stuInfo.put("tid", ssid);
				String tname = rsSet.getString("tname");
				stuInfo.put("tname", tname);
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
				String logContent = "id : " + tid + " role:ST \nUser inquire own information.";
				LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
				return stuInfo;
			} else {
				String logContent = "id : " + tid + " role:ST \nUser inquire own information, but null.";
				LogIplm.addLog(LogIplm.TYPE.WARNING, logContent);
				return stuInfo;
			}
		} catch (SQLException se) {
			System.out.println(se);
			String logContent = "id : " + tid + " role:ST \nUser inquire own information, but error.";
			LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
			return null;
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}

	/**
	 * 给相应教师开已存在的课程
	 * 
	 * @param kkid 开课的id
	 * @param cid  课程的id
	 * @param tid  老师的id
	 * @param oid  操作者的id
	 */
	public static void addSTCourse(String cid, String tid,String oid) {
		String kkid;
		String sqlkkidString = "select max(convert(kkid,signed)) as maxKkid from ct";
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sqlkkidString);
			if(rsSet.next()) {
				kkid = Integer.toString(rsSet.getInt("maxKkid") + 1);
			}else {
				kkid = "0";
			}
		} catch (Exception e) {
			System.out.println(e);
			kkid = "0";
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}

		String sql = "insert into ct (kkid,cid,tid) values('" + kkid + "','" + cid + "','" + tid + "');";
		try {
			MySQLConnector.connect(sql);
			String logContent = "id : " + oid + " role:ST \nUser open new course,whose tid= "+ tid +", cid = "+cid+", kkid="+kkid+".";
			LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
		} catch (Exception e) {
			System.out.println(e);
			String logContent = "id : " + oid + " role:ST \nUser open new course,whose tid= "+ tid +", cid = "+cid+", kkid="+kkid+", but error.";
			LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}
	
	/**
	 * 获得所有Course的id 和 name
	 * 
	 * @return ArrayList<String[]>， String[0]为cid, String[1]为cname
	 */
	public static ArrayList<String[]> selectAllCourse() {
		String sql = "select * from course;";
		ArrayList<String[]> data = new ArrayList<String[]>();
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sql);
			while(rsSet.next()) {
				String[] tuple = {rsSet.getString(1), rsSet.getString(2)};
				data.add(tuple);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
		return data;
	}
	
	/**
	 * 获得所有nteacher的id 和 name
	 * 
	 * @return ArrayList<String[]>， String[0]为tid, String[1]为tname
	 */
	public static ArrayList<String[]> selectAllNTeachers() {
		String sql = "select tid, tname from nteacher;";
		ArrayList<String[]> data = new ArrayList<String[]>();
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sql);
			while(rsSet.next()) {
				String[] tuple = {rsSet.getString(1), rsSet.getString(2)};
				data.add(tuple);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
		return data;
	}
	
	/**
	 * 根据kkid删除开课
	 * @param kkid  开课课程的id
	 * @param oid  操作者的id
	 */
	public static void deleteTCourse(String kkid, String oid) {
		String sql = "delete from ct where kkid = '" + kkid + "';";
		try {
			MySQLConnector.connect(sql);
			String logContent = "id : " + oid + " role:ST \nUser delete course,whose kkid= "+ kkid + ".";
			LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
		} catch (Exception e) {
			System.out.println(e);
			String logContent = "id : " + oid + " role:ST \nUser delete course,whose kkid= "+ kkid + ", but error.";
			LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}
	
	/**
	 * 查询所有学生成绩
	 * @param oid  操作者的id
	 * @return
	 */
	public static ArrayList<String[]> searchStuGrade(String oid) {
		
		String sql = "select ct.kkid,course.cname,ct.tid,nteacher.tname,sc.sid,stu.name,sc.grade\r\n" + 
				"from stu,course,nteacher,sc,ct\r\n" + 
				"where stu.sid=sc.sid and course.cid=ct.cid and nteacher.tid=ct.tid and sc.kkid=ct.kkid ";
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sql);
			ArrayList<String[]> data = new ArrayList<String[]>();
			while(rsSet.next()) {
				String kkid = rsSet.getString("kkid");
				String cname = rsSet.getString("cname");
				String tid = rsSet.getString("tid");
				String tname = rsSet.getString("tname");
				String sid = rsSet.getString("sid");
				String sname = rsSet.getString("name");
				String grade = rsSet.getString("grade");
				if(grade.equals("-1")) {
					grade="烫烫烫";
				}
				String[] info = {kkid , cname, sid, sname, tid, tname, grade};
				data.add(info);
			}
			String logContent = "id : " + oid + " role:ST \nUser inquire all grades information of all students.";
			LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
			return data;
		} catch (SQLException se) {
			String logContent = "id : " + oid + " role:ST \nUser inquire all grades information of all students, but error.";
			LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
			System.out.println(se);
			return null;
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}
	
	/**
	 * 查询所有学生信息
	 * @param oid  操作者的id
	 * @return
	 */
	public static ArrayList<String[]> searchStuInfo(String oid) {
		
		String sql = "select * from stu ";
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sql);
			ArrayList<String[]> data = new ArrayList<String[]>();
			while(rsSet.next()) {
				String sid = rsSet.getString("sid");
				String name = rsSet.getString("name");
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
				String age = rsSet.getString("age");
				String major = rsSet.getString("major");
				String[] info = {sid , name, sexString, age, major};
				data.add(info);
			}
			String logContent = "id : " + oid + " role:ST \nUser inquire all personal information of all students.";
			LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
			return data;
		} catch (SQLException se) {
			String logContent = "id : " + oid + " role:ST \nUser inquire all personal information of all students, but error.";
			LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
			System.out.println(se);
			return null;
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}
	
	/**
	 * 查询所有开课信息
	 * @param oid  操作者的id
	 * @return
	 */
	public static ArrayList<String[]> searchCT(String oid) {
		
		String sql = "select kkid, cname, ct.tid as tid, tname from ct, course, nteacher where ct.cid = course.cid and ct.tid = nteacher.tid";
		try {
			ResultSet rsSet = MySQLConnector.returnConnect(sql);
			ArrayList<String[]> data = new ArrayList<String[]>();
			while(rsSet.next()) {
				String kkid = rsSet.getString("kkid");
				String cname = rsSet.getString("cname");
				String tid = rsSet.getString("tid");
				String tname = rsSet.getString("tname");
				String[] info = {kkid , cname, tid, tname};
				data.add(info);
			}
			String logContent = "id : " + oid + " role:ST \nUser inquire all personal information of all students.";
			LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
			return data;
		} catch (SQLException se) {
			String logContent = "id : " + oid + " role:ST \nUser inquire all personal information of all students, but error.";
			LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
			System.out.println(se);
			return null;
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}
	
	/**
	 * 修改学生信息
	 * @param sid  选择修改的学生id
	 * @param name  学生的姓名
	 * @param sex  学生的性别
	 * @param age  学生的年龄
	 * @param major 学生的专业
	 * @param oid  操作者的id
	 * @throws StudentNotExistException  选择的学生id不存在的异常 
	 */
	public static void alterStuInfo(String sid, String name, int sex, int age, String major, String oid)throws StudentNotExistException {
		if (!VerifyTools.isStudentExist(sid)) {
			throw new StudentNotExistException();
		}
		String sql = "update stu set name = '" + name + "',sex = " + sex + ",age = " + age + ",major='" + major
				+ "' where sid = '" + sid + "';";
		try {
			MySQLConnector.connect(sql);
			String logContent = "id : " + oid + " role:ST \nUser alter student information whose sid =" + sid + ".";
			LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
		} catch (Exception e) {
			String logContent = "id : " + oid + " role:ST \nUser alter student information whose sid =" + sid + ",but error.";
			LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
			System.out.println(e);
		} finally {
			// 完成后关闭
			MySQLConnector.disconnect();
		}
	}


}
