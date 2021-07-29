package com.LTI.Project0.services;

import java.sql.SQLException;

import com.LTI.Project0.daos.UserPostgres;
import com.LTI.Project0.exceptions.UserNotFoundException;
import com.LTI.Project0.models.User;

public class UserServiceImpl implements UserService {

	private UserPostgres use_post = new UserPostgres();
	
	
	@Override
	public boolean addUser(User user) throws SQLException {
		return (use_post.addUser(user)>1);
	}

	@Override
	public User getUser(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String GetRole() {
		// TODO Auto-generated method stub
		return null;
	}

}
