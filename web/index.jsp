<%--
  Created by IntelliJ IDEA.
  User: mvcoder
  Date: 2018/5/24
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <div>
    <form action="/pdchecker/uploadImg" method="post" enctype="multipart/form-data">
      <input type="file" name="uploadImg"/>
      <input type="submit" value="提交" />
    </form>

    <img src="/pdchecker/WEB-INF/upload/0/微信图片_20180604013303.jpg" style="width: 200px; height: 200px"/>
  </div>
  </body>
</html>
