package com.LTI.Project1.DAOs;

import com.LTI.Project1.Exceptions.UserNotFoundException;
import com.LTI.Project1.Models.ErsUser;

public interface AuthDAO {
	ErsUser login(String username, String password) throws UserNotFoundException;
	boolean authorize(String token) throws UserNotFoundException;
}
