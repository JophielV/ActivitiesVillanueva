package com.controller;
 
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.service.BusinessLogicService;
 
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
        
        ModelAndView model = new ModelAndView("index2");
        
        return model;
    }
     
    
}
