package com.servlets.ContactInfoServlet;
 
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
import com.service.BusinessLogicService;
 

public class ContactInfoServlet extends HttpServlet {
     private static String VIEW_ALL = "/contactinfos.jsp";
     private static String CREATE_CONTACT = "/createcontact.jsp";
     private static String UPDATE_CONTACT = "/updatecontact.jsp";
     
     public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String forward="";
        String action = request.getParameter("action");
        BusinessLogicService service = (BusinessLogicService)request.getSession().getAttribute("service");
        Person person = (Person)request.getSession().getAttribute("person");

        if(action.equals("viewAll")) {
           forward = VIEW_ALL;
           request.setAttribute("service",service);
        }
        else if(action.equals("create")) {
           forward = CREATE_CONTACT;
           request.setAttribute("service",service);
           request.setAttribute("person", person);
        }
        else if(action.equals("update")) {
           forward = UPDATE_CONTACT;
           int contactId = Integer.parseInt(request.getParameter("id"));
           ContactInfo ci = service.getContactInfoEntity(contactId);
           request.setAttribute("contact", ci);
           request.setAttribute("service",service);
        }
        else if(action.equals("delete")) {
           forward = VIEW_ALL;
           int contactId = Integer.parseInt(request.getParameter("id"));
           service.deleteContactInfoEntity(contactId);
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
        Person person = (Person)request.getSession().getAttribute("person");
        ContactInfo contact = (ContactInfo)request.getSession().getAttribute("contact");
        request.setAttribute("service",service);
   
        int contactId = -1;   
        String id = request.getParameter("contactId");    
        if(id != null) { contactId = Integer.parseInt(id); }
        boolean contactChecker = service.checkIfEntityContactInfoExist(contactId);

        if(contactChecker) {         
           String info = request.getParameter("information");          
           service.updateContactInfoEntity(contact.getId(),info);
           RequestDispatcher view = request.getRequestDispatcher(forward);
           view.forward(request, response);   
        }

        else if(!contactChecker) {
           ContactInfo ci = new ContactInfo(
                            request.getParameter("type"),
                            request.getParameter("information"),
                            person
                            );
           service.addContactToPerson(person.getId(),ci);
           RequestDispatcher view = request.getRequestDispatcher(forward);
           view.forward(request, response);
        }      
     }

}
