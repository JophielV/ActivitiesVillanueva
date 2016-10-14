package com.dao.interfaces;

import com.model.Person;
import com.model.PersonRoles;
import com.model.Address;
import com.model.ContactInfo;
import com.model.Role;
import java.util.ArrayList;
import java.util.List;

public interface RolesDaoInterface {

   public List listRoles();

   public Role getRole(Integer id);

   public void save(String roleName);

   public void update(Integer id, String newRole);

   public void delete(Integer id);
   
}
