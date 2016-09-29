package com.hibernate.part1.dao.interfaces;

import com.hibernate.part1.model.Person;
import com.hibernate.part1.model.PersonRoles;
import com.hibernate.part1.model.Address;
import com.hibernate.part1.model.ContactInfo;
import com.hibernate.part1.model.Role;
import java.util.ArrayList;
import java.util.List;

public interface ContactInfoDaoInterface {

   public List listContactInfo();
   
   public ContactInfo getContactInfo(Integer id);

   public void save(ContactInfo contactInfo);

   //public void update(Integer id, String update, int choice);

   public void delete(Integer id);

   
  
}
