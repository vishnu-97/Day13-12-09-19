<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String a="visible",b="hidden",c="hidden",ha="auto",hb="0",hc="0";
if(session.getAttribute("user")==null)
	response.sendRedirect("Login.jsp?redirect=BookRegister.jsp");

if(request.getQueryString()!=null){
	if(request.getAttribute("book")!=null){
		a="hidden";
		b="visible";
		ha="0";
		hb="auto";
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
<title>Add Book</title>
</head>
<body>
	<div style="visibility:<%=a %>;height:<%=ha%>">
		<h1>Register Book</h1>
		<form action="BookRegister" method="post" style="padding-left:100px">
			<table style="width:75%" >
			  
			  <tr>
			    <td>Name: </td>
			    <td><input type="text" name="name" required></td>
			    
			  </tr>
			  <tr>
			    <td>Author:</td>
			    <td><input type="text" name="author" required></td>
			    
			  </tr>
			  <tr>
			    <td>Subject:</td>
			    <td><input type="text" name="subject" required></td>
			    
			  </tr>
			  <tr>
			    <td>Price:</td>
			    <td><input type="number" name="price" required ></td>
			    
			  </tr>
			  <tr>
			    <td>File Name(with extension):</td>
			    <td><input type="text" name="file" required></td>
			    
			  </tr>
			  <tr>
			    <td></td>
			    <td><button type="submit" name="submit">Register Book</button></td>
			    
			  </tr>
			</table>
			
		</form>
	</div>
	<div style="visibility:<%=b%>;height:<%=hb%>;">
		<h1>Book Inserted Successfully</h1>
		<table style="width:75%" style="padding-left:100px" >
			  
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
		<h1>Book Not found Please try Again</h1>
		<br>
		<a href="BookRegister.jsp" style="">Go Back and Try Again</a>
	</div>
	<div></div>
	
</body>
</html>