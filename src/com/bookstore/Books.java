package com.bookstore;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class Books
 */
@WebServlet("/Books")
public class Books extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected Connection connection;
	protected int id,uid;
	ServletContext context;
    public Books() {
    	
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
//			config.getServletContext();
			//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+getServletContext());
			
//			System.out.println(getServletContext());
//			ServletContext context=getServletContext();
//			System.out.println("@@@@@@@@@@@@"+context.getInitParameter("class")+" "+context.getInitParameter("url"));
//			
//			Class.forName(context.getInitParameter("class"));
//			connection = DriverManager.getConnection(context.getInitParameter("url"),context.getInitParameter("username"),context.getInitParameter("password"));
			connection=(Connection) config.getServletContext().getAttribute("connection");
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "JavaTraining", "hawking");
//			
			
			PreparedStatement pre=connection.prepareStatement("select max(id) from books");
			ResultSet rs=pre.executeQuery();
			System.out.println("@@@@@@@@@@@@"+connection);
			if(rs.next())
				id=rs.getInt(1);
			else
				id=0;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.print("Connection cannot be closed");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
