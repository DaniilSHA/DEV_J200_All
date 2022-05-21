<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>View client list</title>
</head>
<body>
<table border="1px solid black">
    <thead>
    <tr>
        <th>id</th>
        <th>тип устройства</th>
        <th>модель устройства</th>
        <th>ip</th>
        <th>адрес</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${clientList}" var="client">
    <c:if test="${client != null}">
    <tr>
        <td>${client.idClient}</td>
        <td>${client.type}</td>
        <td>${client.model}</td>
        <td>${client.ip}</td>
        <td>
            <c:forEach items="${client.addressList}" var="adrress">
                <p> ${adrress.idAddress}, ${adrress.city}, ${adrress.street}, ${adrress.num}, ${adrress.subnum}, ${adrress.flat}, ${adrress.extra} </p>
            </c:forEach>
        </td>
        <td>
            <form method="post" action="delete">
                <input type="hidden" name="deletionId" value="${client.idClient}">
                <input type="submit" value="Удалить">
            </form>
        </td>
    <tr>
    </c:if>
    </c:forEach>
    </tbody>
</table>
<a href="index.jsp" style="display: block">Вернуться на главную</a>
<a href="create.jsp" style="display: block">Создать нового клиента</a>
<a href="update.jsp" style="display: block">Изменить данные о клиенте</a>
</body>
</html>