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


public class UpdateDisplay extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		int bookid = Integer.parseInt(req.getParameter("bookid"));
		 String bookname = req.getParameter("bookname");
		 String authorname = req.getParameter("authorname");
		 int bkprice = Integer.parseInt(req.getParameter("bkprice"));

		Connection conn = null;
		PreparedStatement pst = null;
		int count = 0;
		ResultSet rs = null;

		try {

			conn = getConnection();

			

			pst =
			conn.prepareStatement("UPDATE BOOK SET  BOOK_NAME = ?, AUTHOR_NAME = ?, PRICE = ? WHERE BOOK_ID = ?");
			pst.setString(1, bookname);
			pst.setString(2, authorname);
			pst.setInt(3, bkprice);
			pst.setInt(4, bookid);
			

			rs = pst.executeQuery();

			count = count + pst.executeUpdate();
			

			out.println("<HTML><HEAD><TITLE>Your Book:</TITLE></HEAD>");
			out.println();
			out.println("<HTML><HEAD><TITLE>Your Book is Updated</TITLE></HEAD>");
			out.println();
			out.println("<BODY>");
			out.println("Your Book is Updated");

			out.println();
			out.println("<BR>");
			out.println("</BR>");
			out.println();
			out.println();
			out.println("<BR>");

			out.println("</BR>");
			out.println("Please click here for the main menu  ---->  <A HREF=\""
					+ req.getContextPath() + "/htmls/menu.html\">Main Menu</A>");
			
			out.println("<BR>");
			out.println("</BR>");
			out.println();
			out.println("<BR>");
			out.println("</BR>");
			out.println("</BODY></HTML>");
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
