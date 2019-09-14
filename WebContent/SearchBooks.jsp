<%@page import="java.util.ArrayList"%>
<%@ page import="com.bookstore.pojo.Book" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
String a="visible",c="hidden",ha="auto",hc="0";

if(session.getAttribute("result")!=null){
	a="hidden";
	c="visible";
	ha="0";
	hc="auto";
}
	



%>
<html> 
<head> 
<meta charset=\"ISO-8859-1\"> 
<title>SearchBooks</title> 
</head> 
<body>
	<div style="visibility:<%=c %>;height:<%=hc%>"> 
	<h1>Book Details</h1>
		<table style="width:75%;table-layout: fixed;text-align: center;" >
			<tr> 
		    <th>Id </th> 
		    <th>Name</th> 
		    <th>Author</th> 
		    <th>Subject</th> 
		    <th>Price</th> 
		    <th>File</th> 
		     
		  </tr>
<%
String redt=(String)request.getAttribute("redt");
if(c.equals("visible")){
	ArrayList<Book> l=(ArrayList<Book>)session.getAttribute("book");
	
for(Book b :l) {
	
	%>
		<tr> 
		    <td><%=b.getId() %></td> 
		    <td><%= b.getName()%></td> 
		    <td><%=b.getAuthor()%></td> 
		    <td><%=b.getSubject()%></td> 
		    <td><%=b.getPrice()%></td>
		    
		    <td><a href="AddToCart?id=<%=b.getId()%>&price=<%=b.getPrice()%>">add to cart</a></td> 
		     
		  </tr>
<%	
	session.setAttribute("redirect", "");
	
}
session.setAttribute("result",null);
}
%>

	
		</table> 
		<br> 
		<br>
		<a href="<%=redt%>>">Go Back</a> 
		<a href="Index.jsp">Home</a> 
	</div>
		


<div style="visibility:<%=a %>;height:<%=ha%>">
	<h1>Search Books</h1>
	<h4>Search By</h4>
	<form action="BookSearch" method="get" style="padding-left:100px">
		<table style="width:75%" >
		  
		  
		  <tr>
		    <td>Id: </td>
		    <td><input type="number" name="id" ></td>
		    
		  </tr>
		  <tr>
		    <td>Name: </td>
		    <td><input type="text" name="name" ></td>
		    
		  </tr>
		  <tr>
		    <td>Author:</td>
		    <td><input type="text" name="author" ></td>
		    
		  </tr>
		  <tr>
		    <td>Subject:</td>
		    <td><input type="text" name="subject" ></td>
		    
		  </tr>
		  <tr>
		    <td></td>
		    <td><button type="submit" name="submit">Search</button></td>
		    
		  </tr>
		</table>
		
	</form>
	</div>
</body>
</html>