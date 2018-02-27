<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理系统</title>
    <link href="/css/Login.css" rel="Stylesheet" type="text/css">
    <script src="/js/jquery-1.7.1.min.js" type="text/javascript"></script>
    <script src="/js/mLogin.js" type="text/javascript" language="JavaScript"></script>
</head>
<body>
<div id="logindiv" class="login">
    <%--<img id="logoimg" src="images/llogo.jpg" alt="骏伯Butterfly"/>--%>
    <div id="input1">
        <span>用户账号:</span>
        <input id="UserName" type="text" onkeydown="InputUserName()">
        <img id="e1" src="/images/empty.png" alt="清空用户账号" onclick="ClearUserName()">
    </div>
    <div id="input2"><span>登录密码:</span><input id="Psw" onkeydown="InputPsw()" type="password">
        <img id="e2" src="/images/empty.png" alt="清空用户密码" onclick="ClearPsw()">
    </div>
    <div id="dlogin" onclick="Loginnew()">登录</div>
</div>


</body>
</html>