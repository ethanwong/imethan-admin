package cn.imethan.common.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * JdbcUtils.java
 *
 * @author Ethan
 * @since JDK 1.7
 * @datetime 2015年8月24日下午5:15:16
 */
public class JdbcUtils {	
	
	public static String driver = "com.mysql.jdbc.Driver";//驱动名称
	
	public static String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8";//数据库连接地址
	public static String username = "root";//用户名
	public static String password = "123456";//密码
	public static Connection conn;		
	
	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {			
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println("获取数据库连接出错！"+e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}	

	/**
	 * 获取数据库连接
	 * @param host
	 * @param port
	 * @param database
	 * @param username
	 * @param password
	 * @return
	 */
	public static Connection getConnection(String host,String port,String database,String username,String password) {
		try {			
			Class.forName(driver).newInstance();
			String url = "jdbc:mysql://"+host+":"+port+"/"+database+"?useUnicode=true&characterEncoding=UTF-8";//数据库连接地址
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println("获取数据库连接出错！"+e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}	
	
	/**
	 * 判断数据库是否可以连接
	 * @param host
	 * @param port
	 * @param database
	 * @param username
	 * @param password
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月27日 下午4:07:08
	 */
	public static Map<String,Object> isConnection(String host,String port,String database,String username,String password) {
		boolean isSuccess= true;
		String errorMessage = "";
		try {			
			Class.forName(driver).newInstance();
			String url = "jdbc:mysql://"+host+":"+port+"/"+database+"?useUnicode=true&characterEncoding=UTF-8";//数据库连接地址
			conn = DriverManager.getConnection(url, username, password);
			conn.close();
		} catch (Exception e) {
			isSuccess = false;
			System.out.println("获取数据库连接出错！"+e.getMessage());
//			e.printStackTrace();
			errorMessage = e.getMessage();
			if(errorMessage.length()>200){
				errorMessage = errorMessage.substring(0,200);
			}
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("isSuccess", isSuccess);
		map.put("errorMessage", errorMessage);
		return map;
	}	
	
	/**
	 * 获取数据库声明
	 * 
	 * @param conn 数据库连接
	 * @param sql SQL语句
	 * @return
	 */
	public static PreparedStatement getPreparedStatement(Connection conn,  String sql) {
		PreparedStatement pstmt = null; 
		try {
			if(conn != null) {
				pstmt = conn.prepareStatement(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	/**
	 * 获取数据库声明
	 * 
	 * @param conn 数据库连接
	 * @return
	 */
	public static Statement getStatement(Connection conn) {
		Statement stmt = null; 
		try {
			if(conn != null) {
				stmt = conn.createStatement();
			}
		} catch (SQLException e) {
			System.out.println("获取数据库声明出错！"+e.getMessage());
			e.printStackTrace();
		}
		return stmt;
	}
	
	/**
	 * 查询
	 * 
	 * @param stmt 数据库声明
	 * @param sql SQL语句
	 * @return
	 */
	public static ResultSet getResultSet(Statement stmt, String sql) {
		ResultSet rs = null;
		try {
			if(stmt != null) {
				rs = stmt.executeQuery(sql);
			}
		} catch (SQLException e) {
			System.out.println("数据库查询出错！"+e.getMessage());
			e.printStackTrace();
		}
		return rs;
	}
	
	
	/**
	 * 更新数据库，包括：修改数据、添加数据、删除数据
	 * 
	 * @param stmt 数据库声明
	 * @param sql SQL语句
	 */
	public static void executeUpdate(Statement stmt, String sql) {
		try {
			if(stmt != null) {
				stmt.executeUpdate(sql);
			}
		} catch (SQLException e) {
			System.out.println("数据库更新出错！"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭查询结果集
	 * 
	 * @param rs 查询结果集
	 */
	public static void closeResultSet(ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			System.out.println("数据库关闭结果集出错！"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取所有的表名称
	 * @param conn
	 * @throws SQLException
	 */
	public static List<String> getAllTableNames(Connection conn) throws SQLException{
		List<String> list = new ArrayList<String>();
		DatabaseMetaData databaseMetaData = conn.getMetaData();
		ResultSet rs = databaseMetaData.getTables("", "", null, null);
		while(rs.next()) {
			String tableName = rs.getString("TABLE_NAME");
			list.add(tableName);
		}
		return list;
	}
	
	/**
	 * 关闭数据库声明
	 * 
	 * @param stmt 数据库声明
	 */
	public static void closeStatement(Statement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			System.out.println("数据库关闭声明出错！"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭数据库连接
	 * 
	 * @param conn 数据库连接
	 */
	public static void closeConnection(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			System.out.println("数据库关闭连接出错！"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 测试
	 * 
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args){
		System.out.println("--------------");
		try {
			Connection conn = JdbcUtils.getConnection("127.0.0.1","3306","test","root","123456");
			List<String> list = JdbcUtils.getAllTableNames(conn);
			System.out.println("list:"+list);
			JdbcUtils.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
