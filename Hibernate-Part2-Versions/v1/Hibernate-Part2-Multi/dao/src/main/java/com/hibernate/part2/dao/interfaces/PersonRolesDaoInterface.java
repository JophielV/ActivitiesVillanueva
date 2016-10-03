package com.hibernate.part2.dao.interfaces;

import com.hibernate.part2.model.Person;
import com.hibernate.part2.model.PersonRoles;
import com.hibernate.part2.model.Address;
import com.hibernate.part2.model.ContactInfo;
import com.hibernate.part2.model.Role;
import java.util.ArrayList;
import java.util.List;

public interface PersonRolesDaoInterface {

   public List listPersonRoles();

   public List getRolesAssociatedWithPerson(Integer id);
  
}
