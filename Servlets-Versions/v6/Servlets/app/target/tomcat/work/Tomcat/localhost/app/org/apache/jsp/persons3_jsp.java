/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2016-10-13 10:27:07 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import com.service.BusinessLogicService;
import com.model.Person;;

public final class persons3_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.min.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/bootstrap-theme.min.css\">\n");
      out.write("    <script type=\"text/javascript\" src=\"/js/bootstrap.min.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"http://code.jquery.com/jquery.min.js\"></script>\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">\n");
      out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css\" integrity=\"sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp\" crossorigin=\"anonymous\">\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>\n");
      out.write("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script> \n");
      out.write("    <style type=\"text/css\">\n");
      out.write("    .wholecontainer {\n");
      out.write("    \tmargin: 20px;\n");
      out.write("    }\n");
      out.write("    .well {\n");
      out.write("        width: 250px; \n");
      out.write("        padding: 10px 0;\n");
      out.write("    }\n");
      out.write("    .nav-tabs > li, .nav-pills > li {\n");
      out.write("       float:none !important;\n");
      out.write("       display:inline-block !important;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .nav-tabs, .nav-pills {\n");
      out.write("       text-align:center !important;\n");
      out.write("     } \n");
      out.write("     .forceInline {\n");
      out.write("       display: inline;\n");
      out.write("     }\n");
      out.write("     .beside {\n");
      out.write("        float: left;\n");
      out.write("        position: relative;\n");
      out.write("        left: 500px;\n");
      out.write("     }\n");
      out.write("</style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("   <div class=\"wholecontainer\">\n");
      out.write("     \n");
      out.write("    <center>\n");
      out.write("    <ul class=\"nav nav-pills\">\n");
      out.write("        <li class=\"active\"><a href=\"Person?action=viewAll\"><i class=\"glyphicon glyphicon-user\"></i>Persons</a></li>\n");
      out.write("        <li><a href=\"ContactInfo?action=viewAll\"><i class=\"glyphicon glyphicon-envelope\"></i>Contacts</a></li>\n");
      out.write("        <li><a href=\"#\"><i class=\"glyphicon glyphicon-star\"></i>Roles</a></li>\n");
      out.write("        <li><a href=\"#\"><i class=\"glyphicon glyphicon-eye-open\"></i>Person Roles</a></li>\n");
      out.write("    </ul>\n");
      out.write("    </center>\n");
      out.write("\n");
      out.write("     <center>\n");
      out.write("       <div class=\"page-header\">\n");
      out.write("         <div class=\"forceInline\"><div class=\"beside\"><span class=\"glyphicon glyphicon-user\"></span></div><h2>Person Records</h2></div>\n");
      out.write("       </div>\n");
      out.write("     </center>\n");
      out.write("     \n");
      out.write("    <form>\n");
      out.write("        <div class=\"row\">\n");
      out.write("             <div class=\"col-xs-offset-3\">\n");
      out.write("                <div class=\"col-xs-4\">\n");
      out.write("                   <div class=\"input-group\">\n");
      out.write("                      <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-user\"></span></span>\n");
      out.write("                      <input type=\"text\" class=\"form-control\" placeholder=\"First Name\">\n");
      out.write("                   </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-xs-4\">\n");
      out.write("                   <div class=\"input-group\">\n");
      out.write("                      <span class=\"input-group-addon\"><span class=\"glyphicon glyphicon-user\"></span></span>\n");
      out.write("                      <input type=\"text\" class=\"form-control\" placeholder=\"Last Name\">\n");
      out.write("                   </div>\n");
      out.write("                </div>\n");
      out.write("                <button type=\"submit\" class=\"btn btn-primary\"><span class=\"glyphicon glyphicon-search\"></span> Search</button>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </form>\n");
      out.write("    <a href=\"Person?action=create\"><button class=\"btn btn-primary\"><span class=\"glyphicon glyphicon-share\"></span>Create Person</button></a>\n");
      out.write("\n");
      out.write("  \n");
      out.write("   <table class=\"table table-hover\">\n");
      out.write("        <thead>\n");
      out.write("            <tr>\n");
      out.write("                <th><a href=\"Person?action=viewAll\">Person Id</a></th>\n");
      out.write("                <th><a href=\"Person?action=viewAll4\">Name</a></th>\n");
      out.write("                <th>Address</th>\n");
      out.write("                <th>Birthday</th>\n");
      out.write("                <th><a href=\"Person?action=viewAll2\">GWA</a></th>\n");
      out.write("                <th><a href=\"Person?action=viewAll3\">Date Hired</a></th>\n");
      out.write("                <th>Employed</th>\n");
      out.write("                <th>Actions</th>\n");
      out.write("            </tr>\n");
      out.write("        </thead>\n");
      out.write("        <tbody>\n");
      out.write("            ");

               BusinessLogicService service = (BusinessLogicService) request.getAttribute("service");
               request.getSession().setAttribute("service", service);
               List<Person> list = service.listPersons(3);
               for (Person p : list) {
               String holder = p.getCurrentlyEmployed() == true ? "Y" : "N";
            
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td>");
      out.print(p.getId());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(p.getName());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(p.getAddress());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(p.getBirthday());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(p.getGwa());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(p.getDateHired());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(holder);
      out.write("</td>\n");
      out.write("                <td>\n");
      out.write("                   <a href=\"Person?action=view&id=");
      out.print(p.getId());
      out.write("\"><button class=\"btn btn-primary btn-xs\"><span class=\"glyphicon glyphicon-file\"></span>&nbsp;&nbsp;View</button></a>\n");
      out.write("                   <a href=\"Person?action=update&id=");
      out.print(p.getId());
      out.write("\"><button class=\"btn btn-info btn-xs\"><span class=\"glyphicon glyphicon-pencil\"></span>&nbsp;&nbsp;Update</button></a>\n");
      out.write("                   <a href=\"Person?action=deleteconfirm&id=");
      out.print(p.getId());
      out.write("\"><button class=\"btn btn-danger btn-xs\"><span class=\"glyphicon glyphicon-remove\"></span>&nbsp;&nbsp;Delete</button></a>\n");
      out.write("                ");
}
      out.write("                \n");
      out.write("            </tr>\n");
      out.write("        </tbody>\n");
      out.write("    </table>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
