package com.douzone.mysite.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardservice;
	
	@RequestMapping("")
	public String list(Model model, 
					   @RequestParam(value="page", required=true,defaultValue="0")int page,
					   @RequestParam(value="kwd", required=true,defaultValue="")String kwd,
					   @RequestParam(value="option", required=true,defaultValue="")String option
					   ) {
		Map<String,Object> map = boardservice.getList(page,kwd,option);
		model.addAllAttributes(map);
		return "board/list";
	}
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable(value="no") Long no,
						Model model
					   ) {
		
		boardservice.updateHit(no);
		BoardVo vo= boardservice.findByContents(no);
		model.addAttribute("viewVo",vo);
		return "board/view";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable(value="no") Long no,HttpSession session
					   ) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		System.out.println(authUser.getNo());
		BoardVo vo= boardservice.findByContents(no);
		System.out.println(vo.getUserNo());
		if((authUser == null) || (authUser.getNo() != vo.getUserNo()) ) {
			return "redirect:/";
		}
		
		boardservice.deleteBoard(no);
		return "redirect:/board";
	}
	
	@RequestMapping("/modify/{no}")
	public String modify(@PathVariable(value="no") Long no,
						HttpSession session,
						Model model
					   ) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		BoardVo vo= boardservice.findByContents(no);
		
		
		if((authUser == null) || (authUser.getNo() != vo.getUserNo()) ) {
			return "redirect:/";
		}
		
		model.addAttribute("mvo", vo);
		
		return "board/modify";
	}
	
	@RequestMapping(value="/modify/{no}", method=RequestMethod.POST )
	public String modify(@PathVariable(value="no") Long no,
						 BoardVo vo
					   ) {
		vo.setNo(no);
		boardservice.updateContests(vo);
		
		
		return "redirect:/board";
	}
	
	@RequestMapping("/write")
	public String write(HttpSession session
					   ) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			return "redirect:/";
		}
		
		return "board/write";
	}
	
	
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String write(HttpSession session, BoardVo vo , 
						@RequestParam(value="no", required=true, defaultValue="0")Long no
					   ) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			return "redirect:/";
		}
		
		if(no== 0) {
			vo.setUserNo(authUser.getNo());
			boardservice.insertList(vo);
		}else {
			BoardVo newVo = boardservice.findAll(no);
			newVo.setTitle(vo.getTitle());
			newVo.setContents(vo.getContents());
			newVo.setUserNo(authUser.getNo());
			boardservice.update(newVo);
			boardservice.insertContents(newVo);
		}
		
		return "redirect:/board";
	}
	
	@RequestMapping("/reply/{no}")
	public String reply(@PathVariable(value="no") Long no,
						HttpSession session
					   ) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		return "board/write";
	}
	
	
	
	
	
}
