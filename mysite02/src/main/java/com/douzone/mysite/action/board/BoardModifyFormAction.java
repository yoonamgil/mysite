package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String no=request.getParameter("no");
		Long reno=Long.parseLong(no);
		
		BoardVo mvo =new BoardRepository().findByContents(reno);
		request.setAttribute("mvo", mvo);
		
		
		WebUtil.forward("/WEB-INF/views/board/modify.jsp",request,response);
	}

}
