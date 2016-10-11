package com.dao.interfaces;

import com.model.Person;
import com.model.PersonRoles;
import com.model.Address;
import com.model.ContactInfo;
import com.model.Role;
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
