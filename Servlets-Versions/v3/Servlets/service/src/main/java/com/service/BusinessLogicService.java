package com.service;

import java.util.InputMismatchException;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import com.model.Address;
import com.model.ContactInfo;
import com.model.Name;
import com.model.Person;
import com.model.Role;
import com.model.PersonRoles;
import com.dao.implementations.PersonDAO;
import com.dao.implementations.RolesDAO;
import com.dao.implementations.ContactInfoDAO;
import com.dao.implementations.PersonRolesDAO;
import com.service.ValidationService;
import java.util.Iterator; 
import java.util.*; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.*;
import java.util.stream.Collectors; 
import java.util.stream.Stream;
import org.hibernate.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.model.Role;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.proxy.HibernateProxy;


public class BusinessLogicService {  
   PersonDAO p = new PersonDAO();
   RolesDAO r = new RolesDAO();
   ContactInfoDAO c = new ContactInfoDAO();
   PersonRolesDAO pR = new PersonRolesDAO();

   public BusinessLogicService() {}
 
   public BusinessLogicService(String username, String password) {
      this.p = new PersonDAO(username,password);
      this.r = new RolesDAO(username,password);
      this.c = new ContactInfoDAO(username,password);
      this.pR = new PersonRolesDAO(username,password);
   }

 
   public Name createNameEntity(String lastName, String firstName, String middleName, String suffix, String title) {
      Name name = new Name(lastName, firstName, middleName, suffix, title);
      return name;
   }
 
   public Address createAddressEntity(String streetNo, String barangay, String city, String zipCode) {
      int strtNo = Integer.parseInt(streetNo);
      int zCode = Integer.parseInt(zipCode); 
      Address address = new Address(strtNo, barangay, city, zCode);
      return address;
   }

   public Person createPersonEntity(Name name, Address addr ,Date birthday, String gwa, Date dateHired, String currentlyEmployed) {
      double GWA = Double.parseDouble(gwa);
      boolean currEmployed = currentlyEmployed.equals("Y") ? true : false;
      Person person = new Person(name,addr,birthday,GWA,dateHired,currEmployed);
      return person;
   }

   public ContactInfo createContactInfoEntity(String type, String information) {
      ContactInfo ci = new ContactInfo();
      ci.setType(type);
      ci.setInformation(information);
      return ci;
   }

   public void savePersonEntity(Person person) {
      p.save(person);
   }

   public boolean checkIfEntityExist(String id) {
      int ID = Integer.parseInt(id);
      if(p.getPerson(ID) != null) { return true; }
      else if(p.getPerson(ID) == null) { 
         System.out.println("Error: Person with id " + id + " has not been found");
         return false; 
      }
      return false;
   }

   public boolean checkIfEntityRoleExist(String id) {
      int ID = Integer.parseInt(id);
      if(r.getRole(ID) != null) { return true; }
      else if(r.getRole(ID) == null) { 
         System.out.println("Error: Role with id " + id + " has not been found");
         return false; 
      }
      return false;
   }

   public boolean checkIfEntityContactInfoExist(String id) {
      int ID = Integer.parseInt(id);
      if(c.getContactInfo(ID) != null) { return true; }
      else if(c.getContactInfo(ID) == null) { 
         System.out.println("Error: Contact Info with id " + id + " has not been found");
         return false; 
      }
      return false;
   }

   public boolean checkIfEntityContactInfoExist(String id, String id2) {
      int ID = Integer.parseInt(id);
      int ID2 = Integer.parseInt(id2);
      if(c.getContactInfo(ID,ID2) != null) { return true; }
      else if(c.getContactInfo(ID,ID2) == null) { 
         System.out.println("Error: Contact Info with id " + id + " has not been found for this person");
         return false; 
      }
      return false;
   }

   public Person getPersonEntity(String id) {
      int ID = Integer.parseInt(id);
      Person person = new Person();
      person = p.getPerson(ID);
      return person;
   }

   public boolean deletePersonEntity(String id) {
      int ID = Integer.parseInt(id);
      
      if(p.getPerson(ID) != null) {
         p.delete(ID);
         System.out.println("Notice: Person with " + id + " has been successfully deleted.");
         return false;
      }
      else if(p.getPerson(ID) == null) {
         System.out.println("Error: Person with id " + id + " has not been found");
         return true;
      }
      return false;
   }

   
   public void updatePersonEntity(String id, Object update, int type) {
      ValidationService validationService = new ValidationService();
      int ID = Integer.parseInt(id);
      String stringHolder = "";
      switch(type) {
         case 1:
            p.update(ID,update,1);
            break;
         case 2:
            p.update(ID,update,2);
            break;
         case 3:
            p.update(ID,update,3);
            break;
         case 4:
            p.update(ID,update,4);
            break;
         case 5:
            p.update(ID,update,5);
            break;
         case 6:
            p.update(ID,update,6);  
            break;
         case 7:
            Date date = new Date();
            stringHolder = update.toString();
            date = validationService.extractDate(stringHolder);
            p.update(ID,date,7);
            break;
         case 8:
            p.update(ID,update,8);
            break;
         case 9:
            Date date2 = new Date();
            stringHolder = update.toString();
            date2 = validationService.extractDate(stringHolder);
            p.update(ID,date2,9);
            break;
         case 10:       
            p.update(ID,update,10);
          
      }
   }

   public ContactInfo getContactInfoEntity(String id) {
      int ID = Integer.parseInt(id);
      ContactInfo ci = new ContactInfo();
      ci = c.getContactInfo(ID);
      return ci;
   }

   public ContactInfo getContactInfoEntity(String id, String id2) {
      int ID = Integer.parseInt(id);
      int ID2 = Integer.parseInt(id2);
      ContactInfo ci = new ContactInfo();
      ci = c.getContactInfo(ID,ID2);
      return ci;
   }

   public void updateContactInfoEntity(String id, String update) {
      int ID = Integer.parseInt(id);
      c.update(ID,update);
   }
 
   public boolean deleteContactInfoEntity(String id) {
      int ID = Integer.parseInt(id);
      
      if(c.getContactInfo(ID) != null) {
         c.delete(ID);
         System.out.println("Notice: Contact Info with " + id + " has been successfully deleted.");
         return false;
      }
      else if(c.getContactInfo(ID) == null) {
         System.out.println("Error: Contact Info with id " + id + " has not been found");
         return true;
      }
      return false;
   }

   public void addContactToPerson(String id, ContactInfo ci) {
      int ID = Integer.parseInt(id);
      p.addContact(ID,ci);    
   }

   public void addRoleToPerson(String id, Role role) {
      int ID = Integer.parseInt(id);
      p.addRole(ID,role);
   }

   public void deleteRoleToPerson(String id, String id2) {
      int ID = Integer.parseInt(id);
      int ID2 = Integer.parseInt(id2);
      p.deleteRole(ID,ID2);
   }

   public Role getRoleEntity(String id) {
      int ID = Integer.parseInt(id);
      Role role = new Role();
      role = r.getRole(ID);
      return role;
   }

   public void saveRoleEntity(String role) {
      r.save(role);
   }

   public void updateRoleEntity(String id, String update) {
      int ID = Integer.parseInt(id);
      r.update(ID,update);
   }

   public void deleteRoleEntity(String id) {
      int ID = Integer.parseInt(id);
      r.delete(ID);
   }


   public List listPersons(int choice) {
      List<Person> persons = new ArrayList<Person>();
      persons = p.listPersons(choice);

      if(choice == 2) {
         Collections.sort(persons, 
                        (o1, o2) -> new Double(o1.getGwa()).compareTo(new Double(o2.getGwa())));
      }  
      return persons;
   }  

   public void listRoles() {
      List<Role> roles = r.listRoles();
    
      System.out.println("------------------------------ Roles Table ----------------------------");
      System.out.println(String.format("%-20s%-20s" , "Role Id", "Role Name" ));
      System.out.println("------------------------------------------------------------------------");
      for (Iterator iterator = roles.iterator(); iterator.hasNext();) {
            Role role = (Role) iterator.next(); 
            System.out.println(String.format("%-20s%-20s" , Integer.toString(role.getId()), role.getRoleName() ));
      }
      System.out.println("------------------------------------------------------------------------");
      System.out.println("Record Count: " + roles.size());
   }

   public void listPersonRoles() {
      List<PersonRoles> personRoles = pR.listPersonRoles();
    
      System.out.println("----------------------------------------------- Person Role Table ------------------------------------------"
      + "---------------------------------------");
      System.out.println(String.format("%-30s%-30s%-30s%-35s" , "Role Id", "Role", "Name" ,"Person Id" ));
      System.out.println("-------------------------------------------------------------------------------------------------------------"
      + "---------------------------------------" );
      for (Iterator iterator = personRoles.iterator(); iterator.hasNext();) {
           
            PersonRoles pr = (PersonRoles) iterator.next(); 
            Role role = r.getRole(pr.getPersonRole().getRoleId());
            Person person = p.getPerson(pr.getPersonRole().getPersonId());
            System.out.println(String.format("%-30s%-30s%-30s%-35s" , pr.getPersonRole().getRoleId()
            , role.getRoleName(), person.getName() ,pr.getPersonRole().getPersonId()));
           
      }
      System.out.println("-------------------------------------------------------------------------------------------------------------"
      + "---------------------------------------" );
      System.out.println("Record Count: " + personRoles.size());
   }

   public void listContactInfo() {
      List<ContactInfo> cinfos = c.listContactInfo();
                 
      System.out.println("---------------------------------------------------- ContactInfo Table ------------------------------------------"
      + "---------------------------------------");
      System.out.println(String.format("%-25s%-25s%-25s%-25s%-30s" , "Contact Id", "Type", "Information", "Name", "Person Id" ));
      System.out.println("-------------------------------------------------------------------------------------------------------------"
      + "---------------------------------------" );
      
      for (Iterator iterator = cinfos.iterator(); iterator.hasNext();) {
            ContactInfo ci = (ContactInfo) iterator.next(); 
            Person person = p.getPerson(ci.getPerson().getId());
            System.out.println(String.format("%-25s%-25s%-25s%-25s%-30s" , Integer.toString(ci.getId()), ci.getType()
            , ci.getInformation() , person.getName(), ci.getPerson().getId() ));
            
      }
      System.out.println("-------------------------------------------------------------------------------------------------------------"
      + "---------------------------------------" );
      System.out.println("Record Count: " + cinfos.size()); 
   }

   public void listContactInfo(String id) {
      Integer ID = Integer.parseInt(id);
      List<ContactInfo> cinfos = c.listContactInfo(ID);
                 
      System.out.println("---------------------------------------------------- ContactInfo Table ------------------------------------------"
      + "---------------------------------------");
      System.out.println(String.format("%-25s%-25s%-25s%-25s%-30s" , "Contact Id", "Type", "Information", "Name", "Person Id" ));
      System.out.println("-------------------------------------------------------------------------------------------------------------"
      + "---------------------------------------" );
      
      for (Iterator iterator = cinfos.iterator(); iterator.hasNext();) {
            ContactInfo ci = (ContactInfo) iterator.next(); 
            Person person = p.getPerson(ci.getPerson().getId());
            System.out.println(String.format("%-25s%-25s%-25s%-25s%-30s" , Integer.toString(ci.getId()), ci.getType()
            , ci.getInformation() , person.getName(), ci.getPerson().getId() ));
            
      }
      System.out.println("-------------------------------------------------------------------------------------------------------------"
      + "---------------------------------------" );
      System.out.println("Record Count: " + cinfos.size()); 
   }

   public void getRoleOfPerson(String id) {
      int ID = Integer.parseInt(id);      
      List<PersonRoles> personRoles = pR.getRolesAssociatedWithPerson(ID);

      System.out.println("----------------------------------------------- Person Role ------------------------------------------"
      + "---------------------------------------");
      System.out.println(String.format("%-30s%-30s%-30s%-35s" , "Role Id", "Role", "Name" ,"Person Id" ));
      System.out.println("-------------------------------------------------------------------------------------------------------------"
      + "---------------------------------------" );

      if(personRoles.size() != 0) {
         for (Iterator iterator = personRoles.iterator(); iterator.hasNext();) {
            PersonRoles personRole_ = (PersonRoles) iterator.next(); 
            Role role = r.getRole(personRole_.getPersonRole().getRoleId());
            Person person = p.getPerson(ID);
            System.out.println(String.format("%-30s%-30s%-30s%-35s" , personRole_.getPersonRole().getRoleId()
            , role.getRoleName(), person.getName() ,personRole_.getPersonRole().getPersonId()));
         }
      }
   }

   public Person searchPersonEntity(int ID) {
      //int ID = Integer.parseInt(id);
      Person person = p.getPerson(ID);
      List<ContactInfo> cinfos = c.listContactInfo();
      cinfos.stream();
      cinfos = cinfos.stream().filter(p -> p.getPerson().getId() == ID).collect(Collectors.toList());
      List<PersonRoles> personRoles = pR.getRolesAssociatedWithPerson(ID);

      System.out.println("--------------------------- Person with id " + ID + " details: ----------------------------");
      System.out.println("Person Id: " + person.getId() + ", "); 
      System.out.println("Last Name: " + person.getName().getLastName());
      System.out.println("First Name: " + person.getName().getFirstName());
      System.out.println("Middle Name: " + person.getName().getMiddleName());
      System.out.println("Suffix: " + person.getName().getSuffix());
      System.out.println("Title: " + person.getName().getTitle());
      System.out.println("Street No: " + person.getAddress().getStreetNo());
      System.out.println("Barangay: " + person.getAddress().getBarangay());
      System.out.println("City: " + person.getAddress().getCity());
      System.out.println("Zip Code: " + person.getAddress().getZipCode());
      System.out.println("Birthday: " + person.getBirthday());
      System.out.println("GWA: " + person.getGwa()); 
      System.out.println("Date Hired: " + person.getDateHired());
      String currentlyEmployedHolder = person.getCurrentlyEmployed() ? "Y" : "N";
      System.out.println("Currently Employed: " + currentlyEmployedHolder);   
      
      if(cinfos.size() != 0) {
         System.out.println("--------------------------- Person with id " + ID + " contact details: ----------------------------");
         for (Iterator iterator = cinfos.iterator(); iterator.hasNext();) {
            
            ContactInfo ci = (ContactInfo) iterator.next(); 
            System.out.println("Contact Id: " + ci.getId()); 
            System.out.println("Type: " + ci.getType());
            System.out.println("Information: " + ci.getInformation());
            System.out.println();
            
         }
         System.out.println("===================================================================================================");
      }

      if(personRoles.size() != 0) {
         System.out.println("--------------------------- Person with id " + ID + " roles: ----------------------------");
         for (Iterator iterator = personRoles.iterator(); iterator.hasNext();) {
            PersonRoles personRole_ = (PersonRoles) iterator.next(); 
            Role role = r.getRole(personRole_.getPersonRole().getRoleId());
            System.out.println("Role: " + role.getRoleName()); 
            System.out.println(); 
         }
      }
      return person;
   }

}
