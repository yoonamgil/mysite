package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVo;

@Repository
public class AdminRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public SiteVo findByAll() {
		
		return sqlSession.selectOne("admin.findByAll");
	}

	public void update(SiteVo siteVo) {
		System.out.println("_____________"+siteVo);
		sqlSession.update("admin.update",siteVo);
		
		
	}

}
