<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="firstPlayer" value="${match.firstPlayer}" />
<c:set var="secondPlayer" value="${match.secondPlayer}" />
<c:set var="winner" value="${match.winnerName}" />
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/match.css">
    <title>Теннисный счёт</title>
</head>
<body>
<h1>Матч</h1>
<div class="table">
    <table>
        <thead>
        <tr>
            <th>Players</th>
            <th>Sets</th>
            <th>Games</th>
            <th>Points</th>
            <th>Increase point</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>${firstPlayer.name()}</th>
            <th>${firstPlayer.sets}</th>
            <th>${firstPlayer.games}</th>
            <th>${firstPlayer.points}</th>
            <th>
                <form action="${pageContext.request.contextPath}/matches/${uuid}" method="POST">
                    <input type="hidden" name="name" value="${firstPlayer.name}">
                    <button type="submit">Перейти</button>
                </form>
            </th>
        </tr>
        <tr>
            <th>${secondPlayer.name}</th>
            <th>${secondPlayer.sets}</th>
            <th>${secondPlayer.games}</th>
            <th>${secondPlayer.points}</th>
            <th>
                <form action="${pageContext.request.contextPath}/matches/${uuid}" method="POST">
                    <input type="hidden" name="name" value="${secondPlayer.name}">
                    <button type="submit">Перейти</button>
                </form>
            </th>
        </tr>
        </tbody>
    </table>

</div>

</body>
</html>
