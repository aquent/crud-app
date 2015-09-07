<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title>People | Crud Application</title>

		<!-- Bootstrap core CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/crud.css">

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>
	
	 	<%@include file="../includes/nav.jsp" %>
		   
		<div class="container">
			<div>
				<h1>People</h1>
				<p><a class="btn btn-primary" href="${pageContext.request.contextPath}/person/create">Create New Person</a></p>
		        <c:choose>
		            <c:when test="${fn:length(persons) gt 0}">
		                <table class="table">
		                    <thead>
		                        <tr>
		                            <th>First Name</th>
		                            <th>Last Name</th>
		                            <th>Email Address</th>
		                            <th>Actions</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                        <c:forEach items="${persons}" var="person">
		                            <tr>
		                                <td>${person.firstName}</td>
		                                <td>${person.lastName}</td>
		                                <td>${person.emailAddress}</td>
		                                <td>
		                                    <a class="btn btn-default" href="${pageContext.request.contextPath}/person/view/${person.personId}">View Person</a>
		                                    <a class="btn btn-default" href="${pageContext.request.contextPath}/person/edit/${person.personId}">Edit Person</a>
		                                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/person/delete/${person.personId}">Delete Person</a>
		                                </td>
		                            </tr>
		                        </c:forEach>
		                    </tbody>
		                </table>
		            </c:when>
		            <c:otherwise>
		                <p>No results found.</p>
		            </c:otherwise>
		        </c:choose>
		    </div>
		</div><!-- /.container -->
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>