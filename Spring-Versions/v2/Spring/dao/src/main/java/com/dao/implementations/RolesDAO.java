package com.dao.implementations;
  
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.model.Role;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import java.util.Iterator; 
import java.util.*; 
import com.model.Role;
import com.model.PersonRoles;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Query;
import com.dao.interfaces.RolesDaoInterface;
import com.util.HibernateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RolesDAO implements RolesDaoInterface {

    @Autowired
    private SessionFactory sessionFactory;  

    public RolesDAO() {
         
    }
     
    public RolesDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List listRoles() {
       List<Role> roles = new ArrayList<Role>();
       roles = sessionFactory.getCurrentSession().createQuery("FROM Role").list(); 
       
       return roles;
    }

    @Transactional
    public Role getRole(Integer id) {
       Role role = new Role();
       role = (Role)sessionFactory.getCurrentSession().get(Role.class, id); 
           
       return role;
    }

    @Transactional
    public void save(String roleName)  {  
       Integer roleId = null;
       Role role = new Role(roleName);
       roleId = (Integer) sessionFactory.getCurrentSession().save(role);
    } 

    @Transactional  
    public void update(Integer id, String newRole) {
       Role role = (Role)sessionFactory.getCurrentSession().get(Role.class, id); 
       role.setRoleName(newRole); 
       sessionFactory.getCurrentSession().update(role); 
    } 
     
    @Transactional
    public void delete(Integer id) {
         Role role = (Role)sessionFactory.getCurrentSession().get(Role.class, id);
         List<PersonRoles> pr = new ArrayList<PersonRoles>();
         
         pr =  sessionFactory.getCurrentSession().createCriteria(PersonRoles.class, "personRoles")
               .add(Restrictions.eq("personRoles.personRole.roleId", id)).list();
         if(pr.size()!=0) { 
            for(int i = 0; i < pr.size(); i++) {
               sessionFactory.getCurrentSession().delete(pr.get(i)); 
            }
         }         
         role.getPersons().clear();
         sessionFactory.getCurrentSession().update(role);
         sessionFactory.getCurrentSession().delete(role); 
         sessionFactory.getCurrentSession().flush();      
    }
   

}
