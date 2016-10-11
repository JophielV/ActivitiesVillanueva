package com.dao.implementations;
  
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.model.Role;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import java.util.Iterator; 
import java.util.*; 
import com.model.ContactInfo;
import java.util.ArrayList;
import java.util.List;
import com.model.Person;
import com.dao.interfaces.ContactInfoDaoInterface;
import org.hibernate.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Order;
import org.hibernate.Query;
import com.util.HibernateUtility;

public class ContactInfoDAO implements ContactInfoDaoInterface {
   private SessionFactory sessionFactory;   
   private Transaction tx;

   public ContactInfoDAO() {}

   public ContactInfoDAO(String username, String password) {
       HibernateUtility hb = new HibernateUtility(username,password);
       sessionFactory = hb.getSessionFactory();
   }

   public List listContactInfo() {
        Session session = sessionFactory.openSession();
        tx = null;
        List<ContactInfo> cinfos = new ArrayList<ContactInfo>();
        try{
         tx = session.beginTransaction();
         cinfos = session.createQuery("FROM ContactInfo").list(); 
         tx.commit();
         } catch (HibernateException e) {
             if (tx != null) tx.rollback();
             e.printStackTrace(); 
         } finally {
             session.close(); 
         }
         return cinfos;
    }

    public List listContactInfo(Integer id) {
        Session session = sessionFactory.openSession();
        tx = null;
        List<ContactInfo> cinfos = new ArrayList<ContactInfo>();
        try{
         tx = session.beginTransaction();
         Criteria cr = session.createCriteria(ContactInfo.class, "contactInfo")
         .add(Restrictions.eq("contactInfo.person.id", id));
         cinfos = cr.list(); 
         tx.commit();
         } catch (HibernateException e) {
             if (tx != null) tx.rollback();
             e.printStackTrace(); 
         } finally {
             session.close(); 
         }
         return cinfos;
    }


    public ContactInfo getContactInfo(Integer id) {
       Session session = sessionFactory.openSession();
       tx = null;
       ContactInfo ci = new ContactInfo();
       try {
           tx = session.beginTransaction();
           ci = (ContactInfo)session.get(ContactInfo.class, id); 
           tx.commit();
        }
        catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }
        finally {
           session.close(); 
        }       
        return ci;
    }

    public ContactInfo getContactInfo(Integer id, Integer id2) {
       Session session = sessionFactory.openSession();
       tx = null;
       ContactInfo ci = new ContactInfo();
       try {
           tx = session.beginTransaction();
           ci = (ContactInfo) session.createCriteria(ContactInfo.class, "contactInfo")
               .add(Restrictions.eq("contactInfo.person.id", id))
               .add(Restrictions.eq("contactInfo.id", id2)).uniqueResult(); 
           tx.commit();
        }
        catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }
        finally {
           session.close(); 
        }       
        return ci;
    }


    public void save(ContactInfo contactInfo)  {  
       Session session = sessionFactory.openSession();
       tx = null;
       try {
           tx = session.beginTransaction();
           session.save(contactInfo); 
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

    
    public void update(Integer id, String update) {
        Session session = sessionFactory.openSession();
        tx = null;
        try {
           tx = session.beginTransaction();
           ContactInfo ci = (ContactInfo)session.get(ContactInfo.class, id); 
           ci.setInformation(update);
           session.update(ci); 
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
      tx = null;
      try {
         tx = session.beginTransaction();
         ContactInfo ci = (ContactInfo)session.get(ContactInfo.class, id); 
         session.delete(ci); 
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
