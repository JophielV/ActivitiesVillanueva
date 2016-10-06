package com.hibernate.part2.dao.implementations;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.hibernate.part2.model.Role;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Query;
import java.util.Iterator; 
import java.util.*; 
import com.hibernate.part2.model.Person;
import com.hibernate.part2.model.PersonRoles;
import com.hibernate.part2.model.Address;
import com.hibernate.part2.model.ContactInfo;
import com.hibernate.part2.dao.interfaces.PersonRolesDaoInterface;
import com.hibernate.part2.util.HibernateUtility;

public class PersonRolesDAO implements PersonRolesDaoInterface  {
    private SessionFactory sessionFactory;  
    private Transaction tx;

    public PersonRolesDAO() {}

    public PersonRolesDAO(String username, String password) {
       HibernateUtility hb = new HibernateUtility(username,password);
       sessionFactory = hb.getSessionFactory();
    }

    public List listPersonRoles() {
        Session session = sessionFactory.openSession();
        tx = null;
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
        tx = null;
        List<PersonRoles> personWithRoles = new ArrayList<PersonRoles>();
        try{
         tx = session.beginTransaction();
         Criteria cr = session.createCriteria(PersonRoles.class, "personRoles")
         .add(Restrictions.eq("personRoles.personRole.personId", id));
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
