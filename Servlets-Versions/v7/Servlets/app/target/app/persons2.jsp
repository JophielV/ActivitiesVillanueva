<%@page import="java.util.List, com.service.BusinessLogicService, com.model.Person;"%>


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

     <center>
       <div class="page-header">
         <div class="forceInline"><div class="beside"><span class="glyphicon glyphicon-user"></span></div><h2>Person Records</h2></div>
       </div>
     </center>
     
    <form>
        <div class="row">
             <div class="col-xs-offset-3">
                <div class="col-xs-4">
                   <div class="input-group">
                      <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                      <input type="text" class="form-control" placeholder="First Name">
                   </div>
                </div>
                <div class="col-xs-4">
                   <div class="input-group">
                      <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                      <input type="text" class="form-control" placeholder="Last Name">
                   </div>
                </div>
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Search</button>
            </div>
        </div>
    </form>
    <a href="Person?action=create"><button class="btn btn-primary"><span class="glyphicon glyphicon-share"></span>Create Person</button></a>

  
   <table class="table table-hover">
        <thead>
            <tr>
                <th><a href="Person?action=viewAll">Person Id</a></th>
                <th><a href="Person?action=viewAll4">Name</a></th>
                <th>Address</th>
                <th>Birthday</th>
                <th><a href="Person?action=viewAll2">GWA</a></th>
                <th><a href="Person?action=viewAll3">Date Hired</a></th>
                <th>Employed</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
               BusinessLogicService service = (BusinessLogicService) request.getAttribute("service");
               request.getSession().setAttribute("service", service);
               List<Person> list = service.listPersons(2);
               
               for (Person p : list) {
               String holder = p.getCurrentlyEmployed() == true ? "Y" : "N";
            %>
            <tr>
                <td><%=p.getId()%></td>
                <td><%=p.getName()%></td>
                <td><%=p.getAddress()%></td>
                <td><%=p.getBirthday()%></td>
                <td><%=p.getGwa()%></td>
                <td><%=p.getDateHired()%></td>
                <td><%=holder%></td>
                <td>
                   <a href="Person?action=view&id=<%=p.getId()%>"><button class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;View</button></a>
                   <a href="Person?action=update&id=<%=p.getId()%>"><button class="btn btn-info btn-xs"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Update</button></a>
                   <a href="Person?action=deleteconfirm&id=<%=p.getId()%>"><button class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;Delete</button></a>
                <%}%>                
            </tr>
        </tbody>
    </table>
</body>
</html>
