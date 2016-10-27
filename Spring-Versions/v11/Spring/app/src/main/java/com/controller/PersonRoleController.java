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
@RequestMapping("/PersonRole**")
public class PersonRoleController {
    
    @Autowired
    private BusinessLogicService service;
 
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        List<PersonRoles> personRoles = service.listPersonRoles();
        ModelAndView model = new ModelAndView();

        model.addObject("personRoles", personRoles);
        model.addObject("service", service);
        model.setViewName("personroles");

        return model;
    }

    @RequestMapping(value = "/addrole/{id}", method = RequestMethod.GET)
    public ModelAndView addRolePage(@PathVariable("id") int id) throws Exception {
        ModelAndView model = new ModelAndView();
        
        Person person = service.getPersonEntity(id);
        List<Role> roles = service.listRoles();
        model.addObject("person",person);
        model.addObject("roles",roles);
        model.setViewName("addroletoperson");   
       
        return model;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestParam("roleId") int roleId,@RequestParam("personId") int personId) {	 // here
        Role role = service.getRoleEntity(roleId);	
        service.addRoleToPerson(personId,role); 

	return "redirect:/PersonRole/";	
    }

    @RequestMapping(value = "/delete/{id}/{id2}")
    public String delete(@PathVariable("id") int id, @PathVariable("id2") int id2){        
        service.deleteRoleToPerson(id,id2);  

        return "redirect:/PersonRole/";
    }
}
