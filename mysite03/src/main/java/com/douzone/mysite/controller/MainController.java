package com.douzone.mysite.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.service.MainService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.mysite.vo.UserVo;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@RequestMapping({"/","/main"})
	public String index(Model model) {
		
		
		SiteVo vo= new SiteVo();
		vo= mainService.findByAll();
		model.addAttribute("vo",vo);
		return "/main/index";
	}
	
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "유남길";
	}
}
