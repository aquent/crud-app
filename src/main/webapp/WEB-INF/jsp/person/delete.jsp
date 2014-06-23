<%-- 
    Document   : delete
    Created on : Apr 22, 2011, 3:55:55 PM
    Author     : FMilens
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<c:set var="title" value="Delete Person"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
    <p>You are about to delete the person ${person.firstName} ${person.lastName}:  Are you sure?</p>
    <form action="${context}/person/delete" method="post">
        <input type="hidden" name="personId" value="${person.personId}"/>
        <input type="submit" name="command" value="Cancel"/>
        <input type="submit" name="command" value="Delete"/>
    </form>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
