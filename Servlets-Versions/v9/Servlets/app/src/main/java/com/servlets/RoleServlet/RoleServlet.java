package com.servlets.RoleServlet;
 
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.Person;
import com.model.ContactInfo;
import com.model.Role;
import com.model.Name;
import com.model.Address;
import com.service.BusinessLogicService;
 

public class RoleServlet extends HttpServlet {
     private static String VIEW_ALL = "/roles.jsp";
     private static String CREATE_ROLE = "/createrole.jsp";
     private static String UPDATE_ROLE = "/updaterole.jsp";
     
     public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String forward="";
        String action = request.getParameter("action");
        BusinessLogicService service = (BusinessLogicService)request.getSession().getAttribute("service");

        if(action.equals("viewAll")) {
           forward = VIEW_ALL;
           request.setAttribute("service",service);
        }
        else if(action.equals("create")) {
           forward = CREATE_ROLE;
           request.setAttribute("service",service);
        }
        else if(action.equals("update")) {
           forward = UPDATE_ROLE;
           int roleId = Integer.parseInt(request.getParameter("id"));
           Role role = service.getRoleEntity(roleId);
           request.setAttribute("role", role);
           request.setAttribute("service",service);
        }
        else if(action.equals("delete")) {
           forward = VIEW_ALL;
           int roleId = Integer.parseInt(request.getParameter("id"));
           service.deleteRoleEntity(roleId);
           request.setAttribute("service",service);           
        }    

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response); 
     }
  
     public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
        String forward="";
 
        forward = VIEW_ALL;
        BusinessLogicService service = (BusinessLogicService)request.getSession().getAttribute("service");
        request.setAttribute("service",service);
   
        int roleId = -1;   
        String id = request.getParameter("roleId");    
        if(id != null) { roleId = Integer.parseInt(id); }
        boolean roleChecker = service.checkIfEntityRoleExist(roleId);

        if(roleChecker) {         
           String roleName = request.getParameter("roleName");          
           service.updateRoleEntity(roleId,roleName);
           RequestDispatcher view = request.getRequestDispatcher(forward);
           view.forward(request, response);
        }

        else if(!roleChecker) {
           service.saveRoleEntity(request.getParameter("roleName"));
           RequestDispatcher view = request.getRequestDispatcher(forward);
           view.forward(request, response);
        }      
     }

}
