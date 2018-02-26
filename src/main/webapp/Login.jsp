
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理系统</title>
    <link href="C:\Users\Administrator\Desktop\Swap\Butterfly_files\Login.css" rel="Stylesheet" type="text/css">
    <script src="C:\Users\Administrator\Desktop\Swap\Butterfly_files\jquery-1.7.1.min.js" type="text/javascript" language="JavaScript"></script>
    <script src="C:\Users\Administrator\Desktop\Swap\Butterfly_files\Junbo.Service.wn.js" type="text/javascript" language="JavaScript"></script>
    <script src="C:\Users\Administrator\Desktop\Swap\Butterfly_files\mLogin.js" type="text/javascript" language="JavaScript"></script>
</head>
<body>
<div id="logindiv" class="login">
    <img id="logoimg" src="llogo.png" alt="骏伯Butterfly">
    <div id="input1"><span>用户账号:</span><input id="UserName" value="18814129361" type="text" onkeydown="InputUserName()"><img
            id="e1" src="C:\Users\Administrator\Desktop\Swap\Butterfly_files\empty.png" alt="清空用户账号" onclick="ClearUserName()"></div>
    <div id="input2"><span>登录密码:</span><input id="Psw" value="123456" onkeydown="InputPsw()" type="password"><img
            id="e2" src="C:\Users\Administrator\Desktop\Swap\Butterfly_files\empty.png" alt="清空用户密码" onclick="ClearPsw()"></div>
    <div id="dlogin" onclick="Loginnew()">Login</div>
</div>


</body>
</html>