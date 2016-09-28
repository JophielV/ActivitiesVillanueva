package com.hibernate.part1.dao.interfaces;

import com.hibernate.part1.model.Person;
import com.hibernate.part1.model.PersonRoles;
import com.hibernate.part1.model.Address;
import com.hibernate.part1.model.ContactInfo;
import com.hibernate.part1.model.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public interface PersonDaoInterface {
   
   public List listPersonsById();
  
   public List listPersonsByDateHired();

   public List listPersonsByLastName();

   public Person getPerson(Integer id);

   public void addContact(Integer id, ContactInfo ci);

   public void addRole(Integer id, Role role);

   public void saveRolesList(Role role, Person person);

   public void deleteRole(int id, int id2);

   public void save(Person person);

   public void updateLastName(Integer id, String lastName);

   public void updateFirstName(Integer id, String firstName);

   public void updateMiddleName(Integer id, String middleName);

   public void updateSuffix(Integer id, String suffix);

   public void updateTitle(Integer id, String title);

   public void updateAddress(Integer id, Address address);

   public void updateBirthday(Integer id, Date birthday);

   public void updateGWA(Integer id, double gwa);

   public void updateDateHired(Integer id, Date dateHired);

   public void updateCurrentlyEmployed(Integer id, boolean currentlyEmployed);
  
   public void delete(Integer id);      

   
}
