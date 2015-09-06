<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Company</title>
    </head>
    <body>
        <h1>View Company</h1>
        <div>
        	<c:out value="${company.name}"/><br/>
        	<c:out value="${company.website}"/><br/>
        	<c:out value="${company.streetAddress}"/><br/>
        	<c:out value="${company.city}"/>, <c:out value="${company.state}"/><c:out value="${company.zipCode}"/><br/>
        	
        	<p>Contacts:
	        	<c:if test="${empty people}">
	        		No contacts assigned.
	        	</c:if>
	        	<c:if test="${not empty people}">
	        		<ul>
		        		<c:forEach items="${people}" var="person">
		        		<li><a href="${pageContext.request.contextPath}/person/view/${person.personId}">
		        			<c:out value="${person.firstName}"/> <c:out value="${person.lastName}"/>
		        		</a></li>
		        		</c:forEach>
	        		</ul>
	        	</c:if>
        	</p>
        	<a href="${pageContext.request.contextPath}/company/edit/${company.companyId}">Edit Company</a>
           	<a href="${pageContext.request.contextPath}/company/delete/${company.companyId}">Delete Company</a>
        </div>
    </body>
</html>
