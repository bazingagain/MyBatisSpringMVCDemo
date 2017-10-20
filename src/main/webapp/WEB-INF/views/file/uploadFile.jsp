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
    <form id="fileForm" method="post" enctype="multipart/form-data">

        <table cellpadding="5">
            <tr>
                <td>image name:</td>
                <td><input name="title" id="title" data-options="required:true"></td>
            </tr>
            <tr>
                <td>select file:</td>
                <td><input name="imageFile" id="imageFile" data-options="required:true, prompt:'choose a file...'"></td>
            </tr>
        </table>
    </form>
    <div style="text-align: center; padding: 5px">
        <a href="javascript:void(0)" onclick="submitForm()">commit</a>
        <a href="javascript:void(0)" onclick="clearForm()">reset</a>
    </div>
    <script>
        function submitForm() {
            $('#fileForm').form('submit', {
                url: "../file/upload",
                onSubmit: function () {
                    return true;
                },
                success: function (result, a, b) {
                    var jsonResult = $.parseJSON(result);
                    alert(jsonResult.info);
                }
            })
        }
    </script>
</body>
</html>
