<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sach</title>
</head>
<body>
<div class="container">
    <a href="/sach/create">
        <button>Add</button>
    </a>
    <table border="1">
        <thead>
        <th>STT</th>
        <th>ID</th>
        <th>Ma</th>
        <th>Ten</th>
        <th>ID NXB</th>
        <th>Action</th>
        </thead>
        <c:forEach items="${sachList}" var="sach" varStatus="i">
            <tr>
                <td>${i.index +1}</td>
                <td>${sach.id}</td>
                <td>${sach.ma}</td>
                <td>${sach.ten}</td>
                <td>${sach.idNXB}</td>
                <td>
                    <a href="/sach/delete?idSach=${sach.id}">
                        <button>delete</button>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div>
        <c:if test="${curentPage > 1}">
            <a href="/sach/index?page=1">first</a>
            <a href="/sach/index?page=${curentPage-1}">prev</a>
        </c:if>
        <c:forEach begin="1" end="${tutolPage}" varStatus="i">
            <c:url value="/sach/index?page=${i.index}" var="pageNumberUrl">
                <c:param name="page" value="${i.index}"/>
            </c:url>

            <c:choose>
                <c:when test="${i.index == curentPage}">
                    <strong>${i.index}</strong>
                </c:when>
                <c:otherwise>
                    <a href="${pageNumberUrl}">${i.index}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${curentPage < tutolPage}">
            <a href="/sach/index?page=${curentPage+1}">Next</a>
            <a href="/sach/index?page=${tutolPage}">Last</a>
        </c:if>
    </div>
</div>
</body>
</html>
