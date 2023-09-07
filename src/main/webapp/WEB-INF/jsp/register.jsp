<%-- 
    Document   : login
    Created on : Aug 27, 2023, 10:43:43 PM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2 class="text-center">dang ky</h2>

<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>
</c:if>

<c:url value="/register" var="action"/>
<form:form method="post" action="${action}" modelAttribute="account">
    <div class="form-group">
        <label for="username">User name</label>
        <form:input type="text" id="username" path="username" class="form-control"/>
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <form:input type="password" id="password" path="password" class="form-control"/>
    </div>
     <div class="form-group">
        <label for="password">Confirm password</label>
        <form:input type="password" id="confirmPassword" path="confirmPassword" class="form-control"/>
    </div>
    <div class="form-group">
        <label for="username">Full name</label>
        <form:input type="text" id="fullName" path="fullName" class="form-control"/>
    </div>
    <div class="form-group">
        <label for="username">Email</label>
        <form:input type="email" id="email" path="email" class="form-control"/>
    </div>
    <div class="form-group">
        <label for="username">Phone</label>
        <form:input type="text" id="phone" path="phone" class="form-control"/>
    </div>
    <div class="form-group">
        <label for="username">Address</label>
        <form:input type="text" id="address" path="address" class="form-control"/>
    </div>
    <div class="form-group">
        <input type="submit" value="dang ky" class="btn btn-danger"/>
    </div>
</form:form>>