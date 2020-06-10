<%@ page isELIgnored="false" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>TTF Data</title>
</head>
<body>
<div class="container">
    <p class="h3">TTF Data (Last Refresh: <c:out value="${refresh}"/>)</p>
    <table class="table table-hover">
        <thead class="thead-dark">
        <tr>
            <th class="p-2">Trading Day</th>
            <th class="p-2">Month+1</th>
            <th class="p-2">Month+2</th>
            <th class="p-2">Month+3</th>
            <th class="p-2">Month+4</th>
            <th class="p-2">Month+5</th>
            <th class="p-2">Quarter+1</th>
            <th class="p-2">Quarter+2</th>
            <th class="p-2">Quarter+3</th>
            <th class="p-2">Quarter+4</th>
        </tr>
        </thead>
        <tbody class="table-striped">
        <c:forEach items="${list}" var="ttf">
            <tr>
                <td class="p-2" title="Trading Day">${ttf.getDate()}</td>
                <td class="p-2">${ttf.getMonthPlusOne()}</td>
                <td class="p-2">${ttf.getMonthPlusTwo()}</td>
                <td class="p-2">${ttf.getMonthPlusThree()}</td>
                <td class="p-2">${ttf.getMonthPlusFour()}</td>
                <td class="p-2">${ttf.getMonthPlusFive()}</td>
                <td class="p-2">${ttf.getQuarterPlusOne()}</td>
                <td class="p-2">${ttf.getQuarterPlusTwo()}</td>
                <td class="p-2">${ttf.getQuarterPlusThree()}</td>
                <td class="p-2">${ttf.getQuarterPlusFour()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>