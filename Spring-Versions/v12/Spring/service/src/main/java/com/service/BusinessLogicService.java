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
   private PersonDaoInterface personDao;

   @Autowired
   private ContactInfoDaoInterface contactDao;

   @Autowired
   private RolesDaoInterface roleDao;

   @Autowired
   private PersonRolesDaoInterface personRoleDao;

   @Autowired
   private UserDaoInterface uD;

   private List<Person> _persons = new ArrayList<Person>();

   public BusinessLogicService() {}
 
   public BusinessLogicService(PersonDaoInterface personDao
                               ,ContactInfoDaoInterface contactDao 
                               ,RolesDaoInterface roleDao
                               ,PersonRolesDaoInterface personRoleDao
                               ,UserDaoInterface uD) {
      this.personDao = personDao;
      this.contactDao = contactDao;
      this.roleDao = roleDao;
      this.personRoleDao = personRoleDao;
      this.uD = uD;
   }

   public List getPersonList() {
      return this._persons;
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
      personDao.save(person);
   }

   public boolean checkIfEntityExist(int id) {
      if(personDao.getPerson(id) != null) { return true; }
      else if(personDao.getPerson(id) == null) { 
         return false; 
      }
      return false;
   }

   public boolean checkIfEntityRoleExist(int id) {
      if(roleDao.getRole(id) != null) { return true; }
      else if(roleDao.getRole(id) == null) { 
         return false; 
      }
      return false;
   }

   public boolean checkIfEntityContactInfoExist(int id) {
      if(contactDao.getContactInfo(id) != null) { return true; }
      else if(contactDao.getContactInfo(id) == null) { 
         return false; 
      }
      return false;
   }

   public boolean checkIfEntityContactInfoExist(String id, String id2) {
      int ID = Integer.parseInt(id);
      int ID2 = Integer.parseInt(id2);
      if(contactDao.getContactInfo(ID,ID2) != null) { return true; }
      else if(contactDao.getContactInfo(ID,ID2) == null) { 
         System.out.println("Error: Contact Info with id " + id + " has not been found for this person");
         return false; 
      }
      return false;
   }

   public Person getPersonEntity(int id) {
      Person person = personDao.getPerson(id);

      return person;
   }

   public List findPerson(String firstName, String lastName) {
      List<Person> persons = personDao.find(firstName,lastName);

      return persons;
   }

   public void deletePersonEntity(int id) {
      personDao.delete(id);         
   }

   
   public void updatePersonEntity(int id, Person person) {
      personDao.update(id,person);
   }

   public ContactInfo getContactInfoEntity(int id) {
      ContactInfo ci = contactDao.getContactInfo(id);

      return ci;
   }

   public ContactInfo getContactInfoEntity(String id, String id2) {
      int ID = Integer.parseInt(id);
      int ID2 = Integer.parseInt(id2);
      ContactInfo ci = contactDao.getContactInfo(ID,ID2);

      return ci;
   }

   public void updateContactInfoEntity(int id, String update) {
      contactDao.update(id,update);
   }
 
   public void deleteContactInfoEntity(int id) {
      contactDao.delete(id);
   }

   public void addContactToPerson(int id, ContactInfo ci) {
      personDao.addContact(id,ci);    
   }

   public void addRoleToPerson(int id, Role role) {
      personDao.addRole(id,role);
   }

   public void deleteRoleToPerson(int id, int id2) {
      personDao.deleteRole(id,id2);
   }

   public Role getRoleEntity(int id) {
      Role role = roleDao.getRole(id);

      return role;
   }

   public void saveRoleEntity(String role) {
      roleDao.save(role);
   }

   public void updateRoleEntity(int id, String update) {
      roleDao.update(id,update);
   }

   public void deleteRoleEntity(int id) {
      roleDao.delete(id);
   }


   public void listPersons(int choice) {
      List<Person> persons = personDao.listPersons(choice);
      this._persons = persons;
   }  

   public List listRoles() {
      List<Role> roles = roleDao.listRoles();

      return roles;   
   }

   public List listPersonRoles() {
      List<PersonRoles> personRoles = personRoleDao.listPersonRoles();

      return personRoles;
   }

   public List listContactInfo() {
      List<ContactInfo> cinfos = contactDao.listContactInfo();

      return cinfos;
   }

   public List listContactInfo(int id) {
      List<ContactInfo> cinfos = contactDao.listContactInfo(id);
                 
      return cinfos;
   }

   public List getRoleOfPerson(int id) {    
      List<PersonRoles> personRoles = personRoleDao.getRolesAssociatedWithPerson(id);
      return personRoles;
   }

   public Person searchPersonEntity(int ID) {
      Person person = personDao.getPerson(ID);
      List<ContactInfo> cinfos = contactDao.listContactInfo();
      cinfos.stream();
      cinfos = cinfos.stream().filter(personDao -> personDao.getPerson().getId() == ID).collect(Collectors.toList());
      List<PersonRoles> personRoles = personRoleDao.getRolesAssociatedWithPerson(ID);

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
