<%--
  Created by IntelliJ IDEA.
  User: leon
  Date: 17/10/2017
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Role</title>
</head>
<body>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>roleNmae</th>
            <th>note</th>
            <th>createDate</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><c:out value="${role.id}"/></td>
            <td><c:out value="${role.roleName}"/></td>
            <td><c:out value="${role.note}"/></td>
            <td><c:out value="${role.createDate}"/></td>
        </tr>
        </tbody>
    </table>
<a href="/uploadFile.jsp">jump upload file</a>
</body>
</html>
