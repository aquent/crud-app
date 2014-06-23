<%-- 
    Document   : list
    Created on : Apr 22, 2011, 2:25:22 PM
    Author     : FMilens
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<c:set var="title" value="Client Listing"/>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
    <p><a href="${context}/client/create"><img src="${context}/resources/images/add-icon.png" alt="create"></a></p>
    <c:choose>
        <c:when test="${fn:length(clients) gt 0}">
          <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Company Name</th>
                        <th>Phone</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${clients}" var="client">
                        <tr>
                            <td><a href="${client.uri}">${client.companyName}</a></td>
                            <td>${client.phone}</td>
                            <td>
                                <a href="${context}/client/edit/${client.clientId}"><img src="${context}/resources/images/edit-icon.png" alt="edit"></a>
                                <a href="${context}/client/delete/${client.clientId}"><img src="${context}/resources/images/delete-icon.png" alt="delete"></a>
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