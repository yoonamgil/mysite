package com.douzone.mysite.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.MainService;
import com.douzone.mysite.vo.SiteVo;

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
}
