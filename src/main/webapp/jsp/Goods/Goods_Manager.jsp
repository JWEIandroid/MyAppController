<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/27
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
    <%--String Type = new String(request.getParameter("type").getBytes("iso-8859-1"), "utf-8");--%>
<%--%>--%>
<!DOCTYPE html>
<html>
<head>
</head>

<style>
    a {
        color: #0088cc;
        text-decoration: none;
        cursor: pointer;
        margin-left: 5px;
    }
</style>
<body>
<form class="form-inline definewidth m20" >
    商品名称：
    <input type="text" name="goodname" id="goodname"class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
    <button type="button" class="btn btn-primary" onclick="QueryGoods($('#goodname').val())">查询</button>&nbsp;&nbsp; <button type="button" class="btn btn-success" id="addnew">新增商品</button>
</form>
<table class="table table-bordered table-hover definewidth m10"
       id="table_goods"
>
    <thead>
    <tr>
        <th>商品id</th>
        <th>商品名称</th>
        <th>状态</th>
        <th>售价</th>
        <th>卖家名字</th>
        <th>上架时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody  id="Goods_tbody"></tbody>
</table>
</body>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/bootstrap.js"></script>
    <script type="text/javascript" src="/js/ckform.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/goodjs/good.js"></script>
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

        LoadAllGoods();''



    });

    function del(id)
    {


        if(confirm("确定要删除吗？"))
        {

            var url = "index.html";

            window.location.href=url;

        }




    }
</script>