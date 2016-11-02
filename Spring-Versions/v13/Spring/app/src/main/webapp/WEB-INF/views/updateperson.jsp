<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Update Person</title>
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
     $(function() {
        $("#datepicker").datepicker();
        $("#datepicker2").datepicker();
      } );
      
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
      
      <center><h2>Update Person</h2></center>
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
     <br/><br/>  
    <div class="alert alert-warning">
        <strong>Notice:</strong> All fields required. Put N/A if not applicable.
    </div>
      <a href="/app/Person/"><button class="btn btn-primary"><span class="glyphicon glyphicon-user"></span>Back to Persons</button></a>

      <form:form method="post" modelAttribute="person" action="/app/Person/save" cssClass="form-horizontal">
        <h3>Name: </h3>
        <form:hidden path="id" readonly="true" size="8"  disabled="true" />
	<form:hidden path="id" value="${person.getId()}"/>
        <div class="form-group ">
           <form:label path="name.firstName" cssClass="col-xs-2 control-label">First Name:</form:label>
              <div class="col-xs-3">
                 <form:input path="name.firstName" cssClass="form-control" value="${person.getName().getFirstName()}" required="required"></form:input>
              </div>
        </div>
        <div class="form-group ">
           <form:label path="name.lastName" cssClass="col-xs-2 control-label">Last Name:</form:label>
              <div class="col-xs-3">
                 <form:input path="name.lastName" cssClass="form-control" value="${person.getName().getLastName()}" required="required"></form:input>
              </div>
        </div>
        <div class="form-group ">
           <form:label path="name.middleName" cssClass="col-xs-2 control-label">Middle Name:</form:label>
              <div class="col-xs-3">
                 <form:input path="name.middleName" cssClass="form-control" value="${person.getName().getMiddleName()}" required="required"></form:input>
              </div>
        </div>
        <div class="form-group ">
           <form:label path="name.suffix" cssClass="col-xs-2 control-label">Suffix:</form:label>
              <div class="col-xs-3">
                 <form:input path="name.suffix" cssClass="form-control" value="${person.getName().getSuffix()}" required="required"></form:input>
              </div>
        </div>
        <div class="form-group ">
           <form:label path="name.title" cssClass="col-xs-2 control-label">Title:</form:label>
              <div class="col-xs-3">
                 <form:input path="name.title" cssClass="form-control" value="${person.getName().getTitle()}" required="required"></form:input>
              </div>
        </div>
        <h3>Address: </h3>
        <div class="form-group ">
           <form:label path="address.streetNo" cssClass="col-xs-2 control-label">Street No.:</form:label>
              <div class="col-xs-3">
                 <form:input path="address.streetNo" cssClass="form-control" value="${person.getAddress().getStreetNo()}" required="required"></form:input>
              </div>
        </div>
        <div class="form-group ">
           <form:label path="address.barangay" cssClass="col-xs-2 control-label">Barangay:</form:label>
              <div class="col-xs-3">
                 <form:input path="address.barangay" cssClass="form-control" value="${person.getAddress().getBarangay()}" required="required"></form:input>
              </div>
        </div>
        <div class="form-group ">
           <form:label path="address.city" cssClass="col-xs-2 control-label">City:</form:label>
              <div class="col-xs-3">
                 <form:input path="address.city" cssClass="form-control" value="${person.getAddress().getCity()}" required="required"></form:input>
              </div>
        </div>
        <div class="form-group ">
           <form:label path="address.zipCode" cssClass="col-xs-2 control-label">Zip Code:</form:label>
              <div class="col-xs-3">
                 <form:input path="address.zipCode" cssClass="form-control" value="${person.getAddress().getZipCode()}" required="required"></form:input>
              </div>
        </div>
        <h3>Other Information:</h3>
        <div class="form-group ">
           <form:label path="birthday" cssClass="col-xs-2 control-label">Birthday:</form:label>
              <div class="col-xs-3">
                 <form:input path="birthday" cssClass="form-control" value="${finalBirthday}" required="required" id="datepicker"></form:input>
              </div>
        </div>
        <div class="form-group ">
           <form:label path="gwa" cssClass="col-xs-2 control-label">GWA:</form:label>
              <div class="col-xs-3">
                 <form:input path="gwa" cssClass="form-control" value="${person.getGwa()}" pattern="^\d+(\.\d+)?$" required="required"></form:input>
              </div>
        </div>
        <div class="form-group ">
           <form:label path="dateHired" cssClass="col-xs-2 control-label">Date Hired:</form:label>
              <div class="col-xs-3">
                 <form:input path="dateHired" cssClass="form-control" value="${finalDateHired}" required="required" id="datepicker2"></form:input>
              </div>
        </div>
        <div class="form-group ">
           <label class="col-xs-2 control-label">Currently Employed: </label>
              <div class="col-xs-3">
                 <input type="text" name="employed" class="form-control" value="${holderEmployed}" pattern="^Y|N$" required> 
              </div>
        </div>
        <div class="form-group">
            <div class="col-xs-offset-2 col-xs-10">
                <button type="submit" value="Submit" class="btn btn-primary"><span class="glyphicon glyphicon-share"></span>Update</button>
                <button type="reset" value="Reset" class="btn btn-primary"><span class="glyphicon glyphicon-repeat"></span>Reset</button>
            </div>
        </div>
    </form:form>
   </div>

   <br/>
    <h2>Contact Information</h2>   
    <a href="/app/ContactInfo/create/${person.getId()}"><button class="btn btn-primary"><span class="glyphicon glyphicon-share"></span>Add Contact</button></a>
    
         <br/>
      
        <table class="table table-hover">
        <thead>
            <tr>
                <th>Type</th>
                <th>Information</th>
                <th>Name</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
           <c:forEach var="contact" items="${contacts}">
                <tr>
                    <td>${contact.getType()}</td>
                    <td>${contact.getInformation()}</td>
                    <td>${contact.getPerson().getName()}</td>
                    <td>
                       <a href="<c:url value='/ContactInfo/update/${contact.getId()}' />"><button class="btn btn-info btn-xs"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Update</button></a>
                       <a href="<c:url value='/ContactInfo/delete/${contact.getId()}' />"><button class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;Delete</button></a>
                    </td>
                </tr>
           </c:forEach>
        </tbody>
        </table>         
   
    <h2>Roles of this Person</h2>
       <a href="/app/PersonRole/addrole/${person.getId()}"><button class="btn btn-primary"><span class="glyphicon glyphicon-share"></span>Add Role</button></a>
         <br/>
        <table class="table table-hover">
        <thead>
            <tr>
                <th>Role Name</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
           <c:forEach var="role" items="${roles}">
                <tr>
                    <td>${service.getRoleEntity(role.getPersonRole().getRoleId()).getRoleName()}</td>
                    <td>
                      <a href="<c:url value='/PersonRole/delete/${person.getId()}/${role.getPersonRole().getRoleId()}' />"><button class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;Delete</button></a>
                    </td>
                </tr>
           </c:forEach>
        </tbody>
        </table>           
</body>

</html>
