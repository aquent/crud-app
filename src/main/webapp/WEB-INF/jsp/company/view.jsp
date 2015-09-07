<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title>View Company | Crud Application</title>

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
				<h1><c:out value="${company.name}"/></h1>
				<div>
					<p><c:out value="${company.website}"/></p>
					<p>
						<c:out value="${company.streetAddress}"/><br/>
						<c:out value="${company.city}"/>, <c:out value="${company.state}"/><c:out value="${company.zipCode}"/><br/>
					</p>
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
					<a class="btn btn-default" href="${pageContext.request.contextPath}/company/edit/${company.companyId}">Edit Company</a>
				   	<a class="btn btn-danger" href="${pageContext.request.contextPath}/company/delete/${company.companyId}">Delete Company</a>
				</div>
			</div>
		</div><!-- /.container -->
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>