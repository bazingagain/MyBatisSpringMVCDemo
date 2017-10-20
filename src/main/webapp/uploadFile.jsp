<%--
  Created by IntelliJ IDEA.
  User: leon
  Date: 20/10/2017
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload File Test</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
    <form method="post" action="/file/upload" enctype="multipart/form-data">

        <table cellpadding="5">
            <tr>
                <td>image name:</td>
                <td><input name="title" id="title" data-options="required:true"></td>
            </tr>
            <tr>
                <td>select file:</td>
                <td><input type="file" name="imageFile" id="imageFile"></td>
            </tr>
            <tr>
                <td><input type="submit" value="submit"></td>
            </tr>
        </table>
    </form>
</body>
</html>
