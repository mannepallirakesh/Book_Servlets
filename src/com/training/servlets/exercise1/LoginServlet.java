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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.jdbc.driver.OracleDriver;

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String userName = req.getParameter("userName");
		String passWord = req.getParameter("passWord");
		
		if(Authentication.validate(userName, passWord)){
			System.out.println("redirecting to loginsuccessservlet");
			res.sendRedirect("/servlets/htmls/menu.html");
			
			
		}
		else{
			out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
			out.println("<BODY>Your User Name and Password are invalid.<BR>");
			out.println("Please click here to try again<A HREF=\""
					+ req.getContextPath()
					+ "/htmls/LoginPage.html\">try again</A>");
			out.println("</BODY></HTML>");

		}
		
		out.close();
	}

}