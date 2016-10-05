package com.hibernate.part2.dao.interfaces;

import com.hibernate.part2.model.Person;
import com.hibernate.part2.model.PersonRoles;
import com.hibernate.part2.model.Address;
import com.hibernate.part2.model.ContactInfo;
import com.hibernate.part2.model.Role;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session; 

public interface ContactInfoDaoInterface {

   public List listContactInfo();
   
   public List listContactInfo(Integer id);

   public ContactInfo getContactInfo(Integer id, Integer id2);

   public void save(ContactInfo contactInfo);

   public void update(Integer id, String update);

   public void delete(Integer id);

   
  
}
