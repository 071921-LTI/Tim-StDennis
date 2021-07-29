package com.LTI.Project0.services;

import com.LTI.Project0.exceptions.AuthException;
import com.LTI.Project0.models.User;

public interface AuthService {
	public abstract boolean authenticateUser(String userName, String password) throws AuthException;
}
