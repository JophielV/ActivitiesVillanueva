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
import org.hibernate.*;

public class ContactInfoDAO implements ContactInfoDaoInterface {
   public SessionFactory sessionFactory;   

   public ContactInfoDAO() {}

   public ContactInfoDAO(String username, String password) {
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

   public List listContactInfo(Session session) {
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

    
    public void update(Integer id, String update) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
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
