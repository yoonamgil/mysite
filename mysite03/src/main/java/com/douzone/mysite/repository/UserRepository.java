package com.douzone.mysite.repository;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
//	@Autowired
//	private DataSource dataSource;

	@Autowired
	private SqlSession sqlSession;

	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo);

	}

	public int update(UserVo vo) {
		return sqlSession.update("user.update", vo);

	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		return sqlSession.selectOne("user.findByEmailAndPassword", vo);

	}

	public UserVo findByEmailAndPassword(String email, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("e", email);
		map.put("p", password);

		return sqlSession.selectOne("user.findByEmailAndPassword", map);

	}

	public UserVo findByNo(Long authUserNo) {
		return sqlSession.selectOne("user.findByNo", authUserNo);

	}
	
// private Connection getConnection() throws SQLException {
//	Connection conn=null;
//	try {
//		// 1. JDBC Driver(My SQL) 로딩 
//	Class.forName("org.mariadb.jdbc.Driver");
//	
//	// 2. 연결하기 
//	String url="jdbc:mysql://192.168.1.107:3307/webdb";
//		conn =DriverManager.getConnection(url,"webdb","webdb");
//	}catch (ClassNotFoundException e) {
//		// TODO Auto-generated catch block
//		throw new UserRepositoryException("드라이버 로딩 실패"+e);
//	}
//	
//	return conn;
//	
//}	
}
