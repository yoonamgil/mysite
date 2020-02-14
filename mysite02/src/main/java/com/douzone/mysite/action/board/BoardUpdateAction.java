package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Long no= Long.parseLong(request.getParameter("no"));
		String  title = request.getParameter("title");
		String contents= request.getParameter("content");
		
		BoardVo vo=new BoardVo();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContents(contents);
		
		new BoardRepository().updateContents(vo);
		
	
		WebUtil.redirect(request.getContextPath()+"/board?a=list&num=0", request, response);
		

	}

}
