<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<br/>
<br><br>
<!--koden (HTML login formuläret) är hämtat ifrån https://www.geeksforgeeks.org/html-login-form/ -->
<div class="main">
    <h1>Welcome to Budget Duckling Inc.</h1>
    <h3>Enter your login credentials</h3>
    <form action="">
        <label for="first">
            Username:
        </label>
        <input type="text" id="first" name="first"
               placeholder="Enter your Username" required>

        <label for="password"> Password: </label>
        <input type="password" id="password" name="password"
               placeholder="Enter your Password" required>
        <div class="wrap">
            <button type="submit" onclick="solve()"> Submit </button>
        </div>
    </form>
    <p>Not registered?
        <a href="#" style="text-decoration: none;">Create an account</a>
    </p>
</div>
</body>
</html>