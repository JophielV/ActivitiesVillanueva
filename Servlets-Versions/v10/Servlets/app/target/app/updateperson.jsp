<%@page import="java.util.List, com.service.BusinessLogicService, com.model.Person, com.model.ContactInfo, com.model.PersonRoles, com.model.Role;"%>
<html>
<head>
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
    
</style>
</head>
<body>
   <div class="wholecontainer">
      <%
        Person person = (Person) request.getAttribute("person");
        BusinessLogicService service = (BusinessLogicService) request.getAttribute("service");
        request.getSession().setAttribute("service", service);
        request.getSession().setAttribute("person", person);
        String holder = person.getCurrentlyEmployed() == true ? "Y" : "N";
        String[] splitBirthday = person.getBirthday().toString().split("-");
        String finalBirthday = splitBirthday[1] + "/" + splitBirthday[2] + "/" + splitBirthday[0];
        String[] splitDateHired = person.getDateHired().toString().split("-");
        String finalDateHired = splitDateHired[1] + "/" + splitDateHired[2] + "/" + splitDateHired[0];
      %>
      <center><h2>Update Person</h2></center>
      <br/>
      
    <div class="alert alert-warning">
        <strong>Notice:</strong> All fields required. Put N/A if not applicable.
    </div>

    <a href="Person?action=viewAll"><button class="btn btn-primary"><span class="glyphicon glyphicon-user"></span>Back to Persons</button></a>

      <form class="form-horizontal" method="post" action="Person">
        <div class="form-group ">
            
            <div class="col-xs-3">
                <input type="hidden" name="personId" class="form-control" value="<%=person.getId()%>" required> 
            </div>
        </div>
        <h3>Name: </h3>
        <div class="form-group ">
            <label class="col-xs-2 control-label">First Name:</label>
            <div class="col-xs-3">
                <input type="text" name="firstName"  class="form-control" value="<%=person.getName().getFirstName()%>" pattern="^[A-Za-z ,.'-]+$" required> 
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label" >Last name: </label>
            <div class="col-xs-3">
                <input type="text" name="lastName"  class="form-control" value="<%=person.getName().getLastName()%>" pattern="^[A-Za-z ,.'-]+$" required>            
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Middle name: </label>
            <div class="col-xs-3">
                <input type="text" name="middleName"  class="form-control" value="<%=person.getName().getMiddleName()%>" pattern="^[A-Za-z ,.'-]+$" required>
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Suffix: </label>
            <div class="col-xs-3">
                <input type="text" name="suffix"  class="form-control" value="<%=person.getName().getSuffix()%>" pattern="^[A-Za-z ,.'-]+$" required>
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Title: </label>
            <div class="col-xs-3">
                <input type="text" name="title"  class="form-control" value="<%=person.getName().getTitle()%>" pattern="^[A-Za-z ,.'-]+$" required>
            </div>
        </div>
        <h3>Address: </h3>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Street No.: </label>
            <div class="col-xs-3">
                <input type="text" name="streetNo"  class="form-control" value="<%=person.getAddress().getStreetNo()%>" pattern="^[0-9]+$" required>
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Barangay: </label>
            <div class="col-xs-3">
                <input type="text" name="barangay"  class="form-control" value="<%=person.getAddress().getBarangay()%>" pattern="^[A-Za-z ,.'-]+$" required>
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">City: </label>
            <div class="col-xs-3">
                <input type="text" name="city"  class="form-control" value="<%=person.getAddress().getCity()%>" pattern="^[A-Za-z ,.'-]+$" required>
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Zip Code: </label>
            <div class="col-xs-3">
                <input type="text" name="zipCode" class="form-control" value="<%=person.getAddress().getZipCode()%>" pattern="^[0-9]+$" required>
            </div>
        </div>
        <h3>Other Information:</h3>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Birthday: </label>
            <div class="col-xs-3">
                <input type="text" name="birthday" id="datepicker" class="form-control" value="<%=finalBirthday%>" required> 
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">GWA: </label>
            <div class="col-xs-3">
                <input type="text" name="gwa" class="form-control" value="<%=person.getGwa()%>" pattern="^[+-]?\d+(\.\d+)?$" required>
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Date Hired: </label>
            <div class="col-xs-3">
                <input type="text" name="dateHired" id="datepicker2"  class="form-control" value="<%=finalDateHired%>" required>
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Currently Employed: </label>
            <div class="col-xs-3">
                <input type="text" name="currentlyEmployed" class="form-control" value="<%=holder%>" pattern="^Y|N$" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-offset-2 col-xs-10">
                <button type="submit" value="Submit" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span>Update</button>
            </div>
        </div>
    </form>
   
    <br/>
    <h2>Contact Information</h2>   
    <a href="ContactInfo?action=create&id=<%=person.getId()%>"><button class="btn btn-primary"><span class="glyphicon glyphicon-share"></span>Add Contact</button></a>
    
    <%
       List<ContactInfo> list = service.listContactInfo(person.getId());
       if(list.size() == 0) {
    %>
         <br/>
         <div class="alert alert-danger">
           <strong>Notice:</strong> This person has no contacts yet.
         </div>   
       <%}
       else { %>
        <table class="table table-hover">
        <thead>
            <tr>
                <th>Contact Id</th>
                <th>Type</th>
                <th>Information</th>
                <th>Name</th>
                <th>Person Id</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
           <%
               for (ContactInfo ci : list) {
               Person p = service.getPersonEntity(ci.getPerson().getId());
           %>
           <tr>
                <td><%=ci.getId()%></td>
                <td><%=ci.getType()%></td>
                <td><%=ci.getInformation()%></td>
                <td><%=p.getName()%></td>
                <td><%=ci.getPerson().getId()%></td>
                <td>
                   <a href="ContactInfo?action=update&id=<%=ci.getId()%>"><button class="btn btn-info btn-xs"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Update</button></a>
                   <a href="ContactInfo?action=delete&id=<%=ci.getId()%>"><button class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;Delete</button></a>
                <%}%>                
            </tr>
        </tbody>
        </table>         
       <%}%>

       <h2>Roles of this Person</h2>
       <a href="Person?action=addRole&id=<%=person.getId()%>"><button class="btn btn-primary"><span class="glyphicon glyphicon-share"></span>Add Role</button></a>
       <%
       List<PersonRoles> list2 = service.getRoleOfPerson(person.getId());
       if(list2.size() == 0) {
        %>
         <br/>
         <div class="alert alert-danger">
           <strong>Notice:</strong> This person has no roles.
         </div>   
       <%}
       else { %>
        <table class="table table-hover">
        <thead>
            <tr>
                <th>Role Id</th>
                <th>Role Name</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
           <%
               for (PersonRoles pr : list2) {
                  Role role = service.getRoleEntity(pr.getPersonRole().getRoleId());
           %>
           <tr>
                <td><%=role.getId()%></td>
                <td><%=role.getRoleName()%></td>
                <td>
                   <a href="PersonRole?action=delete&id=<%=pr.getPersonRole().getPersonId()%>&id2=<%=pr.getPersonRole().getRoleId()%>"><button class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;Remove</button></a>
                <%}%>                
            </tr>
        </tbody>
        </table>         
       <%}%>
   </div>
</body>

</html>
