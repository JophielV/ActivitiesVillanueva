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
import com.model.Name;
import com.model.Address;
import com.service.BusinessLogicService;
 

public class PersonServlet extends HttpServlet {
     private static String VIEW_SPECIFIC = "/viewperson.jsp";
     private static String VIEW_ALL = "/persons.jsp";
     private static String CREATE_PERSON = "/createperson.jsp";
     private static String CREATE_PERSON_SUCCESS = "";
     private static String UPDATE_PERSON = "/updateperson.jsp";
     private static String DELETE_PERSON_CONFIRM = "/deleteconfirm.jsp";
     private static String ADD_ROLE_TO_PERSON = "/addroletoperson.jsp";
     private static String SEARCH_PERSON = "/searchperson.jsp";
     
     public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
        String forward="";
        String action = request.getParameter("action");
        BusinessLogicService service = (BusinessLogicService)request.getSession().getAttribute("service");
        
        if(action != null) {
           if(action.equals("view")) {
              forward = VIEW_SPECIFIC;
              int personId = Integer.parseInt(request.getParameter("id"));
              Person person = service.searchPersonEntity(personId);
              service.listPersons2(1);
              request.setAttribute("person", person);
              request.setAttribute("service",service);
            }
           else if(action.equals("viewAll")) {
              forward = VIEW_ALL;
              service.listPersons2(1);
           }
           else if(action.equals("viewAll2")) {
              forward = VIEW_ALL;
              service.listPersons2(2);
           }
           else if(action.equals("viewAll3")) {     
              forward = VIEW_ALL;
              service.listPersons2(3); 
           }
           else if(action.equals("viewAll4")) {
              forward = VIEW_ALL;
              service.listPersons2(4);
           }
           else if(action.equals("create")) {
              forward = CREATE_PERSON;
              service.listPersons2(1);
           }
           else if(action.equals("update")) {
              forward = UPDATE_PERSON;
              int personId = Integer.parseInt(request.getParameter("id"));
              Person person = service.searchPersonEntity(personId);
              service.listPersons2(1);
              request.setAttribute("person", person);
           }
           else if(action.equals("deleteconfirm")) {
              forward = DELETE_PERSON_CONFIRM;
              int personId = Integer.parseInt(request.getParameter("id"));
              Person person = service.searchPersonEntity(personId);
              service.listPersons2(1);
              request.setAttribute("person", person);
           }
           else if(action.equals("delete")) {
              forward = VIEW_ALL;
              int personId = Integer.parseInt(request.getParameter("id"));
              service.deletePersonEntity(personId);     
              service.listPersons2(1);     
           }    
           else if(action.equals("addRole")) {
              forward = ADD_ROLE_TO_PERSON;
              int personId = Integer.parseInt(request.getParameter("id"));
              Person person = service.searchPersonEntity(personId);
              service.listPersons2(1);
              request.setAttribute("person", person);
           }
        }
        else { 
           forward = VIEW_ALL; 
           service.listPersons2(1); 
        }

        request.setAttribute("service",service);
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response); 
     }

     public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
        String forward="";
        BusinessLogicService service = (BusinessLogicService)request.getSession().getAttribute("service");
  
        int personId = -1;   
        String id = request.getParameter("personId");    
        if(id != null) { personId = Integer.parseInt(id); }
        boolean personChecker = service.checkIfEntityExist(personId);

   

        if(personChecker && request.getParameter("middleName") != null) {
           forward = VIEW_ALL;
           Name name = new Name(
                       request.getParameter("lastName"),
                       request.getParameter("firstName"),
                       request.getParameter("middleName"),
                       request.getParameter("suffix"),
                       request.getParameter("title")
                    );
           Address address = new Address(
                          Integer.parseInt(request.getParameter("streetNo")),
                          request.getParameter("barangay"),
                          request.getParameter("city"),
                          Integer.parseInt(request.getParameter("zipCode"))
                          );
           String extractBirthday = request.getParameter("birthday");
           String[] splitBirthday = extractBirthday.split("/");
           String finalBirthday = splitBirthday[1] + "/" + splitBirthday[0] + "/" + splitBirthday[2];
           Date date = service.extractDate(finalBirthday);
           String extractDateHired = request.getParameter("dateHired");
           String[] splitDateHired = extractDateHired.split("/");
           String finalDateHired = splitDateHired[1] + "/" + splitDateHired[0] + "/" + splitDateHired[2];
           Date date2 = service.extractDate(finalDateHired);
           boolean employed = request.getParameter("currentlyEmployed").equals("Y") ? true : false;
           Person person = new Person(
                       name, 
                       address,
                       date,
                       Double.parseDouble(request.getParameter("gwa")),
                       date2,
                       employed
                       );
           service.updatePersonEntity(personId,person);
        }

        else if(!personChecker && request.getParameter("middleName") != null) {
           forward = VIEW_ALL;
           Name name = new Name(
                       request.getParameter("lastName"),
                       request.getParameter("firstName"),
                       request.getParameter("middleName"),
                       request.getParameter("suffix"),
                       request.getParameter("title")
                    );
           Address address = new Address(
                          Integer.parseInt(request.getParameter("streetNo")),
                          request.getParameter("barangay"),
                          request.getParameter("city"),
                          Integer.parseInt(request.getParameter("zipCode"))
                          );
           String extractBirthday = request.getParameter("birthday");
           String[] splitBirthday = extractBirthday.split("/");
           String finalBirthday = splitBirthday[1] + "/" + splitBirthday[0] + "/" + splitBirthday[2];
           Date date = service.extractDate(finalBirthday);
           String extractDateHired = request.getParameter("dateHired");
           String[] splitDateHired = extractDateHired.split("/");
           String finalDateHired = splitDateHired[1] + "/" + splitDateHired[0] + "/" + splitDateHired[2];
           Date date2 = service.extractDate(finalDateHired);
           boolean employed = request.getParameter("currentlyEmployed").equals("Y") ? true : false;
           Person person = new Person(
                       name, 
                       address,
                       date,
                       Double.parseDouble(request.getParameter("gwa")),
                       date2,
                       employed
                       );
           service.savePersonEntity(person);
        }
        else {
           forward = SEARCH_PERSON;
           String firstName = request.getParameter("firstName");
           String lastName = request.getParameter("lastName");
           request.setAttribute("firstName",firstName);
           request.setAttribute("lastName",lastName); 
        }
        service.listPersons2(1);
        request.setAttribute("service",service);
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);      
     }
     

}
