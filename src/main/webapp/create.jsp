<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create form</title>
</head>
<body>
<h1 style="text-align: center">Форма создания клиента</h1>
<form action="create" method="post">
    <div style="display: flex; flex-direction: row">
        <div style="margin: 20px; padding: 20px; background: #e8c0bd; border: 2px solid black; border-radius: 5px; flex:1">
            <h1>Введите информацию о клиенте</h1>
            <div>
                <label for="type">тип устройтсва: </label>
                <input type="text" name="clientType" id="type">
                <br>
                <label for="model">модель устройтсва: </label>
                <input type="text" name="clientModel" id="model">
                <br>
                <label for="ip">сетевой адресс: </label>
                <input type="text" name="clientIp" id="ip">
            </div>
        </div>
        <div style="margin: 20px; padding: 20px; background: #e8c0bd; border: 2px solid black; border-radius: 5px; flex:1">
            <h1>Введите информацию об адресе клиента</h1>
            <div>
                <label for="city">город: </label>
                <input type="text" name="adrCity" id="city">
                <br>
                <label for="street">улица: </label>
                <input type="text" name="adrStreet" id="street">
                <br>
                <label for="home">номер дома: </label>
                <input type="number" name="adrHomeNumber" id="home">
                <br>
                <label for="sub">корпус: </label>
                <input type="number" name="adrSubNumber" id="sub">
                <br>
                <label for="flat">номер квартиры: </label>
                <input type="number" name="adrFlatNumber" id="flat">
                <br>
                <label for="extra">доп. информация: </label>
                <input type="text" name="adrExtra" id="extra">
            </div>
        </div>
    </div>
    <div style="display: flex; flex-direction: row;">
        <button type="submit" style="margin: 50px">Отправить данные на сервер</button>
        <button type="reset" style="margin: 50px">Отчистить форму</button>
    </div>

</form>
</body>
</html>