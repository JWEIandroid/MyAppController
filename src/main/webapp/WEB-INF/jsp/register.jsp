<%--
  Created by IntelliJ IDEA.
  User: weij
  Date: 2017/12/24
  Time: 16:40
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
        $.post(url, function (data) {
            console.log(data);
            var users = $.parseJSON(data);
            console.log(users.length);

            for (i in users) {
                var user = users[i];
                $("#messageDiv").html("<br>" + user.name + "   -----   " + user.password);
            }
        });
    });

</script>


</body>
</html>
