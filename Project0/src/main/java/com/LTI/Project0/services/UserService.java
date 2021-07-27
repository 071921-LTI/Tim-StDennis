package com.LTI.Project0.services;

import com.LTI.Project0.exceptions.UserNotFoundException;
import com.LTI.Project0.models.User;

public interface UserService {
	public abstract boolean addUser(User user);
	public abstract User getUser(String username) throws UserNotFoundException;
}
