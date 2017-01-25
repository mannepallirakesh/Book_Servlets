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

public class SignupServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		System.out.println(username);
		System.out.println(password);
		
	
	Connection conn = null;
	PreparedStatement pst = null;
	int count = 0;
	int max = maxRecord();
    
	try
	{
		
	conn = getConnection();
	
	String query = "INSERT INTO USERS(USER_ID, USER_NAME, PASSWORD) VALUES(?, ?, ?)";
	pst = conn.prepareStatement(query);

		pst.setInt(1, max+1);
		pst.setString(2, username);
		pst.setString(3, password);
		
		count = count + pst.executeUpdate();
		
		out.println("<HTML><HEAD><TITLE>Success</TITLE></HEAD>");
		out.println();
	out.println("<BODY>");
	out.println("Congrats! You Registered Successfully");
		
		out.println();
		out.println("<BR>");
		out.println("</BR>");
		out.println();
		out.println("<BR>");
		out.println("</BR>");
		out.println();
		out.println("<BR>");
		out.println("Please click here to Login!  -->   <A HREF=\""
				+ req.getContextPath()
				+ "/htmls/LoginPage.html\"><input type=\"submit\" value=\"LOGIN\" /></A>");
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

rs = st.executeQuery("SELECT MAX(USER_ID) MAX_ID FROM USERS");



if(rs.next())
{
//	System.out.println("The max id = "+rs.getInt("MAX_ID"));

max= rs.getInt("MAX_ID");		

System.out.println(max);
}

}catch(SQLException sqlEx)
{
System.out.println("Problem while executing sql");
sqlEx.printStackTrace();

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
