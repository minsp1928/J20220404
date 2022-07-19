package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class InterDao {
	private static InterDao instance;

	public InterDao() {
	}

	public static InterDao getInstance() {
		if (instance == null) {
			instance = new InterDao();
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
	
	//int result2 = io.insertSport(inter);
	public int insertSport(Inter inter) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result2 = 0;
		
		String sql = "insert into inter_sports values(?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inter.getUser_id());
			pstmt.setString(2, inter.getSport_type());
			result2 = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) conn.close();
			if (pstmt != null) pstmt.close();
		}
		return result2;
	}
	public Inter selectInterInfo(String user_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT sport_type FROM inter_sports WHERE user_id=?";
		System.out.println("InterDao user_id->"+user_id);
		System.out.println("InterDao sql->"+sql);
		Inter inter = new Inter();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				inter.setSport_type(rs.getString("sport_type"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		return inter;
	}
	//int result2 = io.updateSport(inter);
	public int updateSport(Inter inter) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update inter_sports set sport_type=? where user_id=?";
		int result2 = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inter.getSport_type());
			pstmt.setString(2, inter.getUser_id());
			result2 = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return result2;
	}
}