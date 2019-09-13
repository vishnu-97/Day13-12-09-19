package com.bookstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewSubjectwise
 */
@WebServlet("/ViewSubjectwise")
public class ViewSubjectwise extends Books {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter p=response.getWriter();
		try {
			PreparedStatement pre=connection.prepareStatement("select distinct(initcap(subject)) from books ");
			ResultSet rs=pre.executeQuery();
			Cookie[] ck=request.getCookies();
			String offer="All";
			int max=0;
			if(ck!=null) {
				for(Cookie c: ck) {
					if(c.getName().equals("max")) {
						max=Integer.parseInt(c.getValue().substring(c.getValue().indexOf("|")+1));
						if(max>5) {
							offer=c.getValue().substring(0,c.getValue().indexOf("|"));
						}
					}
				}
			}
			System.out.println(max);
			p.print(
			"<html>\r\n" + 
			"<head>\r\n" + 
			"<meta charset=\"ISO-8859-1\">\r\n" + 
			"<title>SearchBooks</title>\r\n" + 
			"</head>\r\n" + 
			"<body>\r\n"
			+ "<marquee>Special offers available on "+offer+" books</marquee>\r\n" + 
			"	<h1>Subject-wise View</h1>\r\n");
			p.println("<form method='post' action='BookSearch'>\r\n");
			while(rs.next()) {
				p.println("<input type=checkbox name='subjects' value='"+rs.getString(1)+"'/>\r\n");
				p.println("<a href=\"BookSearch?subject="+rs.getString(1)+"&redirect=ViewSubjectwise\">"+rs.getString(1)+"</a><br>\r\n");
				
			}
			p.println( "<br>\r\n"
					+ "<input type='submit' value='Show Books' />\r\n"+
					"</form>\r\n"+
					"</body></html>");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
