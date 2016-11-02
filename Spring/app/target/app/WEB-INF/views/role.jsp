<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Role</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script> 
    <style type="text/css">
    .wholecontainer {
    	margin: 20px;
    }
    .well {
        width: 250px; 
        padding: 10px 0;
    }
    .nav-tabs > li, .nav-pills > li {
       float:none !important;
       display:inline-block !important;
    }

    .nav-tabs, .nav-pills {
       text-align:center !important;
     } 
     .forceInline {
       display: inline;
     }
     .beside {
        float: left;
        position: relative;
        left: 500px;
     }
     .logout {
        float: right;
     }
</style>
</head>
<body>
   <div class="wholecontainer">
     
    <center>
    <ul class="nav nav-pills">
        <li><a href="/app/Person/"><i class="glyphicon glyphicon-user"></i>Persons</a></li>
        <li><a href="/app/ContactInfo/"><i class="glyphicon glyphicon-envelope"></i>Contacts</a></li>
        <li class="active"><a href="/app/Role/"><i class="glyphicon glyphicon-star"></i>Roles</a></li>
        <li><a href="/app/PersonRole/"><i class="glyphicon glyphicon-eye-open"></i>Person Roles</a></li>
    </ul>
    </center>

    <sec:authorize access="hasRole('ROLE_USER')">
		<!-- For login user -->
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h4 class="logout">
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h4>
		</c:if>
     </sec:authorize> 

     <center>
       <div class="page-header">
         <div class="forceInline"><div class="beside"><span class="glyphicon glyphicon-user"></span></div><h2>Roles Records</h2></div>
       </div>
     </center>
     
    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
    <a href="/app/Role/create"><button class="btn btn-primary"><span class="glyphicon glyphicon-share"></span>Create Role</button></a>
    </c:if>
  
   <table class="table table-hover">
        <thead>
            <tr>
                
                <th>Role Name</th>
                <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}"><th>Actions</th></c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="role" items="${roles}">
                <tr>
                    <td>${role.getRoleName()}</td>
                    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                    <td>
                       <a href="<c:url value='/Role/update/${role.getId()}' />"><button class="btn btn-info btn-xs"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Update</button></a>
                       <a href="<c:url value='/Role/delete/${role.getId()}' />"><button class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;Delete</button></a>
                    </td>
                    </c:if>
                </tr>
           </c:forEach>
        </tbody>
    </table>
</body>
</html>
