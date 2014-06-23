<%-- 
    Document   : edit
    Created on : Apr 22, 2011, 3:04:46 PM
    Author     : FMilens
--%>

<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" value="Edit Person"/>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<c:if test="${fn:length(errors) gt 0}">
    <p>Please correct the following errors in your submission:</p>
    <ul>
        <c:forEach items="${errors}" var="error">
            <li>${error}</li>
        </c:forEach>
    </ul>
</c:if>
<form action="${context}/person/edit" method="POST" class="col-xs-6 form-horizontal">
    <input type="hidden" name="personId" value="${person.personId}"/>
    <br/>
    <div class="form-group">
      <label for="firstName" class="col-xs-4 control-label">First Name:</label>
      <div class="col-xs-8">
        <input type="text" name="firstName" value="${person.firstName}" class="form-control"/>
      </div>
      <label for="lastName" class="col-xs-4 control-label">Last Name:</label>
      <div class="col-xs-8">
        <input type="text" name="lastName" value="${person.lastName}" class="form-control"/>
      </div>
    </div>
    <div class="form-group">
      <label for="clientId" class="col-xs-4 control-label">Client:</label>
      <div class="col-xs-8">
        <select name="clientId" class="form-control">
            <option value="">** None Selected **</option>
            <c:forEach items="${clients}" var="client">
            <option value="${client.clientId}" <c:if test="${client.clientId==person.clientId}">selected</c:if>>${client.companyName}</option>
            </c:forEach>
        </select>
      </div>
    </div>
    <div class="form-group">
      <label for="emailAddress" class="col-xs-4 control-label">Email Address:</label>
      <div class="col-xs-8">
        <input type="text" name="emailAddress" value="${person.emailAddress}" class="form-control"/>
      </div>
    </div>
    <div class="form-group">
      <label for="streetAddress" class="col-xs-4 control-label">Street Address:</label>
      <div class="col-xs-8">
        <input type="text" name="streetAddress" value="${person.streetAddress}" class="form-control"/>
      </div>
    <label for="city" class="col-xs-4 control-label">City:</label>
      <div class="col-xs-8">
        <input type="text" name="city" value="${person.city}" class="form-control"/>
      </div>
    <label for="state" class="col-xs-4 control-label">State:</label>
      <div class="col-xs-2">
        <input type="text" name="state" value="${person.state}" class="form-control"/>
      </div>
      <label for="zipCode" class="col-xs-3 control-label">Zip Code:</label>
      <div class="col-xs-3">
        <input type="text" name="zipCode" value="${person.zipCode}" class="form-control"/>
      </div>
    <br/>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
