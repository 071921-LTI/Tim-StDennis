package com.LTI.Project0.services;

import java.sql.SQLException;

import com.LTI.Project0.exceptions.UserNotFoundException;
import com.LTI.Project0.models.User;

public interface UserService {
	public abstract boolean addUser(User user) throws SQLException;
	public abstract User getUser(String username) throws UserNotFoundException;
	public abstract String GetRole(String username) throws UserNotFoundException;
}
