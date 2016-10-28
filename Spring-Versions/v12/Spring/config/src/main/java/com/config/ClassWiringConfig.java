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
import org.hibernate.SessionFactory;

import com.service.BusinessLogicService;
import com.dao.implementations.PersonDAO;
import com.dao.implementations.ContactInfoDAO;
import com.dao.implementations.RolesDAO;
import com.dao.implementations.PersonRolesDAO;
import com.dao.implementations.UserDAO;
import com.dao.interfaces.PersonDaoInterface;
import com.dao.interfaces.ContactInfoDaoInterface;
import com.dao.interfaces.PersonRolesDaoInterface;
import com.dao.interfaces.RolesDaoInterface;
import com.dao.interfaces.UserDaoInterface;

@Configuration
@Import(DataWiringConfig.class)
public class ClassWiringConfig {

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

    @Autowired
    @Bean(name = "roleDao")
    public RolesDaoInterface getRolesDao(SessionFactory sessionFactory) {
      return new RolesDAO(sessionFactory);
    }

    @Autowired
    @Bean(name = "personRoleDao")
    public PersonRolesDaoInterface getPersonRolesDao(SessionFactory sessionFactory) {
      return new PersonRolesDAO(sessionFactory);
    }

    @Autowired
    @Bean(name = "userDao")
    public UserDaoInterface getUserDao(SessionFactory sessionFactory) {
      return new UserDAO(sessionFactory);
    }

    @Autowired
    @Bean(name = "service")
    public BusinessLogicService getService(PersonDaoInterface personDao
                                          ,ContactInfoDaoInterface contactDao
                                          ,RolesDaoInterface roleDao 
                                          ,PersonRolesDaoInterface personRoleDao
                                          ,UserDaoInterface userDao) {
      return new BusinessLogicService(personDao,contactDao,roleDao,personRoleDao,userDao);
    }
    
}
