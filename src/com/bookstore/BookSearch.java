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
 * Servlet implementation class BookSearch
 */
@WebServlet("/BookSearch")
public class BookSearch extends Books {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see Books#Books()
     */
    public BookSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			PrintWriter p=response.getWriter();
			
			int i=0;
			if(request.getParameter("id")!=null && request.getParameter("id")!="")
				i=Integer.parseInt(request.getParameter("id"));
			String n="";
			if(request.getParameter("name")!=null )
				n=request.getParameter("name").toLowerCase();
			
			String a="";
			if(request.getParameter("author")!=null )
				a=request.getParameter("author").toLowerCase();
			String g="";
			if(request.getParameter("subject")!=null) 
				g=request.getParameter("subject").toLowerCase();
			
			
			String sub="";
			if(i!=0) {
				sub+=" and id="+i;
			}
			if(n!="") {
				sub+=" and lower(name) like "+"'%"+n+"%'";
			}
			if(a!="") {
				sub+=" and lower(author) like "+"'%"+a+"%'";
			}
			if(g!="") {
				sub+=" and lower(subject) like "+"'%"+g+"%'";
			}
			if(i!=0 || n!="" || a!="" || g!="")
				sub="where" +sub.substring(4);
			
			PreparedStatement pre=connection.prepareStatement("select * from books "+sub);
			
			ResultSet rs=pre.executeQuery();
			String redt="";
			if(request.getParameter("redirect")!=null)
				redt=request.getParameter("redirect");
			else
				redt="SearchDownloadBooks.jsp";
			view(rs,request,response,redt);
			
		}catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	protected void view(ResultSet rs,HttpServletRequest request, HttpServletResponse response,String redt ) {
		try {
			PrintWriter p;
			p = response.getWriter();
			
			Cookie ck[]=request.getCookies();
			
			p.print(
					 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"<meta charset=\"ISO-8859-1\">\r\n" + 
					"<title>SearchBooks</title>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"	<h1>Book Details</h1>\r\n"
					+ "		<table style=\"width:75%;table-layout: fixed;text-align: center;\" >\r\n"
					+ "			<tr>\r\n" + 
					"		    <th>Id </th>\r\n" + 
					"		    <th>Name</th>\r\n" + 
					"		    <th>Author</th>\r\n" + 
					"		    <th>Subject</th>\r\n" + 
					"		    <th>Price</th>\r\n" + 
					"		    <th>File</th>\r\n" + 
					"		    \r\n" + 
					"		  </tr>\r\n");
			
			while(rs.next()) {
				int max,cmax=0,count=0;
				float price=rs.getInt(5);
				boolean nocookm,nocookb,nocooks;
				nocookm=nocookb=nocooks=true;
				
				if(ck!=null) {
					for(Cookie c: ck) {
						count=0;
						//changing the price according to count
						if(c.getName().equals("book")) {
							
							c.setValue(c.getValue()+rs.getInt(1)+"|");
							
							int z=0;
							String s=c.getValue();
							while(z!=-1) {
								z=s.indexOf(rs.getInt(1)+"",z+1);
								if(z!=-1) {
									count++;
									
								}
							}
							
							
							if(count>5 && count<=10)
								price=rs.getInt(5)+rs.getInt(5)*(10.0f/100);
							else if(count>10)
								price=rs.getInt(5)+rs.getInt(5)*(20.0f/100);
							nocookb=false;
							
						}
							
						else if(c.getName().equals("subject"))	{
							//increasing the count of subjects
							
							c.setValue(c.getValue()+rs.getString(4)+"|");
						
							int z=0;
							String s=c.getValue();
							while(z!=-1) {
								z=s.indexOf(rs.getString(4)+"",z+1);
								if(z!=-1) {
									count++;
									
								}
							}
							nocooks=false;
							
							cmax=count;
							
						}
						c.setMaxAge(60*60*24);
						response.addCookie(c);
						
					}
					for(Cookie c: ck) {
						if(c.getName().equals("max")) {
							max=Integer.parseInt(c.getValue().substring(c.getValue().indexOf("|")+1));
							if(max<cmax) {
								c.setValue(rs.getString(4)+"|"+cmax);
							}
							nocookm=false;
							c.setMaxAge(60*60*24);
							response.addCookie(c);
							
						}
						
					}
				}
				if(nocookm) {
					System.out.println("create");
					Cookie c1=new Cookie("max", rs.getString(4)+"|"+1);
					c1.setMaxAge(60*60*24);
					response.addCookie(c1);
					
				}
				if(nocookb) {
					Cookie c1=new Cookie("book", rs.getString(4)+"|"+1);
					c1.setMaxAge(60*60*24);
					response.addCookie(c1);
					
				}
				if(nocooks) {
					Cookie c1=new Cookie("subject", rs.getString(4)+"|"+1);
					c1.setMaxAge(60*60*24);
					response.addCookie(c1);
					
				}
				
				
				p.print(" <tr>\r\n" + 
						"		    <td>"+rs.getInt(1)+"</td>\r\n" + 
						"		    <td>"+rs.getString(2)+"</td>\r\n" + 
						"		    <td>"+rs.getString(3)+"</td>\r\n" + 
						"		    <td>"+rs.getString(4)+"</td>\r\n" + 
						"		    <td>"+price+"</td>\r\n" + 
						"		    <td><a href=\"AddToCart?id="+rs.getInt(1)+"&price="+price+"\">add to cart</a></td>\r\n" + 
						"		    \r\n" + 
						"		  </tr>\r\n");
				request.getSession().setAttribute("redirect", "");
			}
				
			p.print("</table>\r\n" + 
					"<br>\r\n" + 
					"<br>\r\n" + 
					"\r\n" + 
					"<a href=\""+redt+"\">Go Back</a>\r\n"+ 
					"<a href=\"Index.jsp\">Home</a>" + 
					
					"</body>\r\n" + 
					"</html>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	
				String sub[]=request.getParameterValues("subjects");
				PrintWriter out=response.getWriter();
				if(sub.length<=0)
				{
					out.println("No Subjects Selected");
					response.sendRedirect("buyerpage.jsp");
				}
				String subjects="";
				for(int i=0;i<sub.length-1;i++)
				{
					subjects+="'"+sub[i]+"',";
				}
				subjects+="'"+sub[sub.length-1]+"'";
				String sql="SELECT * from books where subject in ("+subjects+")";
				PreparedStatement ps;
				ps = connection.prepareStatement(sql);
				
				//ps.setString(1, subject);
				
				ResultSet rs=ps.executeQuery();
				view(rs,request,response,"ViewSubjectwise");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				
			}

}
