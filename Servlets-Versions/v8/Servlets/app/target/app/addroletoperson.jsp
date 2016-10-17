<%@page import="java.util.List, com.service.BusinessLogicService, com.model.Person,com.model.Role;"%>
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
  <style type="text/css">
    .wholecontainer {
    	margin: 20px;
    }
  </style>
</head>
<body>
   <div class="wholecontainer">
      <%
        BusinessLogicService service = (BusinessLogicService) request.getAttribute("service");
        Person person =(Person) request.getAttribute("person");
        request.getSession().setAttribute("service", service);
        request.getSession().setAttribute("person", person);
      %>
      <center><h2>Create Role</h2></center>
      <br/>
      
    <div class="alert alert-warning">
        <strong>Notice:</strong> All fields required. Put N/A if not applicable.<br/><br/>
        Adding role to:<br/>
        <strong>Person Id:</strong> <%=person.getId()%><br/>
        <strong>Person Name:</strong> <%=person.getName()%>
    </div>
    
     <h3>Role Information: </h3>
        <table class="table table-hover">
        <thead>
            <tr>               
                <th>Role Id</th>
                <th>Role Name</th>
            </tr>
        </thead>
        <tbody>
            <%
               List<Role> list = service.listRoles();
               for (Role role : list) {
            %>
            <tr>
                <td><%=role.getId()%></td>
                <td><%=role.getRoleName()%></td>
                <%}%>                
            </tr>
        </tbody>
    </table>

      <form class="form-horizontal" method="post" action="PersonRole">
         <div class="form-group ">
           <label class="col-xs-2 control-label" >Role Id: </label>
           <div class="col-xs-3">
              <select id="contactType" name="roleId">
                  <%
                  for (Role role : list) {
                 %>
                    <option><%=role.getId()%></option>
                <%}%>                
              </select>
           </div>                        
        </div>
        <div class="form-group">
            <div class="col-xs-offset-2 col-xs-10">
                <button type="submit" value="Submit" class="btn btn-primary" id="submitButton"><span class="glyphicon glyphicon-share"></span>Add Role</button>
            </div>
        </div>
    </form>
   </div>
</body>

</html>
