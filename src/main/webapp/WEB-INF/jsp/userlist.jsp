<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>


<html>
<head>
    <title>UserList</title>
</head>
<body>

<script>

    $('#sender').click(function () {
        var url = "";
        $.post(
            url,
            function (data) {
                console.log(data);
                var users = $.parseJSON(data);
                console.log(users.length);

                for (i in users) {
                    var user = users[i];
                    $("#messageDiv").html("<br>" + user.id + "   -----   " + user.phone);
                }
            });
    });

</script>


<%--<table align='center' border='1' cellspacing='0'>--%>
<%--<tr>--%>
<%--<td>id</td>--%>
<%--<td>phone</td>--%>
<%--</tr>--%>
<%--<c:forEach items="${cs}" var="c" varStatus="st">--%>
<%--<tr>--%>
<%--<td>${c.code}</td>--%>
<%--<td>${c.phone}</td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--</table>--%>


</body>
</html>
