package com.servlets.LoginServlet;
 
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
 

public class LoginServlet extends HttpServlet {
     private static String VIEW = "/persons.jsp";
     
     public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
        String forward="";
        forward = VIEW;
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        BusinessLogicService service = new BusinessLogicService(username,password);
        request.setAttribute("service",service);
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
     }
 
}
