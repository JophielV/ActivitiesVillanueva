package com.model;

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

@Embeddable
public class PersonRoleEmbedded implements Serializable {

    @Column(name = "person_id")
    private int personId;

    @Column(name = "role_id")
    private int roleId;

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

