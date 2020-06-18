<%--
  Created by IntelliJ IDEA.
  User: Dinh Phu
  Date: 6/18/2020
  Time: 2:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
    <form action="home" method="post">
        <input type="hidden" name="action" value="create">
        <label>User Name: </label>
        <input type="text" name="userName"><br>
        <label>User Email: </label>
        <input type="text" name="userEmail"><br>
        <label>User Country: </label>
        <input type="text" name="userCountry"><br>
        <button>Submit</button>
    </form>
</body>
</html>
