<%@page import="java.util.List, com.service.BusinessLogicService, com.model.Person, com.model.ContactInfo;"%>


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
        <li><a href="Person?action=viewAll"><i class="glyphicon glyphicon-user"></i>Persons</a></li>
        <li class="active"><a href="ContactInfo?action=viewAll"><i class="glyphicon glyphicon-envelope"></i>Contacts</a></li>
        <li><a href="Role?action=viewAll"><i class="glyphicon glyphicon-star"></i>Roles</a></li>
        <li><a href="PersonRole?action=viewAll"><i class="glyphicon glyphicon-eye-open"></i>Person Roles</a></li>
    </ul>
    </center>

     <center>
       <div class="page-header">
         <div class="forceInline"><div class="beside"><span class="glyphicon glyphicon-user"></span></div><h2>Contact Records</h2></div>
       </div>
     </center>
     
  
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
               BusinessLogicService service = (BusinessLogicService) request.getAttribute("service");
               request.getSession().setAttribute("service", service);
               List<ContactInfo> list = service.listContactInfo();
               for (ContactInfo ci : list) {
               Person person = service.getPersonEntity(ci.getPerson().getId());
           %>
           <tr>
                <td><%=ci.getId()%></td>
                <td><%=ci.getType()%></td>
                <td><%=ci.getInformation()%></td>
                <td><%=person.getName()%></td>
                <td><%=ci.getPerson().getId()%></td>
                <td>
                   <a href="ContactInfo?action=update&id=<%=ci.getId()%>"><button class="btn btn-info btn-xs"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Update</button></a>
                   <a href="ContactInfo?action=delete&id=<%=ci.getId()%>"><button class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;Delete</button></a>
                </td>
                <%}%>                
            </tr>
        </tbody>
    </table>
</body>
</html>
