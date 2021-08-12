package com.LTI.Project1.DAOs;

import com.LTI.Project1.Exceptions.UserNotFoundException;
import com.LTI.Project1.Models.ErsUser;

public interface UserDAO {
	public ErsUser FindUserById(String iD) throws UserNotFoundException; 
}
