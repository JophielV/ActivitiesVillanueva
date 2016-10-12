package com.dao.interfaces;

import com.model.Person;
import com.model.PersonRoles;
import com.model.Address;
import com.model.ContactInfo;
import com.model.Role;
import java.util.ArrayList;
import java.util.List;

public interface PersonDaoInterface {

   public List listPersons(int choice);
  
   public void update(Integer id, Person person);

   public Person getPerson(Integer id);

   public void save(Person person);

   public void delete(Integer id);

   public void addContact(Integer id, ContactInfo ci);

   public void addRole(Integer id, Role role);

   public void deleteRole(int id, int id2);

}
