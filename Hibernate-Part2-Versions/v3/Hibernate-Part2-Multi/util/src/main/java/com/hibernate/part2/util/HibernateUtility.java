package com.hibernate.part2.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

   public class HibernateUtility {
      private SessionFactory sessionFactory;
      private String username;
      private String password;
      
      public HibernateUtility(String username, String password) {
         this.username = username;
         this.password = password;
         sessionFactory = buildSessionFactory();
      }
  
      private SessionFactory buildSessionFactory() {
         Configuration cfg = new Configuration();
         cfg.configure();
         cfg.getProperties().setProperty("hibernate.connection.username",username);
         cfg.getProperties().setProperty("hibernate.connection.password",password);         
         sessionFactory = cfg.buildSessionFactory();
         return sessionFactory;
      }
 
      public SessionFactory getSessionFactory() {
         return sessionFactory;
      }

   }
