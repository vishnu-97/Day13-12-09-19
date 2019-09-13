
<%
if(request.getParameter("logout")!=null){
	request.getSession().setAttribute("user",null);
	request.getSession().setAttribute("cartList",null);
	request.getSession().setAttribute("price",null);
}

String a="visible",b="hidden";
if(request.getSession().getAttribute("user")!=null){
	a="hidden";
	b="visible";
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body style="left-padding:40px">
	<h1>Home</h1>
	<hr >
	<div  style="padding-left:100px">
		<a href="Search.jsp">Search and Download Books</a><br>
		<a href="ViewSubjectwise">View Subject-wise</a><br>
		<a href="AddToCart"  style="visibility:<%=b%>">View Cart</a><br>
		<a href="Login.jsp" style="visibility:<%=a%>">Login/SignUp</a><br>
		<a href="Index.jsp?logout=true" style="visibility:<%=b%>">Logout></a>
	</div>
	<hr >
	
</body>
</html>