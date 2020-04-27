package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.GuestBookVo;



public class GuestBookRepository {
	
public List<GuestBookVo> findAll() {
		
		List<GuestBookVo> result = new ArrayList<>();
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs =null;
		try {
				
			conn= getConnection();
			String sql="select no,name,contents,password,date_format(reg_date,'%Y-%m-%d')as reg_date "
					+ " from guestbook order by no desc";
			stmt=conn.prepareStatement(sql);
			rs= stmt.executeQuery();
			
			while(rs.next()) {
				Long no=rs.getLong(1);
				String name= rs.getString(2);
				String contents= rs.getString(3);
				String password=rs.getString(4);
				String regDate=rs.getString(5);
				
				
				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContents(contents);
				vo.setPassword(password);
				vo.setRegDate(regDate);
				
				
	
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
	
	public Boolean insert(GuestBookVo GuestBookVo) {
		Boolean result=	null;
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn= getConnection();
			
			String sql="insert into guestbook values(null,?,?,?,now())";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, GuestBookVo.getName());
			stmt.setString(2, GuestBookVo.getContents());
			stmt.setString(3, GuestBookVo.getPassword().trim());

			int count=stmt.executeUpdate();
			result = count ==1;	
			
		}catch(SQLException e) {
			System.out.println("error"+e);
		}finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {	
			}
		}
			return result ;
	}
	
	public Boolean delete(GuestBookVo GuestBookVo) {
		Boolean result=	null;
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn= getConnection();
			
			String sql="delete from guestbook where no=?\r\n" + 
					"and password=?";
			stmt=conn.prepareStatement(sql);
			stmt.setLong(1, GuestBookVo.getNo());
			stmt.setString(2, GuestBookVo.getPassword());

			int count=stmt.executeUpdate();
			result = count ==1;	
			
		}catch(SQLException e) {
			System.out.println("error"+e);
		}finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {	
			}
		}
			return result ;
	}
	private Connection getConnection() throws SQLException {
		Connection conn=null;
		try {
			// 1. JDBC Driver(My SQL) 로딩 
		Class.forName("org.mariadb.jdbc.Driver");
		
		// 2. 연결하기 
		String url="jdbc:mysql://192.168.1.15:3306/webdb";
			conn =DriverManager.getConnection(url,"webdb","webdb");
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("드라이버 로딩 실패"+ e);
		}
		
		return conn;
		
	}	
	
}
