<%--
  Created by IntelliJ IDEA.
  User: leon
  Date: 20/10/2017
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test Paging</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<button onclick="test(this)" value="/role/selectRolesByMap">Test Map</button>
<button onclick="test(this)" value="/role/selectRolesByPOJO">Test POJO</button>
<button onclick="test(this)" value="/role/selectRolesByAnnotation">Test Annotation</button>
</body>

<script>
    function test(e) {
        console.log($(e).val());
        $.post($(e).val(), {roleName : null}, function (data) {
            console.log(data);
        })
    }
</script>

</html>
