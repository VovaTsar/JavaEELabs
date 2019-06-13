<%--
  Created by IntelliJ IDEA.
  User: THY
  Date: 06.03.2019
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>$Title$</title>
</head>
<body>
<h1>Додавання в базу</h1><br />
<form method="post" action="/Servlet_war_exploded/add">
  <label><input type="text" name="brand"></label>brand<br>
  <label><input type="text" name="captain"></label>captain<br>
  <label><input type="text" name="engine"></label>engine<br>
  <label><input type="number" name="series"></label>series<br>
  <input type="submit" value="ok" name="ok" onclick="location.href='/Servlet_war_exploded/index1.jsp'"><br>
</form>
</body>
</html>