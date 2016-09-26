package com.hibernate.part1.model;
import java.util.*;
public class ContactInfo {
   private int id;
   private String landLine;
   private String mobileNumber;
   private String email;
   private Person person;

   public ContactInfo() { }

   public ContactInfo(String landLine, String mobileNumber, String email, Person person) {
      this.landLine = landLine;
      this.mobileNumber = mobileNumber;
      this.email = email;
      this.person = person;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getId() {
      return this.id;
   }

   public void setLandLine(String landLine) {
      this.landLine = landLine;
   }
 
   public String getLandLine() {
      return this.landLine;
   }

   public void setMobileNumber(String mobileNumber) {
      this.mobileNumber = mobileNumber;
   }

   public String getMobileNumber() {
      return this.mobileNumber;
   }

   public void setEmail(String email) {
      this.email = email;
   }
 
   public String getEmail() {
      return this.email;
   }

   public void setPerson(Person person) {
      this.person = person;
   }

   public Person getPerson() {
      return this.person;
   }

}
