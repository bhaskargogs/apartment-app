<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<img alt="" src="${pageContext.request.contextPath}/img/error.jpg">
  <h3>Hey! You are not valid user since your email and password are not correct</h3>
   <a href="${pageContext.request.contextPath}/apartment/auth">Click here to login</a>
   
</body>
</html>