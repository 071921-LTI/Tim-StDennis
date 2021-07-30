package com.LTI.Project0.daos;

import java.sql.SQLException;

import com.LTI.Project0.exceptions.UserNotFoundException;
import com.LTI.Project0.models.User;

public interface UserDao {
	public abstract User getUser(String username) throws UserNotFoundException;
	public abstract int addUser(User user) throws SQLException;
	public abstract String getRole(String username);
}
