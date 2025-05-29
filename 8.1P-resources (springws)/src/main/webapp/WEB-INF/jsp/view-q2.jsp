<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Question 2 - Subtraction</title>
</head>
<body>
<h2>Question 2: Subtraction</h2>
<p>Enter two numbers to subtract (Number1 - Number2), then provide the result.</p>
<form action="/q2" method="post">
    Number 1: <input type="number" name="number1"/> 
    Number 2: <input type="number" name="number2"/> 
    Your Answer: <input type="number" name="result"/>
    <button type="submit">Submit</button>
</form>
<%
    String msg = (String) request.getAttribute("message");
    if (msg != null) {
%>
    <p id="message" style="color:red;"><%= msg %></p>
<% } %>
</body>
</html>
