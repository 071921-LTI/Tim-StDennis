package com.LTI.Project0.services;

import java.sql.SQLException;

import com.LTI.Project0.daos.DAOFactory;
import com.LTI.Project0.exceptions.UserNotFoundException;
import com.LTI.Project0.models.User;

public class UserServiceImpl implements UserService {

	@Override
	public boolean addUser(User user) throws SQLException {
		return (DAOFactory.getDF().getUserDAO().addUser(user)>1);
	}

	@Override
	public User getUser(String username) throws UserNotFoundException {
		return DAOFactory.getDF().getUserDAO().getUser(username);
	}

	@Override
	public String GetRole(String username) throws UserNotFoundException {
		return DAOFactory.getDF().getUserDAO().getRole(username);
	}

}
