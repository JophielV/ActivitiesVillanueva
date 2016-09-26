package com.hibernate.part1.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Person {
   private int id;
   private Name name;
   private Address address;
   private Date birthday;
   private double gwa;
   private Date dateHired;
   private boolean currentlyEmployed;
   private List<ContactInfo> contactInfo = new ArrayList<ContactInfo>();
   private List<Role> roles = new ArrayList<Role>();

   public Person() {  }

   public Person(Name name, Address address, Date birthday, double gwa, Date dateHired, boolean currentlyEmployed) {
      this.name = name;
      this.address = address;
      this.birthday = birthday;
      this.gwa = gwa;
      this.dateHired = dateHired;
      this.currentlyEmployed = currentlyEmployed;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getId() {
      return this.id;
   }

   public void setName(Name name) {
      this.name = name;
   }

   public Name getName() {
      return this.name;
   }

   public void setAddress(Address address) {
      this.address = address;
   }

   public Address getAddress() {
      return this.address;
   }

   public void setBirthday(Date birthday) {
      this.birthday = birthday;
   }

   public Date getBirthday() {
      return this.birthday;
   }

   public void setGwa(double gwa) {
      this.gwa = gwa;
   }

   public double getGwa() {
      return this.gwa;
   }

   public void setDateHired(Date dateHired) {
      this.dateHired = dateHired;
   }

   public Date getDateHired() {
      return this.dateHired;
   } 

   public void setCurrentlyEmployed(boolean currentlyEmployed) {
      this.currentlyEmployed = currentlyEmployed;
   }
     
   public boolean getCurrentlyEmployed() {
      return this.currentlyEmployed;
   }

   public void setContactInfo(List contactInfo) {
      this.contactInfo = contactInfo;
   }

   public List getContactInfo() {
      return this.contactInfo;
   }

   public void setRoles(List roles) {
      this.roles = roles;
   }

   public List getRoles() { 
      return this.roles;
   }  
   
   @Override
   public String toString() {
        return "Person [id=" + this.getId() + ", name=" + this.name.getLastName() + ", gwa="
                + this.getGwa() + "]";
   }
   
}
