<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Please log in to start the quiz</h2>
<form action="/login" method="post">
    User name: <input type="text" id="username" name="username"/><br/>
    Password: <input type="password" id="passwd" name="passwd"/><br/>
    Date of Birth: <input type="date" id="dob" name="dob"/><br/>
    <input type="submit" value="Login"/>
</form>
<%
    String msg = (String) request.getAttribute("message");
    if (msg != null) {
%>
    <p id="message" style="color:red;"><%= msg %></p>
<% } %>
</body>
</html>
