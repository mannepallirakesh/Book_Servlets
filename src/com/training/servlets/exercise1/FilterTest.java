package com.training.servlets.exercise1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.*;
import java.sql.Driver;
import java.sql.Statement;
import java.util.Scanner;
import javax.servlet.Filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.jdbc.driver.OracleDriver;

public class FilterTest  implements Filter {

	
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			    throws IOException, ServletException 
			  {
				       
			    
			    
			    HttpServletRequest httpRequest = (HttpServletRequest)request;
				  
			    HttpSession session = httpRequest.getSession(false);
				   
			    if(session == null || (session != null && session.getAttribute("LoggedInUser") == null))
			    {   System.out.println("User not logged in or session expired.");
			 	   RequestDispatcher dispacher = httpRequest.getRequestDispatcher("/htmls/LoginPage.html");
			 	   dispacher.forward(request, response);
			    	 
			    }else{    	
			    	System.out.println("httpRequest.getRequestURL() "+httpRequest.getRequestURL());
			    	System.out.println("httpRequest.getServletPath() "+httpRequest.getServletPath());
			    	System.out.println("httpRequest.getServerName() "+httpRequest.getServerName());
			    	
			    	 System.out.println("Once the filters verified it will take you to this forwarding page.");
			 	   RequestDispatcher dispacher = httpRequest.getRequestDispatcher(httpRequest.getServletPath());
			 	   dispacher.forward(request, response);
			    }
			    
			  }
		public void destroy() 
		  { 
		  }

		  public void init(FilterConfig filterConfig) 
		  {
			  System.out.println( filterConfig.getInitParameter("userId"));
		  }
		  

		
		
	}
		
	
		
		
	