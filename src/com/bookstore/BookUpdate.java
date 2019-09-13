package com.bookstore;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDao;
import com.bookstore.pojo.Book;

/**
 * Servlet implementation class UpdateBook
 */
@WebServlet("/BookUpdate")
public class BookUpdate extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book b=new Book( );
		b.setId(Integer.parseInt(request.getParameter("id")));
		BookDao dao=new BookDao();
		ArrayList<Book> in=dao.search("where id="+request.getParameter("id"));
		
		RequestDispatcher rd=request.getRequestDispatcher("BookUpdate.jsp?update");
		b=null;
		if(in.size()!=0) {
			b=in.get(0);
			request.setAttribute("update", "working");
		}
		request.setAttribute("book", b);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book b=new Book( Integer.parseInt(request.getParameter("id")),request.getParameter("name"),request.getParameter("author") ,request.getParameter("subject") , Integer.parseInt(request.getParameter("price")), request.getParameter("file"));
		
		BookDao dao=new BookDao();
		boolean in=dao.update(b);
		RequestDispatcher rd=request.getRequestDispatcher("BookUpdate.jsp?update");
		request.setAttribute("update", "success");
		if(!in) {
			b=null;
			request.setAttribute("update", "failled");
		}
		request.setAttribute("book", b);
		rd.forward(request, response);
	}

}
