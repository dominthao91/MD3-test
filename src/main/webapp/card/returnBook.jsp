<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/20/2021
  Time: 10:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <input type="text" name="idBook" value="${card.book.id} " hidden>
    <fieldset>
        <legend>Return Book</legend>
        <table>
            <tr>
                <td>ID</td>
                <td><input type="text" name="id" value="${card.id}"></td>
            </tr>
            <tr>
                <td>Name Book</td>
                <td><input type="text" name="book" value="${card.book.name}"></td>
            </tr>
            <tr>
                <td>Name Student</td>
                <td><input type="text" name="nameStudent" value="${card.student.name}"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Return"></td>
                <td><a href="/borrows">cancel</a> </td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
