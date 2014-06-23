<%-- 
    Document   : create
    Created on : Apr 22, 2011, 3:24:13 PM
    Author     : FMilens
--%>

<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" value="Create Person"/>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<c:if test="${fn:length(errors) gt 0}">
    <p>Please correct the following errors in your submission:</p>
    <ul>
        <c:forEach items="${errors}" var="error">
            <li>${error}</li>
        </c:forEach>
    </ul>
</c:if>
<form action="${context}/person/create" method="POST">
    <br/>
    <label for="firstName">First Name:</label>
    <input type="text" name="firstName" value="${person.firstName}"/>
    <br/>
    <label for="lastName">Last Name:</label>
    <input type="text" name="lastName" value="${person.lastName}"/>
    <br/>
    <label for="clientId">Client:</label>
    <select name="clientId">
        <option>Select One</option>
        <c:forEach items="${clients}" var="client">
        <option value="${client.clientId}">${client.companyName}</option>
        </c:forEach>
    </select>
    <br/>
    <label for="emailAddress">Email Address:</label>
    <input type="text" name="emailAddress" value="${person.emailAddress}"/>
    <br/>
    <label for="streetAddress">Street Address:</label>
    <input type="text" name="streetAddress" value="${person.streetAddress}"/>
    <br/>
    <label for="city">City:</label>
    <input type="text" name="city" value="${person.city}"/>
    <br/>
    <label for="state">State:</label>
    <input type="text" name="state" value="${person.state}"/>
    <br/>
    <label for="zipCode">Zip Code:</label>
    <input type="text" name="zipCode" value="${person.zipCode}"/>
    <br/>
    <input type="submit" name="Submit" value="Submit"/>
</form>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
