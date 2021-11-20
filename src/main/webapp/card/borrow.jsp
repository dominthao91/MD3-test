<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/20/2021
  Time: 8:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Borrow Book</title>
</head>
<body>
<form method="post">
    <input type="hidden" name="id" value="${book.id}">
    <fieldset>
        <legend>Borrow Book</legend>
        <table>
            <tr>
                <td>ID</td>
                <td><input type="text" name="id" value="${book.id}" ></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" value="${book.name}" readonly></td>
            </tr>
            <tr>
                <td>Student Name</td>
                <td>
                    <select name="student">
                        <c:forEach items="${student}" var="student">
                            <option value="${student.id}">${student.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Borrow Date</td>
                <td><input type="text" name="borrow" ></td>
            </tr>
            <tr>
                <td>Return Book </td>
                <td><input type="text" name="return"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Borrow"></td>
                <td><a href="/books">cancel</a> </td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
