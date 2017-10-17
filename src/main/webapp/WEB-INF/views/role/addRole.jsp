<%--
  Created by IntelliJ IDEA.
  User: leon
  Date: 17/10/2017
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Role</title>
</head>
<body>
    <form method="post" enctype="multipart/form-data">
        roleName:<input type="text" name="role.roleName"/>
        note:<input type="text" name="role.note"/>
        <input type="submit" value="add a role" />
    </form>
</body>
</html>
