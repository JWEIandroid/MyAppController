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
                    $("#messageDiv").html("<br>" + user.id + "   -----   " + user.tel);
                }
            });
    });

</script>



</body>
</html>
