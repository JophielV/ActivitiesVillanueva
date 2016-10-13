package com.dao.implementations;
  
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.model.Role;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Order;
import org.hibernate.Query;
import java.util.Iterator; 
import java.util.*; 
import com.model.Person;
import com.model.PersonRoles;
import com.model.Address;
import com.model.ContactInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.dao.interfaces.PersonDaoInterface;
import com.util.HibernateUtility;

public class PersonDAO implements PersonDaoInterface {
    private SessionFactory sessionFactory;  
    private Transaction tx;

    public PersonDAO() {}

    public PersonDAO(String username, String password) {
       HibernateUtility hb = new HibernateUtility(username,password);
       sessionFactory = hb.getSessionFactory();
    }
 
    public List listPersons(int choice) {
        Session session = sessionFactory.openSession();
        tx = null;
        List<Person> persons = new ArrayList<Person>();
        try {
          tx = session.beginTransaction();
          Criteria cr = session.createCriteria(Person.class);
          switch(choice) {
            case 1:
               persons = cr.list();
               break;
            case 2:
               persons = cr.list();
               break;
            case 3: 
               cr.addOrder(Order.asc("dateHired"));
               persons = cr.list(); 
               break;
            case 4:
               Criteria cr2 = session.createCriteria(Person.class, "person").addOrder(Order.asc("person.name.lastName"));
               persons = cr2.list(); 
               break;  
           }
         tx.commit();
         } catch (HibernateException e) {
             if (tx != null) tx.rollback();
             e.printStackTrace(); 
         } finally {
             session.close(); 
         }
         return persons;
    }   

    public void update(Integer id, Person p) {
        Session session = sessionFactory.openSession();
        tx = null;
        try {
           tx = session.beginTransaction();
           Person person = (Person)session.get(Person.class, id); 
           person.getName().setFirstName(p.getName().getFirstName());
           person.getName().setLastName(p.getName().getLastName());
           person.getName().setMiddleName(p.getName().getMiddleName());
           person.getName().setSuffix(p.getName().getSuffix());
           person.getName().setTitle(p.getName().getTitle());
           person.getAddress().setStreetNo(p.getAddress().getStreetNo());
           person.getAddress().setBarangay(p.getAddress().getBarangay());
           person.getAddress().setCity(p.getAddress().getCity());
           person.getAddress().setZipCode(p.getAddress().getZipCode());
           person.setBirthday(p.getBirthday());
           person.setGwa(p.getGwa());
           person.setDateHired(p.getDateHired());
           person.setCurrentlyEmployed(p.getCurrentlyEmployed());
           session.update(person); 
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



    public Person getPerson(Integer id) {
       Session session = sessionFactory.openSession();
       tx = null;
       Person person = new Person();
       try {
           tx = session.beginTransaction();
           person = (Person)session.get(Person.class, id); 
           tx.commit();
        }
        catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }
        finally {
           session.close(); 
        }       
        return person;
    }

    public void save(Person person)  {  
       Session session = sessionFactory.openSession();
       tx = null;
       Integer personId = null;
       try {
          tx = session.beginTransaction();
          personId = (Integer) session.save(person);
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
         Person person = (Person)session.get(Person.class, id); 
 
         person.getRoles().clear();
         session.update(person);                      
         
         session.delete(person); 
         session.flush();
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   } 

    public void addContact(Integer id, ContactInfo ci) {
       Session session = sessionFactory.openSession();
       tx = null;
       Person person = new Person();
       try {
           tx = session.beginTransaction();
           person = (Person)session.get(Person.class, id);
           ci.setPerson(person);
           List<ContactInfo> contactinfos = new ArrayList<ContactInfo>();
           contactinfos = person.getContactInfo();
           contactinfos.add(ci);
           person.setContactInfo(contactinfos);
           session.saveOrUpdate(person);
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

    public void addRole(Integer id, Role role) {
       Session session = sessionFactory.openSession();
       tx = null;
       Person person = new Person();
       try {
           tx = session.beginTransaction();
           person = (Person)session.get(Person.class, id);

           PersonRoles pr = (PersonRoles) session.createCriteria(PersonRoles.class, "personRoles")
           .add(Restrictions.eq("personRoles.personRole.personId", id))
           .add(Restrictions.eq("personRoles.personRole.roleId", role.getId())).uniqueResult();// check if both composite key exist
           if(pr != null) {             
              System.out.println("- ERROR: Person with id " + id + " already has the role with id " + role.getId() + " -");
              return; 
           } else {  }

           List<Role> roles = new ArrayList<Role>();
           roles = person.getRoles();
           roles.add(role);
           person.setRoles(roles);           
                    
           session.saveOrUpdate(person);
           tx.commit();
        }
        catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }
        finally {
           session.close(); 
        }       
        System.out.println("-------- Notice: Add role to Person Success ------");  
    }


    public void deleteRole(int id, int id2) {
       Session session = sessionFactory.openSession();
       tx = null;
       try {
           tx = session.beginTransaction();
           PersonRoles pr = (PersonRoles) session.createCriteria(PersonRoles.class, "personRoles")
                    .add(Restrictions.eq("personRoles.personRole.personId", id))
                    .add(Restrictions.eq("personRoles.personRole.roleId", id2)).uniqueResult();
           session.delete(pr);
           session.flush();
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
   
    
    

}
