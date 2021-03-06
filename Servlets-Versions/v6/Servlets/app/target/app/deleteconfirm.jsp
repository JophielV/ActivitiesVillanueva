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
         %>
 
         <center><h2>Person Details</h2></center>
         <br/>

         <h3>Details:</h3>
	 <b>Name: </b> <%=person.getName() %> </br>
         <b>Address: </b> <%=person.getAddress() %> </br>
         <b>Birthday: </b> <%=person.getBirthday() %> </br>
         <b>GWA: </b> <%=person.getGwa() %> </br>
         <b>Date Hired: </b> <%=person.getDateHired() %> </br>
         <b>Currently Employed?: </b> <%=holder%> </br>
         <br/>
         <h3>Contact Information:</h3>
         
         <a href="Person?action=delete&id=<%=person.getId()%>"><button class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>Delete Person</button></a>
         <a href="Person?action=viewAll"><button class="btn btn-primary"><span class="glyphicon glyphicon-user"></span>Back to Persons</button></a>
      </div>
   </body>
</html>
