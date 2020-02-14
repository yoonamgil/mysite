package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class GuestBookDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		String no=request.getParameter("no");
		String password=request.getParameter("password");
		Long reno=Long.parseLong(no);
		
		GuestBookVo vo= new GuestBookVo();
		vo.setNo(reno);
		vo.setPassword(password);
		
		new GuestBookRepository().delete(vo);
		WebUtil.redirect(request.getContextPath()+"/guestbook?a=list", request, response);
	}

}
