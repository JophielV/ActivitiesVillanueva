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
    @Bean(name = "p")
    public PersonDaoInterface getPersonDao(SessionFactory sessionFactory) {
      return new PersonDAO(sessionFactory);
    }
   
    @Autowired
    @Bean(name = "c")
    public ContactInfoDaoInterface getContactInfoDao(SessionFactory sessionFactory) {
      return new ContactInfoDAO(sessionFactory);
    }

    @Autowired
    @Bean(name = "r")
    public RolesDaoInterface getRolesDao(SessionFactory sessionFactory) {
      return new RolesDAO(sessionFactory);
    }

    @Autowired
    @Bean(name = "pR")
    public PersonRolesDaoInterface getPersonRolesDao(SessionFactory sessionFactory) {
      return new PersonRolesDAO(sessionFactory);
    }

    @Autowired
    @Bean(name = "uD")
    public UserDaoInterface getUserDao(SessionFactory sessionFactory) {
      return new UserDAO(sessionFactory);
    }

    @Autowired
    @Bean(name = "service")
    public BusinessLogicService getService(PersonDaoInterface p, ContactInfoDaoInterface c, RolesDaoInterface r, PersonRolesDaoInterface pR
                                           ,UserDaoInterface uD) {
      return new BusinessLogicService(p,c,r,pR,uD);
    }
    
}
