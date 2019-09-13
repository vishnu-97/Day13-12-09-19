<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SignUp</title>
</head>
<body>
	<h1>SignUp</h1>
	<form action="Register" method="post">
		<pre>
			Username:	<input type="text" name="username">
			
			Name:   	<input type="text" name="name">
			
			Address: 	<input type="text" name="address">
			
			Email:   	<input type="text" name="email" pattern="+@[a-z0-9.-]+\.[a-z]+">
			
			Mobile:   	<input type="number" name="mobile" >
			
			Password:	<input type="password" name="password">
			
					 <button type="submit" name="register">Register </button>
		</pre>
	</form>
	
		
	
</body>
</html>