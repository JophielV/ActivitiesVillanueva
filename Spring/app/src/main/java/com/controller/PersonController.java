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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.annotation.Secured;
import com.service.BusinessLogicService;
import com.dao.implementations.PersonDAO;
import com.dao.interfaces.PersonDaoInterface;
import com.dao.interfaces.ContactInfoDaoInterface;
import com.model.Person;
import com.model.ContactInfo;
import com.model.Role;
import com.model.PersonRoles;
 
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


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("person") Person person, @RequestParam("employed") String employed) {
		
	if(person.getId() == 0) { 
           boolean holder = employed.equals("Y") ? true : false;
           person.setCurrentlyEmployed(holder);
           service.savePersonEntity(person); 

        }
        else { 
           boolean holder = employed.equals("Y") ? true : false;
           person.setCurrentlyEmployed(holder);
           service.updatePersonEntity(person.getId(),person); 
        }
		
	return "redirect:/Person/";	
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView();

        Person person = service.searchPersonEntity(id);
        List<ContactInfo> contacts = service.listContactInfo(person.getId());
        List<PersonRoles> roles = service.getRoleOfPerson(person.getId());
        model.addObject("person",person);
        model.addObject("contacts",contacts);
        model.addObject("roles",roles);
        model.addObject("service", service);
        model.setViewName("viewperson");  

        return model;
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView();

        Person person = service.searchPersonEntity(id);
        List<ContactInfo> contacts = service.listContactInfo(person.getId());
        List<Role> roles = service.getRoleOfPerson(person.getId());

        String holderEmployed = person.getCurrentlyEmployed() == true ? "Y" : "N";
        String[] splitBirthday = person.getBirthday().toString().split("-");
        String finalBirthday = splitBirthday[1] + "/" + splitBirthday[2] + "/" + splitBirthday[0];
        String[] splitDateHired = person.getDateHired().toString().split("-");
        String finalDateHired = splitDateHired[1] + "/" + splitDateHired[2] + "/" + splitDateHired[0];
        model.addObject("holderEmployed",holderEmployed);
        model.addObject("finalBirthday",finalBirthday);
        model.addObject("finalDateHired",finalDateHired);
 
        model.addObject("person",person);
        model.addObject("contacts",contacts);
        model.addObject("roles",roles);
        model.addObject("service",service);
        model.setViewName("updateperson");  

        return model;
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") int id){        
        service.deletePersonEntity(id);  

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
