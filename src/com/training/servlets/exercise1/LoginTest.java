package com.training.servlets.exercise1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginTest extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		this.checkUser(req,res);
	}
	
	private void checkUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String userName = req.getParameter("userName");
		String passWord = req.getParameter("passWord");

		
			
		if (authenticateUser(userName, passWord, req)) 
		{	//When we are forwarding internally it will not call Filter, but when we hit from browser then it will call Filter.
			req.getRequestDispatcher("/htmls/menu.html").forward(req, res);

		} else 
		{
			out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
			out.println("<BODY>Your User Name and Password are invalid.<BR>");
			out.println("Please click here to try again<A HREF=\"" + req.getContextPath() +"/htmls/LoginPage.html\">try again</A>");
			out.println("</BODY></HTML>");
			//if you want you can create another servlet and call to show as a login failure page
			//res.sendRedirect("/Servlets/LoginFailure");	    	

		}
	
		
		
		
	}

	private boolean authenticateUser(String userName, String passWord, HttpServletRequest request) 
	{
		System.out.println("-----Your login credentials are: " + userName + ", " + passWord);
		
		boolean isUserExist = false;
		
			

		if(userName != null && passWord != null)	
		{
			//Connect to Database and check whether the user exist or not in the list.
			if(Authentication.validate(userName, passWord)){
				System.out.println("redirecting to loginsuccessservlet");
			
				System.out.println("We found the user in the database.");
				System.out.println("Creating a new HttpSession for this user.");
				
				HttpSession httpSession = request.getSession(true);
				httpSession.setMaxInactiveInterval(30);//seconds
				
				httpSession.setAttribute("LoggedInUser", userName);
				isUserExist = true;

			} else 
			{
				System.out.println("We could not find the user in the database. Redirecting to login page.");
				isUserExist = false;
			}

		} else 
		{			
			System.out.println("Wrong UserName and PassWord.");
			isUserExist = false;
		}

		return isUserExist;
	}

}