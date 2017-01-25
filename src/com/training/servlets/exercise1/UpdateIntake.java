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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.jdbc.driver.OracleDriver;

public class UpdateIntake extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		Connection conn = null;
		PreparedStatement pst = null;
		int count = 0;
		ResultSet rs = null;

		try {

			conn = getConnection();

			pst = conn.prepareStatement("SELECT * FROM BOOK");

			rs = pst.executeQuery();

			out.println("<HTML><HEAD><TITLE>ALL BOOKS</TITLE></HEAD>");
			out.println();
			out.println("<style>table {	    font-family: arial, sans-serif;	    border-collapse: collapse;	    width: 100%;	}");
			out.println("td, th {    border: 1px solid #dddddd;    text-align: left;    padding: 8px;}");
			out.println("tr:nth-child(even) {    background-color: #dddddd;}");
			out.println("</style>");
			out.println("<BODY>");
			out.println("<form method=\"POST\" action=\"/servlets/UpdateResult\">");
			out.println("<table>");
			out.println(" <tr> <th> </th>  <th>BOOK NAME</th> <th>BOOK AUTHOR</th> <th>BOOK PRICE</th> </tr>");
			// out.println( "	</table>");

			while (rs.next()) {

				int bookid = rs.getInt("BOOK_ID");
				String bookname = rs.getString("BOOK_NAME");
				String authorname = rs.getString("AUTHOR_NAME");
				int bkprice = rs.getInt("PRICE");

				/*
				 * ServletRequest request = null; ServletResponse response =
				 * null; int bookId =
				 * Integer.parseInt(request.getParameter("BOOK_ID"));
				 * 
				 * request.setAttribute("bookid",+bookId); RequestDispatcher rd
				 * = request.getRequestDispatcher("/Update");
				 * 
				 * rd.forward(request,response);
				 */

				out.println(" <tr><td>"
						+ "<input type=\"radio\" name=\"bookid\" value="+bookid+">"
						+ "</td> "

						+ " <td>" + bookname
						+ "</td><td>" + authorname + "</td><td>" + bkprice
						+ "</td>  </tr>");

				out.println();
				out.println("<BR>");

				out.println("</BR>");

			}
			out.println("	</table>");

			out.println();
			out.println("<BR>");

			out.println("<input type=\"submit\"  value=\"Submit\">");

			out.println();
			out.println("<BR>");

			out.println("</BR>");

			out.println();
			out.println("<BR>");

			out.println("</BR>");
			out.println("Please click here for the main menu  ---->  <A HREF=\""
					+ req.getContextPath() + "/htmls/menu.html\">Main Menu</A>");
			
			

			out.println("</form>");

			out.println("</BODY>");
			out.println("</HTML>");
			conn.commit();

		} catch (SQLException sqlEx) {
			System.out.println("Problem while executing sql");

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
