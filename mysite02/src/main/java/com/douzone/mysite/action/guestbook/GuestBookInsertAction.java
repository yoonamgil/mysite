package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class GuestBookInsertAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name=request.getParameter("name");
		String password=request.getParameter("pass");
		String message=request.getParameter("content");
		
		GuestBookVo vo= new GuestBookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setContents(message);
		
		
		if(!(vo.getName().isEmpty()||vo.getPassword().isEmpty()||vo.getContents().isEmpty()))
			new GuestBookRepository().insert(vo);
		WebUtil.redirect(request.getContextPath()+"/guestbook?a=list", request, response);
		
	}
	
}

