<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/27
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    //Manager Data
    String Comment_Id = new String(request.getParameter("commentid").getBytes("iso-8859-1"), "utf-8");
    String Comment_goodname_ = new String(request.getParameter("comment_goodname").getBytes("iso-8859-1"), "utf-8");
    String Comment_Content = new String(request.getParameter("comment_content").getBytes("iso-8859-1"), "utf-8");
    String Comment_UseName = new String(request.getParameter("comment_username").getBytes("iso-8859-1"), "utf-8");
    String Comment_createtime = new String(request.getParameter("comment_time").getBytes("iso-8859-1"), "utf-8");


%>
<!DOCTYPE html>
<html>
<body>
<form class="form-inline definewidth m20">
    评论内容：
    <input type="text" name="comment_content" id="comment_content" class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
    <button type="button" class="btn btn-primary" onclick="QueryComment($('comment_content').val)">查询</button>&nbsp;&nbsp;
    <button type="button" class="btn btn-success" id="addnew">新增用户</button>
</form>
<table class="table table-bordered table-hover definewidth m10"
       id="table_user"
>
    <thead>
    <tr>
        <th>Id</th>
        <th>评论内容</th>
        <th>评论人</th>
        <th>商品名字</th>
        <th>评论时间</th>
    </tr>
    </thead>

    <tbody id="Comment_tbody"></tbody>
</table>
</body>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/bootstrap.js"></script>
    <script type="text/javascript" src="/js/ckform.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/goodjs/mcomment.js"></script>
<%--<script type="text/javascript" src="/js/jquery.sorted.js"></script>--%>


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
</html>
<script>
    $(function () {

        LoadAllComment();


        $('#addnew').click(function () {

            window.location.href = "add.html";
        });


    });

    function del(id) {


        if (confirm("确定要删除吗？")) {

            var url = "index.html";

            window.location.href = url;

        }


    }
</script>