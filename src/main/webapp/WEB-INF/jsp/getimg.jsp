<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/2
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ page import="java.io.*" %>


<%--<%--%>
<%--//    String ppath=request.getParameter("ppath");--%>
<%--//    String file = "D:/"+ppath;--%>
    <%--String file = (String) request.getAttribute("img");--%>
    <%--System.out.println("接受到的文件路径是："+file);--%>
    <%--FileInputStream in = new FileInputStream(new File(file));--%>
    <%--OutputStream o = response.getOutputStream();--%>
    <%--int l = 0;--%>
    <%--byte[] buffer = new byte[4096];--%>
    <%--while((l = in.read(buffer)) != -1){--%>
        <%--o.write(buffer,0,l);--%>
    <%--}--%>
    <%--o.flush();--%>
    <%--in.close();--%>
    <%--o.close();--%>
    <%--request.getRequestDispatcher("index.jsp").forward(request, response);--%>
<%--%>--%>

<img src="<%= (String) request.getAttribute("img") %>"/>

</body>
</html>
