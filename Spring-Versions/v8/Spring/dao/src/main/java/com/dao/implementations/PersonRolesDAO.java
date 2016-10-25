package com.dao.implementations;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.model.Role;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Query;
import java.util.Iterator; 
import java.util.*; 
import com.model.Person;
import com.model.PersonRoles;
import com.model.Address;
import com.model.ContactInfo;
import com.dao.interfaces.PersonRolesDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonRolesDAO implements PersonRolesDaoInterface  {

    @Autowired
    private SessionFactory sessionFactory;  

    public PersonRolesDAO() {
         
    }
     
    public PersonRolesDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List listPersonRoles() {
        List<PersonRoles> personRoles = new ArrayList<PersonRoles>();
        personRoles = sessionFactory.getCurrentSession().createQuery("FROM PersonRoles").list(); 
          
        return personRoles;
    }

    @Transactional
    public List getRolesAssociatedWithPerson(Integer id) {
        List<PersonRoles> personWithRoles = new ArrayList<PersonRoles>();
        
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(PersonRoles.class, "personRoles")
        .add(Restrictions.eq("personRoles.personRole.personId", id));
        personWithRoles = cr.list(); 
       
        return personWithRoles;
    }

}
