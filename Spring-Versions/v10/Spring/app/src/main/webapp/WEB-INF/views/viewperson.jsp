<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
   <head>
     <title>View Person</title>
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
       .logout {
        float: right;
     }
    </style>
   </head>

   <body>
      <div class="wholecontainer">
         <center><h2>Person Details</h2></center> 
         <br/>
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
         <h3>Details:</h3>
         <b>Person Id:</b> ${person.getId()} </br>
	 <b>Name: </b> ${person.getName()} </br>
         <b>Address: </b> ${person.getAddress()} </br>
         <b>Birthday: </b> ${person.getBirthday()} </br>
         <b>GWA: </b> ${person.getGwa()} </br>
         <b>Date Hired: </b> ${person.getDateHired()} </br>
         <b>Currently Employed?: </b> 
                       <c:set var="val" value="${person.getCurrentlyEmployed()}"/>
                       <c:choose> 
                          <c:when test="${val == 'true'}">Y</c:when>
                          <c:otherwise>N</c:otherwise>
                       </c:choose> </br>
         <br/>

         <h3>Contact Information:</h3>
         <c:forEach var="contact" items="${contacts}">
               <b>Type:</b> ${contact.getType()} </br>
               <b>Information:</b> ${contact.getInformation()} </br>
               <br/>
         </c:forEach>

         <h3>Roles:</h3>
         <c:forEach var="role" items="${roles}">
               <b>Role Id:</b> ${role.getPersonRole().getRoleId()} </br>
               <b>Role Name:</b> ${service.getRoleEntity(role.getPersonRole().getRoleId()).getRoleName()} </br>
               <br/>
         </c:forEach>       
         <br/>
         <a href="/app/Person/"><button class="btn btn-primary"><span class="glyphicon glyphicon-user"></span>Back to Persons</button></a>
      </div>
   </body>
</html>
