package cn.edu.cuc.aki.stuMS.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnector {

	/**
	 * mysql锟斤拷锟接癸拷锟斤拷
	 */
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/stu_info_manage_system?useSSL=false&serverTimezone=UTC";

	// 锟斤拷锟捷匡拷锟斤拷没锟斤拷锟斤拷锟斤拷锟斤拷耄拷锟揭拷锟斤拷锟斤拷约锟斤拷锟斤拷锟斤拷锟�
	static final String USER = "root";
	static final String PASS = "Heartcheerful";
	static Connection conn = null;
	static Statement stmt = null;

	// 锟斤拷珊锟截憋拷

	public static ResultSet returnConnect(String sql) {

		try {
			// 注锟斤拷 JDBC 锟斤拷锟斤拷
			Class.forName(JDBC_DRIVER);

			// 锟斤拷锟斤拷锟斤拷
			System.out.println("锟斤拷锟斤拷锟斤拷锟捷匡拷...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 执锟叫诧拷询
			System.out.println(" 实锟斤拷锟斤拷Statement锟斤拷锟斤拷...");
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			return rs;

		} catch (SQLException se) {
			// 锟斤拷锟斤拷 JDBC 锟斤拷锟斤拷
			se.printStackTrace();
			return null;
		} catch (Exception e) {
			// 锟斤拷锟斤拷 Class.forName 锟斤拷锟斤拷
			e.printStackTrace();
			return null;
		}
	}

	public static void connect(String sql) {

		try {
			// 注锟斤拷 JDBC 锟斤拷锟斤拷
			Class.forName(JDBC_DRIVER);

			// 锟斤拷锟斤拷锟斤拷
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 执锟叫诧拷询
			stmt = conn.createStatement();

			stmt.execute(sql);

		} catch (SQLException se) {
			// 锟斤拷锟斤拷 JDBC 锟斤拷锟斤拷
			se.printStackTrace();

		} catch (Exception e) {
			// 锟斤拷锟斤拷 Class.forName 锟斤拷锟斤拷
			e.printStackTrace();

		}
	}

	public static void disconnect() {
		try {

			// 锟斤拷珊锟截憋拷
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
