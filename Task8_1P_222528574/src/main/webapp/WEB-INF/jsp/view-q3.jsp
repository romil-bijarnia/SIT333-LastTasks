<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Question 3</title>
</head>
<body>
    <h1>Question 3: Multiplication</h1>
    <form action="q3" method="post">
        <p>Enter number 1: <input type="text" id="number1" name="number1" /></p>
        <p>Enter number 2: <input type="text" id="number2" name="number2" /></p>
        <p>Your answer (product): <input type="text" id="result" name="result" /></p>
        <button type="submit">Submit</button>
    </form>
    <!-- Display flash message if present (e.g., wrong answer or validation error) -->
    <c:if test="${not empty message}">
        <p style="color:red;">${message}</p>
    </c:if>
</body>
</html>
