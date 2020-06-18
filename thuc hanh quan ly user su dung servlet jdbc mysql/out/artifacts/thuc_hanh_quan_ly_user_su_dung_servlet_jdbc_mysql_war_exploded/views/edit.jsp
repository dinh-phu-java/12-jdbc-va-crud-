<%--
  Created by IntelliJ IDEA.
  User: Dinh Phu
  Date: 6/18/2020
  Time: 4:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<form action="home" method="POST">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="userId" value="${editUser.id}">
    <label>User Name: </label>
    <input type="text" name="userName" value="${editUser.name}"><br>
    <label>User Email: </label>
    <input type="text" name="userEmail" value="${editUser.email}"><br>
    <label>User Country: </label>
    <input type="text" name="userCountry" value="${editUser.country}"><br>
    <button>Submit</button>
</form>
</body>
</html>
