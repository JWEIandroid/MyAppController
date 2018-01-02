<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.OutputStream" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/2
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    String file = (String) request.getAttribute("img");
    FileInputStream in = new FileInputStream(new File(file));
    OutputStream o = response.getOutputStream();
    int l = 0;
    byte[] buffer = new byte[4096];
    while ((l = in.read(buffer)) != -1) {
        o.write(buffer, 0, l);
    }
    o.flush();
    in.close();
    o.close();
%>

</body>
</html>
