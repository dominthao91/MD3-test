<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/19/2021
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List Book</title>
</head>
<body>
<center>LiBraRy</center>
<center>
    <p><a href="/borrows?action=">List Card</a></p>
</center>
<div align="center">
    <table border="1">
        <caption>List Book</caption>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>author</th>
            <th>Note</th>
            <th>quantity</th>
            <th>action</th>
        </tr>
        <c:forEach var="book" items="${book}">
            <tr>
                <td>
                    <c:out value="${book.id}"/>
                </td>
                <td>
                    <c:out value="${book.name}"/>
                </td>
                <td>
                    <c:out value="${book.author}"/>
                </td>
                <td>
                    <c:out value="${book.note}"/>
                </td>
                <td>
                    <c:out value="${book.quantity}"/>
                </td>
                <td>
                    <a href="books?action=borrow&id=${book.id}">borrow</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
