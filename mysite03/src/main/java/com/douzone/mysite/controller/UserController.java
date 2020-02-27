package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String join() {

		return "user/join";
	}

	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String join(UserVo vo) {
		System.out.println(vo);
		userService.join(vo);
		System.out.println(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

//	@RequestMapping(value="/login",method=RequestMethod.POST)
//	public String login(HttpSession session,@ModelAttribute UserVo vo) {
//		
//		UserVo authUser = userService.getUser(vo);
//		
//		if(authUser== null) {
//			return "user/login";
//		}
//		
//		session.setAttribute("authUser", authUser);
//	
//		return "redirect:/";
//	}

//	@RequestMapping(value="/logout")
//	public String login(HttpSession session) { // 접근제어를 행함 
//		UserVo authUser = (UserVo)session.getAttribute("authUser");
//		/////////////////////////////////접근제어 //////////////////////////////
//		if(authUser == null) {
//			return "redirect:/";
//		}
//		/////////////////////////////////////////////////////////////////////
//		
//		session.removeAttribute("authUser");
//		session.invalidate();
//	
//		return "redirect:/";
//	}

	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model, HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		Long no = authUser.getNo();
		UserVo vo = userService.getUser(no);

		model.addAttribute("userVo", vo);
		return "user/update";
	}

	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpSession session, UserVo userVo) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		userVo.setNo(authUser.getNo());
		userService.update(userVo);
		session.setAttribute("authUser", userVo);

		return "redirect:/";
	}

//	@ExceptionHandler(Exception.class)
//	public String handleException() {
//			return "error/exception";
//	}

}
