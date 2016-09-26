package com.hibernate.part1.model;

public class InputModel {
   private int mainChoice;
   private String lastName;
   private String firstName;
   private String middleName;
   private String suffix;
   private String title;
   private String streetNo;
   private String barangay;

   public InputModel() {}

   public void setMainChoice(int c) {
      this.mainChoice = c;
   }

   public int getMainChoice() {
      return this.mainChoice;
   }
}
