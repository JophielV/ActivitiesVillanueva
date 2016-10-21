package com.model;

import javax.persistence.Column;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

public class Name {

   @Column(name = "last_name")
   private String lastName;
   
   @Column(name = "first_name")
   private String firstName;

   @Column(name = "middle_name")
   private String middleName;

   @Column(name = "suffix")
   private String suffix;

   @Column(name = "title")
   private String title;

   public Name() { }

   public Name(String lastName, String firstName, String middleName, String suffix, String title) {
      this.lastName = lastName;
      this.firstName = firstName;
      this.middleName = middleName;
      this.suffix = suffix;
      this.title = title;
   }

   public void setLastName(String lastName) { 
      this.lastName = lastName;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setMiddleName(String middleName) {
      this.middleName = middleName;
   }

   public String getMiddleName() {
      return this.middleName;
   }

   public void setSuffix(String suffix) {
      this.suffix = suffix;
   }

   public String getSuffix() {
      return this.suffix;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getTitle() {
      return this.title;
   }

   @Override
   public String toString() {
        String suffixHolder = this.getSuffix();
        if(suffixHolder.equals("N/A")) { suffixHolder = ""; }
        return this.getTitle() + " " + this.getFirstName() + " " + this.getMiddleName() + " " + this.getLastName() + " " + suffixHolder;
   }   

}
