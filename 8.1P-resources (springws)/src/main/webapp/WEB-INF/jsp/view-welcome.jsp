<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h2>Welcome to the STEM Quiz Game</h2>
<%
    String msg = (String) request.getAttribute("message");
    if (msg != null) {
%>
    <p id="message" style="color:green;"><%= msg %></p>
<%
    } else {
%>
    <p><a href="/login">Click here to start the quiz</a></p>
<% } %>
</body>
</html>
