package com.hibernate.part1.model;
import java.util.*;
public class ContactInfo {
   private int id;
   private String type;
   private String information;
   private Person person;

   public ContactInfo() { }

   public ContactInfo(String type, String information, Person person) {
      this.type = type;
      this.information = information;
      this.person = person;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getId() {
      return this.id;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getType() {
      return this.type;
   }

   public void setInformation(String information) {
      this.information = information;
   }

   public String getInformation() {
      return this.information;
   }

   public void setPerson(Person person) {
      this.person = person;
   }

   public Person getPerson() {
      return this.person;
   }

}
