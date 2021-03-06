package com.hibernate.part2.dao.implementations;
  
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.hibernate.part2.model.Role;
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
import com.hibernate.part2.model.Person;
import com.hibernate.part2.model.PersonRoles;
import com.hibernate.part2.model.Address;
import com.hibernate.part2.model.ContactInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.hibernate.part2.dao.interfaces.PersonDaoInterface;

public class PersonDAO implements PersonDaoInterface {
    private SessionFactory sessionFactory;  

    public PersonDAO() {}

    public PersonDAO(String username, String password) {
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
 
    public List listPersons(int choice) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List persons = new ArrayList();
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

    public void update(Integer id, Object update, int type) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
           Person person = (Person)session.get(Person.class, id); 
           String stringObjectHolder = "";
           switch(type) { 
              case 1:
                 stringObjectHolder = update.toString();
                 person.getName().setLastName(stringObjectHolder);
                 break;
              case 2:
                 stringObjectHolder = update.toString();
                 person.getName().setFirstName(stringObjectHolder);
                 break;
              case 3:
                 stringObjectHolder = update.toString();
                 person.getName().setMiddleName(stringObjectHolder);
                 break;
              case 4: 
                 stringObjectHolder = update.toString();
                 person.getName().setSuffix(stringObjectHolder);
                 break;
              case 5:
                 stringObjectHolder = update.toString();
                 person.getName().setTitle(stringObjectHolder);
                 break;
              case 6:
                 Address address = new Address();           
                 address = (Address) update;
                 person.setAddress(address);
                 break;
              case 7: 
                 Date date = new Date();
                 date = (Date) update;
                 person.setBirthday(date);
                 break;
              case 8:
                 double doubleObjectHolder = new Double(update.toString());
                 person.setGwa(doubleObjectHolder);
                 break;
              case 9 :
                 Date date2 = new Date();
                 date2 = (Date) update;
                 person.setDateHired(date2); 
                 break;
              case 10:
                 stringObjectHolder = update.toString();
                 boolean currEmployed = stringObjectHolder.equals("Y") ? true : false;
                 person.setCurrentlyEmployed(currEmployed); 
                 break;
           }
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
       Transaction tx = null;
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
       Transaction tx = null;
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
      Transaction tx = null;
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
       Transaction tx = null;
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
       Transaction tx = null;
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
        saveRolesList(role,person); // for bidirectional relationship
        System.out.println("-------- Notice: Add role to Person Success ------");  
    }

    public void saveRolesList(Role role, Person person) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Role roleHolder = new Role();
        try {
           tx = session.beginTransaction();
           roleHolder = (Role)session.get(Role.class, role.getId());
                    
           List<Person> persons = new ArrayList<Person>();
           persons = roleHolder.getPersons();
           persons.add(person);
           roleHolder.setPersons(persons);           

           session.saveOrUpdate(roleHolder);
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

    public void deleteRole(int id, int id2) {
       Session session = sessionFactory.openSession();
       Transaction tx = null;
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
