package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardservice;
	
	@RequestMapping(value="/list/{num}")
	public String list(Model model, 
					   @PathVariable("num")Long num) {
		List<BoardVo> list = boardservice.boardList();
		model.addAttribute("boardlist",list);
		
		
		return "board/list";
	}
	
	
	
	
	
	
	
}
