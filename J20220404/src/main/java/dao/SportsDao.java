package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SportsDao {
	private static SportsDao instance;
	private SportsDao() {
		
	}
	public static SportsDao getInstance() {
		if(instance == null) {
			instance = new SportsDao();
		}
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public int getTotalCnt(String sport_type) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tot = 0;
		String sql = "";
		
		if(sport_type == "0") {
			sql = "select count(*) from sports";
		} else {
			sql = "select count(*) from sports where sport_type=?";
		}
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			if(sport_type != "0") pstmt.setString(1, sport_type);
			rs = pstmt.executeQuery();
			if(rs.next()) tot = rs.getInt(1);
			
		} catch (SQLException e) {
			System.out.println("sportsCnt"+e.getMessage());
		} finally {
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
		}
		return tot;
	}
	
	public List<Sports> sportList(String sport_type, String userid) throws SQLException {
		
		List<Sports> list = new ArrayList<Sports>();
		
		String sql = " select s.*, nvl(c.cnt, 0), nvl(i.cnt, 0) from sports s," +
				     "(select exnum, count(*) cnt from check_sports where sport_type=? group by exnum) c, "+
				     "(select exnum, count(*) cnt from check_sports where sport_type=? and  user_id = ? group by exnum) i " +
				     " where s.exnum = c.exnum(+) and s.exnum = i.exnum(+) and sport_type = ? order by s.exnum";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sport_type);
			pstmt.setString(2, sport_type);
			pstmt.setString(3, userid);
			pstmt.setString(4, sport_type);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Sports sports = new Sports();
				sports.setSport_type(sport_type);
				sports.setExnum(rs.getInt(2));
				sports.setExname(rs.getString(3));
				sports.setPictureurl(rs.getString(4));					
				sports.setExcontext(rs.getString(5));
				sports.setTime(rs.getString(6));
				sports.setLoc(rs.getString(7));
				sports.setHomeurl(rs.getString(8));
				sports.setTel(rs.getString(9));
				sports.setCheckcnt(rs.getInt(10));
				sports.setCheck(rs.getInt(11));
			
				list.add(sports);
			}
		} catch (SQLException e) {
			System.out.println("sportsDao list : " + e.getMessage() );
		} finally {
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
		}
		
		return list;
	}
	
	public int adminSportDelete(String sport_type, int exnum) throws SQLException {
	    int result = 0;
	    PreparedStatement pstmt = null;
	    Connection conn = null;
	    String sql = "delete from sports where sport_type = ? and exnum = ?";
	
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, sport_type);
	        pstmt.setInt(2, exnum);
	        result = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    } finally {
	        if(conn!=null) conn.close();
	        if(pstmt!=null) pstmt.close();
	    }
	    return result;
	}
	
	public int exnumCount(String sport_type) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		String sql = "select max(exnum) from sports where sport_type=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sport_type);
			rs = pstmt.executeQuery();
			if(rs.next()) result = rs.getInt(1);
			
		} catch (SQLException e) {
			System.out.println("exnumError =>" + e.getMessage());
			
		} finally {
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
		}

		return  result;
	}
	
	public int insertSports(Sports sports) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "insert into sports values(?,?,?,?,?,?,?,?,?)";
		
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sports.getSport_type());
			pstmt.setInt(2, sports.getExnum());
			pstmt.setString(3, sports.getExname());
			pstmt.setString(4, sports.getPictureurl());
			pstmt.setString(5, sports.getExcontext());
			pstmt.setString(6, sports.getTime());
			pstmt.setString(7, sports.getLoc());
			pstmt.setString(8, sports.getHomeurl());
			pstmt.setString(9, sports.getTel());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("insertError =>" + e.getMessage());
		} finally {
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
		}
	
		return result;
	}
	
	public Sports select(String sport_type, int exnum) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Sports sports =  new Sports();
		
		String sql = "select * from sports where sport_type = ? and exnum = ?";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sport_type);
			pstmt.setInt(2, exnum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				sports.setSport_type(rs.getString(1));
				sports.setExnum(rs.getInt(2));
				sports.setExname(rs.getString(3));
				sports.setPictureurl(rs.getString(4));					
				sports.setExcontext(rs.getString(5));
				sports.setTime(rs.getString(6));
				sports.setLoc(rs.getString(7));
				sports.setHomeurl(rs.getString(8));
				sports.setTel(rs.getString(9));
			}
			
		} catch (SQLException e) {
			System.out.println("sportsSelect Err -> " + e.getMessage());
		} finally {
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
		}		
		
		return sports;
	}
	
	public int updateSports(Sports sports) throws SQLException {
	 
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "update sports " 
					+ " set exname = ?, pictureurl = ?, excontext = ?, time = ?, loc = ?, homeurl = ?, tel = ? "
				    + " where sport_type = ? and exnum = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sports.getExname());
			pstmt.setString(2, sports.getPictureurl());
			pstmt.setString(3, sports.getExcontext());
			pstmt.setString(4, sports.getTime());
			pstmt.setString(5, sports.getLoc());
			pstmt.setString(6, sports.getHomeurl());
			pstmt.setString(7, sports.getTel());
			pstmt.setString(8, sports.getSport_type());
			pstmt.setInt(9, sports.getExnum());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("sportUpdate Err-> " + e.getMessage());
		} finally {
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
		}	
	
		return result;
	}
	
	public List<Sports> adminSportList(String sport_type) throws SQLException {
		
		List<Sports> list = new ArrayList<Sports>();
		String sql = "";
		if(sport_type == "0") 	sql = "select * from sports";
		else 					sql = "select * from sports where sport_type=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			if(sport_type != "0") pstmt.setString(1, sport_type);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Sports sports = new Sports();
				sports.setSport_type(rs.getString(1));
				sports.setExnum(rs.getInt(2));
				sports.setExname(rs.getString(3));
				sports.setPictureurl(rs.getString(4));					
				sports.setExcontext(rs.getString(5));
				sports.setTime(rs.getString(6));
				sports.setLoc(rs.getString(7));
				sports.setHomeurl(rs.getString(8));
				sports.setTel(rs.getString(9));			
				list.add(sports);
			}
		
		} catch (SQLException e) {
			System.out.println("admin list : " + e.getMessage() );
		} finally {
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
		}
		return list;
	}
	
	
	public Sports sportListSelect(String list) throws SQLException {
//		List<Sports> sp = new ArrayList<Sports>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "select * from sports where loc = ?";
		Sports sports = new Sports();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, list);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sports.setSport_type(rs.getString("sport_type"));
				sports.setExnum(rs.getInt("exnum"));
				sports.setExname(rs.getString("exname"));
				sports.setPictureurl(rs.getString("pictureurl"));					
				sports.setExcontext(rs.getString("excontext"));
				sports.setTime(rs.getString("time"));
				sports.setLoc(rs.getString("loc"));
				sports.setHomeurl(rs.getString("homeurl"));
				sports.setTel(rs.getString("tel"));
//				lsp.add(sports);
			}
		} catch (SQLException e) {
			System.out.println("sd.sportListSelect error -> "+e.getMessage());
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			if(rs != null) rs.close();
		}
		
		return sports;
	}
	
	public List<Sports> SearchContents(String search) throws SQLException {
		List<Sports> list = new ArrayList<Sports>();
		Connection conn = null;	
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = "select s.sport_type, s.exnum, s.excontext, c.con "
				+ "from sports s, common c "
				+ "where s.sport_type = c.mcd "
				+ "and s.excontext like ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Sports spo = new Sports();
				spo.setSport_type(rs.getString("sport_type"));
				spo.setExnum(rs.getInt("exnum"));
				spo.setExcontext(rs.getString("excontext"));
				spo.setSports_type_name(rs.getString("con"));
				list.add(spo);
			}
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		} 
		return list;

	}//end of SearchContents
	
	public List<Sports> SearchSubject(String search) throws SQLException {
		List<Sports> list = new ArrayList<Sports>();
		Connection conn = null;	
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = "select s.sport_type, s.exnum, s.exname, c.con "
				+ "from sports s, common c "
				+ "where s.sport_type = c.mcd "
				+ "and s.exname like ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Sports spo = new Sports();
				spo.setSport_type(rs.getString("sport_type"));
				spo.setExnum(rs.getInt("exnum"));
				spo.setExname(rs.getString("exname"));
				spo.setSports_type_name(rs.getString("con"));
				list.add(spo);
			}
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		} 
		return list;

	}//end of SearchContents

}