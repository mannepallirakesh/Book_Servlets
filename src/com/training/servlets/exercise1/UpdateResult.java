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

public class UpdateResult extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		int bookid = Integer.parseInt(req.getParameter("bookid"));

		// String bookName = req.getParameter("bookName");
		// String authorName = req.getParameter("authorName");
		// int prIce = Integer.parseInt(req.getParameter("prIce"));

		Connection conn = null;
		PreparedStatement pst = null;
		int count = 0;
		ResultSet rs = null;

		try {

			conn = getConnection();

			pst = conn
					.prepareStatement("SELECT BOOK_NAME, AUTHOR_NAME, PRICE FROM BOOK WHERE BOOK_ID = ?");
			pst.setInt(1, bookid);

			// pst =
			// conn.prepareStatement("UPDATE BOOK SET BOOK_NAME = ?, AUTHOR_NAME = ?, PRICE = ? WHERE BOOK_NAME = ?");

			// pst.setInt(1, prIce);
			// pst.setString(2, bookName);

			rs = pst.executeQuery();
			
			rs.next();
			

	//		count = count + pst.executeUpdate();
			String bookname = rs.getString("BOOK_NAME");
			String authorname = rs.getString("AUTHOR_NAME");
			int bkprice = rs.getInt("PRICE");

			out.println("<HTML><HEAD><TITLE>Your Book:</TITLE></HEAD>");
			out.println();
/*			out.println("<style>table {	    font-family: arial, sans-serif;	    border-collapse: collapse;	    width: 100%;	}");
			out.println("td, th {    border: 1px solid #dddddd;    text-align: left;    padding: 8px;}");
			out.println("tr:nth-child(even) {    background-color: #dddddd;}");
			out.println("</style>");
			out.println("<table>");
			out.println(" <tr>  <th>BOOK NAME</th> <th>BOOK AUTHOR</th> <th>BOOK PRICE</th> </tr>");
			out.println(" <tr>   <td>" + bookname + "</td><td>" + authorname
					+ "</td><td>" + bkprice + "</td>  </tr>");
			out.println("	</table>");

			// out.println("<HTML><HEAD><TITLE>Your Book is Updated</TITLE></HEAD>");
			out.println();
			
			*/
			out.println("<BODY>");
	//		out.println("Your Book is Updated");
			out.println("<form method=\"POST\" action=\"/servlets/UpdateDisplay\">");
			out.println("<div align=\"center\"><br><br><br><br><br><br><br><br><br><br><br>");
			out.println("Book Name:<input type=\"hidden\" name=\"bookid\" value="+bookid+">");
		out.println("Book Name:<input type=\"text\" name=\"bookname\" value="+bookname+">");
		out.println("</br>");
		out.println("</br>");
		out.println("Author Name:<input type=\"text\" name=\"authorname\" value="+authorname+">");
		out.println("</br>");
		out.println("</br>");
		out.println("Book Price:<input type=\"text\" name=\"bkprice\" value="+bkprice+">");

			out.println();
			out.println("<BR>");
			out.println("</BR>");
			out.println("<input type=\"submit\"  value=\"Update\">");
			out.println();
			out.println("<BR>");
			out.println("</BR>");
			out.println();
			out.println("<BR>");
			out.println("Please click here for the main menu  -->  <A HREF=\""
					+ req.getContextPath() + "/htmls/menu.html\">Main Menu</A>");
			out.println("</BR>");
			out.println("</form>");
			out.println("</BODY></HTML>");
			out.println("</BODY>");
			out.println("</HTML>");

			conn.commit();

		} catch (SQLException sqlEx) {
			System.out.println("Problem while executing sql");
			sqlEx.printStackTrace();

		} catch (Exception ex) {
			System.out.println("Unknown exception.");

		} finally {
			try {
				pst.close();
				conn.close();

			} catch (SQLException sqlExce) {
				System.out.println("Problem while closing the connection.");
				
			}

		}
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
