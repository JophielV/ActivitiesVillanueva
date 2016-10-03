package com.hibernate.part2.model;

public class Name {
   private String lastName;
   private String firstName;
   private String middleName;
   private String suffix;
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
