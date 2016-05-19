package com.bailian.ta.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bailian.ta.log.LogUtil;

public class DataBaseUtil {
	public static ResultSet rs = null;
	public static Statement stmt = null;
	public static Connection conn = null;

	public static void loadOrcl(String IP, String port, String db, String user, String psw) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			LogUtil.info("Load DataBase Driver Success!");
		} catch (ClassNotFoundException e) {
			LogUtil.info("Load DataBase Driver Failed!");
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@" + IP + ":" + port + ":" + db, user, psw);
			LogUtil.info("Connect to DataBase Success!");
		} catch (SQLException e) {
			LogUtil.info("Connect to DataBase Failed!");
			e.printStackTrace();
		}
	}

	public static String querySql(String sql, String field) {
		String rsStr = "";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				rsStr += rs.getString(field);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rsStr;
	}

	public static void executeSql(String sql) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Map<String, String>> querySql(String sql) {
		List<Map<String, String>> result = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			result = new ArrayList<Map<String, String>>();
			while (rs.next()) {
				Map<String, String> recordMap = new HashMap<String, String>();
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					recordMap.put(rsmd.getColumnName(i), rs.getString(rsmd
							.getColumnName(i)));
				}
				result.add(recordMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static List<String> querySql_(String sql) {
		List<String> result = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			result = new ArrayList<String>();
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					result.add(rs.getString(rsmd.getColumnName(i)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
			LogUtil.info("Close the Connection!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	//	loadOrcl("10.201.64.106","1521","orcl","cps_admin","cps_admin");

	//	List<Map<String, String>> test = querySql("select ID,TARGET_NAME from ads_target where status = 'precheck' order by ID ASC");
		
	//	List<Map<String, String>> test = querySql("select count(*) from ads_target where status = 'precheck'");
	//	List<String> test = querySql_("select ID,TARGET_NAME from ads_target where status = 'valid' order by ID ASC");
	//	for (int i = 0; i <=test.size()-1; i++){
	//		System.out.println(test.get(i).toString());
	//	}
	//	close();
		
		

	}
}
