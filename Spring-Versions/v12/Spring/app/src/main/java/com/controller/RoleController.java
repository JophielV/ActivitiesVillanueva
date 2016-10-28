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
@RequestMapping("/Role**")
public class RoleController {
    
    @Autowired
    private BusinessLogicService service;
 
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {

        List<Role> roles = service.listRoles();
        ModelAndView model = new ModelAndView();
        model.addObject("roles", roles);
        model.setViewName("role");

        return model;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createPage() throws Exception {
        ModelAndView model = new ModelAndView();
        Role role = new Role();
        model.addObject("role", role);
        model.setViewName("createrole");          
        return model;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView();

        Role role = service.getRoleEntity(id); 
        model.addObject("role",role);
        model.setViewName("updaterole");  

        return model;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestParam("role") String role) {		
	 service.saveRoleEntity(role); 
        //else { service.updatePersonEntity(person.getId(),person); }
		
	return "redirect:/Role/";	
    }

    @RequestMapping(value = "/saveupdate", method = RequestMethod.POST)
    public String saveupdate(@RequestParam("roleId") int id, @RequestParam("roleName") String update) {		
	 service.updateRoleEntity(id,update); 
		
	return "redirect:/Role/";	
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") int id){        
        service.deleteRoleEntity(id);  

        return "redirect:/Role/";
    }

}
