<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Tennis</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/matches.css">
</head>
<body>
<h1>Прошедшие матчи:</h1>
<table>
    <thead>
    <tr>
        <th>Первый игрок</th>
        <th>Второй игрок</th>
        <th>Победитель</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="matches" items="${requestScope.matches.matches}">
        <tr>
            <td>${matches.firstPlayerName}</td>
            <td>${matches.secondPlayerName}</td>
            <td class="winner">${matches.winnerName}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="navigation">
    <p>Текущая страница: ${requestScope.matches.currentPage} из ${requestScope.matches.totalPages}</p>
    <div class="btn">
        <a href="${pageContext.request.contextPath}/matches?pageNumber=${requestScope.matches.currentPage-1}">
            <button ${matches.currentPage < 2 ? 'disabled' : ''}>предыдущая страница</button>
        </a>
        <a href="${pageContext.request.contextPath}/matches?pageNumber=${requestScope.matches.currentPage+1}">
            <button ${matches.currentPage == matches.totalPages ? 'disabled' : ''}>след страница</button>
        </a>
    </div>

</div>


</body>
</html>
