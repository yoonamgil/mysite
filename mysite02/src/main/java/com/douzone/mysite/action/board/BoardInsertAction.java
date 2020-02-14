package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;


public class BoardInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		Long userNo =Long.parseLong(request.getParameter("authNo"));
		String sw = request.getParameter("truename");
		Long no =Long.parseLong(request.getParameter("no"));
		
		
		if("false".equals(sw)) {
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setUserNo(userNo);
		
			if(!(title.isEmpty()||contents.isEmpty())) {
				new BoardRepository().insert(vo);
			}
			
			response.sendRedirect(request.getContextPath()+"/board?a=list&num=0");
			//WebUtil.redirect(request.getContextPath(), request, response);
		}else {
			
			BoardVo newVo = new BoardRepository().findByAll(no);
			newVo.setTitle(title);
			newVo.setContents(contents);
			newVo.setUserNo(userNo);
			new BoardRepository().update(newVo);
			new BoardRepository().insertContents(newVo);
			
			
			WebUtil.redirect(request.getContextPath()+"/board?a=list&num=0", request, response);
			
			
			
			
			
		}
	}

}
