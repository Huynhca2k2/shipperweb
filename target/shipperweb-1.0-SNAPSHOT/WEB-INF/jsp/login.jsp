<%-- 
    Document   : login
    Created on : Aug 27, 2023, 10:43:43 PM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2 class="text-center">dang nhap</h2>

<c:if test="${param.error != null}">
    <div class="alert alert-danger">
        ban da nhap sai mat khau hoac password!!!
    </div>
</c:if>
<c:if test="${param.accessDenied != null}">
    <div class="alert alert-danger">
        ban khong co quyen truy cap!!!
    </div>
</c:if>

<c:url value="/login" var="action"/>
<form method="post" action="${action}">
    <div class="form-group">
        <label for="username">User name</label>
        <input type="text" id="username" name="username" class="form-control" />
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" class="form-control" />
    </div>
    <div class="form-group">
        <input type="submit" value="dang nhap" class="btn btn-danger"/>
    </div>
</form>