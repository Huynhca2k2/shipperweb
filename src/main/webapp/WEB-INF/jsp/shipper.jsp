<%-- 
    Document   : shipper
    Created on : Aug 27, 2023, 5:43:09 PM
    Author     : huynh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-danger">quan ly shipper</h1>

<c:url value="/register/shipper" var="sendAction"/>
<form:form method="POST" action="${sendAction}" modelAttribute="shipper" enctype="multipart/form-data">
    <div class="form-group">
        <lable for="file">Image avatar shipper</lable>
        <form:input type="file" id="fileAvatar" path="fileAvatar" Class="form-control" />
    </div>
    <div class="form-group">
        <lable for="file">Image cmnd shipper</lable>
        <form:input type="file" id="fileCmnd" path="fileCmnd" Class="form-control" />
    </div>
    <div class="form-group">
        <input type="submit" value="them anh" class="btn btn-danger" />
    </div>
</form:form>
