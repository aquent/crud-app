<%-- 
    Document   : delete
    Created on : Apr 22, 2011, 3:55:55 PM
    Author     : FMilens
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <c:set var="title" value="Delete Person"/>
    <c:set var="context" value="${pageContext.request.contextPath}"/>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
    </head>
    <body>
      <div class="container-fluid">
        <h1>Delete Person</h1>
        <p>You are about to delete the person ${person.firstName} ${person.lastName}:  Are you sure?</p>
        <form action="${context}/person/delete" method="post">
            <input type="hidden" name="personId" value="${person.personId}"/>
            <input type="submit" name="command" value="Cancel"/>
            <input type="submit" name="command" value="Delete"/>
        </form>
      </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
