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
        <form id="editCompany" action="${pageContext.request.contextPath}/company/edit" method="POST">
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
            <label for="personIds">Contacts:</label>
            <select name="personIds" multiple size="5">
            	<c:forEach items="${people}" var="person">
            	<option value="${person.personId}" ${company.personIds.contains(person.personId)  ? 'selected' : ''}>
            		<c:out value="${person.firstName}"/> <c:out value="${person.lastName}"/>
            	</option>
            	</c:forEach>
            </select>
            <br/>
            <input type="submit" name="Submit" value="Submit"/>
        </form>
        
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
	    <script type="text/javascript">
		    $(document).ready(function () {	
			    $('#editCompany').validate({
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
		    		}
		    	});
		    });
	    </script>
    </body>
</html>
