<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title>View Person | Crud Application</title>

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
				<h1>View Person</h1>
				<div>
					<h2><a href="mailto:${person.emailAddress}"><c:out value="${person.firstName}"/> <c:out value="${person.lastName}"/></a></h2>
		        	<p>
			        	<c:out value="${person.streetAddress}"/><br/>
			        	<c:out value="${person.city}"/>, <c:out value="${person.state}"/><c:out value="${person.zipCode}"/><br/>
		        	</p>
		        	<p>Client:
			        	<c:if test="${empty company}">
			        		Not Assigned to a client.
			        	</c:if>
			        	<c:if test="${not empty company}">
			        		<a href="${pageContext.request.contextPath}/company/view/${company.companyId}"><c:out value="${company.name}"/></a>
			        	</c:if>
		        	</p>
		        	<a class="btn btn-default" href="${pageContext.request.contextPath}/person/edit/${person.personId}">Edit Person</a>
		           	<a class="btn btn-danger" href="${pageContext.request.contextPath}/person/delete/${person.personId}">Delete Person</a>
				</div>
			</div>
		</div><!-- /.container -->
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>
