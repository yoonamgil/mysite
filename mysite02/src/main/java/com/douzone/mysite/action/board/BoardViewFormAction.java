package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardViewFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String userName = request.getParameter("name");
		Long no=Long.parseLong(request.getParameter("no"));
		
		new BoardRepository().updateHit(no);
		BoardVo viewVo= new BoardRepository().findByContents(no);
		viewVo.setUserName(userName);
		viewVo.setNo(no);
		request.setAttribute("viewVo", viewVo);
		
		WebUtil.forward("/WEB-INF/views/board/view.jsp",request,response);
	}

}
