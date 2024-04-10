<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sua</title>
</head>
<body>
<form action="/sach/update?id=${data.id}" method="post">
    <label>Ma</label>
    <input type="text" name="ma" value="${data.ma}">
    <label>Ten</label>
    <input type="text" name="ten" value="${data.ten}">
    <label>IdNXB</label>
    <input type="text" name="idNXB" value="${data.idNXB}">
    <label>NXB</label>
    <button type="submit">Sua</button>
</form>
</body>
</html>
