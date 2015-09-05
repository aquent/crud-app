<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Person</title>
    </head>
    <body>
        <h1>View Person</h1>
        <div>
        	<c:out value="${person.firstName}"/> <c:out value="${person.lastName}"/><br/>
        	<c:out value="${person.emailAddress}"/><br/>
        	<c:out value="${person.streetAddress}"/><br/>
        	<c:out value="${person.city}"/>, <c:out value="${person.state}"/><c:out value="${person.zipCode}"/><br/>
        	
        	<p>Client:
	        	<c:if test="${empty company}">
	        		Not Assigned to a client.
	        	</c:if>
	        	<c:if test="${not empty company}">
	        		<a href="${pageContext.request.contextPath}/company/view/${company.companyId}"><c:out value="${company.name}"/></a>
	        	</c:if>
        	</p>
        	<a href="${pageContext.request.contextPath}/person/edit/${person.personId}">Edit Person</a>
           	<a href="${pageContext.request.contextPath}/person/delete/${person.personId}">Delete Person</a>
        </div>
    </body>
</html>
