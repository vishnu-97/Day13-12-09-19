<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

if(session.getAttribute("user")==null)
	response.sendRedirect("Login.jsp?redirect=BookDelete.jsp");
String a="visible",b="hidden",c="hidden",d="hidden",ha="auto",hb="0",hc="0",hd="0";


if(request.getQueryString()!=null){
	if(request.getAttribute("Delete")=="working"){
		a="hidden";
		d="visible";
		ha="0";
		hd="auto";
	}
	else if(request.getAttribute("Delete")=="success"){
		a="hidden";
		b="visible";
		ha="0";
		hb="auto";
		request.setAttribute("Delete",null);
	}
	else{
		a="hidden";
		c="visible";
		ha="0";
		hc="auto";
	}
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Book</title>
</head>
<body>
	<div style="visibility:<%=a %>;height:<%=ha%>">
		<h1>Delete Book</h1>
		<form action="BookDelete" method="get" style="padding-left:100px">
			<table style="width:75%" >
			  
			  <tr>
			    <td>Id: </td>
			    <td><input type="number" name="id" required></td>
			    
			  </tr>
			  
			  <tr>
			    <td></td>
			    <td><button type="submit" name="submit">Fetch Book</button></td>
			    
			  </tr>
			</table>
			
		</form>
	</div>
	<div style="visibility:<%=d %>;height:<%=hd%>">
		<h1>Delete Book</h1>
		<form action="BookDelete" method="post" style="padding-left:100px">
			<table style="width:75%" >
			  <tr style="visibility:hidden;">
			  <td><input type="text" name="id" value=${book.getId()}></td>
			  <td></td>
			  <tr>
			    <td>Id: </td>
			    <td><input type="text" name="id1" value=${book.getId()} readonly></td>
			    
			  </tr>
			  <tr>
			    <td>Name: </td>
			    <td><input type="text" name="name" value=${book.getName()} readonly></td>
		    
			  </tr>
			  <tr>
			    <td>Author:</td>
			    <td><input type="text" name="author" value=${book.getAuthor()} readonly></td>
			    
			  </tr>
			  <tr>
			    <td>Subject:</td>
			    <td><input type="text" name="subject" value=${book.getSubject()} readonly></td>
			    
			  </tr>
			  <tr>
			    <td>Price:</td>
			    <td><input type="number" name="price" value= ${book.getPrice()} readonly></td>
			    
			  </tr>
			  <tr>
			    <td>File Name(with extension):</td>
			    <td><input type="text" name="file" value=${book.getFile()} readonly></td>
			    
			  </tr>
			  <tr>
			    <td></td>
			    <td><button type="submit" name="submit">Delete Book</button></td>
			    
			  </tr>
			</table>
			
		</form>
	</div>
	<div style="visibility:<%=b%>;height:<%=hb%>;">
		<h1>Book Deleted Successfully</h1>
		<table style="width:75%; padding-left:100px" >
			  
			  <tr>
			    <td>Id: </td>
			    <td>${book.getId()}</td>
			    
			  </tr>
			  <tr>
			    <td>Name: </td>
			    <td>${book.getName()}</td>
			    
			  </tr>
			  <tr>
			    <td>Author:</td>
			    <td>${book.getAuthor()}</td>
			    
			  </tr>
			  <tr>
			    <td>Subject:</td>
			    <td>${book.getSubject()}</td>
			    
			  </tr>
			  <tr>
			    <td>Price:</td>
			    <td>${book.getPrice()}</td>
			    
			  </tr>
			  <tr>
			    <td>File Name(with extension):</td>
			    <td>${book.getFile()}</td>
			    
			  </tr>
			 
			</table>
			
	</div>
	<div style="visibility:<%=c %>;height:<%=hc%>;text-align: center;">
		<h1>Book Not found </h1>
		<br>
		<a href="BookUpdate.jsp" >Go Back and Try Again</a>
	</div>
	<div></div>
	
</body>
</html>