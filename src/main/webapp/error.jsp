<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ошибка ввода данные</title>
</head>
<body>
<h1> Поле ${errorField} - не прошло валидацию по причине ${errorReason} </h1>
<a href="create.jsp" style="display: block">Вернуть к форме создания</a>
<a href="update.jsp" style="display: block">Вернуть к форме редактирования</a>
<a href="index.jsp" style="display: block">Вернуться на главную</a>
</body>
</html>