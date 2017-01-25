package com.training.servlets.exercise1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
	String logoutOption = req.getParameter("options");
					
		 if ("yes".equals(logoutOption)){
		    	
		    	System.out.println("loggingout");
				
				HttpSession httpSession = null;
				
				
		    }
		 
		 if ("no".equals(logoutOption)){
		    	
		    	System.out.println("redirecting to update");
				res.sendRedirect("/servlets/htmls/menu.html");
		    	
		    }
		 
	}
	
	
}