package com.bookstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveUser
 */
@WebServlet("/Register")
public class Register extends Books {
	
	private PreparedStatement ps;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//reads-request
		String uid=request.getParameter("username");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");
		//process
		try{
			
			String sql="insert into users values(?,?,?,?,?,?)";
			
			ps=connection.prepareStatement(sql);
		
			ps.setString(1, uid);
			ps.setString(2, email);
			ps.setString(3, name);
			ps.setString(4, mobile);
			ps.setString(5, password);
			ps.setString(6, address);
			ps.executeUpdate();
			response.sendRedirect("/Login.jsp");
			
			
		}catch(Exception e){
			out.println("<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"<meta charset=\"ISO-8859-1\">\r\n" + 
					"<title>SignUp</title>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"	<h4>invalid username please try again</h4>\r\n"
					+ "<a href=\"Register.jsp\">Please try again</a>"
					+ "	\r\n" + 
					"</body>\r\n" + 
					"</html>");
		}
		//provides-response
		
	}

}
