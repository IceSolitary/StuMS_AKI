package cn.edu.cuc.aki.stuMS.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnector {

	/**
	 * mysql���ӹ���
	 */
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/stuinfomanage?useSSL=false&serverTimezone=UTC";

	// ���ݿ���û��������룬��Ҫ�����Լ�������
	static final String USER = "root";
	static final String PASS = "Heartcheerful";
	static Connection conn = null;
	static Statement stmt = null;

	// ��ɺ�ر�

	public static ResultSet returnConnect(String sql) {

		try {
			// ע�� JDBC ����
			Class.forName(JDBC_DRIVER);

			// ������
			System.out.println("�������ݿ�...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// ִ�в�ѯ
			System.out.println(" ʵ����Statement����...");
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			return rs;

		} catch (SQLException se) {
			// ���� JDBC ����
			se.printStackTrace();
			return null;
		} catch (Exception e) {
			// ���� Class.forName ����
			e.printStackTrace();
			return null;
		}
	}

	public static void connect(String sql) {

		try {
			// ע�� JDBC ����
			Class.forName(JDBC_DRIVER);

			// ������
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// ִ�в�ѯ
			stmt = conn.createStatement();

			stmt.execute(sql);

		} catch (SQLException se) {
			// ���� JDBC ����
			se.printStackTrace();

		} catch (Exception e) {
			// ���� Class.forName ����
			e.printStackTrace();

		}
	}

	public static void disconnect() {
		try {

			// ��ɺ�ر�
			if (stmt != null)
				stmt.close();

		} catch (SQLException e) {
			// TODO: handle exception
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			// TODO: handle exception
			se.printStackTrace();
		}
	}

}
