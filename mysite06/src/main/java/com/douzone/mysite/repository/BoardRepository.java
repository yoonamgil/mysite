package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.BoardRepositoryException;
import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> findByBoard(String kwd, String option,int page) {
		Map<String, Object> map = new HashMap<>();
		Integer forstart = page*10;
		map.put("kwd", kwd);
		map.put("option", option);
		map.put("forstart", forstart);
	
		List<BoardVo> result = sqlSession.selectList("board.findByBoard", map);
		return result;
	}

	public int totalCount (String kwd,String option) {
		Map<String, Object> map = new HashMap<>();
		map.put("kwd", kwd);
		map.put("option", option);
		return sqlSession.selectOne("board.totalCount",map);
	}
	

	public BoardVo findByContents(Long no) {
		return sqlSession.selectOne("board.findByContents", no);
	}

	public BoardVo findByAll(Long no) {
		return sqlSession.selectOne("board.findByAll", no);
	}

	public void updateHit(Long no) {
		sqlSession.update("board.updateHit", no);
	}

//
	public void updateContents(BoardVo vo) {
		sqlSession.update("board.updateContents",vo);
		
	}

	public void update(BoardVo vo) {
		sqlSession.update("board.update",vo);
		
	}

//
	public void delete(Long no){
		sqlSession.update("board.delete",no);
	}

//
	public void insert(BoardVo vo) {
		sqlSession.insert("board.insert",vo);
		
	}

	public void insertContents(BoardVo vo) {
		vo.setDepth(vo.getDepth()+1);
		sqlSession.insert("board.insertContents",vo);
		
		
		
	}

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
//			throw new BoardRepositoryException("드라이버 로딩 실패" + e);
//		}
//
//		return conn;
//
//	}

}
