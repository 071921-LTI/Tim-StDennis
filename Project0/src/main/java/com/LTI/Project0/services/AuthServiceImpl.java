package com.LTI.Project0.services;

import com.LTI.Project0.exceptions.AuthException;
import com.LTI.Project0.exceptions.UserNotFoundException;
import com.LTI.Project0.models.User;

public class AuthServiceImpl implements AuthService {

	//UserDao upcasted to User-DB?
	
	@Override
	public boolean login(User user) throws AuthException {
		/*
		try {
			User persistedUser = ud.getUser(user.getUsername());
			if(persistedUser.getPassword().equals(user.getPassword())) {
				return true;
			} else {
				return false;
			}
			
		} catch (UserNotFoundException unf_EX)
		{
			unf_EX.printStackTrace();
		}
		*/
		return false;
	}

}
