<%-- 
    Document   : delete
    Created on : Apr 22, 2011, 3:55:55 PM
    Author     : FMilens
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<c:set var="title" value="Delete Client"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
    <c:choose>
        <c:when test="${fn:length(persons) eq 0}">
        <p>You are about to delete the client ${client.companyName}:  Are you sure?</p>
        <form action="${context}/client/delete" method="post">
            <input type="hidden" name="clientId" value="${client.clientId}"/>
            <input type="submit" name="command" value="Cancel"/>
            <input type="submit" name="command" value="Delete"/>
        </form>
        </c:when>
        <c:otherwise><p>You cannot delete a client which has associated people.</p>
          <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email Address</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${persons}" var="person">
                        <tr>
                            <td>${person.firstName}</td>
                            <td>${person.lastName}</td>
                            <td>${person.emailAddress}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
          </div>
        </c:otherwise>
    </c:choose>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
