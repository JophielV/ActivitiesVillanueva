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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/Person**")
public class PersonController {
    
    @Autowired
    private BusinessLogicService service;
 
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {

        List<Person> persons = new ArrayList<Person>();
        service.listPersons(1);
        persons = service.getPersonList();
        ModelAndView model = new ModelAndView();
        model.addObject("persons", persons);
        model.setViewName("person");

        return model;
    }

    @RequestMapping(value = "/i2", method = RequestMethod.GET)
    public ModelAndView index2() throws Exception {

        List<Person> persons = new ArrayList<Person>();
        service.listPersons(2);
        persons = service.getPersonList();
        ModelAndView model = new ModelAndView();
        model.addObject("persons", persons);
        model.setViewName("person");

        return model;
    }

    @RequestMapping(value = "/i3", method = RequestMethod.GET)
    public ModelAndView index3() throws Exception {

        List<Person> persons = new ArrayList<Person>();
        service.listPersons(3);
        persons = service.getPersonList();
        ModelAndView model = new ModelAndView();
        model.addObject("persons", persons);
        model.setViewName("person");

        return model;
    }

    @RequestMapping(value = "/i4", method = RequestMethod.GET)
    public ModelAndView index4() throws Exception {

        List<Person> persons = new ArrayList<Person>();
        service.listPersons(4);
        persons = service.getPersonList();
        ModelAndView model = new ModelAndView();
        model.addObject("persons", persons);
        model.setViewName("person");

        return model;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createPage() throws Exception {
        ModelAndView model = new ModelAndView();
        Person person = new Person();
        model.addObject("person", person);
        model.setViewName("createperson");          
        return model;
    }

    @RequestMapping(value= "/save", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person person){		
	if(person.getId() == 0){
            //System.out.println("-----------------------------------------------------PRINTING PERSON!: " + person.getName());
	    //new person, add it
            
	    service.savePersonEntity(person);
	}
        else{ service.savePersonEntity(person); }
		
	return "redirect:/Person/";	
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
     public ModelAndView accesssDenied() {

	  ModelAndView model = new ModelAndView();

	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		model.addObject("username", userDetail.getUsername());
	  }

	  model.setViewName("403");
	  return model;

	}
    
    
}
