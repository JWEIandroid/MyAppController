<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/27
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//    String Type = new String(request.getParameter("type").getBytes("iso-8859-1"), "utf-8");
    String Type = request.getParameter("type");
%>
<!DOCTYPE html>
<html>
<head></head>
<style>
    a {
        color: #0088cc;
        text-decoration: none;
        cursor: pointer;
        margin-left: 5px;
    }
</style>
<body>
<form class="table form-inline definewidth m20" >
    用户名称：
    <input type="text" name="username" id="username"class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
    <button type="button" class="btn btn-primary" onclick="QueryUser(<%= Type%>)">查询</button>&nbsp;&nbsp; <button type="button" class="add btn btn-success" id="addnew">新增用户</button>
</form>
<table class="table table-bordered table-hover definewidth m10"
       id="table_user"
>
    <thead>
    <tr>
        <th>用户id</th>
        <th>用户名称</th>
        <th>真实姓名</th>
        <th>最后登录时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <%--<tr>--%>
        <%--<td>2</td>--%>
        <%--<td>admin</td>--%>
        <%--<td>管理员</td>--%>
        <%--<td></td>--%>
        <%--<td>--%>
            <%--<a href="edit.html">编辑</a>--%>
        <%--</td>--%>
    <%--</tr>--%>
    <tbody  id="User_tbody"></tbody>
</table>

<%--// Add  2018/3/4--%>
<div id="modal-table-update" class="updateModal modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header no-padding">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    更新用户信息
                </div>
            </div>

            <form class="form-horizontal" style="margin:1rem;">
                <div class="form-group form-group-lg">
                    <input type="hidden" class="update-id">
                    <label for="username" class="col-sm-3 control-label" style="font-size:16px;">用户名:</label>
                    <div class="col-sm-5">
                        <input type="text" id="add_username" name="add_username" class="username form-control" style="font-size:16px;" required="required">
                    </div>
                </div>


                <div class="modal-footer-update no-margin-top">
                    <button class="btn btn-danger" data-dismiss="modal">
                        取消
                    </button>
                    <button class="updatebtn btn btn-primary">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>


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
    <script type="text/javascript" src="/js/userjs/user.js"></script>
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

        if(<%= Type.equals("0")%>){
            loadUserData();
        }else if (<%= Type.equals("1")%>){
            loadManagerData();
        }



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