<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	
		<title>Create Company | Crud Application</title>
	
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
				<h1>Create Company</h1>
				<c:if test="${fn:length(errors) gt 0}">
					<p>Please correct the following errors in your submission:</p>
					<ul>
						<c:forEach items="${errors}" var="error">
							<li>${error}</li>
						</c:forEach>
					</ul>
				</c:if>
				<form id="createCompany" action="${pageContext.request.contextPath}/company/create" method="POST">
				<div class="control-group">
					<label class="control-label" for="name">Name:</label>
					<input class="form-control" type="text" id="name" name="name" value="${company.name}"/>
				</div>
				<div class="control-group">
					<label class="control-label" for="website">Website:</label>
					<input class="form-control" type="text" name="website" value="${company.website}"/>
				</div>
				<div class="control-group">
					<label class="control-label" for="phoneNumber">Phone Number:</label>
					<input class="form-control" type="text" name="phoneNumber" value="${company.phoneNumber}"/>
				</div>
				<div class="control-group">
					<label class="control-label" for="streetAddress">Street Address:</label>
					<input class="form-control" type="text" name="streetAddress" value="${company.streetAddress}"/>
				</div>
				<div class="control-group">
					<label class="control-label" for="city">City:</label>
					<input class="form-control" type="text" name="city" value="${company.city}"/>
				</div>
				<div class="control-group">
					<label class="control-label" for="state">State:</label>
					<input class="form-control" type="text" name="state" value="${company.state}"/>
				</div>
				<div class="control-group">
					<label class="control-label" for="zipCode">Zip Code:</label>
					<input class="form-control" type="text" name="zipCode" value="${company.zipCode}"/>
				</div>
				<div class="control-group">
					<label class="control-label" for="personIds">Contacts:</label>
					<select class="form-control" name="personIds" multiple size="5">
					<c:forEach items="${people}" var="person">
						<option value="${person.personId}">
						<c:out value="${person.firstName}"/> <c:out value="${person.lastName}"/>
					</option>
					</c:forEach>
					</select>
				</div>
				<input class="btn btn-primary" type="submit" name="Submit" value="Submit"/>
		       </form>
		   </div>
		</div><!-- /.container -->

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function () {	
				$('#createCompany').validate({
					rules: {
						name: {
							required: true,
							minlength: 1,
							maxlength: 50
						},
						website: {
							required: true,
							minlength: 1,
							maxlength: 50
						},
						phoneNumber: {
							required: true,
							minlength: 10,
							maxlength: 10
						},
						streetAddress: {
							required: true,
							minlength: 1,
							maxlength: 50
						},
						city: {
							required: true,
							minlength: 1,
							maxlength: 50
						},
						state: {
							required: true,
							minlength: 2,
							maxlength: 2
						},
						zipCode: {
							required: true,
							minlength: 5,
							maxlength: 5
						}
					},
					highlight: function (element) {
				    	$(element).closest('.control-group').addClass('has-error');
					},
					success: function (element) {
				    	element.closest('.control-group').removeClass('has-error');
					}
				});
			});
		</script>
	</body>
</html>