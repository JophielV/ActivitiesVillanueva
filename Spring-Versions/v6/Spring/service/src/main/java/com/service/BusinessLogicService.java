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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.dao.interfaces.PersonDaoInterface;
import com.dao.interfaces.ContactInfoDaoInterface;
import com.dao.interfaces.PersonRolesDaoInterface;
import com.dao.interfaces.RolesDaoInterface;
import com.dao.interfaces.UserDaoInterface;


public class BusinessLogicService {

   @Autowired  
   private PersonDaoInterface p;

   @Autowired
   private ContactInfoDaoInterface c;

   @Autowired
   private RolesDaoInterface r;

   @Autowired
   private PersonRolesDaoInterface pR;

   @Autowired
   private UserDaoInterface uD;

   public BusinessLogicService() {}
 
   public BusinessLogicService(PersonDaoInterface p, ContactInfoDaoInterface c, RolesDaoInterface r, PersonRolesDaoInterface pR
                               ,UserDaoInterface uD) {
      this.p = p;
      this.c = c;
      this.r = r;
      this.pR = pR;
      this.uD = uD;
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

   public boolean checkIfEntityExist(int id) {
      if(p.getPerson(id) != null) { return true; }
      else if(p.getPerson(id) == null) { 
         return false; 
      }
      return false;
   }

   public boolean checkIfEntityRoleExist(int id) {
      if(r.getRole(id) != null) { return true; }
      else if(r.getRole(id) == null) { 
         return false; 
      }
      return false;
   }

   public boolean checkIfEntityContactInfoExist(int id) {
      if(c.getContactInfo(id) != null) { return true; }
      else if(c.getContactInfo(id) == null) { 
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

   public Person getPersonEntity(int id) {
      Person person = new Person();
      person = p.getPerson(id);
      return person;
   }

   public List findPerson(String firstName, String lastName) {
      List<Person> persons = new ArrayList<Person>();
      persons = p.find(firstName,lastName);
      return persons;
   }

   public void deletePersonEntity(int id) {
      p.delete(id);         
   }

   
   public void updatePersonEntity(int id, Person person) {
      p.update(id,person);
   }

   public ContactInfo getContactInfoEntity(int id) {
      ContactInfo ci = new ContactInfo();
      ci = c.getContactInfo(id);
      return ci;
   }

   public ContactInfo getContactInfoEntity(String id, String id2) {
      int ID = Integer.parseInt(id);
      int ID2 = Integer.parseInt(id2);
      ContactInfo ci = new ContactInfo();
      ci = c.getContactInfo(ID,ID2);
      return ci;
   }

   public void updateContactInfoEntity(int id, String update) {
      c.update(id,update);
   }
 
   public void deleteContactInfoEntity(int id) {
      c.delete(id);
   }

   public void addContactToPerson(int id, ContactInfo ci) {
      p.addContact(id,ci);    
   }

   public void addRoleToPerson(int id, Role role) {
      p.addRole(id,role);
   }

   public void deleteRoleToPerson(int id, int id2) {
      p.deleteRole(id,id2);
   }

   public Role getRoleEntity(int id) {
      Role role = new Role();
      role = r.getRole(id);
      return role;
   }

   public void saveRoleEntity(String role) {
      r.save(role);
   }

   public void updateRoleEntity(int id, String update) {
      r.update(id,update);
   }

   public void deleteRoleEntity(int id) {
      r.delete(id);
   }


   public List listPersons(int choice) {
      List<Person> persons = new ArrayList<Person>();
      persons = p.listPersons(choice);

      return persons;
   }  

   public List listRoles() {
      List<Role> roles = r.listRoles();
      return roles;   
   }

   public List listPersonRoles() {
      List<PersonRoles> personRoles = pR.listPersonRoles();
      return personRoles;
   }

   public List listContactInfo() {
      List<ContactInfo> cinfos = c.listContactInfo();

      return cinfos;
   }

   public List listContactInfo(int id) {
      List<ContactInfo> cinfos = c.listContactInfo(id);
                 
      return cinfos;
   }

   public List getRoleOfPerson(int id) {    
      List<PersonRoles> personRoles = pR.getRolesAssociatedWithPerson(id);
      return personRoles;
   }

   public Person searchPersonEntity(int ID) {
      Person person = p.getPerson(ID);
      List<ContactInfo> cinfos = c.listContactInfo();
      cinfos.stream();
      cinfos = cinfos.stream().filter(p -> p.getPerson().getId() == ID).collect(Collectors.toList());
      List<PersonRoles> personRoles = pR.getRolesAssociatedWithPerson(ID);

      return person;
   }

   public Date extractDate(String n) { 
      Date date = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
      String numbers[] = n.split("/");
      String dateInString = numbers[0] + "-" + numbers[1] + "-" + numbers[2] + " 00:00:00";
      try { date = sdf.parse(dateInString); }
      catch(ParseException pe) {
            System.out.println("ERROR: Cannot parse \"" + dateInString + "\"");
      }
      return date;
   }

}
