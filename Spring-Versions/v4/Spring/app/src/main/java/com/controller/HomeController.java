package com.controller;
 
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.service.BusinessLogicService;
import com.dao.implementations.PersonDAO;
import com.dao.interfaces.PersonDaoInterface;
import com.dao.interfaces.ContactInfoDaoInterface;
import com.model.Person;
 
/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/Home")
public class HomeController {
    
    @Autowired
    private BusinessLogicService service;
 
    @RequestMapping("/")
    public ModelAndView handleRequest() throws Exception {
        List<Person> persons = new ArrayList<Person>();
        persons = service.listPersons(1);
        ModelAndView model = new ModelAndView("index");
        model.addObject("persons", persons);
        return model;
    }
    
    
}
