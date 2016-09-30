package com.hibernate.part1.dao.interfaces;

import com.hibernate.part1.model.Person;
import com.hibernate.part1.model.PersonRoles;
import com.hibernate.part1.model.Address;
import com.hibernate.part1.model.ContactInfo;
import com.hibernate.part1.model.Role;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session; 

public interface ContactInfoDaoInterface {

   public List listContactInfo();

   public List listContactInfo(Session session);
   
   public ContactInfo getContactInfo(Integer id);

   public void save(ContactInfo contactInfo);

   public void update(Integer id, String update);

   public void delete(Integer id);

   
  
}
