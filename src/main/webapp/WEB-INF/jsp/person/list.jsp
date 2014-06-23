<%-- 
    Document   : list
    Created on : Apr 22, 2011, 2:25:22 PM
    Author     : FMilens
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<c:set var="title" value="Person Listing"/>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<p><a href="${context}/person/create"><img src="${context}/resources/images/add-icon.png" alt="create"></a></p>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
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
                        <td>
                            <a href="${context}/person/edit/${person.personId}"><img src="${context}/resources/images/edit-icon.png" alt="edit"></a>
                            <a href="${context}/person/delete/${person.personId}"><img src="${context}/resources/images/delete-icon.png" alt="delete"></a>
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
<%@ include file="/WEB-INF/jspf/footer.jspf" %>