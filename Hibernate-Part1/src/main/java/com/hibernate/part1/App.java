package com.hibernate.part1;


import com.hibernate.part1.model.Address;
import com.hibernate.part1.model.ContactInfo;
import com.hibernate.part1.model.Name;
import com.hibernate.part1.model.Person;
import com.hibernate.part1.model.Role;
import com.hibernate.part1.model.InputModel;
import com.hibernate.part1.service.ValidationService;
import com.hibernate.part1.service.BusinessLogicService;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.*;
import com.hibernate.part1.dao.*;
import java.util.Scanner;
import com.hibernate.part1.dao.PersonDAO;
import com.hibernate.part1.dao.ContactInfoDAO;

import org.hibernate.*;
/**
 * Hello world!
 *
 */
public class App 
{
    static int mainChoice = 0;
    static Scanner sc = new Scanner(System.in);
    static PersonDAO p = new PersonDAO();
    static ValidationService validationService = new ValidationService();
    static BusinessLogicService businessLogicService = new BusinessLogicService();
    static ContactInfoDAO ci = new ContactInfoDAO();
    public static void main( String[] args )
    {      
        System.out.println("--------------------- Welcome to Hibernate-Part1 Program!------------------");

        while(mainChoice != 17) {
           displayMenu();
           switch(mainChoice) {
              case 1:   
                 createPersonModule();
                 break;

              case 2:  
                 deletePersonModule();
                 break;

              case 3: 
                 updatePersonModule();
                 break;
                                  
              case 4:
                 System.out.println("------------ Listing All Person ---------------");
                 p.listAll();
                 pauseProgram();
                 break;
              
              case 5:
                 addPersonRoleModule();
                 break;

              case 6:
                 deletePersonRoleModule();
                 break;
   
              case 7:
                 listPersonRolesModule();
                 break; 

              case 8:
                 addContactModule();
                 break;    
               
              case 9:
                  updateContactModule();
                  break;
  
              case 10:
                  deleteContactModule();
                  break;              

              case 11:
                  listContactInfoModule();
                  break;     
 
              case 12:
                 addRoleModule();
                 break;
               
              case 13:
                 updateRoleModule();
                 break;

              case 14:
                 deleteRoleModule();
                 break;

              case 15:
                 listRoleModule();
                 break;  
        
              case 17:
                   System.out.println("Exiting program .... "); System.exit(0); break;

              default: System.out.println("Error: Please enter a valid choice...");
           }
           
        }

    }

    public static void initializeRoles() {
        RolesDAO r = new RolesDAO();
        ContactInfoDAO ci = new ContactInfoDAO();   
	r.save("Project Manager");
        r.save("Software Engineer");
        r.save("Developer"); 
    }

    public static void createPersonModule() {
        String lastName = "", firstName = "", middleName = "", suffix = "", title = "", streetNo = "", barangay = "", city = "";
        String currentlyEmployed = "", zipCode = "", birthday = "", gwa = "", dateHired = "";
        boolean holder = true;
        System.out.println("------------------------- Create Person ------------------------");
        System.out.println("Enter the following information: ");
        sc.nextLine();
        while(holder) {
             System.out.print("Last Name: ");
             lastName = sc.nextLine(); 
             holder = validationService.validateName(lastName);
        }
        holder = true;
        while(holder) {
             System.out.print("First Name: ");
             firstName = sc.nextLine();
             holder = validationService.validateName(firstName);
        }
        holder = true;
        while(holder) {
             System.out.print("Middle Name: ");
             middleName = sc.nextLine();
             holder = validationService.validateName(middleName);
        }
        holder = true;
        while(holder) {
             System.out.print("Suffix: ");
             suffix = sc.nextLine();
             holder = validationService.validateSuffix(suffix);
        }
        holder = true;
        while(holder) {
             System.out.print("Title: ");
             title = sc.nextLine();
             holder = validationService.validateTitle(title);
        }
        holder = true;
        while(holder) { 
              System.out.print("Street No: ");
              streetNo = sc.nextLine();
              holder = validationService.validateStreetNo(streetNo);
        }
        holder = true;
        while(holder) {
              System.out.print("Barangay: ");
              barangay = sc.nextLine();
              holder = validationService.validateBarangay(barangay);
        }
        holder = true;
        while(holder) {
              System.out.print("City: ");
              city = sc.nextLine();
              holder = validationService.validateCity(city);
        }
        holder = true;
        while(holder) {
              System.out.print("Zip Code: ");
              zipCode = sc.nextLine();
              holder = validationService.validateZipCode(zipCode);
        }
        holder = true;
        while(holder) {
              System.out.print("Birthday(DD/MM/YYYY): ");
              birthday = sc.nextLine();
              holder = validationService.validateDate(birthday);
        } 
        Date date = new Date();
        date = validationService.extractDate(birthday);
        holder = true;
        while(holder) {
        System.out.print("GWA: ");
        gwa = sc.nextLine();
        holder = validationService.validateGWA(gwa);
        }
        holder = true;             
        while(holder) {
              System.out.print("Date Hired(DD/MM/YYYY): ");
              dateHired = sc.nextLine();
              holder = validationService.validateDate(dateHired);
        } 
        Date date2 = new Date();
        date2 = validationService.extractDate(dateHired);
        while(holder) {               
              System.out.print("Currently Employed?(Y/N): ");
              currentlyEmployed = sc.nextLine();
              holder = validationService.validateCurrentlyEmployed(currentlyEmployed);
        }
        Name name = new Name(); 
        Address address = new Address();
        Person person = new Person();
        name = businessLogicService.createNameEntity(lastName,firstName,middleName,suffix,title);                 
        address = businessLogicService.createAddressEntity(streetNo,barangay,city,zipCode);
        person = businessLogicService.createPersonEntity(name,address,date,gwa,date2,currentlyEmployed);
        businessLogicService.savePersonEntity(person);
        System.out.println("----------------------Notice: Person Saved in the db!--------------------");
        pauseProgram();       
    }

    public static void deletePersonModule() {
       sc.nextLine();
       String id = "";
       boolean holder = true;
       boolean holder2 = true;
       System.out.println("------------------------- Delete Person ------------------------");
       while(holder || holder2) { 
          System.out.print("Enter the id of the person to be deleted: ");
          id = sc.nextLine();
          holder = validationService.validateId(id);
          if(holder)  { continue; }
             holder2 = businessLogicService.deletePersonEntity(id);
          }             
       pauseProgram();
    }

    public static void updatePersonModule() {
       sc.nextLine();
       String id = "";
       boolean holder = true;
       boolean holder2 = true;
       int subChoice = 0;
       System.out.println("------------------------- Update Person ------------------------");
       while(holder || !holder2) { 
           System.out.print("Enter the id of the person to be updated: ");
           id = sc.nextLine();
           holder = validationService.validateId(id);
           if(holder)  { continue; }
               holder2 = businessLogicService.checkIfEntityExist(id);
       }
       pauseProgram();
       while(subChoice != 11) {
           displayUpdateChoice();
           System.out.print("Enter your choice: ");
           subChoice = sc.nextInt();
           switchCaseUpdateField(subChoice,id);
       }
    }
 
    public static void switchCaseUpdateField(int subChoice, String id) {
       boolean holder = true;
       String lastName = "", firstName = "", middleName = "", suffix = "", title = "", streetNo = "", barangay = "", city = "";
       String currentlyEmployed = "", zipCode = "", birthday = "", gwa = "", dateHired = ""; 
       switch(subChoice) {
          case 1:
             sc.nextLine();
             while(holder) {
                System.out.print("Enter the new Last Name: ");
                lastName = sc.nextLine(); 
                holder = validationService.validateName(lastName);
             }
             businessLogicService.updatePersonEntity(id,lastName,1);
             System.out.println("-------- Notice: Update Success ------");
             pauseProgram(); 
             break;
          case 2:
             sc.nextLine();
             holder = true;
             while(holder) {
                System.out.print("Enter the new First Name: ");
                firstName = sc.nextLine(); 
                holder = validationService.validateName(firstName);
             }
             businessLogicService.updatePersonEntity(id,firstName,2);
             System.out.println("-------- Notice: Update Success ------");
             pauseProgram(); 
             break;
          case 3:
             sc.nextLine();
             holder = true;
             while(holder) {
                System.out.print("Enter the new Middle Name: ");
                middleName = sc.nextLine(); 
                holder = validationService.validateName(middleName);
             }
             businessLogicService.updatePersonEntity(id,middleName,3);
             System.out.println("-------- Notice: Update Success ------");
             pauseProgram(); 
             break;
          case 4:
             sc.nextLine();
             holder = true;
             while(holder) {
                System.out.print("Enter the new Suffix: ");
                suffix = sc.nextLine(); 
                holder = validationService.validateSuffix(suffix);
             }
             businessLogicService.updatePersonEntity(id,suffix,4);
             System.out.println("-------- Notice: Update Success ------");
             pauseProgram(); 
             break;
          case 5:
             sc.nextLine();
             holder = true;
             while(holder) {
                System.out.print("Enter the new Title: ");
                title = sc.nextLine(); 
                holder = validationService.validateTitle(title);
             }
             businessLogicService.updatePersonEntity(id,title,5);
             System.out.println("-------- Notice: Update Success ------");
             pauseProgram(); 
             break;
          case 6:
             sc.nextLine();
             System.out.println("Notice: Enter the following information for changing the address.");
             holder = true;
             while(holder) {
                System.out.print("Enter the new Street No: ");
                streetNo = sc.nextLine(); 
                holder = validationService.validateStreetNo(streetNo);
             }
             holder = true;
             while(holder) {
                System.out.print("Enter the new Barangay: ");
                barangay = sc.nextLine(); 
                holder = validationService.validateBarangay(barangay);
             }
             holder = true;
             while(holder) {
                System.out.print("Enter the new City: ");
                city = sc.nextLine(); 
                holder = validationService.validateCity(city);
             }
             holder = true;
             while(holder) {
                System.out.print("Enter the new Zip Code: ");
                zipCode = sc.nextLine(); 
                holder = validationService.validateZipCode(zipCode);
             }
             businessLogicService.updatePersonAddressEntity(id,streetNo,barangay,city,zipCode);
             System.out.println("-------- Notice: Update Success ------"); 
             pauseProgram(); 
             break;
          case 7:
             sc.nextLine();
             holder = true;
             while(holder) {
                System.out.print("Enter the new Birthday(DD/MM/YYYY): ");
                birthday = sc.nextLine(); 
                holder = validationService.validateDate(birthday);
             }
             businessLogicService.updatePersonEntity(id,birthday,7);
             System.out.println("-------- Notice: Update Success ------");
             pauseProgram(); 
             break;
          case 8:
             sc.nextLine();
             holder = true;
             while(holder) {
                System.out.print("Enter the new GWA: ");
                gwa = sc.nextLine(); 
                holder = validationService.validateGWA(gwa);
             }
             businessLogicService.updatePersonEntity(id,gwa,8);
             System.out.println("-------- Notice: Update Success ------");
             pauseProgram(); 
             break;
          case 9:
             sc.nextLine();
             holder = true;
             while(holder) {
                System.out.print("Enter the new Date hired(DD/MM/YYYY): ");
                dateHired = sc.nextLine(); 
                holder = validationService.validateDate(dateHired);
             }
             businessLogicService.updatePersonEntity(id,dateHired,9);
             System.out.println("-------- Notice: Update Success ------");
             pauseProgram(); 
             break;
          case 10: 
             sc.nextLine();
             holder = true;
             while(holder) {
                System.out.print("Enter the new Currently Employed status(Y/N): ");
                currentlyEmployed = sc.nextLine(); 
                holder = validationService.validateCurrentlyEmployed(currentlyEmployed);
             }
             businessLogicService.updatePersonEntity(id,currentlyEmployed,10);
             System.out.println("-------- Notice: Update Success ------");
             pauseProgram(); 
             break;
          default:
             System.out.println("Taking you back to the Main Menu....");
             pauseProgram();   
       }
    }

    public static void addPersonRoleModule() {
       sc.nextLine();
       String id = "", id2 = "";
       boolean holder = true;
       boolean holder2 = true;
       System.out.println("------------------------- Add Person Role ------------------------");
       while(holder || !holder2) { 
          System.out.print("Enter the id of the person that you want to add a role: ");
          id = sc.nextLine();
          holder = validationService.validateId(id);
          if(holder)  { continue; }
          holder2 = businessLogicService.checkIfEntityExist(id);
       }
       holder = true; holder2 = true;
       while(holder || !holder2) { 
          System.out.print("Enter the id of the role to add for the person: ");
          id2 = sc.nextLine();
          holder = validationService.validateId(id2);
          if(holder)  { continue; }
          holder2 = businessLogicService.checkIfEntityRoleExist(id2);
       }  
       Role role = new Role();
       role = businessLogicService.getRoleEntity(id2);      
       businessLogicService.addRoleToPerson(id,role);  
       pauseProgram();
    }

    public static void deletePersonRoleModule() {
       sc.nextLine();
       String id = "", id2 = "";
       boolean holder = true;
       boolean holder2 = true;
       System.out.println("------------------------- Delete Person Role ------------------------");
       while(holder || !holder2) { 
          System.out.print("Enter the id of the person that you want to delete a role: ");
          id = sc.nextLine();
          holder = validationService.validateId(id);
          if(holder)  { continue; }
          holder2 = businessLogicService.checkIfEntityExist(id);
       }
       holder = true; holder2 = true;
       while(holder || !holder2) { 
          System.out.print("Enter the id of the role to delete for the person: ");
          id2 = sc.nextLine();
          holder = validationService.validateId(id2);
          if(holder)  { continue; }
          holder2 = businessLogicService.checkIfEntityRoleExist(id2);
       }  
       Role role = new Role();
       role = businessLogicService.getRoleEntity(id2);      
       businessLogicService.deleteRoleToPerson(id,id2);
       System.out.println("-------- Notice: Delete role to Person Success ------");    
       pauseProgram();
    }

    public static void addContactModule() {
       String landLine = "", mobileNumber = "", email = "";
       String id = "";
       boolean holder = true;
       boolean holder2 = true;
       sc.nextLine();

       System.out.println("-------------------- Add Contact ----------------------");
       while(holder || !holder2) { 
          System.out.print("Enter the id of which person to add contact: ");
          id = sc.nextLine();
          holder = validationService.validateId(id);
          if(holder)  { continue; }
          holder2 = businessLogicService.checkIfEntityExist(id);
       }
       holder = true;

       System.out.println("- Notice: The following format are accepted for Land Line field: ");
       System.out.println("xxx-xxxx");
       System.out.println("0-xx-xxx-xxxx");
       System.out.println("0-2-xxx-xxxx");
       System.out.println("011-63-2-xxx-xxxx");
       System.out.println("011-63-xx-xxx-xxxx");
       System.out.println("- Notice: The following format are accepted for Mobile number field: ");
       System.out.println("0xxxxxxxxxx ");
       System.out.println("+63xxxxxxxxxx ");
       System.out.println("Enter the following information:");
       while(holder) {
             System.out.print("Land Line No: ");
             landLine = sc.nextLine(); 
             holder = validationService.validateLandLine(landLine);
       }
       holder = true;
       while(holder) {
             System.out.print("Mobile No: ");
             mobileNumber = sc.nextLine(); 
             holder = validationService.validateMobileNumber(mobileNumber);
       }
       holder = true;
       while(holder) {
             System.out.print("Email: ");
             email = sc.nextLine(); 
             holder = validationService.validateEmail(email);
       }
       ContactInfo ci = new ContactInfo();
       ci = businessLogicService.createContactInfoEntity(landLine,mobileNumber,email);
       businessLogicService.addContactToPerson(id,ci);
       System.out.println("-------- Notice: Add Contact Success ------");
       pauseProgram();
    }

    public static void updateContactModule() {
       String id = "";
       boolean holder = true;
       boolean holder2 = true;
       int subChoice = 0;
       sc.nextLine();
       System.out.println("------------------------- Update Contact Info ------------------------");
       while(holder || !holder2) { 
           System.out.print("Enter the id of the contact to be updated: ");
           id = sc.nextLine();
           holder = validationService.validateId(id);
           if(holder)  { continue; }
           holder2 = businessLogicService.checkIfEntityContactInfoExist(id);
       }
       pauseProgram();
       while(subChoice != 4) {
          displayUpdateCiChoice();
          System.out.print("Enter your choice: ");
          subChoice = sc.nextInt();
          switchCaseUpdateCiField(subChoice,id);
       }
    }

    public static void switchCaseUpdateCiField(int subChoice, String id) {
       boolean holder = true;
       String landLine = "", mobileNumber = "", email = "";
       switch(subChoice) {
          case 1:
             sc.nextLine();
             while(holder) {
                System.out.print("Enter new LandLine No: ");
                landLine = sc.nextLine(); 
                holder = validationService.validateLandLine(landLine);
             }
             businessLogicService.updateContactInfoEntity(id,landLine,1);
             System.out.println("-------- Notice: Update Success ------");
             pauseProgram(); 
             break;
         case 2:
             sc.nextLine();
             while(holder) {
                System.out.print("Enter new Mobile No: ");
                mobileNumber = sc.nextLine(); 
                holder = validationService.validateMobileNumber(mobileNumber);
             }
             businessLogicService.updateContactInfoEntity(id,mobileNumber,2);
             System.out.println("-------- Notice: Update Success ------");
             pauseProgram(); 
             break;
         case 3:
             sc.nextLine();
             while(holder) {
                System.out.print("Enter new Email: ");
                email = sc.nextLine(); 
                holder = validationService.validateEmail(email);
             }
             businessLogicService.updateContactInfoEntity(id,email,3);
             System.out.println("-------- Notice: Update Success ------");
             pauseProgram(); 
             break;
       }
    }

    public static void deleteContactModule() {
       sc.nextLine();
       String id = "";
       boolean holder = true;
       boolean holder2 = true;
       System.out.println("------------------------- Delete Contact ------------------------");
       while(holder || holder2) { 
          System.out.print("Enter the id of Contact to be deleted: ");
          id = sc.nextLine();
          holder = validationService.validateId(id);
          if(holder)  { continue; }
          holder2 = businessLogicService.deleteContactInfoEntity(id);
       }             
       pauseProgram();
    }

    public static void addRoleModule() {
       boolean holder = true;
       String role = "";
       sc.nextLine();
       System.out.println("-------------------- Add Role ----------------------");
       while(holder) {
          System.out.print("Enter the role to add: ");
          role = sc.nextLine();
          holder = validationService.checkNullity(role);
       }
       businessLogicService.saveRoleEntity(role);
       System.out.println("-------- Notice: Add Role Success ------");
       pauseProgram();
    }

    public static void updateRoleModule() {
       String id = "", role = "";
       boolean holder = true;
       boolean holder2 = true;
                 
       System.out.println("------------------------- Update Role ------------------------");
       sc.nextLine();
       while(holder || !holder2) { 
          System.out.print("Enter the id of the role to be updated: ");
          id = sc.nextLine();
          holder = validationService.validateId(id);
          if(holder)  { continue; }
          holder2 = businessLogicService.checkIfEntityRoleExist(id);
       }
       holder = true;
       while(holder) {
          System.out.print("Enter the new role: ");
          role = sc.nextLine();
          holder = validationService.checkNullity(role);
       }
       businessLogicService.updateRoleEntity(id,role);
       System.out.println("-------- Notice: Update Role Success ------");
       pauseProgram();
    }

    public static void deleteRoleModule() {
       String id = "", role = "";
       boolean holder = true;
       boolean holder2 = true;
                 
       System.out.println("------------------------- Delete Role ------------------------");
       sc.nextLine();
       while(holder || !holder2) { 
          System.out.print("Enter the id of the role to be deleted: ");
          id = sc.nextLine();
          holder = validationService.validateId(id);
          if(holder)  { continue; }
          holder2 = businessLogicService.checkIfEntityRoleExist(id);
       }   
       businessLogicService.deleteRoleEntity(id);
       System.out.println("-------- Notice: Delete Role Success ------");
       pauseProgram();
    }

    public static void listPersonRolesModule() {
       System.out.println("");
       businessLogicService.listPersonRoles();
       pauseProgram();
    }

    public static void listContactInfoModule() {
       System.out.println("");
       businessLogicService.listContactInfo();
       pauseProgram();
    }

    public static void listRoleModule() {
       System.out.println("");
       businessLogicService.listRoles();
       pauseProgram();
    }

    public static void displayMenu() {
        System.out.println("----------------------------- Main Menu ------------------------------");
	System.out.println("(1) Create Person");
        System.out.println("(2) Delete Person");
        System.out.println("(3) Update Person");
        System.out.println("(4) List Person");
        System.out.println("(5) Add Person Role");
        System.out.println("(6) Delete Person Role");
        System.out.println("(7) List Person Roles");
        System.out.println("(8) Add Contact");
        System.out.println("(9) Update Contact");
        System.out.println("(10) Delete Contact");
        System.out.println("(11) List Person & Contacts");
        System.out.println("(12) Add Role"); 
        System.out.println("(13) Update Role");
        System.out.println("(14) Delete Role");
        System.out.println("(15) List Roles"); 
        System.out.println("(16) Search Person");
        System.out.println("(17) Exit");
        System.out.println("----------------------------------------------------------------------");
        System.out.print("Enter your choice: ");
        mainChoice = sc.nextInt();
       
    }

    public static void displayUpdateChoice() {
       System.out.println("--------------------- Update Field----------------------");
       System.out.println("(1) Last Name");
       System.out.println("(2) First Name");
       System.out.println("(3) Middle Name");
       System.out.println("(4) Suffix");
       System.out.println("(5) Title");
       System.out.println("(6) Address");
       System.out.println("(7) Birthday"); 
       System.out.println("(8) GWA");
       System.out.println("(9) Date Hired");
       System.out.println("(10) Currently Employed");
       System.out.println("(11) Back to Main Menu");
    } 

    public static void displayUpdateCiChoice() {
       System.out.println("--------------------- Update Field----------------------");
       System.out.println("(1) Landline No.");
       System.out.println("(2) Mobile No.");
       System.out.println("(3) Email");
       System.out.println("(4) Back to Main Menu");
    }

    public static void pauseProgram() {
	System.out.println();
    	System.out.println("Press a key to continue...");
	Scanner pause = new Scanner(System.in);
	pause.nextLine();
    }
}
