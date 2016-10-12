<html>
   <head>
     <meta charset="utf-8">
     <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
     <link rel="stylesheet" href="css/bootstrap-theme.min.css">
     <script type="text/javascript" src="/js/bootstrap.min.js"></script>
     <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script> 
     <style type="text/css">
        .wholeForm {
           position: relative;
           top: 200px;
           left: 250px;
        }
     </style>
   </head>

   <body>
      <center><h2>Database Credentials</h2></center>
      <div class="wholeForm">
        <form class="form-horizontal" method="post" action="LoginServlet">
            <div class="form-group">
                <label for="inputEmail" class="control-label col-xs-2">Username</label>
                <div class="col-xs-4">
                     <input type="text" class="form-control" name="userName" placeholder="Username">
                </div>
             </div>
             <div class="form-group">
                <label for="inputPassword" class="control-label col-xs-2">Password</label>
                <div class="col-xs-4">
                   <input type="password" class="form-control" name="password" placeholder="Password">
                </div>
             </div>
             <div class="form-group">
                <div class="col-xs-offset-2 col-xs-10">
                   <button type="submit" value="Submit" class="btn btn-primary">Login</button>
                </div>
             </div>
        </form>
    </div>
   </body>

</html>
