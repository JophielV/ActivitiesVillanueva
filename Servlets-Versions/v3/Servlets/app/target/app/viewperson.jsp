<%@page import="java.util.List, com.service.BusinessLogicService, com.model.Person;"%>

<html>
   <head>
     <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
     <link rel="stylesheet" href="css/bootstrap-theme.min.css">
     <script type="text/javascript" src="/js/bootstrap.min.js"></script>
     <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
     <style type="text/css">
       .wholecontainer {
    	  margin: 20px;
       }
   
    </style>
   </head>

   <body>
      <div class="wholecontainer">
         <%
            Person person =(Person) request.getAttribute("person");
            
         %>
	 <%=person.getName() %>

         
      
      </div>
   </body>
</html>