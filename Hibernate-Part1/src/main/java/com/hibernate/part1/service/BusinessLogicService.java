package com.hibernate.part1.service;
import com.hibernate.part1.model.InputModel;
import java.util.InputMismatchException;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import com.hibernate.part1.model.Address;
import com.hibernate.part1.model.ContactInfo;
import com.hibernate.part1.model.Name;
import com.hibernate.part1.model.Person;
import com.hibernate.part1.model.Role;
import com.hibernate.part1.model.PersonRoles;
import com.hibernate.part1.dao.PersonDAO;
import com.hibernate.part1.dao.RolesDAO;
import com.hibernate.part1.dao.ContactInfoDAO;
import com.hibernate.part1.dao.PersonRolesDAO;
import com.hibernate.part1.service.ValidationService;
import java.util.Iterator; 
import java.util.*; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import com.ibatis.common.jdbc.ScriptRunner;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.*;



public class BusinessLogicService {
   private static final String DB_DRIVER = "com.mysql.jdbc.Driver"; 
   private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/HibernatePart1";
   private static final String DB_USER = "root";
   private static final String DB_PASSWORD = "root";

   PersonDAO p = new PersonDAO();
   RolesDAO r = new RolesDAO();
   ContactInfoDAO c = new ContactInfoDAO();
   PersonRolesDAO pR = new PersonRolesDAO();

   public BusinessLogicService() {}

   public void runSQLScripts() throws ClassNotFoundException,SQLException {
        String sqlScriptFilePath = "/usr/local/apache-maven-3.3.9/Hibernate-Part1/src/main/java/com/hibernate/part1/scripts/CreatePersonTable.sql";
        String sqlScriptFilePath2 = "/usr/local/apache-maven-3.3.9/Hibernate-Part1/src/main/java/com/hibernate/part1/scripts/CreateContactInfoTable.sql";
        String sqlScriptFilePath3 = "/usr/local/apache-maven-3.3.9/Hibernate-Part1/src/main/java/com/hibernate/part1/scripts/CreateRolesTable.sql";
        String sqlScriptFilePath4 = "/usr/local/apache-maven-3.3.9/Hibernate-Part1/src/main/java/com/hibernate/part1/scripts/CreatePersonRolesTable.sql";
        try { Class.forName(DB_DRIVER); }
        catch(ClassNotFoundException e) { }
        Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);

        try {
            ScriptRunner runner = new ScriptRunner(connection, false, false);
            Reader br = new BufferedReader(new FileReader(sqlScriptFilePath));
            runner.runScript(br);
            br = new BufferedReader(new FileReader(sqlScriptFilePath2));
            runner.runScript(br);
            br = new BufferedReader(new FileReader(sqlScriptFilePath3));
            runner.runScript(br);
            br = new BufferedReader(new FileReader(sqlScriptFilePath4));
            runner.runScript(br); 
        }
        catch (Exception e) {
                System.out.println("Exception Occoured" + e.getMessage());
        }     
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

   public ContactInfo createContactInfoEntity(String landLine, String mobileNumber, String email) {
      ContactInfo ci = new ContactInfo();
      ci.setLandLine(landLine);
      ci.setMobileNumber(mobileNumber);
      ci.setEmail(email);
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

   
   public void updatePersonEntity(String id, String update, int type) {
      ValidationService validationService = new ValidationService();
      int ID = Integer.parseInt(id);
      switch(type) {
         case 1:
            p.updateLastName(ID,update);
            break;
         case 2:
            p.updateFirstName(ID,update);
            break;
         case 3:
            p.updateMiddleName(ID,update);
            break;
         case 4:
            p.updateSuffix(ID,update);
            break;
         case 5:
            p.updateTitle(ID,update);
            break;
         case 7:
            Date date = new Date();
            date = validationService.extractDate(update);
            p.updateBirthday(ID,date);
            break;
         case 8:
            double GWA = Double.parseDouble(update);
            p.updateGWA(ID,GWA);
            break;
         case 9:
            Date date2 = new Date();
            date2 = validationService.extractDate(update);
            p.updateDateHired(ID,date2);
            break;
         case 10:
            boolean currEmployed = update.equals("Y") ? true : false;
            p.updateCurrentlyEmployed(ID,currEmployed);
      }
   }

   public void updatePersonAddressEntity(String id, String streetNo, String barangay, String city, String zipCode) {    
      int ID = Integer.parseInt(id);
      int strtNo = Integer.parseInt(streetNo);
      int zCode = Integer.parseInt(zipCode);
      Address address = new Address(strtNo, barangay, city, zCode);
      p.updateAddress(ID,address);
   }

   public void updateContactInfoEntity(String id, String update, int type) {
      int ID = Integer.parseInt(id);
      switch(type) {
         case 1:
            c.updateLandLine(ID,update);
            break;
         case 2:
            c.updateMobileNumber(ID,update);
         case 3:
            c.updateEmail(ID,update);
      }
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

   public void listPersons(int choice) {

      List<Person> persons = new ArrayList<Person>();
      switch(choice) {
         case 1:
            persons = p.listPersonsById();
            break;
         case 2:
            persons = p.listPersonsById();
            //Comparator<Person> gwaComparator = (o1, o2) -> new Double(o1.getGwa()).compareTo(new Double(o2.getGwa()));
            Collections.sort(persons, 
                        (o1, o2) -> new Double(o1.getGwa()).compareTo(new Double(o2.getGwa())));
            //Collections.reverse(persons);
            //Collections.sort(persons, gwaComparator.reverseOrder());
            break;
         case 3:
            persons = p.listPersonsByDateHired();
            break;
         case 4:
            persons = p.listPersonsByLastName();
            break;
         case 5:
            System.out.println("- Notice: Taking you back to the menu... -");
            break;
         default:
            System.out.println("-- Notice: Not a valid option --");
            return;
      }

      System.out.println("---------------------------------------------------- Persons Table ------------------------------------------"
      + "---------------------------------------");
      System.out.println(String.format("%-5s%-35s%-40s%-20s%-7s%-20s%-5s" , "Id", "Name" 
      , "Address", "Birthday", "GWA", "Date Hired", "Currently Employed"));
      System.out.println("-------------------------------------------------------------------------------------------------------------"
      + "---------------------------------------" );
      for (Iterator iterator = persons.iterator(); iterator.hasNext();) {
            Person person = (Person) iterator.next(); 
            String employedStatus = person.getCurrentlyEmployed() ? "Y" : "N";
            System.out.println(String.format("%-5s%-35s%-40s%-20s%-7s%-20s%-5s" , Integer.toString(person.getId()), person.getName() 
            , person.getAddress(), person.getBirthday().toString(), Double.toString(person.getGwa()), person.getDateHired().toString() 
            , employedStatus));
      }
      System.out.println("-------------------------------------------------------------------------------------------------------------"
      + "---------------------------------------");
      System.out.println("Record Count: " + persons.size());
    
   }

   /*
   public void listPersons() {
      List persons = p.listPersons();
    
      System.out.println("---------------------------------------------------- Persons Table ------------------------------------------"
      + "---------------------------------------");
      System.out.println(String.format("%-5s%-35s%-40s%-20s%-7s%-20s%-5s" , "Id", "Name" 
      , "Address", "Birthday", "GWA", "Date Hired", "Currently Employed"));
      System.out.println("-------------------------------------------------------------------------------------------------------------"
      + "---------------------------------------" );
      for (Iterator iterator = persons.iterator(); iterator.hasNext();) {
            Person person = (Person) iterator.next(); 
            String employedStatus = person.getCurrentlyEmployed() ? "Y" : "N";
            System.out.println(String.format("%-5s%-35s%-40s%-20s%-7s%-20s%-5s" , Integer.toString(person.getId()), person.getName() 
            , person.getAddress(), person.getBirthday().toString(), Double.toString(person.getGwa()), person.getDateHired().toString() 
            , employedStatus));
      }
      System.out.println("-------------------------------------------------------------------------------------------------------------"
      + "---------------------------------------");
      System.out.println("Record Count: " + persons.size());
   }
   */  

   public void listRoles() {
      List roles = r.listRoles();
    
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
      List personRoles = pR.listPersonRoles();
    
      System.out.println("------------------------------ Person Roles Table ----------------------------");
      System.out.println(String.format("%-20s%-20s" , "Person Id", "Role Id" ));
      System.out.println("------------------------------------------------------------------------");
      for (Iterator iterator = personRoles.iterator(); iterator.hasNext();) {
            PersonRoles pr = (PersonRoles) iterator.next(); 
            System.out.println(String.format("%-20s%-20s" , pr.getPersonId(), pr.getRoleId()));
      }
      System.out.println("------------------------------------------------------------------------");
      System.out.println("Record Count: " + personRoles.size());
   }

   public void listContactInfo() {
      List cinfos = c.listContactInfo();
    
      System.out.println("---------------------------------------------- Contact Info Table ---------------------------------");
      System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s" , "Contact Id", "Land Line", "Mobile Number", "Email", "Person Id" ));
      System.out.println("--------------------------------------------------------------------------------------------");
      for (Iterator iterator = cinfos.iterator(); iterator.hasNext();) {
            ContactInfo ci = (ContactInfo) iterator.next(); 
            System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s" , Integer.toString(ci.getId()), ci.getLandLine()
            , ci.getMobileNumber() , ci.getEmail(), ci.getPerson().getId() ));
      }
      System.out.println("--------------------------------------------------------------------------------------------");
      System.out.println("Record Count: " + cinfos.size());
   }
}