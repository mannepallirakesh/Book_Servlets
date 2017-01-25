package com.training.servlets.exercise1;

import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;



public class Authentication {


	
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

public static boolean validate(String userName, String passWord) {
	boolean status=false;

	Connection conn = null;
	PreparedStatement pst = null;
    ResultSet rs = null;
    
	try
	{
		conn = getConnection();
		
		
		pst = conn.prepareStatement("select * from users where USER_NAME=? and PASSWORD=?");
		pst.setString(1,userName);
		pst.setString(2,passWord);
		rs = pst.executeQuery();
		status=rs.next();
	}
	catch(Exception e){System.out.println(e);}
	return status;
}
}
