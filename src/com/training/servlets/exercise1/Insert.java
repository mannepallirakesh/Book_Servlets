package com.training.servlets.exercise1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import oracle.jdbc.driver.OracleDriver;

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

public class Insert extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String bookName = req.getParameter("bookName");
		String authorName = req.getParameter("authorName");
		int prIce = Integer.parseInt(req.getParameter("prIce"));
	
	Connection conn = null;
	PreparedStatement pst = null;
	int count = 0;
	int max = maxRecord();
    
	try
	{
		
	conn = getConnection();
	
	String query = "INSERT INTO BOOK(BOOK_ID, BOOK_NAME, AUTHOR_NAME, PRICE) VALUES(?, ?, ?, ?)";
	pst = conn.prepareStatement(query);

		pst.setInt(1, max+1);
		pst.setString(2, bookName);
		pst.setString(3, authorName);
		pst.setInt(4, prIce);
		count = count + pst.executeUpdate();
		
		out.println("<HTML><HEAD><TITLE>Your Book is Uploaded</TITLE></HEAD>");
		out.println();
	out.println("<BODY>");
	out.println("Your Book is Uploaded");
		
		out.println();
		out.println("<BR>");
		out.println("</BR>");
		out.println();
		out.println("<BR>");
		out.println("</BR>");
		out.println();
		out.println("<BR>");
		out.println("Please click here for the main menu  -->  <A HREF=\""
				+ req.getContextPath()
				+ "/htmls/menu.html\">Main Menu</A>");
		out.println("</BR>");
		
		out.println("</BODY></HTML>");
		out.println("</BODY>");
		out.println("</HTML>");


    conn.commit();
    		   		    
    
}catch(SQLException sqlEx)
{
	System.out.println("Problem while executing sql");
	
}catch(Exception ex)
{
	System.out.println("Unknown exception.");
	
}finally
{
	try
	{
		pst.close();
		conn.close();
		
	}catch(SQLException sqlExce)
	{
		System.out.println("Problem while closing the connection.");
	}
	
}
}

private  int maxRecord()
{
Connection conn = null;
Statement st = null;
ResultSet rs = null;
int max = 1;


try
{
conn = getConnection();
st = conn.createStatement();

rs = st.executeQuery("SELECT MAX(BOOK_ID) MAX_ID FROM BOOK");



if(rs.next())
{
//	System.out.println("The max id = "+rs.getInt("MAX_ID"));

max= rs.getInt("MAX_ID");		
}

}catch(SQLException sqlEx)
{
System.out.println("Problem while executing sql");

}catch(Exception ex)
{
System.out.println("Unknown exception.");

}finally
{
try
{
rs.close();
st.close();
conn.close();

}catch(SQLException sqlExce)
{
System.out.println("Problem while closing the connection.");
}

}
return max;

}


private static Connection getConnection() throws Exception

{
String driver = "oracle.jdbc.driver.OracleDriver";
String url = "jdbc:oracle:thin:@localhost:1521:XE";
String username = "SYSTEM";
String password = "SYSADMIN";
DriverManager.registerDriver(new OracleDriver());
Connection conn = DriverManager.getConnection(url, username, password);
conn.setAutoCommit(false);

return conn;
}

}
