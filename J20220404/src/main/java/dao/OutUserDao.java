package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OutUserDao {
	private static OutUserDao instance;
	
	public OutUserDao() {
	}
	
	public static OutUserDao getInstance() {
		if (instance == null) {
			instance = new OutUserDao();
		}
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	//result2 = ud.insertOut(user_id);
	public int insertOut(OutUser outUser) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result2 = 0;
		String sql = "insert into outuser values(?,?)";
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, outUser.getUser_id());
			pstmt.setString(2, outUser.getUnregister_reason());
			result2 = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)	pstmt.close();
			if (conn != null)	conn.close();
		}
		return result2;
	}
}
