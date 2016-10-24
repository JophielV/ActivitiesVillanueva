<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Person</title>
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
        <li class="active"><a href="Person?action=viewAll"><i class="glyphicon glyphicon-user"></i>Persons</a></li>
        <li><a href="ContactInfo?action=viewAll"><i class="glyphicon glyphicon-envelope"></i>Contacts</a></li>
        <li><a href="Role?action=viewAll"><i class="glyphicon glyphicon-star"></i>Roles</a></li>
        <li><a href="PersonRole?action=viewAll"><i class="glyphicon glyphicon-eye-open"></i>Person Roles</a></li>
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
         <div class="forceInline"><div class="beside"><span class="glyphicon glyphicon-user"></span></div><h2>Person Records</h2></div>
       </div>
     </center>
     
    <form method="post" action="Person">
        <div class="row">
             <div class="col-xs-offset-3">
                <div class="col-xs-4">
                   <div class="input-group">
                      <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                      <input type="text" name="firstName" class="form-control" placeholder="First Name">
                   </div>
                </div>
                <div class="col-xs-4">
                   <div class="input-group">
                      <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                      <input type="text" name="lastName" class="form-control" placeholder="Last Name">
                   </div>
                </div>
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Search</button>
            </div>
        </div>
    </form>

    <a href="/app/Person/create"><button class="btn btn-primary"><span class="glyphicon glyphicon-share"></span>Create Person</button></a>

  
   <table class="table table-hover">
        <thead>
            <tr>
                <th><a href="/app/Person/">Person Id</a></th>
                <th><a href="/app/Person/i4">Name</a></th>
                <th>Address</th>
                <th>Birthday</th>
                <th><a href="/app/Person/i2">GWA</a></th>
                <th><a href="/app/Person/i3">Date Hired</a></th>
                <th>Employed</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            
            <c:forEach var="person" items="${persons}">
                <tr>
                    <td>${person.getId()}</td>
                    <td>${person.getName()}</td>
                    <td>${person.getAddress()}</td>
                    <td>${person.getBirthday()}</td>
                    <td>${person.getGwa()}</td>
                    <td>${person.getDateHired()}</td>
                    <td>
                       <c:set var="val" value="${person.getCurrentlyEmployed()}"/>
                       <c:choose> 
                          <c:when test="${val == 'true'}">Y</c:when>
                          <c:otherwise>N</c:otherwise>
                       </c:choose>
                    </td>
                    <td>Action</td>
                </tr>
                </c:forEach>
        </tbody>
    </table>
</body>
</html>
