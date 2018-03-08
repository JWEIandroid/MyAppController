
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body>
<form class="form-inline definewidth m20">
    留言板：
    <input type="text" name="message_content" id="message_content" class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
    <button type="button" class="btn btn-primary" onclick="QueryMsg()">查询</button>&nbsp;&nbsp;
    <%--<button type="button" class="btn btn-success" id="addnew">新增留言</button>--%>
</form>
<table class="table table-bordered table-hover definewidth m10"
       id="table_user"
>
    <thead>
    <tr>
        <th>Id</th>
        <th>用户</th>
        <th>留言人</th>
        <th>留言内容</th>
        <th>留言时间</th>
        <th>操作</th>
    </tr>
    </thead>

    <tbody id="Message_tbody"></tbody>
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
</html>


<script>
    $(function () {

        LoadAllMsg();

    });



    function del(id) {


        if (confirm("确定要删除吗？")) {

            var url = "index.html";

            window.location.href = url;

        }


    }
</script>