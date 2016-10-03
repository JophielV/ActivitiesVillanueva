package com.hibernate.part2.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;


@Entity
@Table(name = "PERSON_ROLES")
public class PersonRoles implements Serializable {
   
   @EmbeddedId
   private PersonRoleEmbedded personRole;

   public void setPersonRole(PersonRoleEmbedded pr) {
      this.personRole = pr;
   }

   public PersonRoleEmbedded getPersonRole() {
      return this.personRole;
   }

}
