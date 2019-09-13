package com.bookstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;

import com.bookstore.dao.BookDao;
import com.bookstore.pojo.Book;

/**
 * Servlet implementation class BookRegister
 */
@WebServlet("/BookRegister")
public class BookRegister extends Books {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see Books#Books()
     */
    public BookRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book b=new Book(++id, request.getParameter("name"),request.getParameter("author") ,request.getParameter("subject") , Integer.parseInt(request.getParameter("price")), request.getParameter("file"));
		
		BookDao dao=new BookDao();
		boolean in=dao.insert(b);
		RequestDispatcher rd=request.getRequestDispatcher("BookRegister.jsp?insert");
		if(!in) {
			b=null;
			id--;
		}
		request.setAttribute("book", b);
		rd.forward(request, response);
		
	}
	

}
