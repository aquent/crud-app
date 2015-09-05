<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Company</title>
    </head>
    <body>
        <h1>Delete Company</h1>
        <p>You are about to delete the company ${company.name}:  Are you sure?</p>
        <form action="${pageContext.request.contextPath}/company/delete" method="post">
            <input type="hidden" name="companyId" value="${company.companyId}"/>
            <input type="submit" name="command" value="Cancel"/>
            <input type="submit" name="command" value="Delete"/>
        </form>
    </body>
</html>
