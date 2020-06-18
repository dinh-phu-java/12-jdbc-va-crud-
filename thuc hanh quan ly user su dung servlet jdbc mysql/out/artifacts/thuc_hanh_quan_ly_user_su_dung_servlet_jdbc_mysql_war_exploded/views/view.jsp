<%@ page import="java.util.ArrayList" %>
<%@ page import="model.User" %>
<%@ page import="com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput" %>
<%@ page import="java.io.IOException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dinh Phu
  Date: 6/18/2020
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home View</title>
</head>
<body>
    <p>User List</p>
    <table border="1">
        <tr>
            <th>User Id</th>
            <th>User Name</th>
            <th>User Email</th>
            <th>Country</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.country}</td>
                <td><a href="${pageContext.request.contextPath}/home?action=edit&amp;id=${user.id}">Edit</a></td>
                <td><a href="${pageContext.request.contextPath}/home?action=delete&amp;id=${user.id}">Delete</a></td>
            </tr>
        </c:forEach>

    </table>

</body>
</html>
