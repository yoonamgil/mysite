package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.douzone.mysite.vo.GuestBookVo;

@Repository
public class GuestBookRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<GuestBookVo> findAll() {
		List<GuestBookVo> result = sqlSession.selectList("guestbook.findByAll");
		return result;

	}

	public int insert(GuestBookVo guestBookVo) {
		return sqlSession.insert("guestbook.insert", guestBookVo);

	}

	public int delete(GuestBookVo guestBookVo) {
		return sqlSession.delete("guestbook.delete",guestBookVo);
		
	
	}
//
//	private Connection getConnection() throws SQLException {
//		Connection conn = null;
//		try {
//			// 1. JDBC Driver(My SQL) 로딩
//			Class.forName("org.mariadb.jdbc.Driver");
//
//			// 2. 연결하기
//			String url = "jdbc:mysql://192.168.1.107:3307/webdb";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//		} catch (ClassNotFoundException e) {
//
//			throw new GuestBookRepositoryException("드라이버 로딩 실패" + e);
//		}
//
//		return conn;
//
//	}

}
