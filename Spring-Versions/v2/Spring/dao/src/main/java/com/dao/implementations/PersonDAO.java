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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonDAO implements PersonDaoInterface {

    @Autowired
    private SessionFactory sessionFactory;  

    public PersonDAO() {
         
    }
     
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    @Transactional
    public List listPersons(int choice) {
        List<Person> persons = new ArrayList<Person>();
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Person.class,"person");
        switch(choice) {
            case 1:
               persons = cr.list();
               break;
            case 2:
               persons = cr.list();
               Collections.sort(persons, 
                        (o1, o2) -> new Double(o1.getGwa()).compareTo(new Double(o2.getGwa())));
               break;
            case 3: 
               cr.addOrder(Order.asc("dateHired"));
               persons = cr.list(); 
               break;
            case 4:
               cr.addOrder(Order.asc("person.name.lastName"));
               persons = cr.list(); 
               break;  
        }
        return persons;
    }   

    @Transactional
    public List find(String firstName, String lastName) {       
        List<Person> persons = new ArrayList<Person>();
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Person.class, "person")
                     .add(Restrictions.eq("person.name.firstName", firstName))
                     .add(Restrictions.eq("person.name.lastName", lastName));
        persons = cr.list(); 
        return persons;
    }

    @Transactional
    public void update(Integer id, Person p) {
        Person person = (Person)sessionFactory.getCurrentSession().get(Person.class, id); 
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
        sessionFactory.getCurrentSession().update(person);    
    }


    @Transactional
    public Person getPerson(Integer id) {
       Person person = new Person();
       person = (Person)sessionFactory.getCurrentSession().get(Person.class, id); 
           
       return person;
    }

    @Transactional
    public void save(Person person)  {  
       Integer personId = null;
       personId = (Integer) sessionFactory.getCurrentSession().save(person);
    }

    @Transactional
    public void delete(Integer id) {
       Person person = (Person)sessionFactory.getCurrentSession().get(Person.class, id); 
       person.getRoles().clear();
       sessionFactory.getCurrentSession().update(person);                      
       sessionFactory.getCurrentSession().delete(person); 
       sessionFactory.getCurrentSession().flush();     
   } 

    @Transactional
    public void addContact(Integer id, ContactInfo ci) {
       Person person = new Person();
       person = (Person)sessionFactory.getCurrentSession().get(Person.class, id);
       ci.setPerson(person);
       List<ContactInfo> contactinfos = new ArrayList<ContactInfo>();
       contactinfos = person.getContactInfo();
       contactinfos.add(ci);
       person.setContactInfo(contactinfos);
       sessionFactory.getCurrentSession().saveOrUpdate(person);
    }

    @Transactional
    public void addRole(Integer id, Role role) {
       Person person = new Person();
       person = (Person)sessionFactory.getCurrentSession().get(Person.class, id);
       PersonRoles pr = (PersonRoles) sessionFactory.getCurrentSession().createCriteria(PersonRoles.class, "personRoles")
           .add(Restrictions.eq("personRoles.personRole.personId", id))
           .add(Restrictions.eq("personRoles.personRole.roleId", role.getId())).uniqueResult();
           // check if both composite key exist
       if(pr != null) {             
          return; 
       } else {  }

       List<Role> roles = new ArrayList<Role>();
       roles = person.getRoles();
       roles.add(role);
       person.setRoles(roles);           
                    
       sessionFactory.getCurrentSession().saveOrUpdate(person);      
    }

    @Transactional
    public void deleteRole(int id, int id2) {
       PersonRoles pr = (PersonRoles) sessionFactory.getCurrentSession().createCriteria(PersonRoles.class, "personRoles")
                    .add(Restrictions.eq("personRoles.personRole.personId", id))
                    .add(Restrictions.eq("personRoles.personRole.roleId", id2)).uniqueResult();
       sessionFactory.getCurrentSession().delete(pr);
       sessionFactory.getCurrentSession().flush();
    }
   
    
    

}
