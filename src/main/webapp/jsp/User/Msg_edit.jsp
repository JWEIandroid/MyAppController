<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/27
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    String Id_Message = request.getParameter("msgid");
    String content_Message = request.getParameter("comment_content");

%>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/bootstrap.js"></script>
    <script type="text/javascript" src="/js/userjs/msgboard.js"></script>


    <style type="text/css">
        body {
            padding-bottom: 40px;
        }

        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>
<body>
<form class="definewidth m20" method="post">
    <input type="hidden" name="id" value="{$user.id}"/>
    <table class="table table-bordered table-hover definewidth m10">


        <tr>
            <td width="10%" class="tableleft">留言内容</td>
            <td><input  id="message_content" type="text" name="message_content" value="<%= content_Message%>"/></td>
        </tr>
        <%--<tr>--%>
        <%--<td class="tableleft">真实姓名</td>--%>
        <%--<td><input id="realname" type="text" name="realname" value=""/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td class="tableleft">地址</td>--%>
        <%--<td><input id="adress" type="text" name="adress" value=""></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td class="tableleft">性别</td>--%>
        <%--<td><input id="sex" type="text" name="sex" value=""/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <td class="tableleft"></td>
        <td>
            <button type="button" class="btn btn-primary" onclick="UpdateMessage(<%= Id_Message%>)">保存</button> &nbsp;&nbsp;
            <button type="button" class="btn btn-success" name="backid" id="backid">返回列表
            </button>
        </td>
        </tr>
    </table>
</form>
</body>
</html>


<script>
    $(function () {
        $('#backid').click(function () {
            javascript:history.go(-1);
        });

    });
</script>
