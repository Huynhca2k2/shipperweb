<%-- 
    Document   : header.jsp
    Created on : Aug 27, 2023, 4:24:20 PM
    Author     : huynh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <a class="navbar-brand" href="#">logo</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-link active" href="/shipperweb">trang chu</a>
      <a class="nav-link" href="/shipperweb/register">dang ky</a>
      <c:if test="${pageContext.request.userPrincipal.name == null}">
          <a class="nav-link text-danger" href="<c:url value="/login"/>">dang nhap</a>
      </c:if>
      <c:if test="${pageContext.request.userPrincipal.name != null}">
        <a class="nav-link text-danger" href="<c:url value="/"/>">${pageContext.request.userPrincipal.name}</a>
        <a class="nav-link text-danger" href="<c:url value="/logout"/>">log out</a>
          <sec:authorize access="hasRole('ROLE_USER')">
            <a class="nav-link text-danger" 
             href="<c:url value="/register/shipper"/>">dang ky shipper</a>
        </sec:authorize>
      </c:if>

      
    </div>
  </div>
</nav>
