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
import com.model.PersonRoles;
 
/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/ContactInfo**")
public class ContactInfoController {
    
    @Autowired
    private BusinessLogicService service;
 
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {

        List<ContactInfo> contacts = service.listContactInfo();
        ModelAndView model = new ModelAndView();
        model.addObject("contacts", contacts);
        model.setViewName("contactinfo");

        return model;
    }

    @RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
    public ModelAndView createPage(@PathVariable("id") int id) throws Exception {
        ModelAndView model = new ModelAndView();
        ContactInfo contact = new ContactInfo();
        model.addObject("contact", contact);
        model.addObject("id",id);
        model.setViewName("createcontact");          
        return model;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("contact") ContactInfo contact, @RequestParam("personId") int personId, @RequestParam("information") String information) {
         ContactInfo ci = new ContactInfo();
         
         if(!service.checkIfEntityContactInfoExist(personId)) { service.addContactToPerson(personId,contact); }
         else { service.updateContactInfoEntity(personId,information); } 
		
	return "redirect:/ContactInfo/";	
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView();

        ContactInfo contact = service.getContactInfoEntity(id); 
        model.addObject("id",id);
        model.addObject("contact",contact);
        model.setViewName("updatecontact");  

        return model;
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") int id){        
        service.deleteContactInfoEntity(id);  

        return "redirect:/ContactInfo/";
    }
}
