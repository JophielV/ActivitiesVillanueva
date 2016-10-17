package com.servlets.PersonRoleServlet;
 
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
import com.model.Name;
import com.model.Address;
import com.model.Role;
import com.service.BusinessLogicService;
 

public class PersonRoleServlet extends HttpServlet {
     private static String VIEW_ALL = "/personroles.jsp";

     public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String forward="";
        String action = request.getParameter("action");
        BusinessLogicService service = (BusinessLogicService)request.getSession().getAttribute("service");
        
        if(action.equals("viewAll")) {
           forward = VIEW_ALL;
           request.setAttribute("service",service);
        }
        
        else if(action.equals("delete")) {
           forward = VIEW_ALL;
           int personId = Integer.parseInt(request.getParameter("id"));
           int roleId = Integer.parseInt(request.getParameter("id2"));
           service.deleteRoleToPerson(personId,roleId);
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
        Person person = (Person)request.getSession().getAttribute("person");
        String id = request.getParameter("roleId");
        int roleId = Integer.parseInt(id);
        Role role = service.getRoleEntity(roleId);
        int personId = person.getId();
        service.addRoleToPerson(personId,role);

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response); 
     }

}
