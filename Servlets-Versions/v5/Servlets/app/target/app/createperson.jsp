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
        BusinessLogicService service = (BusinessLogicService) request.getAttribute("service");
        request.getSession().setAttribute("service", service);
      %>
      <center><h2>Create Person</h2></center>
      <br/>
      
    <div class="alert alert-warning">
        <strong>Notice:</strong> All fields required. Put N/A if not applicable.
    </div>

      <form class="form-horizontal" method="post" action="Person">
        <h3>Name: </h3>
        <div class="form-group ">
            <label class="col-xs-2 control-label">First Name:</label>
            <div class="col-xs-3">
                <input type="text" name="firstName"  class="form-control" placeholder="First Name" pattern="^[A-Za-z ,.'-]+$" required> 
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label" >Last name: </label>
            <div class="col-xs-3">
                <input type="text" name="lastName"  class="form-control" placeholder="Last Name" pattern="^[A-Za-z ,.'-]+$" required>            
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Middle name: </label>
            <div class="col-xs-3">
                <input type="text" name="middleName"  class="form-control" placeholder="Middle Name" pattern="^[A-Za-z ,.'-]+$" required>
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Suffix: </label>
            <div class="col-xs-3">
                <input type="text" name="suffix"  class="form-control" placeholder="Suffix" pattern="^[A-Za-z ,.'-]+$" required>
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Title: </label>
            <div class="col-xs-3">
                <input type="text" name="title"  class="form-control" placeholder="Title" pattern="^[A-Za-z ,.'-]+$" required>
            </div>
        </div>
        <h3>Address: </h3>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Street No.: </label>
            <div class="col-xs-3">
                <input type="text" name="streetNo"  class="form-control" placeholder="Street No." pattern="^[0-9]+$" required>
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Barangay: </label>
            <div class="col-xs-3">
                <input type="text" name="barangay"  class="form-control" placeholder="Barangay" pattern="^[A-Za-z ,.'-]+$" required>
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">City: </label>
            <div class="col-xs-3">
                <input type="text" name="city"  class="form-control" placeholder="City" pattern="^[A-Za-z ,.'-]+$" required>
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Zip Code: </label>
            <div class="col-xs-3">
                <input type="text" name="zipCode" class="form-control" placeholder="Zip Code" pattern="^[0-9]+$" required>
            </div>
        </div>
        <h3>Other Information:</h3>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Birthday: </label>
            <div class="col-xs-3">
                <input type="text" name="birthday" id="datepicker" class="form-control" placeholder="Birthday" required> 
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">GWA: </label>
            <div class="col-xs-3">
                <input type="text" name="gwa" class="form-control" placeholder="GWA" pattern="^[+-]?\d+(\.\d+)?$" required>
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Date Hired: </label>
            <div class="col-xs-3">
                <input type="text" name="dateHired" id="datepicker2"  class="form-control" placeholder="Date Hired" required>
            </div>
        </div>
        <div class="form-group ">
            <label class="col-xs-2 control-label">Currently Employed: </label>
            <div class="col-xs-3">
                <input type="text" name="currentlyEmployed" class="form-control" placeholder="Employed?" pattern="^Y|N$" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-offset-2 col-xs-10">
                <button type="submit" value="Submit" class="btn btn-primary"><span class="glyphicon glyphicon-share"></span>Create</button>
                <button type="reset" value="Reset" class="btn btn-primary"><span class="glyphicon glyphicon-repeat"></span>Reset</button>
            </div>
        </div>
    </form>
   </div>
</body>

</html>
