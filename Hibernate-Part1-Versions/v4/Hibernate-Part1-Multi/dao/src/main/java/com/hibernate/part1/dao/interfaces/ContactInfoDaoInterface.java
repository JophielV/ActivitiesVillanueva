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

   public void updateLandLine(Integer id, String landLine);

   public void updateMobileNumber(Integer id, String mobileNumber);

   public void updateEmail(Integer id, String email);

   public void delete(Integer id);

}
