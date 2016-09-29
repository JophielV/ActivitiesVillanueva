package com.hibernate.part1.dao.interfaces;

import com.hibernate.part1.model.Person;
import com.hibernate.part1.model.PersonRoles;
import com.hibernate.part1.model.Address;
import com.hibernate.part1.model.ContactInfo;
import com.hibernate.part1.model.Role;
import java.util.ArrayList;
import java.util.List;

public interface PersonDaoInterface {

   public List listPersons(int choice);
  
   public void update(Integer id, Object update, int type);

   public Person getPerson(Integer id);

   public void save(Person person);

   public void delete(Integer id);

   public void addContact(Integer id, ContactInfo ci);

   public void addRole(Integer id, Role role);

   public void saveRolesList(Role role, Person person);

   public void deleteRole(int id, int id2);

}
