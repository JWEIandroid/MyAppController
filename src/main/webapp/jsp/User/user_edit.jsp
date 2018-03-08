<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/27
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
//    String Name = request.getParameter("name");
//    String Adress = request.getParameter("adress");
//    String Sex = request.getParameter("sex");
//    String Password = request.getParameter("password");
//    String Tel = request.getParameter("tel");
//    String Id = request.getParameter("id");
    String Password = new String(request.getParameter("password").getBytes("iso-8859-1"), "utf-8");
    String Name = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
    String Adress = new String(request.getParameter("adress").getBytes("iso-8859-1"), "utf-8");
    String Sex = new String(request.getParameter("sex").getBytes("iso-8859-1"), "utf-8");
    String Tel = new String(request.getParameter("tel").getBytes("iso-8859-1"), "utf-8");
    String Id = new String(request.getParameter("id").getBytes("iso-8859-1"), "utf-8");
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
    <script type="text/javascript" src="/js/userjs/user.js" charset="UTF-8"></script>


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
            <td width="10%" class="tableleft">id</td>
            <td><input id="id" type="text" name="id" value="<%= Id%>"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">登录名</td>
            <td><input id="tel" type="text" name="tel" value="<%= Tel%>"/></td>
        </tr>
        <tr>
            <td class="tableleft">密码</td>
            <td><input id="password" type="password" name="密码" value="<%= Password%>"/></td>
        </tr>
        <tr>
            <td class="tableleft">真实姓名</td>
            <td><input id="realname" type="text" name="realname" value="<%= Name%>"/></td>
        </tr>
        <tr>
            <td class="tableleft">地址</td>
            <td><input id="adress" type="text" name="adress" value="<%= Adress%>"/></td>
        </tr>
        <tr>
            <td class="tableleft">性别</td>
            <td><input id="sex" type="text" name="sex" value="<%= Sex%>"/></td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="button" class="btn btn-primary" onclick="updateUserData()">保存</button> &nbsp;&nbsp;
                <button type="button" class="btn btn-success" name="backid" id="backid" >返回列表
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
