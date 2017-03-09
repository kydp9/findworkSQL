<%--
  Created by IntelliJ IDEA.
  User: ikydp
  Date: 02.09.2016
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>


<%@include file="includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Job Finder  v 0.05</title>
    <style type="text/css">
        span.error {
            color: red;
        }
    </style>
</head>
<body>
<h1>Job find v01</h1>
<form:form action="job.do" method="POST" commandName="vacancy">
    <table>

        <tr>
            <td>Job title </td>
            <td><form:input path="title" /></td>
            <td><span class="error"><form:errors path="title" /></span></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" name="action" value="Add" />
                <input type="submit" name="action" value="Edit" />
                <input type="submit" name="action" value="Delete" />
                <input type="submit" name="action" value="Search" />
                <input type="submit" name="action" value="GetId" />
                <input type="submit" name="action" value="Update" />
            </td>
        </tr>
    </table>
</form:form>
<br>
<table border="1">
    <th>ID</th>
    <th>title</th>
    <th>compaty</th>
    <th>site</th>
    <th>salary</th>
    <th>City</th>
    <th>updateDate</th>

    <c:forEach items="${vacancyList}" var="vacancy">
        <tr>
            <td>${vacancy.id}</td>
            <td><a href="${vacancy.url}"> ${vacancy.title}</a></td>
            <td>${vacancy.companyName}</td>
            <td>${vacancy.salary}</td>
            <td>${vacancy.siteName}</td>
            <td>${vacancy.city}</td>
            <td>${vacancy.updateDate}</td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
