package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {

	@Autowired
	private GuestBookService guestBookService;
	
	@RequestMapping(value="list")
	public String list(Model model) {
		List<GuestBookVo> list=guestBookService.guestBookList();
		model.addAttribute("list",list);
		
		return "guestbook/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(GuestBookVo vo) {
		
		guestBookService.add(vo);
		
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete( Long no) {
		
	
		return "guestbook/delete";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(GuestBookVo vo) {
		
		guestBookService.delete(vo);
		return "redirect:/guestbook/list";
	}
	@ExceptionHandler(Exception.class)
	public String handleException() {
			return "error/exception";
	}
	
}
