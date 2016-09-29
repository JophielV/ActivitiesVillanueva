package com.hibernate.part1.dao.implementations;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.hibernate.part1.model.Role;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Query;
import java.util.Iterator; 
import java.util.*; 
import com.hibernate.part1.model.Person;
import com.hibernate.part1.model.PersonRoles;
import com.hibernate.part1.model.Address;
import com.hibernate.part1.model.ContactInfo;
import com.hibernate.part1.dao.interfaces.PersonRolesDaoInterface;

public class PersonRolesDAO implements PersonRolesDaoInterface  {
   SessionFactory sessionFactory;  

    public PersonRolesDAO() {}

    public PersonRolesDAO(String username, String password) {
       try {
         Configuration cfg = new Configuration();
         cfg.configure(); 
         cfg.getProperties().setProperty("hibernate.connection.username",username);
         cfg.getProperties().setProperty("hibernate.connection.password",password);
         sessionFactory = cfg.buildSessionFactory();
      } catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
    }

    public List listPersonRoles() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<PersonRoles> personRoles = new ArrayList<PersonRoles>();
        try{
          tx = session.beginTransaction();
          personRoles = session.createQuery("FROM PersonRoles").list(); 
          tx.commit();
        } 
        catch (HibernateException e) {
          if (tx!=null) tx.rollback();
          e.printStackTrace(); 
        } 
        finally {
          session.close(); 
        }
        return personRoles;
    }

    public List getRolesAssociatedWithPerson(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<PersonRoles> personWithRoles = new ArrayList<PersonRoles>();
        try{
         tx = session.beginTransaction();
         Criteria cr = session.createCriteria(PersonRoles.class)
         .add(Restrictions.eq("personId", id));
         personWithRoles = cr.list(); 
         tx.commit();
         } catch (HibernateException e) {
             if (tx != null) tx.rollback();
             e.printStackTrace(); 
         } finally {
             session.close(); 
         }
         return personWithRoles;
    }
}
