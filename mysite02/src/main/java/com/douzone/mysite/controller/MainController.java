package com.douzone.mysite.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.action.main.MainActionFactory;
import com.douzone.web.action.Action;



public class MainController extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		Map<String,Object> map= new HashMap<>();
		
		@Override
		public void init() throws ServletException {
		
		String configPath=this.getServletConfig().getInitParameter("config");
		System.out.println("inint()called!!!!!!:"+configPath);
		
		
		
		
		//this.getServletContext().setAttribute("chche", map);
		
		super.init();
	}
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("service called");
		super.service(arg0, arg1);
	}

	

	


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		
		System.out.println("doget called");
		String actionName=request.getParameter("a");
		Action action = new MainActionFactory().getAction(actionName);
		action.execute(request,response);
		

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	@Override
		public void destroy() {
			System.out.println("destroy() called");
			super.destroy();
		}
}
