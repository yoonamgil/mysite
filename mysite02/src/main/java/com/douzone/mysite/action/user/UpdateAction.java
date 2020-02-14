package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String password =request.getParameter("password");
		String gender = request.getParameter("gender");
		
		Long reNo=Long.parseLong(no);
		
		UserVo userVo = new UserVo();
		userVo.setNo(reNo);
		userVo.setName(name);
		userVo.setPassword(password);
		userVo.setGender(gender);
		
		new UserRepository().update(userVo);
		
		WebUtil.redirect(request.getContextPath() , request, response);

	}

}
