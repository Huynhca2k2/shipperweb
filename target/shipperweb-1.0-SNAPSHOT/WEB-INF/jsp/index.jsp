<%-- 
    Document   : index.jsp
    Created on : Aug 6, 2023, 2:39:59 AM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <div class="btn btn-danger">
        <a href="<c:url value="/admin/shippers"/>">them shipper</a>
    </div>
</sec:authorize>
    <ul>
        <c:forEach var="acc" items="${accounts}">
            <li>${acc.username} - ${acc.password} - ${acc.roleAccount}</li>
        </c:forEach>        
    </ul>

