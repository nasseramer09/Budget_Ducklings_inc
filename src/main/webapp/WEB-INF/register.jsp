<%--
  Created by IntelliJ IDEA.
  User: nasseramer
  Date: 2024-01-23
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> User Registration page</title>
</head>
<body>
<form action="auth-servlet/" method="post">
  <div>
    <label>
      <input name="userName" type="text"/>
    </label>
  </div>
  <div>
    <label>
      <input name="password" type="text"/>
    </label>
  </div>
  <br>
  <button>Register</button>
</form>

</body>
</html>
