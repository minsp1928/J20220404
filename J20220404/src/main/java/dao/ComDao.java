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
import javax.sql.DataSource;

public class ComDao {
	private static ComDao instance; // 인스턴스화
	private ComDao() {}
	public static ComDao getInstance() {
		
		if(instance == null) {
		   instance = new ComDao();
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
	
	public List<Com> SearchContents(String search) throws SQLException {
		System.out.println("Start Com SearchContents sql");
		List<Com> list = new ArrayList<Com>();
		Connection conn = null;	
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = "select comm.num, comm.com_type, comm.content, c.con "
				+ "from community comm, common c "
				+ "where comm.com_type = c.mcd "
				+ "and re_level = 0 "
				+ "and content like ?";
		try {
			System.out.println("%"+search+"%");
			System.out.println("sql->"+sql);
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			System.out.println("comDao test1");
			pstmt.setString(1, "%"+search+"%");
			System.out.println("comDao test2");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Com com = new Com();
				com.setNum(rs.getInt("num"));
				com.setContent(rs.getString("content"));
				com.setCom_type(rs.getInt("com_type"));
				com.setCom_type_name(rs.getString("con"));
				list.add(com);
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
	
	// 문자열을 가지고 테이블 제목 검색
	// JSH220421
	public List<Com> SearchSubject(String search) throws SQLException {
		System.out.println("Start Com SearchSubject sql");
		List<Com> list = new ArrayList<Com>();
		Connection conn = null;	
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = "select comm.num, comm.com_type, comm.subject, c.con "
				+ "from community comm, common c "
				+ "where comm.com_type = c.mcd "
				+ "and re_level = 0"
				+ "and subject like ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Com com = new Com();
				com.setNum(rs.getInt("num"));
				com.setSubject(rs.getString("subject"));
				com.setCom_type(rs.getInt("com_type"));
				com.setCom_type_name(rs.getString("con"));
				list.add(com);
			}
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		} 
		return list;

	}//end of SearchSubject
	

	public int getTotalCnt(int com_type) throws SQLException {//댓글수를 세는 메서드
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tot = 0;
		String sql = "select count(*) from community where re_level = 0 and com_type = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, com_type);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				tot = rs.getInt(1);
			}
			System.out.println("tot->"+tot);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return tot;
		
	}//end of getTotalCnt

	public List<Com> hashList(int num) throws SQLException {//해시태그 리스트
		List<Com> hashList = new ArrayList<Com>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select h.num, h.h_seq, h.hsre_content from  hash h, community c where c.num = h.num and c.num = ? ";
		System.out.println("hashList sql->"+sql); 
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Com com = new Com();
				com.setH_seq(rs.getInt("h_seq"));
				com.setHsre_content(rs.getString("hsre_content"));
				hashList.add(com);
				System.out.println("h_seq->"+rs.getInt("h_seq"));
				System.out.println("hsre_content->"+rs.getString("hsre_content"));
			}			
		} catch (Exception e) {
			System.out.println("e.getMessage()"+e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		
		return hashList;
		
		
	}//end of hashList
	public Com select(int num) throws SQLException {//셀렉트 메서드

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from community where num=? ";
		Com com = new Com();
		System.out.println("DAO select sql->"+sql);

		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				com.setNum(rs.getInt("num"));
				com.setUser_id(rs.getString("user_id"));
				com.setCom_type(rs.getInt("com_type"));
				com.setSubject(rs.getString("subject"));
				com.setContent(rs.getString("content"));
				com.setPoto(rs.getString("poto"));
				com.setCount(rs.getInt("count"));
				com.setRef(rs.getInt("ref"));
				com.setRe_step(rs.getInt("re_step"));
				com.setRe_level(rs.getInt("re_level"));
				com.setReg_date(rs.getDate("reg_date"));
				com.setReco(rs.getInt("reco"));
				com.setRe_content(rs.getString("re_content"));
				System.out.println("CD select content->"+rs.getString("content"));
				System.out.println("ComDAO Select com_type->"+rs.getInt("com_type"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			System.out.println("------------select끝----------");
		}return com;
	}// end of select
	
	public void count(int num) throws SQLException {//조회수(카운팅 메서드)
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE community set count = count+1 where num=?";
		System.out.println("DAO count num->"+num);
		System.out.println("DAO count sql->"+sql);
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
	}// end of count
	public int update(Com com) throws SQLException {//게시글 수정 메서드(민서)
		Connection conn =null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update community set user_id=?, subject=?, content=?, poto=? where num=?";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			System.out.println("user_id->"+ com.getUser_id());
			System.out.println("subject->"+	com.getSubject());
			System.out.println("content->"+com.getContent());
			System.out.println("getPoto->"+com.getPoto());
			System.out.println("num->"+com.getNum());
			pstmt.setString(1, com.getUser_id());
			pstmt.setString(2, com.getSubject());
			pstmt.setString(3, com.getContent());
			pstmt.setString(4, com.getPoto());
			pstmt.setInt(5, com.getNum());
		

			result=pstmt.executeUpdate();
			
			
			System.out.println("ComDao update result->"+result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}finally {
			if(pstmt != null) pstmt.close();
			if(conn  != null) conn.close();
		}
		return result;
	}// end of update

	public List<Com> recnList(int num) throws SQLException {//댓글 리스트 (민서)

			List<Com> recnList = new ArrayList<Com>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select * from community where re_level>0 and ref= ? order by re_step desc";
			System.out.println("recnList sql->"+sql);
			System.out.println("recnList num->"+num);
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				System.out.println("+++++++++++++Com Dao recnList sql->"+sql); 
				rs = pstmt.executeQuery();
				System.out.println("recnList rs->"+rs);
				System.out.println("recnList num->"+num);
				while (rs.next()) {
					Com com = new Com();
					System.out.println("recnList test1");
					com.setRe_content(rs.getString("re_content"));
					System.out.println("recnList re_content->"+rs.getString("re_content"));
					com.setNum(rs.getInt("num"));
					com.setUser_id(rs.getString("user_id"));
					System.out.println("Com Dao recnList->"+rs.getString("user_id"));
					com.setRef(rs.getInt("ref"));
					com.setRe_step(rs.getInt("re_step"));
					recnList.add(com);
				}			
			} catch (Exception e) {
				System.out.println("e.getMessage()"+e.getMessage());
			} finally {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}
			System.out.println("+++++recnList 끝");
			return recnList;
			
			
		}//end of commList
	public int commInsert(Com com) throws SQLException {//댓글 입력 메서드 (민서)
		   int num = com.getNum();
		   
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   int result = 0;
		   ResultSet rs = null;
		   String sql1 = "select nvl(max(num),0) from community";//새로운 데이터이므로
		   String sql2 = "insert into community values (?,?,?,?,?,?,?,?,?,?,sysdate,?,?)"; //댓글이니까 가져갈 값 넘, ref, re_level, re_step, user_id, RE_CONTENT, 작성일...?
		   String sql3 = "update community set re_step = re_step+1 where ref=? and re_step > ? ";
		 //String sql3 = "update community set re_step = re_step+1 where ref=? and re_step > ? order by re_step desc";->오더바이 sql문 문제

		   System.out.println("CD commInsert sql1->"+sql1);
		   System.out.println("CD commInsert sql2->"+sql2);
		   System.out.println("CD commInsert sql3->"+sql3);
		   System.out.println("CD commInsert num->"+num);
			
		   try {  System.out.println("------------CD commInsert try문--------");
			conn = getConnection();//댓글
			 if(num !=0){
				 System.out.println("CD commInsert if문");
				 pstmt=conn.prepareStatement(sql3);
				 pstmt.setInt(1, com.getRef());
				 pstmt.setInt(2, com.getRe_step());
				 System.out.println("CD insert sql3->"+sql3);
				 System.out.println("CD com.getRef()->"+com.getRef());
				 System.out.println("CD com.getRe_step()->"+com.getRe_step());
				 pstmt.executeUpdate();
				 pstmt.close();//db에 업데이트
				 com.setRe_step(com.getRe_step()+1);
				 com.setRe_level(com.getRe_level()+1);//com에 업데이트 
				 System.out.println("CD com.getRe_level()+1->"+com.getRe_level()+1);
			 }
			 
			 pstmt = conn.prepareStatement(sql1);
			 rs = pstmt.executeQuery();
			 rs.next();
	         int number = rs.getInt(1)+1;
	         System.out.println("CD commInsertnumber->"+number);
	         rs.close();
	         pstmt.close();
	        
	         if(num == 0) com.setRef(number);
	         System.out.println("--****-----CD commInsert id num == 0----****--"+number);
	         pstmt=conn.prepareStatement(sql2);
	         pstmt.setInt(1, number);
	         pstmt.setString(2, com.getUser_id());
	         System.out.println("-----com.getUser_id()->"+com.getRe_content());

	         pstmt.setInt(3, com.getCom_type());
	         pstmt.setString(4, com.getSubject());
	         pstmt.setString(5, com.getContent());
	         pstmt.setString(6, com.getPoto());
	         pstmt.setInt(7, com.getCount());
	         pstmt.setInt(8, com.getRef());//
	         pstmt.setInt(9, com.getRe_step());
	         pstmt.setInt(10, com.getRe_level());
	         pstmt.setInt(11, com.getReco());
	         pstmt.setString(12, com.getRe_content());
	         System.out.println("-----com.getRe_content()->"+com.getRe_content());
	         result = pstmt.executeUpdate();
			
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if(pstmt != null) pstmt.close();
				if(conn != null)  conn.close(); 
			}
			   System.out.println("-----CD commInsert num->"+num);
			   System.out.println("-------++_---CD commInsert end-------++_---");
			   return result;
			
	   }//end of commInsert
	public int commDelete(int recnnum) throws SQLException {//댓글 삭제 메서드 (민서)
		Connection conn= null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql ="delete from community where num=?";
		System.out.println("CD commDelete sql->"+sql);
		try {System.out.println("CD commDelete try test");
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recnnum);
			/* pstmt.setInt(2, re_step); */	
			result = pstmt.executeUpdate();
			System.out.println("CD commDelete num->"+recnnum); 
			
		} catch (Exception e) {
			System.out.println("CD commDelete e.getMessage()->"+e.getMessage());
		}finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return result;
	}
//민주언니 작업물ㄱ	
	public List<Com> reviewList(int startRow,int endRow, int com_type) throws SQLException{//후기리스트(민주)
	      ArrayList<Com> reviewList = new ArrayList<Com>();
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      String sql = "select * "
	               + "from (select rownum rn, a.*"
	                     + "from                  (  select  num , user_id ,com_type,  subject,"
	                                                   + "content, poto, count, ref, re_step, re_level, reg_date, reco , "
	                                                   + "re_content ,com_re_content2(ref) re_content2,  "
	                                                   + "com_re_content(ref) user_id2 "
	                                          + "from   community "
	                                          + "where  re_level=0 "
	                                          + "and    com_type=?  order by ref desc, ref"
	                                          + "                               ) a"
	                     + "  )  "
	               + "where rn between ? and ?";
	      
	      System.out.println("sql=> "+sql);
	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setInt(1, com_type);
	         pstmt.setInt(2, startRow);
	         pstmt.setInt(3, endRow);
	         
	         rs=pstmt.executeQuery();

	         while(rs.next()) {
	            
	            Com com = new Com();
	            
	            com.setNum(rs.getInt("num"));
	            com.setUser_id(rs.getString("user_id"));
	            com.setCom_type(rs.getInt("com_type"));
	            com.setSubject(rs.getString("subject"));
	            com.setContent(rs.getString("content"));
	            com.setPoto(rs.getString("poto"));
	            com.setCount(rs.getInt("count"));
	            com.setRef(rs.getInt("ref"));
	            com.setRe_level(rs.getInt("re_level"));
	            com.setRe_step(rs.getInt("re_step"));
	            com.setReg_date(rs.getDate("reg_date"));
	            com.setReco(rs.getInt("reco"));
	            com.setRe_max_content(rs.getString("re_content2"));
	            com.setRe_user_id(rs.getString("user_id2"));
	            System.out.println(com.getRe_max_content());
	            reviewList.add(com);
	         }
	         
	      } catch (Exception e) {
	         System.out.println("reviewList error=>"+e.getMessage());
	      }finally {
	         if(rs!=null) rs.close();
	         if(pstmt!=null) pstmt.close();
	         if(conn!=null) conn.close();
	      }

	      return reviewList;
	   }// end of reviewList
	   
    public List<Com> rehashList(String inNumStr) throws SQLException {//해시태그리스트(민주)
	         List<Com> rehashList = new ArrayList<Com>();
	         Connection conn = null;
	         Statement stmt = null;
	         ResultSet rs = null;
	         String sql = " select h.num,h.h_seq, h.hsre_content "
	                     + " from  hash h, community c "
	                     + " where c.num = h.num and c.num IN " + inNumStr;
	         try {
	            conn = getConnection();
	           System.out.println("hashList sql->"+sql);
	          // stmt = conn.prepareStatement(sql);
	           stmt = conn.createStatement();
	            // pstmt.setString(1, inNumStr);
	           System.out.println("hashList inNumStr->"+inNumStr);
	            rs = stmt.executeQuery(sql);
	           System.out.println("hashList executeQuery After...");
	            while (rs.next()) {
	              Com com = new Com();
	               System.out.println("h_seq->"+rs.getInt("h_seq"));
	               com.setH_seq(rs.getInt("h_seq"));
	               System.out.println("hsre_content->"+rs.getString("hsre_content"));
	               com.setHsre_content(rs.getString("hsre_content"));
	               rehashList.add(com);
	               
	            }         
	         } catch (Exception e) {
	            System.out.println("e.getMessage()"+e.getMessage());
	         } finally {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	         }
	         
	         return rehashList;
	   }// end of rehashList
	   
    public int insert (Com com) throws SQLException {//게시글 insert메서드(민주)
	      int com_type = com.getCom_type();
	      
	      Connection conn = null;
	      int result = 0;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      String sql1 = "select nvl(max(num),0) from community";
	      String sql = "insert into community values (?,?,?,?,?,?,?,?,?,?,sysdate,?,?)";
	      try {
	         conn = getConnection();
	         pstmt = conn.prepareStatement(sql1);
	         rs=pstmt.executeQuery();
	         rs.next();
	         int number = rs.getInt(1)+1;
	         rs.close();
	         pstmt.close();
	         pstmt=conn.prepareStatement(sql);
	         pstmt.setInt(1, number);
	         pstmt.setString(2, com.getUser_id());
	         pstmt.setInt(3, com_type);
	         pstmt.setString(4, com.getSubject());
	         pstmt.setString(5, com.getContent());
	         pstmt.setString(6, com.getPoto());
	         pstmt.setInt(7, com.getCount());
	         pstmt.setInt(8, number);
	         pstmt.setInt(9, com.getRe_step());
	         pstmt.setInt(10, com.getRe_level());
//	         pstmt.setDate(10, number);
	         pstmt.setInt(11, com.getReco());
	         pstmt.setString(12, com.getRe_content());
	         result = pstmt.executeUpdate();
	         
	      } catch (Exception e) {
	         System.out.println("게시물 insert 에러-> "+e.getMessage());
	      }finally {
	         if( pstmt != null) pstmt.close();
	         if(conn != null) conn.close();
	      }
	      return result;
	   }// end of insert
	public int getComCnt(int num) throws SQLException {//댓글수를 세는 메서드 (민주)
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   int Cmtot = 0;
		   String sql = "select count(re_content) re_content from community where re_level>0 and  ref=?";
		   try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();			
				if(rs.next()) {
					Cmtot = rs.getInt(1);				
				}
				System.out.println("Cmtot->"+Cmtot);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} System.out.println("Cmtot->"+Cmtot);
			  System.out.println("comDao getComCnt sql->"+sql);
			return Cmtot;
				
	   
	   }//end of getComCnt
	
	public int delete(int num) {//게시글 삭제 메서드 (민주)
		int result = 0;
		Connection conn = null ;
		PreparedStatement pstmt = null;
		String sql = "delete from community where num = ?";
		System.out.println("delete sql 문 ===>"+sql);
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			System.out.println("result 결과 ====>"+ result);
			
		} catch (Exception e) {
			System.out.println("Delete 쿼리문에러 ---->?>>"+e.getMessage());
		}
		return result;
	 } // end of delete(int num)
	 
	public int commentUpdate(Com com) {//댓글 수정 메서드 (민주)
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update community set user_id = ?, re_content = ? where num = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("user_id->"+ com.getUser_id());
			System.out.println("re_content->"+ com.getRe_content());
			System.out.println("num --> "+ com.getNum());
			
			pstmt.setString(1, com.getUser_id());
			pstmt.setString(2, com.getRe_content());
			pstmt.setInt(3, com.getNum());
			
			result = pstmt.executeUpdate();
			
			System.out.println("댓글 수정 update 쿼리 결과 --->"+ result);
			
		} catch (Exception e) {
			System.out.println("CommentUpdate sql 문 에러!!!!=>"+e.getMessage());
		}
		return result;
	}//end of commentUpdate
	
	public String comtSelect (String com_type) throws SQLException {//커뮤니티 종목구분 숫자를 이름으로 만드는 메서드(민서)
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select con from common where bcd=100 and mcd=?";
		System.out.println("ComDAO comtSelect sql->"+sql);
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, com_type);
				System.out.println("ComDAO comtSelect com_type->"+com_type);
				rs=pstmt.executeQuery();
				while (rs.next()) {
					com_type = rs.getString("con");
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				System.out.println("------------comtSelect끝----------");
			}
			return com_type;
	}//end of comtSelect
	
	public Com contentSelect(String user_id) throws SQLException {//컨텐트용 셀렉트 메서드(민서)

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from community where user_id=? ";
		Com com2 = new Com();
		System.out.println("DAO contentSelect sql->"+sql);

		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				com2.setNum(rs.getInt("num"));
				com2.setUser_id(rs.getString("user_id"));
				com2.setCom_type(rs.getInt("com_type"));
				com2.setSubject(rs.getString("subject"));
				com2.setContent(rs.getString("content"));
				com2.setPoto(rs.getString("poto"));
				com2.setCount(rs.getInt("count"));
				com2.setRef(rs.getInt("ref"));
				com2.setRe_step(rs.getInt("re_step"));
				com2.setRe_level(rs.getInt("re_level"));
				com2.setReg_date(rs.getDate("reg_date"));
				com2.setReco(rs.getInt("reco"));
				com2.setRe_content(rs.getString("re_content"));
				System.out.println("CD contentSelect user_id->"+rs.getString("user_id"));
				System.out.println("CD contentSelect content->"+rs.getString("content"));
				System.out.println("ComDAO contentSelect com_type->"+rs.getInt("com_type"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			System.out.println("------------contentSelect끝----------");
		}return com2;
	}// end of contentSelect
}//end of class

