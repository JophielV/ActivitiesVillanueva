package com.model;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.OrderColumn;
import javax.persistence.TableGenerator;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;


@Entity
@Table(name = "PERSON")
public class Person {
   
   @Id 
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name = "person_id")
   private int id;

   @Embedded
   private Name name;

   @Embedded
   private Address address;

   @Column(name = "birthday")
   @Temporal(TemporalType.DATE) 
   private Date birthday;

   @Column(name = "gwa")
   private double gwa;

   @Column(name = "date_hired")
   @Temporal(TemporalType.DATE) 
   private Date dateHired;

   @Column(name = "currently_employed")
   @Type(type="yes_no")
   private boolean currentlyEmployed;

   @OneToMany(cascade={CascadeType.ALL})
   @JoinColumn(name="person_id", nullable=false)
   @OrderColumn(name="idx")
   private List<ContactInfo> contactInfo = new ArrayList<ContactInfo>();

   @ManyToMany(cascade = {CascadeType.ALL})
   @JoinTable(name="PERSON_ROLES", 
	      joinColumns={@JoinColumn(name="person_id")}, 
	      inverseJoinColumns={@JoinColumn(name="role_id")})
   @OrderColumn(name="idx")
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
