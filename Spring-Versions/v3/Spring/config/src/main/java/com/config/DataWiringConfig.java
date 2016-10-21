package com.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.service.BusinessLogicService;
import com.dao.implementations.PersonDAO;
import com.dao.implementations.ContactInfoDAO;
import com.dao.interfaces.PersonDaoInterface;
import com.dao.interfaces.ContactInfoDaoInterface;


@Configuration
@ComponentScan("com.controller")
@EnableTransactionManagement
public class DataWiringConfig {

   @Bean(name = "viewResolver")
   public InternalResourceViewResolver getViewResolver() {
     InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
     viewResolver.setPrefix("/WEB-INF/views/");
     viewResolver.setSuffix(".jsp");
     return viewResolver;
   }

   @Autowired
   @Bean(name = "sessionFactory")
   public SessionFactory getSessionFactory(DataSource dataSource) {
      LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
      sessionBuilder.scanPackages("com.model")
                          .addProperties(getHibernateProperties());
      return sessionBuilder.buildSessionFactory();
   }

   @Bean(name = "dataSource")
   public DataSource getDataSource() {
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://localhost:3306/HibernatePart1");
      dataSource.setUsername("root");
      dataSource.setPassword("sample");
 
      return dataSource;
   }

   private Properties getHibernateProperties() {
      Properties properties = new Properties();
      properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
      properties.put("hibernate.hbm2ddl.auto", "update");
      return properties;
   }

   @Autowired
   @Bean(name = "transactionManager")
   public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
      HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
 
      return transactionManager;
   }

   @Autowired
   @Bean(name = "personDao")
   public PersonDaoInterface getPersonDao(SessionFactory sessionFactory) {
      return new PersonDAO(sessionFactory);
   }
   
   @Autowired
   @Bean(name = "contactDao")
   public ContactInfoDaoInterface getContactInfoDao(SessionFactory sessionFactory) {
      return new ContactInfoDAO(sessionFactory);
   }

   

   
}
