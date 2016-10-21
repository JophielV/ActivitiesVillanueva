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
import com.dao.implementations.ContactInfoDAO;
import com.dao.interfaces.PersonDaoInterface;
import com.dao.interfaces.ContactInfoDaoInterface;
import com.model.Person;
import com.model.ContactInfo;
 
/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/Next", method = RequestMethod.GET)
public class NextController {
    
    @Autowired
    private BusinessLogicService service;
 
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView handleRequest() throws Exception {
        List<ContactInfo> contacts = new ArrayList<ContactInfo>();
        contacts = service.listContactInfo();
        ModelAndView model = new ModelAndView("index2");
        model.addObject("ci", contacts);
        return model;
    }
    
    
}
