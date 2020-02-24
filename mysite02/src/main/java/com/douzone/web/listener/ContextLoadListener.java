package com.douzone.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


//@WebListener
public class ContextLoadListener implements ServletContextListener {
		
	// 어플리케이션에서 초기화 하는 것 
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    		ServletContext context= servletContextEvent.getServletContext();
    		String contextConfigLocation = context.getInitParameter("contextConfigLocation");
    		
        	System.out.println("Application starts...."+contextConfigLocation );
    }
	
    
	// 서블리에서 초기화하는것
    public void contextDestroyed(ServletContextEvent arg0)  { 
    
    }

	
}
