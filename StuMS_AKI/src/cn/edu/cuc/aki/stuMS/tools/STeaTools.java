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
	 * ������ʦ����
	 */

	/**
	 * ��ѯ������ʦ�ĸ�����Ϣ
	 * 
	 * @param tid ������ʦ��id
	 * @return ������ʦ�ĸ�����Ϣ�ֵ�
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
					sexString = "��";
				}
				else if(sex==2) {
					sexString = "Ů";
				}
				else {
					sexString = "δ֪";
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
			// ��ɺ�ر�
			MySQLConnector.disconnect();
		}
	}

	/**
	 * ����Ӧ��ʦ���Ѵ��ڵĿγ�
	 * 
	 * @param kkid ���ε�id
	 * @param cid  �γ̵�id
	 * @param tid  ��ʦ��id
	 * @param oid  �����ߵ�id
	 * @throws TeacherNotExistException ����ʦ�����ڵ��쳣
	 * @throws CourseNotExistException  �ÿγ̲����ڵ��쳣
	 */
	public static void addSTCourse(String kkid, String cid, String tid,String oid)
			throws TeacherNotExistException, CourseNotExistException {
		if (!VerifyTools.isTeacherExist(tid)) {
			throw new TeacherNotExistException();
		}

		if (!VerifyTools.isCourseExist(cid)) {
			throw new CourseNotExistException();
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
			// ��ɺ�ر�
			MySQLConnector.disconnect();
		}
	}

	/**
	 * ɾ����Ӧ��ʦ�Ŀ�
	 * @param tid  ��ʦ��id
	 * @param cid  �γ̵�id
	 * @param oid  �����ߵ�id
	 * @throws CourseNotMatchTeacherException �����Ӧ��ʦ�Ƿ��˴˿�
	 */
	public static void deleteTCourse(String tid, String cid, String oid) throws CourseNotMatchTeacherException {

		if (!VerifyTools.isCourseMatchTeacher(cid, tid)) {
			throw new CourseNotMatchTeacherException();
		}

		String sql = "delete from ct where cid = '" + cid + "' and tid = '"+ tid +"';";
		try {
			MySQLConnector.connect(sql);
			String logContent = "id : " + oid + " role:ST \nUser delete course,whose tid= "+ tid +", cid = "+cid+".";
			LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
		} catch (Exception e) {
			System.out.println(e);
			String logContent = "id : " + oid + " role:ST \nUser delete course,whose tid= "+ tid +", cid = "+cid+", but error.";
			LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
		} finally {
			// ��ɺ�ر�
			MySQLConnector.disconnect();
		}
	}
	
	/**
	 * ��ѯ����ѧ���ɼ�
	 * @param oid  �����ߵ�id
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
				int grade = rsSet.getInt("grade");
				String graString = Integer.toString(grade);
				String[] info = {kkid , cname, tid, tname, graString};
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
			// ��ɺ�ر�
			MySQLConnector.disconnect();
		}
	}
	
	/**
	 * �޸�ѧ����Ϣ
	 * @param sid  ѡ���޸ĵ�ѧ��id
	 * @param name  ѧ��������
	 * @param sex  ѧ�����Ա�
	 * @param age  ѧ��������
	 * @param major ѧ����רҵ
	 * @param oid  �����ߵ�id
	 * @throws StudentNotExistException  ѡ���ѧ��id�����ڵ��쳣 
	 */
	public static void alterStuInfo(String sid, String name, int sex, int age, String major, String oid)throws StudentNotExistException {
		if (!VerifyTools.isStudentExist(sid)) {
			throw new StudentNotExistException();
		}
		String sql = "upadate stu set name = '" + name + "',sex = " + sex + ",age = " + age + ",major='" + major
				+ "', where sid = '" + sid + "';";
		try {
			MySQLConnector.connect(sql);
			String logContent = "id : " + oid + " role:ST \nUser alter student information whose sid =" + sid + ".";
			LogIplm.addLog(LogIplm.TYPE.INFORMATION, logContent);
		} catch (Exception e) {
			String logContent = "id : " + oid + " role:ST \nUser alter student information whose sid =" + sid + ",but error.";
			LogIplm.addLog(LogIplm.TYPE.ERROR, logContent);
			System.out.println(e);
		} finally {
			// ��ɺ�ر�
			MySQLConnector.disconnect();
		}
	}


}
