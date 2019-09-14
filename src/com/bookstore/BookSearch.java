package com.bookstore;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bookstore.dao.BookDao;
import com.bookstore.pojo.Book;

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
		
		BookDao dao=new BookDao();
		ArrayList<Book> l=dao.search(sub);
		String redt="";
		if(request.getParameter("redirect")!=null)
			redt=request.getParameter("redirect");
		else
			redt="SearchBooks.jsp";
		cookie(l, request, response, redt);
		
		
	}
	
	protected void cookie(ArrayList<Book> l,HttpServletRequest request, HttpServletResponse response,String redt ) {
		try {
			Cookie ck[]=request.getCookies();
			for(Book b :l) {
				int max,cmax=0,count=0;
				float price=b.getPrice();
				boolean nocookm,nocookb,nocooks;
				nocookm=nocookb=nocooks=true;
				
				if(ck!=null) {
					for(Cookie c: ck) {
						count=0;
						//changing the price according to count
						if(c.getName().equals("book")) {
							
							c.setValue(c.getValue()+b.getId()+"|");
							
							int z=0;
							String s=c.getValue();
							while(z!=-1) {
								z=s.indexOf(b.getId()+"",z+1);
								if(z!=-1) {
									count++;
									
								}
							}
							
							
							if(count>5 && count<=10)
								price=b.getPrice()+b.getPrice()*(10.0f/100);
							else if(count>10)
								price=b.getPrice()+b.getPrice()*(20.0f/100);
							nocookb=false;
							
						}
							
						else if(c.getName().equals("subject"))	{
							//increasing the count of subjects
							
							c.setValue(c.getValue()+b.getSubject()+"|");
						
							int z=0;
							String s=c.getValue();
							while(z!=-1) {
								z=s.indexOf(b.getSubject()+"",z+1);
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
								c.setValue(b.getSubject()+"|"+cmax);
							}
							nocookm=false;
							c.setMaxAge(60*60*24);
							response.addCookie(c);
							
						}
						
					}
				}
				if(nocookm) {
					System.out.println("create");
					Cookie c1=new Cookie("max", b.getSubject()+"|"+1);
					c1.setMaxAge(60*60*24);
					response.addCookie(c1);
					
				}
				if(nocookb) {
					Cookie c1=new Cookie("book", b.getId()+"|");
					c1.setMaxAge(60*60*24);
					response.addCookie(c1);
					
				}
				if(nocooks) {
					Cookie c1=new Cookie("subject", b.getSubject()+"|"+1);
					c1.setMaxAge(60*60*24);
					response.addCookie(c1);
					
				}
				
				b.setPrice((int)price);
				
			}
			request.setAttribute("redt", redt);
			request.getSession().setAttribute("book", l);
			request.getSession().setAttribute("result", true);
			RequestDispatcher rd=request.getRequestDispatcher("SearchBooks.jsp");
			rd.forward(request, response);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
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
			
			BookDao dao=new BookDao();
			ArrayList<Book> l=dao.search("where subject in ("+subjects+")");
			cookie(l, request, response, "ViewSubjectWise");
			
			}

}
