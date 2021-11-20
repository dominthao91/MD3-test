<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/20/2021
  Time: 10:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Borrow List</title>
</head>
<body>
<div align="center">
    <table border="1px">
        <caption>List of Card</caption>
        <tr>
            <th>CardId</th>
            <th>name Book</th>
            <th>author</th>
            <th>Name Student</th>
            <th>Class</th>
            <th>Borrow Day</th>
            <th>Return Day</th>
            <th>Action</th>
        </tr>
        <c:forEach var="card" items="${cardList}">
            <tr>
                <td>
                    <c:out value="${card.id}"/>
                </td>
                <td>
                    <c:out value="${card.book.name}"/>
                </td>
                <td>
                    <c:out value="${card.book.author}"/>
                </td>
                <td>
                    <c:out value="${card.student.name}"/>
                </td>
                <td>
                    <c:out value="${card.student.className}"/>
                </td>
                <td>
                    <c:out value="${card.borrowDate}"/>
                </td>
                <td>
                    <c:out value="${card.returnDate}"/>
                </td>
                <td>
                    <a href="/borrows?action=returnBook&id=${card.id}">Return</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
