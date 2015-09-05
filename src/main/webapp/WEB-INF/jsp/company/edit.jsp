<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Company</title>
    </head>
    <body>
        <h1>Edit Company</h1>
        <c:if test="${fn:length(errors) gt 0}">
            <p>Please correct the following errors in your submission:</p>
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </c:if>
        <form action="${pageContext.request.contextPath}/company/edit" method="POST">
            <input type="hidden" name="companyId" value="${company.companyId}"/>
            <br/>
            <label for="name">Name:</label>
            <input type="text" name="name" value="${company.name}"/>
            <br/>
            <label for="website">Website:</label>
            <input type="text" name="website" value="${company.website}"/>
            <br/>
            <label for="phoneNumber">Phone Number:</label>
            <input type="text" name="phoneNumber" value="${company.phoneNumber}"/>
            <br/>
            <label for="streetAddress">Street Address:</label>
            <input type="text" name="streetAddress" value="${company.streetAddress}"/>
            <br/>
            <label for="city">City:</label>
            <input type="text" name="city" value="${company.city}"/>
            <br/>
            <label for="state">State:</label>
            <input type="text" name="state" value="${company.state}"/>
            <br/>
            <label for="zipCode">Zip Code:</label>
            <input type="text" name="zipCode" value="${company.zipCode}"/>
            <br/>
            <input type="submit" name="Submit" value="Submit"/>
        </form>
    </body>
</html>
