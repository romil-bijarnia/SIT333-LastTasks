<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Question 1 - Addition</title>
</head>
<body>
<h2>Question 1: Addition</h2>
<p>Enter two numbers to add, then provide the sum as your answer.</p>
<form action="/q1" method="post">
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
