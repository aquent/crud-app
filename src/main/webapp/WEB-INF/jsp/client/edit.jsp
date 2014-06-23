<%-- 
    Document   : edit
    Created on : Apr 22, 2011, 3:04:46 PM
    Author     : FMilens
--%>

<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" value="Edit Client"/>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<c:set var="style">
<style>
#clientForm label.error {
    margin-left: 10px;
    width: auto;
    display: inline;
}
</style>
</c:set>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<c:if test="${fn:length(errors) gt 0}">
    <p>Please correct the following errors in your submission:</p>
    <ul>
        <c:forEach items="${errors}" var="error">
            <li>${error}</li>
        </c:forEach>
    </ul>
</c:if>
<form id="clientForm" action="${context}/client/edit" method="POST" class="col-xs-6 form-horizontal">
    <input type="hidden" name="clientId" value="${client.clientId}"/>
    <br/>
    <div class="form-group">
      <label for="companyName" class="col-xs-4 control-label">Company Name:</label>
      <div class="col-xs-8">
        <input type="text" id="companyName" name="companyName" value="${client.companyName}" class="form-control"/>
      </div>
    </div>
    <div class="form-group">
      <label for="uri" class="col-xs-4 control-label">URL:</label>
      <div class="col-xs-8">
        <input type="text" name="uri" value="${client.uri}" class="form-control"/>
      </div>
    </div>
    <div class="form-group">
      <label for="phone" class="col-xs-4 control-label">Phone:</label>
      <div class="col-xs-8">
        <input type="text" name="phone" value="${client.phone}" class="form-control"/>
      </div>
    </div>

    <div class="form-group">
      <label for="streetAddress" class="col-xs-4 control-label">Street Address:</label>
      <div class="col-xs-8">
        <input type="text" name="streetAddress" value="${client.streetAddress}" class="form-control"/>
      </div>
    <label for="city" class="col-xs-4 control-label">City:</label>
      <div class="col-xs-8">
        <input type="text" name="city" value="${client.city}" class="form-control"/>
      </div>
    <label for="state" class="col-xs-4 control-label">State:</label>
      <div class="col-xs-2">
        <input type="text" name="state" value="${client.state}" class="form-control"/>
      </div>
      <label for="zipCode" class="col-xs-3 control-label">Zip Code:</label>
      <div class="col-xs-3">
        <input type="text" name="zipCode" value="${client.zipCode}" class="form-control"/>
      </div>
    <br/>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
<div class="col-xs-6">
<h2>Contacts</h2>
<c:set var="js">onclick="window.open(this.href, 'create person', 'left=20,top=20,width=500,height=500,toolbar=1,resizable=0'); return false;"</c:set>
<p><a href="${context}/person/create" ${js}><img src="${context}/resources/images/add-icon.png" alt="create"></a></p>
<c:choose>
    <c:when test="${fn:length(persons) gt 0}">
      <div class="table-responsive">
        <table class="table table-striped">
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
                        <td><form action="${context}/person/edit" method="POST">
                            <input type="hidden" name="clientId" value=""/>
                            <input type="hidden" name="personId" value="${person.personId}"/>
                            <input type="hidden" name="firstName" value="${person.firstName}"/>
                            <input type="hidden" name="lastName" value="${person.lastName}"/>
                            <input type="hidden" name="emailAddress" value="${person.emailAddress}"/>
                            <input type="hidden" name="streetAddress" value="${person.streetAddress}"/>
                            <input type="hidden" name="city" value="${person.city}"/>
                            <input type="hidden" name="state" value="${person.state}"/>
                            <input type="hidden" name="zipCode" value="${person.zipCode}"/>
                            <input type="image" src="${context}/resources/images/delete-icon.png" alt="remove association" onclick="this.form.target='_blank';return true;">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
      </div>
    </c:when>
    <c:otherwise>
        <p>No results found.</p>
    </c:otherwise>
</c:choose>
</div>
<c:set var="script">
<script>
$.validator.setDefaults({
    submitHandler: function() { alert("submitted!"); }
});
$(document).ready(function(){
  $("#clientForm").validate({
   rules: {
     companyName: {
         required: true,
         maxlength: 50
     },
     uri: {
         required: true,
         url: true
     },
     phone: {
         required: true,
         phoneUS: true
     },
     streetAddress: {
         required: true,
         maxlength: 50
     },
     city: {
         required: true,
         maxlength: 50
     },
     state: {
         required: true,
         minlength: 2,
         maxlength: 2
     },
     zipCode: {
         required: true,
         maxlength: 5
     }
   },
   messages: {
     companyName: {
        required: "required",
        maxlength: "50 characters max"
     },
     uri: {
        required: "required",
        uri: "Not valid URL format"
     },
     phone: {
         required: "required",
         phoneUS: "Please enter a valid US phone number"
     },
     streetAddress: {
         required: "required",
         maxlength: "50 characters max"
     },
     city: {
         required: "required",
         maxlength: "50 characters max"
     },
     state: {
         required: "required",
         minlength: "Please use 2-letter state abbreviation",
         maxlength: "Please use 2-letter state abbreviation"
     },
     zipCode: {
         required: "required",
         maxlength: "50 digits max"
     }
   }
});
console.log('after validator');
});
</script>
</c:set>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>