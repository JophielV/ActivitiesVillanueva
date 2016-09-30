package com.hibernate.part1.model;
import java.io.Serializable;

public class PersonRoles implements Serializable {
   private int personId;
   private int roleId;

   public PersonRoles() {}

   public void setPersonId(int personId) {
      this.personId = personId;
   }

   public int getPersonId() {
      return this.personId;
   }

   public void setRoleId(int roleId) {
      this.roleId = roleId;
   }

   public int getRoleId() {
      return this.roleId;
   }

}
