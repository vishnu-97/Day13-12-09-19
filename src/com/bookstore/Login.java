package com.bookstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/*@WebServlet(
		urlPatterns = { "/Login" }, 
		initParams = { 
				@WebInitParam(name = "username", value = "admin"), 
				@WebInitParam(name = "password", value = "admin")
		})*/
public class Login extends Books {
	ServletConfig config;
		public void init(ServletConfig config) throws ServletException {
			this.config=config;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String userid=request.getParameter("username");
		String password=request.getParameter("password");
		String utype=request.getParameter("utype");
		System.out.println(request.getHeader("referer"));
		
		try{
			if(utype.equals("owner")){
				
				String u=config.getInitParameter("username"),p=config.getInitParameter("password");
				System.out.println(u+" "+p);
				if(userid.equals(u) && password.equals(p))
				{
					HttpSession s=request.getSession();
					s.setAttribute("user", "admin");
					if(request.getParameter("redirect")!=null)
						response.sendRedirect(request.getParameter("redirect"));
					else
						response.sendRedirect("adminpage.jsp");
				}else{
					out.println("INVALID CREDENTIALS FOR ADMIN");
				}
				
			}else{
				
				String sql="SELECT name FROM USERS where userid=? AND password=?";
				System.out.println(connection);
				PreparedStatement ps=connection.
						prepareStatement(sql);
				ps.setString(1,userid);
				ps.setString(2,password);
				ResultSet rs=ps.executeQuery();
			 	if(rs.next()){
					
					//whether user want to save the password
					String choice=request.getParameter("save");
					if(choice!=null){
						
						Cookie c1=new Cookie("id",userid);
						Cookie c2=new Cookie("pw", password);
						
						c1.setMaxAge(60*60*24*7);
						c2.setMaxAge(60*60*24*7);
						
						response.addCookie(c1);
						response.addCookie(c2);
						
						
					}
					HttpSession s=request.getSession();
					s.setAttribute("user", userid);
					
					if(request.getParameter("redirect")!=null)
						response.sendRedirect(request.getParameter("redirect"));
					else
						response.sendRedirect("Index.jsp");
					
					
					
				}else{
					out.println("INVALID BUYER CREDENTIALS");
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
