package com.hibernate.part1.dao;
  
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

public class PersonDAO {
    SessionFactory sessionFactory;  

    public PersonDAO() {
       try {
         sessionFactory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
    }

    public void listAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
         tx = session.beginTransaction();
         List persons = session.createQuery("FROM Person").list(); 
         for (Iterator iterator = persons.iterator(); iterator.hasNext();){
            Person person = (Person) iterator.next(); 
            System.out.println("Person_Id: " + person.getId() + ", "); 
            System.out.println("Last_Name: " + person.getName().getLastName() + ", ");
            System.out.println("First_Name: " + person.getName().getFirstName() + ", ");
            System.out.println("Middle_Name: " + person.getName().getMiddleName() + ", ");
            System.out.println("Suffix: " + person.getName().getSuffix() + ", ");
            System.out.println("Title: " + person.getName().getLastName() + ", ");
            System.out.println("Street_No: " + person.getAddress().getStreetNo() + ", ");
            System.out.println("Barangay: " + person.getAddress().getBarangay() + ", ");
            System.out.println("City: " + person.getAddress().getCity() + ", ");
            System.out.println("Zip_Code: " + person.getAddress().getZipCode() + ", ");
            System.out.println("Birthday: " + person.getBirthday() + ", ");
            System.out.println("GWA: " + person.getGwa() + ", "); 
            System.out.println("Date_Hired: " + person.getDateHired() + ", ");
            System.out.println("Currently_Employed: " + person.getCurrentlyEmployed() + ", ");   
            System.out.println("=============================================");
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null) tx.rollback();
         e.printStackTrace(); 
      } finally {
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

          PersonRoles pr = (PersonRoles) session.createCriteria(PersonRoles.class)
         .add(Restrictions.eq("personId", id)).add(Restrictions.eq("roleId", role.getId())).uniqueResult();// check if both composite key exist
           if(pr != null) {             
              System.out.println("- ERROR: Person with id " + id + " already has the role with id " + role.getId() + " -");
              return; 
           } else { System.out.println("Pr exists! " ); }

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
           PersonRoles pr = (PersonRoles) session.createCriteria(PersonRoles.class)
                    .add(Restrictions.eq("personId", id)).add(Restrictions.eq("roleId", id2)).uniqueResult();
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

    public void updateLastName(Integer id, String lastName) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
           Person person = (Person)session.get(Person.class, id); 
           person.getName().setLastName(lastName); 
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

    public void updateFirstName(Integer id, String firstName) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
           Person person = (Person)session.get(Person.class, id); 
           person.getName().setFirstName(firstName); 
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

    public void updateMiddleName(Integer id, String middleName) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
           Person person = (Person)session.get(Person.class, id); 
           person.getName().setMiddleName(middleName); 
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

    public void updateSuffix(Integer id, String suffix) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
           Person person = (Person)session.get(Person.class, id); 
           person.getName().setSuffix(suffix); 
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

    public void updateTitle(Integer id, String title) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
           Person person = (Person)session.get(Person.class, id); 
           person.getName().setTitle(title); 
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

    public void updateAddress(Integer id, Address address) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
           Person person = (Person)session.get(Person.class, id); 
           person.setAddress(address); 
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

    public void updateBirthday(Integer id, Date birthday) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
           Person person = (Person)session.get(Person.class, id); 
           person.setBirthday(birthday); 
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

    public void updateGWA(Integer id, double gwa) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
           Person person = (Person)session.get(Person.class, id); 
           person.setGwa(gwa); 
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

    public void updateDateHired(Integer id, Date dateHired) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
           Person person = (Person)session.get(Person.class, id); 
           person.setDateHired(dateHired); 
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

    public void updateCurrentlyEmployed(Integer id, boolean currentlyEmployed) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
           Person person = (Person)session.get(Person.class, id); 
           person.setCurrentlyEmployed(currentlyEmployed); 
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

   public void deleteRoleManually() {

   }

}
