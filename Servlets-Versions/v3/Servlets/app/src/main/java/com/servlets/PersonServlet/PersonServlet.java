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
     private static String VIEW = "/viewperson.jsp";
     private static String LIST_USER = "/listUser.jsp";
     
     public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
        String forward="";
        //String action = request.getParameter("action");
        //String myObjectId = request.getParameter("utility");
        //BusinessLogicService service = (BusinessLogicService)request.getSession().getAttribute(myObjectId);
        //request.getSession().removeAttribute(myObjectId);
        //BusinessLogicService service = (BusinessLogicService) request.getParameter("utility");
        BusinessLogicService service = (BusinessLogicService)request.getSession().getAttribute("service");
        forward = VIEW;
        int personId = Integer.parseInt(request.getParameter("id"));
        //BusinessLogicService service = new BusinessLogicService("root", "sample");
        //BusinessLogicService service = (BusinessLogicService) request.getAttribute("service");
        //System.out.println("Object name: " +myObjectId);
        //System.out.println("Object: " + service);
        Person person = service.searchPersonEntity(personId);
        request.setAttribute("person", person);
        request.setAttribute("service",service);
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
     }
 
}
