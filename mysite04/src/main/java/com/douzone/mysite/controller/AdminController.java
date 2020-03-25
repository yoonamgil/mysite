package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;


@Auth("ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("")
	public String main(Model model) {
		
		SiteVo siteVo= new SiteVo();
		siteVo= adminService.findByAll();
		model.addAttribute("siteVo",siteVo);
		
		return "admin/main";
	}
	
	@RequestMapping(value = "",method=RequestMethod.POST)
	public String update(@RequestParam (value="welcomeMessage", required= true)String welcomeMessage,
			@RequestParam (value="description", required= true)String description,
			@RequestParam (value="title", required= true)String title,
			@RequestParam (value="file1",required= true)MultipartFile file1) {
			
		
			String url = adminService.restore(file1);
			SiteVo siteVo=new SiteVo();
			siteVo.setTitle(title);
			if(file1.isEmpty()) {
				SiteVo newSiteVo= new SiteVo();
				newSiteVo=adminService.findByAll();
				siteVo.setProfile(newSiteVo.getProfile());
			}else {
				
				siteVo.setProfile(url);
			}
			siteVo.setWelcomeMassage(welcomeMessage);
			siteVo.setDescription(description);
			System.out.println(siteVo);
			adminService.update(siteVo);
		
		return "redirect:/";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
}
