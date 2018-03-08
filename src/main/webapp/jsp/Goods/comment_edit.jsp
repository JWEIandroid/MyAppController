<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/27
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
//    String Comment_Content = request.getParameter("comment_content");
//    String Comment_Time = request.getParameter("comment_time");
//    String Comment_ID = request.getParameter("commentid");
    String Comment_ID = new String(request.getParameter("commentid").getBytes("iso-8859-1"), "utf-8");
    String Comment_Content = new String(request.getParameter("comment_content").getBytes("iso-8859-1"), "utf-8");
    String Comment_Time  = new String(request.getParameter("comment_time").getBytes("iso-8859-1"), "utf-8");
//    String Comment_GoodName = new String(request.getParameter("comment_goodname").getBytes("iso-8859-1"), "utf-8");
//    String Comment_UserName = new String(request.getParameter("comment_username").getBytes("iso-8859-1"), "utf-8");
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
    <script type="text/javascript" src="/js/goodjs/mcomment.js" charset="UTF-8"></script>


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
            <td width="10%" class="tableleft">评论内容</td>
            <td><input id="comment_content" type="text" name="comment_content" value="<%= Comment_Content%>"/></td>
        </tr>

        <tr>
            <td class="tableleft">评论时间</td>
            <td><input id="comment_time" type="text" name="comment_time" value="<%= Comment_Time%>"/></td>
        </tr>

        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="button" class="btn btn-primary" onclick="UpdateComment(<%= Comment_ID%>)">保存</button> &nbsp;&nbsp;
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
            // window.location.href = "{:U('User/index')}";
            javascript:history.go(-1);
        });

    });
</script>
