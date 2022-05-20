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
        <th>address-list</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${clientList}" var="client">
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
        <tr>
        </c:forEach>
    </tbody>
</table>
<a href="index.jsp">Вернуться на главную</a>
</body>
</html>