<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title>Search | Crud Application</title>

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
				<h1>Search</h1>
				<form id="search" action="${pageContext.request.contextPath}/search" method="POST">
					<div class="control-group">
						<input class="form-control" type="text" id="query" name="query" value="${query}"/>
					</div>
					<input class="btn btn-primary" type="submit" name="Submit" value="Submit"/>
				</form>
			</div>
			
			<c:if test="${not empty query}">
				<div class="row marketing">
					<div class="col-md-12">
						<c:choose>
							<c:when test="${fn:length(searchResults) gt 0}">
								<c:forEach items="${searchResults}" var="searchResult">
									<div>
										<c:if test="${not empty searchResult.company}">
											<h2><a href="${pageContext.request.contextPath}/company/view/${searchResult.company.companyId}"><c:out value="${searchResult.company.name}"/></a></h2>
											<p>
									        	<c:out value="${searchResult.company.streetAddress}"/><br/>
									        	<c:out value="${searchResult.company.city}"/>, <c:out value="${searchResult.company.state}"/><c:out value="${searchResult.company.zipCode}"/><br/>
								        	</p>
										</c:if>
										<c:if test="${not empty searchResult.person}">
											<h2><a href="${pageContext.request.contextPath}/person/view/${searchResult.person.personId}"><c:out value="${searchResult.person.firstName}"/> <c:out value="${searchResult.person.lastName}"/></a></h2>
											<p>
									        	<c:out value="${searchResult.person.streetAddress}"/><br/>
									        	<c:out value="${searchResult.person.city}"/>, <c:out value="${searchResult.person.state}"/><c:out value="${searchResult.person.zipCode}"/><br/>
								        	</p>
										</c:if>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<h2>No results found.</h2>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:if>
		</div><!-- /.container -->
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>