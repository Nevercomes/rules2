<%--
  Created by IntelliJ IDEA.
  User: ltaoj
  Date: 17-6-8
  Time: 下午3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录测试</title>
</head>
<body>
<form action="/account/login" method="post">
    <input name="studentId" type="text" placeholder="请输入学号"/>
    <input name="password" type="password" placeholder="请输入密码"/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>
