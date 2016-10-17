<%@page import="java.util.List, com.service.BusinessLogicService, com.model.Person,com.model.ContactInfo;"%>
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
     });
     $(function() {
        $("#submitButton").hover(function() {
           var chosenType = document.getElementById("contactType");
           var typeValue = chosenType.value;
           var information = document.getElementById("info");
           var informationValue = information.value;
           if(typeValue=="Mobile Number") { 
              var pattern1 =  /^0[0-9]{10}$/;
              var pattern2 =  /^\+63[0-9]{10}$/;

              if(informationValue.match(pattern1) || informationValue.match(pattern2)) {
                 $(".validate").text("Valid!"); 
                 $(":submit").attr("disabled", false);
              }
              else { 
                 $(".validate").text("Not Valid!"); 
                 $(":submit").attr("disabled", true);
              }
           }
           else if(typeValue=="Land Line") { 
              var pattern1 =  /^[0-9]{3}-[0-9]{4}$/;
              var pattern2 =  /^0-[0-9]{2}-[0-9]{3}-[0-9]{4}$/;
              var pattern3 =  /^0-2-[0-9]{3}-[0-9]{3}-[0-9]{4}$/;
              var pattern4 =  /^011-63-2-[0-9]{3}-[0-9]{4}$/;
              var pattern5 =  /^011-63-[0-9]{2}-[0-9]{3}-[0-9]{4}$/;

              if(informationValue.match(pattern1) || informationValue.match(pattern2) || informationValue.match(pattern3) 
              || informationValue.match(pattern4) || informationValue.match(pattern5)) {
                 $(".validate").text("Valid!"); 
                 $(":submit").attr("disabled", false);
              }
              else { 
                 $(".validate").text("Not Valid!"); 
                 $(":submit").attr("disabled", true);
              }
           }
           else if(typeValue=="Email") { 
              var pattern1 =  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

              if(informationValue.match(pattern1)) {
                 $(".validate").text("Valid!"); 
                 $(":submit").attr("disabled", false);
              }
              else { 
                 $(".validate").text("Not Valid!"); 
                 $(":submit").attr("disabled", true);
              }
           }
        });
     });
      function answers(){ 
         var answer=document.getElementById("contactType");
         var value = answer.value;
         
      }
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
        BusinessLogicService service = (BusinessLogicService) request.getAttribute("service");
        Person person =(Person) request.getAttribute("person");
        request.getSession().setAttribute("service", service);
        request.getSession().setAttribute("person", person);
      %>
      <center><h2>Create Contact</h2></center>
      <br/>
      
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

      <form class="form-horizontal" method="post" action="ContactInfo">
        <h3>Contact Information: </h3>
        <div class="form-group ">
           <label class="col-xs-2 control-label" >Contact Type: </label>
           <div class="col-xs-3">
              <select id="contactType" name="type">
                 <option>Mobile Number</option>
                 <option>Land Line</option>
                 <option>Email</option>
              </select>
           </div>                        
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label" >Information: </label>
            <div class="col-xs-3">
                <input type="text" name="information" id="info" class="form-control" placeholder="Information" required>            
            </div>
            <span class="validate"></span>
        </div>
        <div class="form-group">
            <div class="col-xs-offset-2 col-xs-10">
                <button type="submit" value="Submit" class="btn btn-primary" id="submitButton"><span class="glyphicon glyphicon-share"></span>Create</button>
                <button type="reset" value="Reset" class="btn btn-primary"><span class="glyphicon glyphicon-repeat"></span>Reset</button>
            </div>
        </div>
    </form>
   </div>
</body>

</html>
