package cn.edu.cuc.aki.stuMS.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnector {

	/**
	 * mysql閿熸枻鎷烽敓鎺ョ櫢鎷烽敓鏂ゆ嫹
	 */
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/stu_info_manage_system?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF8";

	// 閿熸枻鎷烽敓鎹峰尅鎷烽敓鏂ゆ嫹娌￠敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷疯�勵剨鎷烽敓鎻亷鎷烽敓鏂ゆ嫹閿熸枻鎷风害閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�
	static final String USER = "root";
	static final String PASS = "123456";
	static Connection conn = null;
	static Statement stmt = null;

	// 閿熸枻鎷风強閿熸埅鎲嬫嫹

	public static ResultSet returnConnect(String sql) {

		try {
			// 娉ㄩ敓鏂ゆ嫹 JDBC 閿熸枻鎷烽敓鏂ゆ嫹
			Class.forName(JDBC_DRIVER);

			// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
			System.out.println("閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鎹峰尅鎷�...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 鎵ч敓鍙鎷疯
			System.out.println(" 瀹為敓鏂ゆ嫹閿熸枻鎷稴tatement閿熸枻鎷烽敓鏂ゆ嫹...");
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			return rs;

		} catch (SQLException se) {
			// 閿熸枻鎷烽敓鏂ゆ嫹 JDBC 閿熸枻鎷烽敓鏂ゆ嫹
			se.printStackTrace();
			return null;
		} catch (Exception e) {
			// 閿熸枻鎷烽敓鏂ゆ嫹 Class.forName 閿熸枻鎷烽敓鏂ゆ嫹
			e.printStackTrace();
			return null;
		}
	}

	public static void connect(String sql) {

		try {
			// 娉ㄩ敓鏂ゆ嫹 JDBC 閿熸枻鎷烽敓鏂ゆ嫹
			Class.forName(JDBC_DRIVER);

			// 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 鎵ч敓鍙鎷疯
			stmt = conn.createStatement();

			stmt.execute(sql);

		} catch (SQLException se) {
			// 閿熸枻鎷烽敓鏂ゆ嫹 JDBC 閿熸枻鎷烽敓鏂ゆ嫹
			se.printStackTrace();

		} catch (Exception e) {
			// 閿熸枻鎷烽敓鏂ゆ嫹 Class.forName 閿熸枻鎷烽敓鏂ゆ嫹
			e.printStackTrace();

		}
	}

	public static void disconnect() {
		try {

			// 閿熸枻鎷风強閿熸埅鎲嬫嫹
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
