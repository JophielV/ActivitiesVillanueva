package com.hibernate.part1.service;

import java.util.InputMismatchException;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class ValidationService {
  
   public ValidationService() {}


   public boolean validateName(String n) {
      if(n.matches("^[\\p{L} .'-]+$")) { 
          return false;
      }
      if(n.trim().isEmpty() || n.isEmpty() || n == null) {
          System.out.println("-Notice: Please enter a valid value. -");
          return true;
      }
      return false;
   }

   public boolean validateSuffix(String n) {
      if(n.matches("[a-zA-Z]+\\.?[a-zA-Z]*") || n.equals("N/A")) { return false; }
      if(n.matches("^.*[^a-zA-Z].*$")) { 
          System.out.println("- Notice: Suffix contains non alphabetic character(s). Enter a valid suffix. -");
          return true; 
      }
      if(n.trim().isEmpty() || n.isEmpty() || n == null) {
          System.out.println("- Notice: Please enter a valid value. Enter N/A if not applicable -");
          return true;
      }
      return false;
   }
   
   public boolean validateTitle(String n) {
      if(n.matches("[a-zA-Z]+\\.?[a-zA-Z]*") || n.equals("N/A")) { return false; }
      if(n.matches("^.*[^a-zA-Z].*$")) { 
          System.out.println("- Notice: Title contains non alphabetic character(s). Enter a valid title. -");
          return true; 
      }
      if(n.trim().isEmpty() || n.isEmpty() || n == null) {
          System.out.println("- Notice: Please enter a valid value. Enter N/A if not applicable -");
          return true;
      }
      return false;
   }

   public boolean validateStreetNo(String n) {
      if(n.matches("[0-9]+")) { 
          return false; 
      }
      if(n.trim().isEmpty() || n.isEmpty() || n == null) {
          System.out.println("- Notice: Please enter a valid value. Enter N/A if not applicable -");
          return true;
      }
      else { 
         System.out.println("- Notice: Input contains alphabetic or special characters.Please enter a valid street no. -");
         return true;
      }
   }

   public boolean validateBarangay(String n) {
      if(n.matches("^[\\p{L} .'-]+$")) { 
          return false; 
      }
      if(n.trim().isEmpty() || n.isEmpty() || n == null) {
          System.out.println("- Notice: Please enter a valid value. Enter N/A if not applicable -");
          return true;
      }
      return false;
   }

    public boolean validateCity(String n) {
      if(n.matches("^[\\p{L} .'-]+$")) { 
          return false; 
      }
      if(n.trim().isEmpty() || n.isEmpty() || n == null) {
          System.out.println("- Notice: Please enter a valid value. Enter N/A if not applicable -");
          return true;
      }
      return false;
    }

    public boolean validateZipCode(String n) {
      if(n.matches("[0-9]{4}")) { 
          return false; 
      }
      if(n.matches("[0-9]{0,3}") || n.matches("[0-9]{5,}")) { 
           System.out.println("- Notice: Please make sure that the zip code you entered has exactly 4 digits. -");
          return true; 
      }
      if(n.trim().isEmpty() || n.isEmpty() || n == null) {
          System.out.println("- Notice: Please enter a valid value. Enter N/A if not applicable -");
          return true;
      }
      else { 
         System.out.println("- Notice: Input contains alphabetic or special characters.Please enter a valid Zip Code. -");
         return true;
      }
   }

   public boolean validateGWA(String n) {
      if(n.matches("[0-9]{1}\\.[0-9]+")) { 
          return false; 
      }
      if(n.trim().isEmpty() || n.isEmpty() || n == null) {
          System.out.println("- Notice: Please enter a valid value. Enter N/A if not applicable-");
          return true;
      }
      else { 
         System.out.println("- Notice: Input contains alphabetic characters or does" +
        " not follow the format.Please enter a valid GWA(Ranges from 1.0 to 5.0). -");
         return true;
      }
   }
 
   public boolean validateDate(String n) {
      if(n.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) { return false; }
      else { 
         System.out.println("- Notice: Please make sure you follow the correct format (DD/MM/YYYY) -");
         return true;
      }
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

   public boolean validateCurrentlyEmployed(String n) {
      if(n.equals("Y") || n.equals("N")) { 
         return false;
      }
      else {
         System.out.println("--------- Notice: Please make sure you entered Y or N only -------");
         return true;
      }
   }

   public boolean validateId(String n) {
      if(n.matches("[0-9]+")) { return false; }
      else {
         System.out.println("------ Notice: Please enter a valid id number. ------");
         return true;
      }
  }
  
  public boolean checkNullity(String n) {
      if(n.trim().isEmpty() || n.isEmpty() || n == null) {
          System.out.println("------Notice: Please enter a valid value.------");
          return true;
      }
      else { return false; } 
  }

  public boolean validateLandLine(String n) {
      if(n.matches("[0-9]{3}-[0-9]{4}")) { return false; }
      if(n.matches("0-[0-9]{2}-[0-9]{3}-[0-9]{4}")) { return false; }
      if(n.matches("0-2-[0-9]{3}-[0-9]{3}-[0-9]{4}")) { return false; }
      if(n.matches("011-63-2-[0-9]{3}-[0-9]{4}")) { return false; }
      if(n.matches("011-63-[0-9]{2}-[0-9]{3}-[0-9]{4}")) { return false; }
      else {
         System.out.println("- Notice: Invalid Landline number format. Please follow one of the following format:");
         System.out.println("xxx-xxxx");
         System.out.println("0-xx-xxx-xxxx");
         System.out.println("0-2-xxx-xxxx");
         System.out.println("011-63-2-xxx-xxxx");
         System.out.println("011-63-xx-xxx-xxxx");
         return true;
      }
  }

  public boolean validateMobileNumber(String n) {
      if(n.matches("0[0-9]{10}")) { return false; }
      if(n.matches("\\+63[0-9]{10}")) { return false; }
      else {
         System.out.println("- Notice: Invalid Mobile number format. Please follow one of the following format: ");
         System.out.println("0xxxxxxxxxx ");
         System.out.println("+63xxxxxxxxxx ");
         return true;
      }
  }

  public boolean validateEmail(String n) {
     if(n.matches("[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) { return false; }
     else {
        System.out.println("- Notice: The email you entered is not a valid email -");
        return true;
     }
  }
 

  
}
