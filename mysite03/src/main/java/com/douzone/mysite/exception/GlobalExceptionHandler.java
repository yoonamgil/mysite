package com.douzone.mysite.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public void HandleeException(HttpServletRequest request,
								 HttpServletResponse response,
								 Exception e) throws ServletException, IOException {
		
		//1. 로깅(logginng)
			
			StringWriter errors = new StringWriter(); // 버퍼 
			e.printStackTrace(new PrintWriter(errors));
			
			//LOGGER.error(errors.toString());
			
		//2. 안내페이지 가기(정상종료)
			request.setAttribute("exception", errors.toString());
			request
				.getRequestDispatcher("/WEB-INF/views/error/exception.jsp")
				.forward(request, response);
			
		
	}
}
