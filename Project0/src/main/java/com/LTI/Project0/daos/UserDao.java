package com.LTI.Project0.daos;

import com.LTI.Project0.exceptions.UserNotFoundException;
import com.LTI.Project0.models.User;

public interface UserDao {
	public abstract User getUser(String username) throws UserNotFoundException;
	public abstract boolean addUser(User user);
}
