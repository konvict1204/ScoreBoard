<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Новый матч</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/newmatch.css"/>
</head>
<body>
<form action="matches" method="POST">
    <label>Имя первого игрока:
        <input type="text" name="firstPlayerName" required>
    </label>
    <br><br>
    <label>Имя второго игрока:
        <input type="text" name="secondPlayerName" required>
    </label>
    <br><br>
    <button type="submit">Отправить</button>
</form>

</body>
</html>
