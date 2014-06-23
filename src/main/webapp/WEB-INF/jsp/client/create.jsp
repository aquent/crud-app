<%-- 
    Document   : create
    Created on : Apr 22, 2011, 3:24:13 PM
    Author     : FMilens
--%>

<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" value="Create Client"/>
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
<form action="${context}/client/create" method="POST">
    <br/>
    <label for="companyName">Company Name:</label>
    <input type="text" name="companyName" value="${client.companyName}"/>
    <br/>
    <label for="uri">URL:</label>
    <input type="text" name="uri" value="${client.uri}"/>
    <br/>
    <label for="phone">Phone:</label>
    <input type="text" name="phone" value="${client.phone}"/>
    <br/>
    <label for="streetAddress">Street Address:</label>
    <input type="text" name="streetAddress" value="${client.streetAddress}"/>
    <br/>
    <label for="city">City:</label>
    <input type="text" name="city" value="${client.city}"/>
    <br/>
    <label for="state">State:</label>
    <input type="text" name="state" value="${client.state}"/>
    <br/>
    <label for="zipCode">Zip Code:</label>
    <input type="text" name="zipCode" value="${client.zipCode}"/>
    <br/>
    <input type="submit" name="Submit" value="Submit"/>
</form>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
