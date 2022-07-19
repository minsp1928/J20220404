package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONObject;


public class AdminDao {
	private static AdminDao instance;
	private AdminDao() {}
	public static AdminDao getInstance() {
		if (instance == null) {
			instance = new AdminDao();
		}
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)
				ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch(Exception e) { 
			System.out.println(e.getMessage());	
		}
		return conn;
	}
	
	public List<Admin> adminLogView() throws SQLException {
		List<Admin> adminLogView = new ArrayList<Admin>();
		Connection conn = null;
		String sql = "SELECT * FROM admin_log ORDER BY admin_date DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					Admin admin = new Admin();
					admin.setAdmin_date(rs.getDate(1));
					admin.setAdmin_ip(rs.getString(2));
					adminLogView.add(admin);
				} while (rs.next());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return adminLogView;
	}
	
// **************************************
	public int adminCheck(String admin_id, String admin_ps) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select admin_ps from admin where admin_id=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbAdmin_ps = rs.getString(1);
				if (dbAdmin_ps.equals(admin_ps))
					result = 1;
				else
					result = 0;
			} else
				result = -1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

// **************************************
	public int adminLoginsert(String remoteAddr) throws SQLException {
		int result = 0;
		Connection conn = null;
		String sql = "insert into admin_log values(sysdate,?)";
		System.out.println("insert admin_log.getAdmin_Ip()->"+remoteAddr);
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			System.out.println("insert sql->"+sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  remoteAddr);
			result = pstmt.executeUpdate();
			System.out.println("insert result->"+result);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	
	/**
	 * 관심스포츠 차트 
	 * 
	 * @return
	 * @throws SQLException
	 */
	public JSONObject getInterSportsChart() throws SQLException {
		List result = new ArrayList();
		List cList = new ArrayList();
		JSONObject rowsMap = new JSONObject();
		JSONObject cMap = new JSONObject();
		JSONObject vMap = new JSONObject();
		Connection conn = null;
		String sql = " SELECT DECODE(sport_type, 1, '헬스', 2, '수영', 3,'필라요가', 4, '골프', 5, '기타') as sport_name  ";
			  sql += "       ,cnt  ";
		      sql += "       ,sport_type  ";
		      sql += " FROM (  SELECT sport_type, COUNT(sport_type) as cnt  ";
		      sql += "         FROM inter_sports  ";
		      sql += "         GROUP BY sport_type)  ";
		      sql += " ORDER BY sport_type ASC  ";
		
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					vMap = new JSONObject();
					vMap.put("v", rs.getString(1));
					cList.add(vMap);
					vMap = new JSONObject();
					vMap.put("v", rs.getInt(2));
					cList.add(vMap);
					vMap = new JSONObject();
					cMap.put("c", cList);
					result.add(cMap);
					cList = new ArrayList();
					cMap = new JSONObject();
				} while (rs.next());
				
				rowsMap.put("rows", result);

				List colList = new ArrayList();
				JSONObject labelMap = new JSONObject();
				labelMap.put("label", "종목");
				labelMap.put("type", "string");
				colList.add(labelMap);
				labelMap = new JSONObject();
				labelMap.put("label", "관심수");
				labelMap.put("type", "number");
				colList.add(labelMap);

				rowsMap.put("cols", colList);
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return rowsMap;
	}
	
	/**
	 * 찜 스포츠 차트 
	 * 
	 * @return
	 * @throws SQLException
	 */
	public JSONObject getCheckSportsChart() throws SQLException {
		List result = new ArrayList();
		List cList = new ArrayList();
		JSONObject rowsMap = new JSONObject();
		JSONObject cMap = new JSONObject();
		JSONObject vMap = new JSONObject();
		Connection conn = null;
		String sql = " SELECT DECODE(sport_type, 1, '헬스', 2, '수영', 3,'필라요가', 4, '골프', 5, '기타') as sport_name  ";
			  sql += "       ,cnt  ";
		      sql += "       ,sport_type  ";
		      sql += " FROM (  SELECT sport_type, COUNT(sport_type) as cnt  ";
		      sql += "         FROM check_sports  ";
		      sql += "         GROUP BY sport_type)  ";
		      sql += " ORDER BY sport_type ASC  ";
		
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					vMap = new JSONObject();
					vMap.put("v", rs.getString(1));
					cList.add(vMap);
					vMap = new JSONObject();
					vMap.put("v", rs.getInt(2));
					cList.add(vMap);
					vMap = new JSONObject();
					cMap.put("c", cList);
					result.add(cMap);
					cList = new ArrayList();
					cMap = new JSONObject();
				} while (rs.next());
				
				rowsMap.put("rows", result);

				List colList = new ArrayList();
				JSONObject labelMap = new JSONObject();
				labelMap.put("label", "종목");
				labelMap.put("type", "string");
				colList.add(labelMap);
				labelMap = new JSONObject();
				labelMap.put("label", "찜한수");
				labelMap.put("type", "number");
				colList.add(labelMap);

				rowsMap.put("cols", colList);
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return rowsMap;
	}
	
	/**
	 * 탈퇴유저 차트 
	 * 
	 * @return
	 * @throws SQLException
	 */
	
	public JSONObject getOutuserChart() throws SQLException {
		List result = new ArrayList();
		List cList = new ArrayList();
		JSONObject rowsMap = new JSONObject();
		JSONObject cMap = new JSONObject();
		JSONObject vMap = new JSONObject();
		Connection conn = null;
		String sql = " SELECT DECODE(unregister_reason, 1, '거주지 변경', 2, '타 사이트 이용', 3,'기타') as unregister_reason_name";
			  sql += "       ,cnt  ";
		      sql += "       ,unregister_reason  ";
		      sql += " FROM (  SELECT unregister_reason, COUNT(unregister_reason) as cnt  ";
		      sql += "         FROM outuser  ";
		      sql += "         GROUP BY unregister_reason)  ";
		      sql += " ORDER BY unregister_reason ASC  ";
		
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					vMap = new JSONObject();
					vMap.put("v", rs.getString(1));
					cList.add(vMap);
					vMap = new JSONObject();
					vMap.put("v", rs.getInt(2));
					cList.add(vMap);
					vMap = new JSONObject();
					cMap.put("c", cList);
					result.add(cMap);
					cList = new ArrayList();
					cMap = new JSONObject();
				} while (rs.next());
				
				rowsMap.put("rows", result);

				List colList = new ArrayList();
				JSONObject labelMap = new JSONObject();
				labelMap.put("label", "탈퇴사유");
				labelMap.put("type", "string");
				colList.add(labelMap);
				labelMap = new JSONObject();
				labelMap.put("label", "해당사유 탈퇴 건수");
				labelMap.put("type", "number");
				colList.add(labelMap);

				rowsMap.put("cols", colList);
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return rowsMap;
	}
	

}
