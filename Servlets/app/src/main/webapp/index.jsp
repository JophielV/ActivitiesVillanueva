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
        <li class="active"><a href="index.jsp"><i class="glyphicon glyphicon-user"></i>Persons</a></li>
        <li><a href="contactinfo.jsp"><i class="glyphicon glyphicon-envelope"></i>Contacts</a></li>
        <li><a href="#"><i class="glyphicon glyphicon-star"></i>Roles</a></li>
        <li><a href="#"><i class="glyphicon glyphicon-eye-open"></i>Person Roles</a></li>
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
    <button class="btn btn-primary"><span class="glyphicon glyphicon-share"></span>Create Person</button>

    <form>
       <div class="row">
          <div class="col-xs-offset-4">
             Sort By:&nbsp;&nbsp;
             <label class="radio-inline">
                <input type="radio" name="optradio">Last Name
             </label>
             <label class="radio-inline">
                <input type="radio" name="optradio">GWA
             </label>
             <label class="radio-inline">
                <input type="radio" name="optradio">Date Hired
             </label>
             &nbsp;&nbsp;
             <button type="submit" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;Sort</button>
          </div>
       </div>
   </form>
  
   <table class="table table-hover">
        <thead>
            <tr>
                <th>Person Id</th>
                <th>Name</th>
                <th>Address</th>
                <th>Birthday</th>
                <th>GWA</th>
                <th>Date Hired</th>
                <th>Employed</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>Virgil Reyes</td>
                <td>Quezon</td>
                <td>12/12/1990</td>
                <td>2.0</td>
                <td>12/12/2000</td>
                <td>Y</td>
                <td>
                   <button class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;View</button>
                   <button class="btn btn-info btn-xs"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Update</button>
                   <button class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;Delete</button>
                </td>
                
            </tr>
            <tr>
                <td>2</td>
                <td>Virgil Reyes</td>
                <td>Quezon</td>
                <td>12/12/1990</td>
                <td>2.0</td>
                <td>12/12/2000</td>
                <td>Y</td>
                <td>
                   <button class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;View</button>
                   <button class="btn btn-info btn-xs"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Update</button>
                   <button class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;Delete</button>
                </td>
            </tr>
            <tr>
                <td>3</td>
                <td>Virgil Reyes</td>
                <td>Quezon</td>
                <td>12/12/1990</td>
                <td>2.0</td>
                <td>12/12/2000</td>
                <td>Y</td>
                <td>
                   <button class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;View</button>
                   <button class="btn btn-info btn-xs"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Update</button>
                   <button class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;Delete</button>
                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>
