<% 
	String s1="",s2="";

	//to read the cookies
	//step-1 (fetch all the cookies coming with request)
		Cookie ck[]=request.getCookies();
	//step-2 (search for the desired one)
	if(ck!=null)
		for(Cookie c:ck){
			String name=c.getName();
			if(name.equals("id")){
				s1=c.getValue();
			}else if(name.equals("pw")){
				s2=c.getValue();
			}
		}
	
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1>LOGIN</h1>
	<form action="Login" method="post">
		<pre>
			<input type="hidden" name="redirect" value="<%=request.getParameter("redirect")%>">
			UserName: <input type="text" name="username"><br>
			Password: <input type="password" name="password"><br>
			RememberMe<input type="checkbox" name="save" value="yes" checked="checked" />
			User Type	owner	<input type="radio" name="utype" value="owner" checked="checked"/>
					buyer 	<input type="radio" name="utype" value="buyer"/>
					  <button type="submit" name="login">LogIn</button><br>
			<hr>
			New User? <a href="/Day12/Register.jsp">SignUp</a>
		</pre>
	</form>
</body>
</html>