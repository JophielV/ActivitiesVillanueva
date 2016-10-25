package com.dao.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.User;
import com.dao.interfaces.UserDaoInterface;

@Repository
public class UserDAO implements UserDaoInterface {

	@Autowired
	private SessionFactory sessionFactory;

        public UserDAO() { }

        public UserDAO(SessionFactory sessionFactory) { 
           this.sessionFactory = sessionFactory;
        }

	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {

		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
			.createQuery("from USER where username=?")
			.setParameter(0, username)
			.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

}
