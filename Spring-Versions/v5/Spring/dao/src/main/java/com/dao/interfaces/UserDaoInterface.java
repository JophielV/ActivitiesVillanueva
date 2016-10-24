package com.dao.interfaces;

import com.model.User;

public interface UserDaoInterface {

	User findByUserName(String username);

}
