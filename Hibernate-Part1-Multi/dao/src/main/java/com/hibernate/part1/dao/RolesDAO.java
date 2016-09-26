package com.hibernate.part1.dao;
  
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.hibernate.part1.model.Role;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import java.util.Iterator; 
import java.util.*; 
import com.hibernate.part1.model.Role;
import com.hibernate.part1.model.PersonRoles;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class RolesDAO {
    SessionFactory sessionFactory;  

    public RolesDAO() {
       try{
         sessionFactory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
    }

    public List listRoles() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List roles = new ArrayList();
        try{
          tx = session.beginTransaction();
          roles = session.createQuery("FROM Role").list(); 
          tx.commit();
      }catch (HibernateException e) {
          if (tx!=null) tx.rollback();
          e.printStackTrace(); 
      }finally {
          session.close(); 
      }
      return roles;
    }

    public Role getRole(Integer id) {
       Session session = sessionFactory.openSession();
       Transaction tx = null;
       Role role = new Role();
       try {
           tx = session.beginTransaction();
           role = (Role)session.get(Role.class, id); 
           tx.commit();
        }
        catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }
        finally {
           session.close(); 
        }       
        return role;
    }

    public void save(String roleName)  {  
       Session session = sessionFactory.openSession();
       Transaction tx = null;
       Integer roleId = null;
       try {
          tx = session.beginTransaction();
          Role role = new Role(roleName);
          roleId = (Integer) session.save(role);
          tx.commit();
       }
       catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
       }
       finally {
         session.close(); 
       }
    }

    public void update(Integer id, String newRole) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
           Role role = (Role)session.get(Role.class, id); 
           role.setRoleName(newRole); 
           session.update(role); 
           tx.commit();
        }
        catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }
        finally {
           session.close(); 
        }
    } 
   
     public void delete(Integer id) {
      Session session = sessionFactory.openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         Role role = (Role)session.get(Role.class, id); 
         PersonRoles pr = (PersonRoles) session.createCriteria(PersonRoles.class)
                    .add(Restrictions.eq("roleId", id)).uniqueResult();
         session.delete(pr);
         role.getPersons().clear();
         session.update(role);
         session.delete(role); 
         session.flush();
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
   

}
