package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;


public class BoardRepository {
public List<BoardVo> findByBoard() {
		List<BoardVo> result = new ArrayList<>();
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs =null;
		try {
				
			conn= getConnection();
			String sql="select a.title, b.name, a.hit, a.reg_date, a.depth,a.no "
					+ " from board a, user b where a.user_no=b.no order by a.g_no desc, a.o_no desc";
			stmt=conn.prepareStatement(sql);
			
			
			rs= stmt.executeQuery();
			
			while(rs.next()) {
				String title= rs.getString(1);
				String userName= rs.getString(2);
				int hit=rs.getInt(3);
				String regDate=rs.getString(4);
				int depth=rs.getInt(5);
				Long no=rs.getLong(6);
				
				BoardVo vo = new BoardVo();
				vo.setTitle(title);
				vo.setUserName(userName);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setDepth(depth);
				vo.setNo(no);
				
				result.add(vo);
			}
				
		} catch(SQLException e) {
			System.out.println("error"+e);
		}finally {
			try {
				
				// 3. 자원 정리
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {	
			}
		}
			return result;
	}
public List<BoardVo> findByBoardTitle(String option) {
	List<BoardVo> result = new ArrayList<>();
	Connection conn=null;
	PreparedStatement stmt=null;
	ResultSet rs =null;
	String k="%"+option+"%";
	try {
			
		conn= getConnection();
		String sql="select a.title, b.name, a.hit, a.reg_date, a.depth,a.no, a.contents from board a, user b where a.user_no=b.no " + 
				" and a.title like ? " + 
				" order by a.g_no desc, a.o_no desc";
		stmt=conn.prepareStatement(sql);
		stmt.setString(1, k);
		
		rs= stmt.executeQuery();
		
		while(rs.next()) {
			String title= rs.getString(1);
			String userName= rs.getString(2);
			int hit=rs.getInt(3);
			String regDate=rs.getString(4);
			int depth=rs.getInt(5);
			Long no=rs.getLong(6);
			String contents=rs.getString(7);
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setUserName(userName);
			vo.setHit(hit);
			vo.setRegDate(regDate);
			vo.setDepth(depth);
			vo.setNo(no);
			vo.setContents(contents);
			if(!vo.getContents().isEmpty())
				result.add(vo);
		}
			
	} catch(SQLException e) {
		System.out.println("error"+e);
	}finally {
		try {
			
			// 3. 자원 정리
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
		}catch(SQLException e) {	
		}
	}
		return result;
}
public List<BoardVo> findByBoardUserName(String option) {
	List<BoardVo> result = new ArrayList<>();
	Connection conn=null;
	PreparedStatement stmt=null;
	ResultSet rs =null;
	String k="%"+option+"%";
	try {
			
		conn= getConnection();
		String sql="select a.title, b.name, a.hit, a.reg_date, a.depth,a.no  , a.contents from board a, user b where a.user_no=b.no and b.name like ? order by a.g_no desc, a.o_no desc";
		stmt=conn.prepareStatement(sql);
		stmt.setString(1, k);
		
		rs= stmt.executeQuery();
		
		while(rs.next()) {
			String title= rs.getString(1);
			String userName= rs.getString(2);
			int hit=rs.getInt(3);
			String regDate=rs.getString(4);
			int depth=rs.getInt(5);
			Long no=rs.getLong(6);
			String contents=rs.getString(7);
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setUserName(userName);
			vo.setHit(hit);
			vo.setRegDate(regDate);
			vo.setDepth(depth);
			vo.setNo(no);
			vo.setContents(contents);
			
			if(!vo.getContents().isEmpty())
			result.add(vo);
		}
			
	} catch(SQLException e) {
		System.out.println("error"+e);
	}finally {
		try {
			
			// 3. 자원 정리
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
		}catch(SQLException e) {	
		}
	}
		return result;
}
public BoardVo findByContents(Long vno) {
	BoardVo returnVo=null;
	Connection conn=null;
	PreparedStatement stmt=null;
	ResultSet rs =null;
	try {
			
		conn= getConnection();
		String sql="select a.title, b.name, a.hit, a.reg_date, a.depth, a.no,a.contents "
				+ " from board a, user b where a.user_no=b.no and a.no=? order by a.no desc";
		
		stmt=conn.prepareStatement(sql);
		stmt.setLong(1, vno);
		rs= stmt.executeQuery();
		
		while(rs.next()) {
			String title= rs.getString(1);
			String userName= rs.getString(2);
			int hit=rs.getInt(3);
			String regDate=rs.getString(4);
			int depth=rs.getInt(5);
			Long no=rs.getLong(6);
			String contents=rs.getString(7);
			
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setUserName(userName);
			vo.setHit(hit);
			vo.setRegDate(regDate);
			vo.setDepth(depth);
			vo.setNo(no);
			vo.setContents(contents);
			returnVo=vo;
			
			return returnVo;
		}
			
	} catch(SQLException e) {
		System.out.println("error"+e);
	}finally {
		try {
			
			// 3. 자원 정리
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
		}catch(SQLException e) {	
		}
	}
		return returnVo;
}


public BoardVo findByAll(Long vno) {
	BoardVo returnVo=null;
	Connection conn=null;
	PreparedStatement stmt=null;
	ResultSet rs =null;
	try {
			
		conn= getConnection();
		String sql="select a.*,b.name from board a ,user b where a.user_no=b.no and a.no= ?";
		
		stmt=conn.prepareStatement(sql);
		stmt.setLong(1, vno);
		rs= stmt.executeQuery();
		
		while(rs.next()) {
			Long no=rs.getLong(1);
			String title= rs.getString(2);
			String contents=rs.getString(3);
			int hit=rs.getInt(4);
			String regDate=rs.getString(5);
			int gNo=rs.getInt(6);
			int oNo=rs.getInt(7);
			int depth=rs.getInt(8);
			Long userNo=rs.getLong(9);
			String userName= rs.getString(10);
			
			

			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setUserName(userName);
			vo.setHit(hit);
			vo.setRegDate(regDate);
			vo.setDepth(depth);
			vo.setNo(no);
			vo.setContents(contents);
			vo.setUserNo(userNo);
			vo.setgNo(gNo);
			vo.setoNo(oNo);
			
			returnVo=vo;
			
			return returnVo;
		}
			
	} catch(SQLException e) {
		System.out.println("error"+e);
	}finally {
		try {
			
			// 3. 자원 정리
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
		}catch(SQLException e) {	
		}
	}
		return returnVo;
}



	
public int updateHit(Long vo) {
	
	int count = 0;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
		conn = getConnection();

		String sql = 
			" update board set hit=hit+1 where no=?";
		pstmt = conn.prepareStatement(sql);

	
		pstmt.setLong(1, vo);

		count = pstmt.executeUpdate();

	} catch (SQLException e) {
		System.out.println("error :" + e);
	} finally {
		// 자원 정리
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	return count;
}

public int updateContents(BoardVo vo) {
	
	int count = 0;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
		conn = getConnection();

		String sql = 
			" update board set title=?,contents=? ,reg_date= now() where no=?";
		pstmt = conn.prepareStatement(sql);

	
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContents());
		pstmt.setLong(3, vo.getNo());

		count = pstmt.executeUpdate();

	} catch (SQLException e) {
		System.out.println("error :" + e);
	} finally {
		// 자원 정리
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	return count;
}


public int update(BoardVo vo) {
	
	int count = 0;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
		conn = getConnection();

		String sql = 
			"update board set o_no=o_no+1 where g_no= ? and o_no >= ?";
		pstmt = conn.prepareStatement(sql);

	
		pstmt.setInt(1, vo.getgNo());
		pstmt.setInt(2, vo.getoNo());
		

		count = pstmt.executeUpdate();

	} catch (SQLException e) {
		System.out.println("error :" + e);
	} finally {
		// 자원 정리
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	return count;
}

public int delete(Long vo) {
	
	int count = 0;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
		conn = getConnection();

		String sql = 
			"update board set title ='(삭제 된 글입니다.)' , contents ='',hit=0  where no=?";
		pstmt = conn.prepareStatement(sql);

	
		pstmt.setLong(1, vo);

		count = pstmt.executeUpdate();

	} catch (SQLException e) {
		System.out.println("error :" + e);
	} finally {
		// 자원 정리
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	return count;
}
public int insert(BoardVo vo) {
	
	int count = 0;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
		conn = getConnection();

		String sql = 
			" insert into board "
			+ " values(null,?,?,0,now(),(select ifnull(max(a.g_no),0) from board a)+1,1,0,?)";
		pstmt = conn.prepareStatement(sql);

	
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContents());
		pstmt.setLong(3,vo.getUserNo());

		count = pstmt.executeUpdate();

	} catch (SQLException e) {
		System.out.println("error :" + e);
	} finally {
		// 자원 정리
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	return count;
}
public int insertContents(BoardVo vo) {
	
	int count = 0;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
		conn = getConnection();

		String sql = 
			" insert into board "
			+ " values(null,?,?,0,now(),?,?,?,?)";
		pstmt = conn.prepareStatement(sql);

	
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContents());
		pstmt.setInt(3, vo.getgNo());
		pstmt.setInt(4, vo.getoNo());
		
		pstmt.setInt(5, vo.getDepth()+1);
		
		pstmt.setLong(6,vo.getUserNo());

		count = pstmt.executeUpdate();

	} catch (SQLException e) {
		System.out.println("error :" + e);
	} finally {
		// 자원 정리
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	return count;
}



	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			//1. 드라이버 로딩
			Class.forName( "com.mysql.jdbc.Driver" );
			
			//2. 연결하기
			String url="jdbc:mysql://localhost/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch( ClassNotFoundException e ) {
			System.out.println( "드러이버 로딩 실패:" + e );
		} 
		
		return conn;
	}
	
}
