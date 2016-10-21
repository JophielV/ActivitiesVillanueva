<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h2>Hello World!</h2>

<a href="/app/Next/">Link</a>

<table border="1">
                <th>Name</th>
                
                 
                <c:forEach var="person" items="${persons}">
                <tr>
                    <td>${person.getName()}</td>
                </tr>
                </c:forEach>             
            </table>
</body>
</html>
