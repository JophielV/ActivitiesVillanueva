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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ContactInfoDAO implements ContactInfoDaoInterface {
   
   @Autowired
    private SessionFactory sessionFactory;  

    public ContactInfoDAO() {
         
    }
     
    public ContactInfoDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

   @Transactional
   public List listContactInfo() {
        List<ContactInfo> cinfos = sessionFactory.getCurrentSession().createQuery("FROM ContactInfo").list();
        
        return cinfos;
    }

    @Transactional
    public List listContactInfo(int id) {
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(ContactInfo.class, "contactInfo")
                      .add(Restrictions.eq("contactInfo.person.id", id));
        List<ContactInfo> cinfos = cr.list();
         
        return cinfos;
    }

    @Transactional
    public ContactInfo getContactInfo(int id) {
       ContactInfo ci = (ContactInfo)sessionFactory.getCurrentSession().get(ContactInfo.class, id); 
           
       return ci;
    }

    @Transactional
    public ContactInfo getContactInfo(int id, int id2) {
       ContactInfo ci = (ContactInfo) sessionFactory.getCurrentSession().createCriteria(ContactInfo.class, "contactInfo")
               .add(Restrictions.eq("contactInfo.person.id", id))
               .add(Restrictions.eq("contactInfo.id", id2)).uniqueResult(); 
           
       return ci;
    }

    @Transactional
    public void save(ContactInfo contactInfo)  {  
       sessionFactory.getCurrentSession().save(contactInfo); 
    }

    @Transactional
    public void update(int id, String update) {
       ContactInfo ci = (ContactInfo)sessionFactory.getCurrentSession().get(ContactInfo.class, id); 
       ci.setInformation(update);
       sessionFactory.getCurrentSession().update(ci); 
    }
    
    @Transactional
    public void delete(int id) {
       ContactInfo ci = (ContactInfo)sessionFactory.getCurrentSession().get(ContactInfo.class, id); 
       sessionFactory.getCurrentSession().delete(ci); 
       sessionFactory.getCurrentSession().flush();
   } 

}
