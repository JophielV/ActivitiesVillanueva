package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.model.Person;
import com.model.PersonRoles;
import com.model.Address;
import com.model.ContactInfo;
import com.model.Role;

   public class HibernateUtility {
      private SessionFactory sessionFactory;
      private String username;
      private String password;    
 
      public HibernateUtility(String username, String password) {
         this.username = username;
         this.password = password;
         sessionFactory = buildSessionFactory();
      }
  
      private final SessionFactory buildSessionFactory() {
         Configuration configuration = new Configuration().configure();
         configuration.getProperties().setProperty("hibernate.connection.username",username);
         configuration.getProperties().setProperty("hibernate.connection.password",password); 
         configuration.addAnnotatedClass(Person.class);
         configuration.addAnnotatedClass(ContactInfo.class);
         configuration.addAnnotatedClass(Role.class);
         configuration.addAnnotatedClass(PersonRoles.class);
         ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
            configuration.getProperties()).build();
         sessionFactory = configuration.buildSessionFactory(serviceRegistry);
         return sessionFactory;
      }
 
      public SessionFactory getSessionFactory() {
         return sessionFactory;
      }

   }
