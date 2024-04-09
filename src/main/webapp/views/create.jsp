<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Them</title>
</head>
<body>
<form action="/sach/store" method="post">
    <label>Ma</label>
    <input type="text" name="ma">
    <label>Ten</label>
    <input type="text" name="ten">
    <label>IdNXB</label>
    <input type="text" name="idNXB">
    <label>NXB</label>
    <select>
        <c:forEach items="${nxb}" var="list">
            <option>${list.ten}</option>
        </c:forEach>
    </select>
    <span style="color: red">${error}</span>
    <button type="submit">Add</button>
</form>
</body>
</html>
