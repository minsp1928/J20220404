package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;




public class MemberDao {
	private static MemberDao instance;

	public MemberDao() {
	}

	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
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

	public int confirmId(String user_id) throws SQLException {
		int result = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select user_id from member where user_id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next())
				result = 1;
			else
				result = 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return result;
	}

	public int insertMember(Member member) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "insert into member values(?,?,?,?,?,?,?,sysdate,'1')";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUser_id());
			pstmt.setString(2, member.getUser_ps());
			pstmt.setString(3, member.getUname());
			pstmt.setLong(4, member.getId_num());
			pstmt.setString(5, member.getGender());
			pstmt.setString(6, member.getUser_picture());
			pstmt.setString(7, member.getTel());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
		}
		return result;
	}

	public int check(String user_id, String user_ps) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select user_ps from member where user_id=? and status=1";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbUser_ps = rs.getString(1);
				if (dbUser_ps.equals(user_ps))
					result = 1;
				else
					result = 0; // 유저는 맞고, 비밀번호는 틀리다.
			} else
				result = -1; // 유저 존재하지 않으면
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public Member selectMemberInfo(String user_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(user_id);
		String sql = "SELECT user_id, user_ps, uname, id_num, gender, user_picture, tel FROM member WHERE user_id=?";
		Member member = new Member();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setUser_id(rs.getString("user_id"));
				member.setUser_ps(rs.getString("user_ps"));
				member.setUname(rs.getString("uname"));
				member.setId_num(rs.getLong("id_num"));
				member.setGender(rs.getString("gender"));
				member.setUser_picture(rs.getString("user_picture"));
				member.setTel(rs.getString("tel"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		if(conn!=null) conn.close();
	}
	return member;
}


public int updateMember(Member member) throws SQLException {
	Connection conn = null;
	PreparedStatement pstmt = null;
	String sql = "update member set user_ps=?, uname=?, id_num=?, gender=?, user_picture=?, tel=? where user_id=?";
	int result = 0;
	try {
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, member.getUser_ps());
		pstmt.setString(2, member.getUname());
		pstmt.setLong(3, member.getId_num());
		pstmt.setString(4, member.getGender());
		pstmt.setString(5, member.getUser_picture());
		pstmt.setString(6, member.getTel());
		pstmt.setString(7, member.getUser_id());
		result = pstmt.executeUpdate();
	} catch (Exception e) {
		System.out.println(e.getMessage());

	} finally {
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}
	return result;
}

	public Member idCheck(String uname, Long id_num, String tel) throws SQLException {
		Member member = new Member();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select user_id from member where uname=? and id_num=? and tel=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uname);
			pstmt.setLong(2, id_num);
			pstmt.setString(3, tel);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setUser_id(rs.getString("user_id"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs !=null) rs.close();
			if (pstmt !=null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return member;
	}
	public Member psCheck(String user_id, Long id_num, String tel) throws SQLException {
		Member member = new Member();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select user_ps from member where user_id=? and id_num=? and tel=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setLong(2, id_num);
			pstmt.setString(3, tel);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setUser_ps(rs.getString("user_ps"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs !=null) rs.close();
			if (pstmt !=null) rs.close();
			if (conn != null) rs.close();
		}
		return member;
	}
	
	
	public int delupdate(String user_id, String user_ps) throws SQLException {
		int result = 0;
		Connection conn = null;
		System.out.println(result);
		result = check(user_id, user_ps);
		System.out.println("delupdate result -> "+result);
		if (result != 1) 
			return result;
		String sql = "update member set status =2 where user_id=? and user_ps=?";
		System.out.println("sql->"+sql);
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_ps);
			System.out.println("execute?");
			result = pstmt.executeUpdate();
			System.out.println("result->"+result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return result;
	}

}

