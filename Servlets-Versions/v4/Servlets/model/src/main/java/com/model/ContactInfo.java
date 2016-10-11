package com.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.OrderColumn;
 import javax.persistence.GenerationType;

@Entity
@Table(name = "CONTACT_INFO")
public class ContactInfo {
   
   @Id 
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name = "contact_id")
   private int id;

   @Column(name = "type")
   private String type;

   @Column(name = "information")
   private String information;

   @ManyToOne
   @JoinColumn(name="person_id", insertable=false, updatable=false, nullable=false)
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
