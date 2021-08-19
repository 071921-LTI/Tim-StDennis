package com.LTI.Project1.DAOs;

import java.util.List;

import com.LTI.Project1.Exceptions.UserNotFoundException;
import com.LTI.Project1.Models.ErsUser;

public interface UserDAO {
	public ErsUser FindUserById(String iD) throws UserNotFoundException;
	public ErsUser UpdateUserWithInfo(String iD,String[] info) throws UserNotFoundException;
	List<ErsUser> FindAllUsers() throws UserNotFoundException;
}
