package com.hibernate.part1.dao.implementations;
  
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.hibernate.part1.model.Role;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import java.util.Iterator; 
import java.util.*; 
import com.hibernate.part1.model.ContactInfo;
import java.util.ArrayList;
import java.util.List;
import com.hibernate.part1.model.Person;
import com.hibernate.part1.dao.interfaces.ContactInfoDaoInterface;

public class ContactInfoDAO implements ContactInfoDaoInterface {
   SessionFactory sessionFactory;   

   public ContactInfoDAO() {
       try {
         sessionFactory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
   }

   public List listContactInfo() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List cinfos = new ArrayList();
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

    public ContactInfo getContactInfo(Integer id) {
       Session session = sessionFactory.openSession();
       Transaction tx = null;
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


    public void save(ContactInfo contactInfo)  {  
       Session session = sessionFactory.openSession();
       Transaction tx = null;
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

    public void updateLandLine(Integer id, String landLine) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
           ContactInfo ci = (ContactInfo)session.get(ContactInfo.class, id); 
           ci.setLandLine(landLine);
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

    public void updateMobileNumber(Integer id, String mobileNumber) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
           ContactInfo ci = (ContactInfo)session.get(ContactInfo.class, id); 
           ci.setMobileNumber(mobileNumber);
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

    public void updateEmail(Integer id, String email) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
           ContactInfo ci = (ContactInfo)session.get(ContactInfo.class, id); 
           ci.setEmail(email);
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
      Transaction tx = null;
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
