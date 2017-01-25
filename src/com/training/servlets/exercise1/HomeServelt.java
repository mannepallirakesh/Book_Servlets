package com.training.servlets.exercise1;




import java.io.IOException;
import java.io.PrintWriter;
//import java.lang.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HomeServelt extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
  {
	  res.setContentType("text/html");
	  PrintWriter out = res.getWriter();

     out.println("<HTML><HEAD><TITLE>Login Success</TITLE></HEAD>");
      out.println("<BODY>You credentials are success. You are in the home page.<BR>");
     out.println("<form>"
     		+ "<input type=\"radio\" name=\"options\" value=\"insert\" checked>Upload New Book Details<br>\""
     		+ "<input type=\"radio\" name=\"options\" value=\"update\" checked>Update Existing Book Details<br>\""
     		+ "<input type=\"radio\" name=\"options\" value=\"delete\" checked>Delete Existing Book Details<br>\""
     		+ "<input type=\"radio\" name=\"options\" value=\"showBook\" checked>To Know Your Book<br>\""
     		+ "<input type=\"radio\" name=\"options\" value=\"showAllBooks\" checked>To Know All the Books in the BookStore<br>\""
     		+ "<input type=\"radio\" name=\"options\" value=\"exit\" checked>Exit<br>\"");
     out.println("</form>");
           out.println("</BODY></HTML>");
  
  }
  
}