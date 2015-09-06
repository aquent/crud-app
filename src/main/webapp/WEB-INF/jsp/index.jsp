<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crud Application</title>
    </head>
    <body>
        <h1>Crud Application</h1>
        <p>Select and option to get started.</p>
        <div>
        	<p><a href="${pageContext.request.contextPath}/company/list/">List Companies</a></p>
        </div>
        <div>
        	<p><a href="${pageContext.request.contextPath}/person/list/">List People</a></p>
        </div>
    </body>
</html>
