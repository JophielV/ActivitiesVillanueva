package com.dao.interfaces;

import com.model.Person;
import com.model.PersonRoles;
import com.model.Address;
import com.model.ContactInfo;
import com.model.Role;
import java.util.ArrayList;
import java.util.List;

public interface PersonRolesDaoInterface {

   public List listPersonRoles();

   public List getRolesAssociatedWithPerson(Integer id);
  
}
