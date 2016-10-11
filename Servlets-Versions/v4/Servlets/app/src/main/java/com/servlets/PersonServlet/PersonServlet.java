package com.servlets.PersonServlet;
 
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
import com.service.BusinessLogicService;
 

public class PersonServlet extends HttpServlet {
     private static String VIEW_SPECIFIC = "/viewperson.jsp";
     private static String VIEW_ALL = "/persons.jsp";
     int hits = 0;
     
     public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
        String forward="";
        String action = request.getParameter("action");
      
        if(action.equals("view")) {
           forward = VIEW_SPECIFIC;
           BusinessLogicService service = (BusinessLogicService)request.getSession().getAttribute("service");
           int personId = Integer.parseInt(request.getParameter("id"));
           Person person = service.searchPersonEntity(personId);
           request.setAttribute("person", person);
           request.setAttribute("service",service);
        
           RequestDispatcher view = request.getRequestDispatcher(forward);
           view.forward(request, response);
        }
        else if(action.equals("viewAll")) {
           forward = VIEW_ALL;
           BusinessLogicService service = (BusinessLogicService)request.getSession().getAttribute("service");
           request.setAttribute("service",service);
        
           RequestDispatcher view = request.getRequestDispatcher(forward);
           view.forward(request, response);
        }
      
     }
     

}
