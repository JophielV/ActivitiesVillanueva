<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Update Contact</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script> 
     <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
     
  </script>
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
      <center><h2>Create Contact</h2></center>
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

    <div class="alert alert-warning">
        <strong>Notice:</strong> All fields required. Put N/A if not applicable.<br/><br/>
        <strong>Mobile Number</strong> formats:<br/>
        0xxxxxxxxxx<br/>
        +63xxxxxxxxxx<br/><br/>

        <strong>Landline Number</strong> formats:<br/>
        xxx-xxxx<br/>
        0-xx-xxx-xxxx<br/>
        0-2-xxx-xxxx<br/>
        011-63-2-xxx-xxxx<br/>
        011-63-xx-xxx-xxxx<br/>
    </div>

      <form:form method="post" modelAttribute="contact" action="/app/ContactInfo/saveUpdate" cssClass="form-horizontal">
        <h3>Contact Information: </h3>
        <div class="form-group ">
           <input type="hidden" name="personId" class="form-control" value="${id}" required> 
           <input type="hidden" name="contactId" class="form-control" value="${contact.getId()}" required> 
           <form:hidden path="id" readonly="true" size="8"  disabled="true" />
           <form:label path="type" cssClass="col-xs-2 control-label">Type:</form:label>
           <div class="col-xs-3">
              <form:input path="type" cssClass="form-control" value="${contact.getType()}"  readonly="true" required="required"></form:input>
           </div>                        
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Information: </label>
            <div class="col-xs-3">
                <input type="text" name="information" class="form-control" value="${contact.getInformation()}" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-offset-2 col-xs-10">
                <button type="submit" value="Submit" class="btn btn-primary" id="submitButton"><span class="glyphicon glyphicon-share"></span>Update</button>
                <button type="reset" value="Reset" class="btn btn-primary"><span class="glyphicon glyphicon-repeat"></span>Reset</button>
            </div>
        </div>
    </form:form>
   </div>
</body>

</html>
