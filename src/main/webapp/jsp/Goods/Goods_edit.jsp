<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/27
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    //Manager Data
    String Good_Id = new String(request.getParameter("goodid").getBytes("iso-8859-1"), "utf-8");
    String Good_Name = new String(request.getParameter("goodname").getBytes("iso-8859-1"), "utf-8");
    String Good_Price = new String(request.getParameter("goodprice").getBytes("iso-8859-1"), "utf-8");
    String Good_Createtime_ = new String(request.getParameter("good_createtime").getBytes("iso-8859-1"), "utf-8");
    String Good_Status = new String(request.getParameter("goodstatus").getBytes("iso-8859-1"), "utf-8");


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
    <script type="text/javascript" src="/js/goodjs/good.js"></script>


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
            <td width="10%" class="tableleft">商品Id</td>

            <td><input  id="good_id" type="text" name="good_id" value="<%= Good_Id%>"/></td>
        </tr>

        <tr>
            <td width="10%" class="tableleft">商品名称</td>
            <td><input  id="good_name" type="text" name="good_name" value="<%= Good_Name%>"/></td>
        </tr>

        <tr>
            <td class="tableleft">状态</td>
            <td><input id="good_status" type="text" name="good_status" value="<%= Good_Status%>"/></td>
        </tr>

        <tr>
            <td class="tableleft">上架时间</td>
            <td><input id="good_time" type="text"  name="good_time" value="<%= Good_Createtime_%>"/></td>
        </tr>

        <tr>
            <td class="tableleft">售价</td>
            <td><input id="good_price"  type="text" name="good_price" value="<%= Good_Price%>"/></td>
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
            <button type="button" class="btn btn-primary" onclick=UpdateGoods(<%= Good_Id%>)>保存</button> &nbsp;&nbsp;
            <button type="button" class="btn btn-success" name="backid" id="backid" onclick="custom_close()">返回列表
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
