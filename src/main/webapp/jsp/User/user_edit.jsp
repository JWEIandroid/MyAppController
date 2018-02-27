<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/27
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String testId=request.getParameter("id");

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
    <script type="text/javascript" src="/js/jquery.sorted.js"></script>
    <script type="text/javascript" src="/js/bootstrap.js"></script>


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
<form action="{:U('User/edit')}" method="post" class="definewidth m20">
    <input type="hidden" name="id" value="{$user.id}"/>
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">登录名</td>
            <td><input type="text" name="username" value="<%=testId%>"/></td>
        </tr>
        <tr>
            <td class="tableleft">密码</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td class="tableleft">真实姓名</td>
            <td><input type="text" name="realname" value="{$user.realname}"/></td>
        </tr>
        <tr>
            <td class="tableleft">地址</td>
            <td><input type="text" name="adress" value="{$user.adress}"/></td>
        </tr>
        <tr>
            <td class="tableleft">性别</td>
            <td><input type="text" name="sex" value="{$user.sex}"/></td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;<button
                    type="button" class="btn btn-success" name="backid" id="backid">返回列表
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
            // window.location.href = "{:U('User/index')}";
        });

    });
</script>
