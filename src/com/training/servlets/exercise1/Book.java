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

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.jdbc.driver.OracleDriver;

public class Book extends HttpServlet{

	public void  doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		  String bookOperation = req.getParameter("options");
		  
		     if ("insert".equals(bookOperation)){
		    	
		    	System.out.println("redirecting to insert");
				res.sendRedirect("/servlets/Insert");
		    }
		    
		    if ("update".equals(bookOperation)){
		    	
		    	System.out.println("redirecting to update");
				res.sendRedirect("/servlets/UpdateIntake");
		    	
		    }
		    
		    if ("delete".equals(bookOperation)){
		    	
		    	System.out.println("redirecting to delete");
				res.sendRedirect("/servlets/DeleteIntake");
		    	
		    }
		    
		    if ("showbook".equals(bookOperation)){
		    	
		    	System.out.println("redirecting to showbook");
				res.sendRedirect("/servlets/htmls/showbook.html");
		    	
		    }
		    
		    if ("showall".equals(bookOperation)){
		    	
		    	System.out.println("redirecting to showall");
				res.sendRedirect("/servlets/ShowAll");
		    	
		    }
		    
		    if ("exit".equals(bookOperation)){
		    	
		    	System.out.println("redirecting to exit");
				res.sendRedirect("/servlets/htmls/exit.html");
		    	
		    }
		    }
		 

//	private void elseif(boolean equals) {
		// TODO Auto-generated method stub
		
//	}
	
	 

}

