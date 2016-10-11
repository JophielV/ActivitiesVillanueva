<html>
   <head>
     <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
     <link rel="stylesheet" href="css/bootstrap-theme.min.css">
     <script type="text/javascript" src="/js/bootstrap.min.js"></script>
     <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
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
