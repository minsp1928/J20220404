package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CheckSportsDao {
	
	private static CheckSportsDao instance;
	private CheckSportsDao() {}
	public static CheckSportsDao getInstance() {
		if(instance == null) {
			instance = new CheckSportsDao();
		}
		return instance;	
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = 
					(DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public int checkLike(CheckSports checkSports) throws SQLException {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from check_sports where user_id=? and sport_type=? and exnum = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, checkSports.getUser_id());
			pstmt.setString(2, checkSports.getSport_type());
			pstmt.setInt(3, checkSports.getExnum());
			rs = pstmt.executeQuery();
			if(rs.next()) result = rs.getInt(1);
			System.out.println("result -> " + result);
			
		} catch (SQLException e) {
			System.out.println("check ->"  + e.getMessage());
		} finally {
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
		}
		
		return result;
	}
	
	public int insertLike(CheckSports checkSports) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "insert into check_sports values(?,?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, checkSports.getUser_id());
			pstmt.setString(2, checkSports.getSport_type());
			pstmt.setInt(3, checkSports.getExnum());	
			result = pstmt.executeUpdate();
			if(result > 0) result = 1;
			else           result = 0;
			
		} catch (SQLException e) {
			System.out.println("insertLike ->"  + e.getMessage());
		} finally {
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
			 
		}
		
		System.out.println("insert result=>"+ result);
		return result;
	}
	
	public int deleteLike(CheckSports checkSports) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "delete from check_sports where user_id=? and sport_type=? and exnum=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, checkSports.getUser_id());
			pstmt.setString(2, checkSports.getSport_type());
			pstmt.setInt(3, checkSports.getExnum());
			result = pstmt.executeUpdate();
			if(result > 0) result = 1;
			else           result = 0;
			
		} catch (SQLException e) {
			System.out.println("deleteLike ->"  + e.getMessage());
		} finally {
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();	 
		}
		System.out.println("delete result=>"+ result);
		return result;
	}
}
