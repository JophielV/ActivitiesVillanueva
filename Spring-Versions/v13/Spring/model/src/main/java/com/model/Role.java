package com.model;

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
import javax.persistence.GenerationType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="ROLES")
public class Role {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name = "role_id")
   private int id;

   @Column(name = "role_name")
   private String roleName;

   @ManyToMany(mappedBy="roles")
   @OrderColumn(name="idx")
   private List<Person> persons = new ArrayList<Person>();

   public Role() {}

   public Role(String roleName) {
      this.roleName = roleName;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getId() {
      return this.id;
   } 

   public void setRoleName(String roleName) {
      this.roleName = roleName;
   }

   public String getRoleName() {
      return this.roleName;
   }

   public void setPersons(List persons) {
      this.persons = persons;
   }

   public List getPersons() {
      return this.persons;
   }

   @Override
   public String toString() {
        return "Role [id=" + this.getId() + ", role=" + this.getRoleName();
   }
}
