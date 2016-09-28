package com.hibernate.part1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Role {
   private int id;
   private String roleName;
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
