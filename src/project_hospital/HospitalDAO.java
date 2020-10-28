package project_hospital;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.conn.DBConn;

public class HospitalDAO extends DBConn {
	/*
	 * 미용등록
	 */
	public boolean insert(UserVO vo) {
		boolean result = false;
		
		try {
			String sql = "insert into SALONRES values(?,?,?,?,?,?,?,?)";
			
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getSno());
			pstmt.setString(2, vo.getSyear());
			pstmt.setString(3, vo.getSmonth());
			pstmt.setString(4, vo.getSday());
			pstmt.setString(5, vo.getStime());
			pstmt.setString(6, vo.getSgender());
			pstmt.setString(7, vo.getStext());
			pstmt.setString(8, vo.getSmid());
			
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* 
	 * 병원 예약 등록 
	 */
	public boolean Hospital_reserve(UserVO vo) {
		boolean result = false;
		
		try {
			
			String sql = "insert into hbooking values(?,?,?,?,?,?,?)";
			
			getPreparedStatement(sql);
			
			pstmt.setString(1, vo.getHno());
			pstmt.setString(2, vo.getHsymptom());
			pstmt.setString(3, vo.getHyear());
			pstmt.setString(4, vo.getHmonth());
			pstmt.setString(5, vo.getHday());
			pstmt.setString(6, vo.getHtime());
			pstmt.setString(7, vo.getHmid());
			
			
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/** * 전체조회 병원편
	 * */
	public ArrayList<UserVO> hselectall (String id){
			
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		
		try {
			String sql ="select hno, mname, hyear,hmonth,hday , htime from hbooking h, member m where h.mid = m.mid and h.mid = ?";
			
			//getStatement();
			//rs = stmt.executeQuery(sql);
			
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				UserVO vo = new UserVO();
				
				vo.setHno(rs.getString(1));
				vo.setMname(rs.getString(2));
				vo.setHyear(rs.getString(3));
				vo.setHmonth(rs.getString(4));
				vo.setHday(rs.getString(5));
				vo.setHtime(rs.getString(6));
				
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		 return list;
	}//select
	
	/** 전체조회 미용편 
	 *
	 */
	public ArrayList<UserVO> sselectall (String id){
		
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		
		try {
			String sql ="select sno, mname, syear, smonth,sday , stime from SALONRES s, member m where s.mid = m.mid and s.mid = ?";
			
			//getStatement();
			//rs = stmt.executeQuery(sql);
			
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				UserVO vo = new UserVO();
				
				vo.setSno(rs.getString(1));
				vo.setMname(rs.getString(2));
				vo.setSyear(rs.getString(3));
				vo.setSmonth(rs.getString(4));
				vo.setSday(rs.getString(5));
				vo.setStime(rs.getString(6));
				
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		 return list;
	}//select
		
	/** 멤버 전체 조회 */
	public ArrayList<UserVO> memberSelect(){
			
		ArrayList<UserVO> memlist = new ArrayList<UserVO>();
		
		try {
			String sql ="select mid, mpass, mphone, mname, mkind from member";
			
			getPreparedStatement(sql);
			rs=pstmt.executeQuery(sql);
			
			while(rs.next()) {
				UserVO vo = new UserVO();
				
				vo.setMid(rs.getString(1));
				vo.setMpass(rs.getString(2));
				vo.setMphone(rs.getString(3));
				vo.setMname(rs.getString(4));
				vo.setMkind(rs.getString(5));
				
				memlist.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		 return memlist;
	}//select

	/** 병원예약번호 검색
	 *  */
		public int hsearch(String hno) {

			int result = 0;
		
			try {
				String sql = "select count(*) from hbooking where hno = ? ";
				getPreparedStatement(sql);
				
				pstmt.setString(1, hno);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					result = rs.getInt(1);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		
		/** 미용예약번호 검색
		 *  */
			public int ssearch(String sno) {
				int result = 0;
			
				try {
					String sql = "select count(*) from SALONRES where sno = ? ";
					getPreparedStatement(sql);
					
					pstmt.setString(1, sno);
					
					rs=pstmt.executeQuery();
					
					if(rs.next()) {
						result = rs.getInt(1);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return result;
			}
		
		
		
		/** 병원 수정
		 *  */
		public boolean hupdate(UserVO vo) {
			boolean result = false;
			
			try {
				
				String sql="update hbooking set hyear =? ,hmonth =? , hday =?, htime =? where hno =?";
				getPreparedStatement(sql);
				
				pstmt.setString(1, vo.getHyear());
				pstmt.setString(2, vo.getHmonth());
				pstmt.setString(3, vo.getHday());
				pstmt.setString(4, vo.getHtime());
				pstmt.setString(5, vo.getHno());

				System.out.println(pstmt.executeUpdate());
				
				int count = pstmt.executeUpdate();
				if(count != 0) result = true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return result;
		}
		
		/** 미용수정
		 *  */
		public boolean supdate(UserVO vo) {
			boolean result = false;
			
			try {
				
				String sql="update SALONRES set syear =? ,smonth =? , sday =?, stime =? where sno =?";
				getPreparedStatement(sql);
				
			
				pstmt.setString(1, vo.getSyear());
				pstmt.setString(2, vo.getSmonth());
				pstmt.setString(3, vo.getSday());
				pstmt.setString(4, vo.getStime());
				pstmt.setString(5, vo.getSno());

				
				System.out.println(pstmt.executeUpdate());
				
				int count = pstmt.executeUpdate();
				if(count != 0) result = true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return result;
		}
		
		/** 병원 예약정보 조회
		 *  */
		public UserVO hselect(String hno) {
			UserVO vo = new UserVO();
			try {
				String sql = "select mname, hyear,hmonth,hday from hbooking h ,member m where m.mid = h.mid and hno =?";
				getPreparedStatement(sql);
				
				pstmt.setString(1, hno);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					vo.setMname(rs.getString(1));
					vo.setHyear(rs.getString(2));
					vo.setHmonth(rs.getString(3));
					vo.setHday(rs.getString(4));
				}
				
		
			} catch (Exception e) {
				e.printStackTrace();
			}
			return vo;
		}
		
		/** 미용정보 조회
		 *  */
		public UserVO sselect(String sno) {
			UserVO vo = new UserVO();
			try {
				String sql = "select mname, syear,smonth,sday from SALONRES s ,member m where m.mid = s.mid and sno =?";
				getPreparedStatement(sql);
				
				pstmt.setString(1, sno);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					vo.setMname(rs.getString(1));
					vo.setSyear(rs.getString(2));
					vo.setSmonth(rs.getString(3));
					vo.setSday(rs.getString(4));
				}
				
		
			} catch (Exception e) {
				e.printStackTrace();
			}
			return vo;
		}
		
		/** 예약삭제 병원예약 
		 * */
		public boolean hdelete(String hno) {
			boolean result = false;
			
			try {
				String sql = "delete from hbooking where hno = ?";
				getPreparedStatement(sql);
				pstmt.setString(1, hno);
				int count = pstmt.executeUpdate();
				
				if(count!=0) result =true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}


		/** 예약삭제 미용예약
		 *
		 **/
		public boolean sdelete(String sno) {
			boolean result = false;
			
			try {
				String sql = "delete from SALONRES where sno = ?";
				getPreparedStatement(sql);
				pstmt.setString(1, sno);
				int count = pstmt.executeUpdate();
				
				if(count!=0) result =true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		
		/**내정보 조회
		 * */
		public UserVO mselect(String mid) {
			UserVO vo = new UserVO();
			try {
				String sql = "select mid,mpass,mphone,mname,mkind from member where mid = ?";
				getPreparedStatement(sql);
				pstmt.setString(1, mid);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					vo.setMid(rs.getString(1));
					vo.setMpass(rs.getString(2));
					vo.setMphone(rs.getString(3));
					vo.setMname(rs.getString(4));
					vo.setMkind(rs.getString(5));	
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return vo;
		}
		
		/** 내정보 수정
		 */
		public boolean memberupdate(UserVO vo) {
			boolean result = false;
			
			try {
				String sql = "update member set mid = ?, mpass=? ,mphone=?, mname =? , mkind =? where mid =? ";
				getPreparedStatement(sql);
				
				pstmt.setString(1, vo.getMid());
				pstmt.setString(2, vo.getMpass());
				pstmt.setString(3, vo.getMphone());
				pstmt.setString(4, vo.getMname());
				pstmt.setString(5, vo.getMkind());
				pstmt.setString(6, vo.getMid());
				
				System.out.println(pstmt.executeUpdate());
				
				int count = pstmt.executeUpdate();
				if(count != 0) result = true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return result;
		}
		
		/** 로그인 
		 * */
		public boolean login(String id, String pass) {
			boolean result = false;
			
			try {
				String sql = "select count(mid) from member where mid =? and mpass = ?";
				getPreparedStatement(sql);
				
				pstmt.setString(1, id);
				pstmt.setString(2, pass);
				
				rs = pstmt.executeQuery();
				
				int count = 0;
				
				if(rs.next()) {
					count=rs.getInt(1);
				}
				if(count != 0) result = true;
				
			} catch (Exception e) {
				e.printStackTrace();
		}
		return result;
	}
		
	/** 회원 로그인 -- 시작할 때 **/
	public boolean memlogin(String id, String pass) {
		boolean result = false;
			
		try {
			String sql = "SELECT COUNT(MID) FROM MEMBER WHERE MID=? AND MPASS=? ";
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			int count = 0;
			if(rs.next()) {
				count=rs.getInt(1);
			}
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
				
			
	/** 매니저 로그인 -- 시작할 때*/
	public boolean manlogin(String id, String pass) {
		boolean result = false;
			
		try {
			String sql = "select count(sid) from manager where sid=? and spass=? "; 
			getPreparedStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
				
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) != 0) 
					result = true;
		
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return result;
	}
		
	/** 회원가입 -- 시작할 때**/
	public boolean register(UserVO vo) {
		boolean result = false;
		
		try {
		//아이디가 있을경우
		//아이디가 없을경우
			String sql = "insert into member values(?,?,?,?,?) "; 
			getPreparedStatement(sql);
			
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpass());
			pstmt.setString(3, vo.getMphone());
			pstmt.setString(4, vo.getMname());
			pstmt.setString(5, vo.getMkind());
					
			pstmt.executeUpdate();
			result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
		
	/** delSelect(String mid) **/
	public boolean delSelect(String mid) {
		boolean result = false;
		
		try {
			String sql = "SELECT COUNT(*) FROM MEMBER WHERE MID=?";
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1) != 0) result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
		
	/** delete(String mid) **/
	public boolean delete(String mid) {
		boolean result = false;
		
		try {
			String sql = "DELETE FROM MEMBER WHERE MID=?";
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			int count = pstmt.executeUpdate();
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
