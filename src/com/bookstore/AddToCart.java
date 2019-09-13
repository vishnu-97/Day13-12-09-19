package com.bookstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends Books {
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter p=response.getWriter();
		HttpSession s=request.getSession();
		
		
		ArrayList<Integer> cart;
		ArrayList<Float> price;
		if(request.getParameter("remove")!=null) {
			cart=(ArrayList<Integer>) s.getAttribute("cartList");
			price=(ArrayList<Float>) s.getAttribute("price");
			price.remove((int)cart.indexOf(Integer.parseInt(request.getParameter("remove"))));
			cart.remove((Integer)Integer.parseInt(request.getParameter("remove")));
			
		}
		else {
			if(s.getAttribute("cartList")==null) {
				s.setAttribute("price", new ArrayList<Float>());
				s.setAttribute("cartList", new ArrayList<Integer>());
			}	
			cart=(ArrayList<Integer>) s.getAttribute("cartList");
			price=(ArrayList<Float>) s.getAttribute("price");
			
			System.out.println(request.getParameter("id")+" "+cart);
			if(request.getParameter("id")!=null) {
				cart.add(Integer.parseInt(request.getParameter("id")));
				price.add( Float.parseFloat(request.getParameter("price")));
			}
		}
		
		if(s.getAttribute("user")==null) {
			response.sendRedirect("Login.jsp?redirect=AddToCart");
			
		}
		p.print(
						 
						"<html>\r\n" + 
						"<head>\r\n" + 
						"<meta charset=\"ISO-8859-1\">\r\n" + 
						"<title>SearchBooks</title>\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"	<h1>Cart Details</h1>\r\n"
						+ "		<table style=\"width:75%;table-layout: fixed;text-align: center;\" >\r\n"
						+ "			<tr>\r\n" + 
						"		    <th>Id </th>\r\n" + 
						"		    <th>Name</th>\r\n" + 
						"		    <th>Author</th>\r\n" + 
						"		    <th>Subject</th>\r\n" + 
						"		    <th>Price</th>\r\n" + 
						"		    <th>Remove</th>\r\n" + 
						"		    \r\n" + 
						"		  </tr>\r\n");
		int total=0;
		for(int i=0;i<cart.size();i++) {
			int id=cart.get(i);
			float pr=price.get(i);
			total+=pr;
			PreparedStatement pre;
			try {
				pre = connection.prepareStatement("select * from books where id="+id);
			
			
				ResultSet rs=pre.executeQuery();
				
				while(rs.next()) {
					
					p.print(" <tr>\r\n" + 
							"		    <td>"+rs.getInt(1)+"</td>\r\n" + 
							"		    <td>"+rs.getString(2)+"</td>\r\n" + 
							"		    <td>"+rs.getString(3)+"</td>\r\n" + 
							"		    <td>"+rs.getString(4)+"</td>\r\n" + 
							"		    <td>"+pr+"</td>\r\n" + 
							"		    <td><a href=\"AddToCart?remove="+rs.getInt(1)+"\">Remove<a><td>\r\n" + 
							"		  </tr>\r\n");
					
				}
			}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} 	
		}			
		p.print("</table>\r\n" + 
				"<br>\r\n"+
				"<h3>Total:<h3>"+total+"\r\n" + 
				"<br>\r\n" + 
				"\r\n" + 
				"<a href=\"BookSearch\">Go Back</a>\r\n" + 
				
				"</body>\r\n" + 
				"</html>");
				
				
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
