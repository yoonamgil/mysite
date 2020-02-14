package com.douzone.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;



//@WebFilter("/*")
public class EncodingFilter implements Filter {

	private String encoding;
  
		public void init(FilterConfig fConfig) throws ServletException {
			fConfig.getInitParameter("encoding");
			if(encoding==null) {
				encoding="UTF-8";
			}
		System.out.println("encodinf filter Initialized...");
	}
	

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//request 처리 
		
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
		
		//response 처리 
		
	}

	public void destroy() {
			// TODO Auto-generated method stub
		}


}
