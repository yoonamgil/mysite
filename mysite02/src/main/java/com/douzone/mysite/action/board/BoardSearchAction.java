package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String option=request.getParameter("option");
		String kwd=request.getParameter("kwd");
		String num=request.getParameter("num");
		List<BoardVo> list;
		if("true".equals(option)) {
		 list = new BoardRepository().findByBoardTitle(kwd);
		}else {
		 list = new BoardRepository().findByBoardUserName(kwd);
		}
		
		request.setAttribute("boardlist", list);
		request.setAttribute("num", num);
		request.setAttribute("kwd", kwd);
		request.setAttribute("option", option);
		
		WebUtil.forward("/WEB-INF/views/board/list.jsp",request,response);
		
	}

}
