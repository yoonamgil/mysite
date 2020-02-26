package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;

	public List<BoardVo> boardList(String kwd, String option,int page) {

		return boardRepository.findByBoard(kwd, option,page);
	}

	public void updateHit(Long no) {

		boardRepository.updateHit(no);
	}

	public BoardVo findByContents(Long no) {

		return boardRepository.findByContents(no);
	}

	public void deleteBoard(Long no) {

		boardRepository.delete(no);

	}

	public void updateContests(BoardVo vo) {

		boardRepository.updateContents(vo);
	}

	public void insertList(BoardVo vo) {
		boardRepository.insert(vo);

	}

	public BoardVo findAll(Long no) {
		return boardRepository.findByAll(no);

	}

	public void update(BoardVo vo) {
		boardRepository.update(vo);

	}

	public void insertContents(BoardVo vo) {
		boardRepository.insertContents(vo);

	}


	public Map<String, Object> getList(int page, String kwd, String option) {
		Map<String, Object> map = new HashMap<>();
		List<BoardVo> list = boardList(kwd,option,page);
		int count = boardRepository.totalCount(kwd,option);
		double limit = ((double)count/10.0);
		
	
		System.out.println(page*10);
		
		System.out.println(list.size());
		map.put("boardlist",list);
		map.put("kwd",kwd);
		map.put("option",option);
		map.put("currentPage",page);
		map.put("count",count);
		map.put("start",page*10);
		map.put("startPage",(page/5)*5);
		map.put("endPage",(page/5)*5+4);
		map.put("limit",limit);
		map.put("nextPage",page+1);
		map.put("prevPage",page-1);
		

		return map;
	}

}
