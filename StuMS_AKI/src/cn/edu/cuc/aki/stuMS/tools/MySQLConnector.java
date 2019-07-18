package cn.edu.cuc.aki.stuMS.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnector {
	 static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	    static final String DB_URL = "jdbc:mysql://localhost:3306/stuinfomanage?useSSL=false&serverTimezone=UTC";
	 
	 
	    // 数据库的用户名与密码，需要根据自己的设置
	    static final String USER = "root";
	    static final String PASS = "Heartcheerful";
	    static Connection conn = null;
        static Statement stmt = null;
	    
        
        // 完成后关闭
        
      
	    public static ResultSet returnConnect(String sql) {
	    	 
	         try{
	             // 注册 JDBC 驱动
	             Class.forName(JDBC_DRIVER);
	         
	             // 打开链接
	             System.out.println("连接数据库...");
	             conn = DriverManager.getConnection(DB_URL,USER,PASS);
	         
	             // 执行查询
	             System.out.println(" 实例化Statement对象...");
	             stmt = conn.createStatement();
	             
	             ResultSet rs = stmt.executeQuery(sql);
	         
	           
	             return rs;
	             
	         }catch(SQLException se){
	             // 处理 JDBC 错误
	             se.printStackTrace();
	             return null;
	         }catch(Exception e){
	             // 处理 Class.forName 错误
	             e.printStackTrace();
	             return null;
	         }
	    }
	    

	    public static void connect(String sql) {
	    	 
	         try{
	             // 注册 JDBC 驱动
	             Class.forName(JDBC_DRIVER);
	         
	             // 打开链接
	             System.out.println("连接数据库...");
	             conn = DriverManager.getConnection(DB_URL,USER,PASS);
	         
	             // 执行查询
	             System.out.println(" 实例化Statement对象...");
	             stmt = conn.createStatement();
	             
	             stmt.execute(sql);

	         }catch(SQLException se){
	             // 处理 JDBC 错误
	             se.printStackTrace();
	             
	         }catch(Exception e){
	             // 处理 Class.forName 错误
	             e.printStackTrace();
	            
	         }
	    }
	    
	    public static void disconnect() {
	    	try {
	    		  
	             // 完成后关闭
	    		if(stmt!=null) stmt.close();
	    		
	    	}catch (SQLException e) {
				// TODO: handle exception
			}
	    	try {
	    		if(conn!=null) conn.close();
	    	}catch (SQLException se) {
				// TODO: handle exception
	    		se.printStackTrace();
			}
	    }
	  
}
