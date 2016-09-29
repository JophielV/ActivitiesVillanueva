package com.hibernate.part1.dao.interfaces;

import com.hibernate.part1.model.Person;
import com.hibernate.part1.model.PersonRoles;
import com.hibernate.part1.model.Address;
import com.hibernate.part1.model.ContactInfo;
import com.hibernate.part1.model.Role;
import java.util.ArrayList;
import java.util.List;

public interface RolesDaoInterface {

   public List listRoles();

   public Role getRole(Integer id);

   public void save(String roleName);

   public void update(Integer id, String newRole);

   public void delete(Integer id);
   
}
