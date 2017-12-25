<%--
  Created by IntelliJ IDEA.
  User: weij
  Date: 2017/12/25
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<script>

    $('#sender').click(function () {
        var url = "";
        url,
        $.post(
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
