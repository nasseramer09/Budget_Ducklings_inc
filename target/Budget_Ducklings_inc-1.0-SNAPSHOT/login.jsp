<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Budget Ducklings inc</title>
</head>
<body>
<br/>
<br><br>
<!--koden (HTML login formuläret) är hämtat ifrån https://www.geeksforgeeks.org/html-login-form/ -->
<div class="main">
    <h1>Welcome to Budget Duckling Inc.</h1>
    <h3>Enter your login credentials</h3>
    <form action="${pageContext.request.contextPath}/auth-servlet/login" method="post">

        <label for="first">Username:</label>
        <input type="text" id="first" name="UserName" placeholder="Enter your Username" required>
        <label for="password"> Password: </label>
        <input type="password" id="password" name="password" placeholder="Enter your Password" required>
        <div class="wrap">
            <p>${param.error}</p>
            <button type="submit"> Login </button>
        </div>
    </form>
</div>
</body>
</html>