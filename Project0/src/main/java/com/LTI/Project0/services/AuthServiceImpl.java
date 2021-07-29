package com.LTI.Project0.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.LTI.Project0.exceptions.AuthException;
import com.LTI.Project0.exceptions.UserNotFoundException;
import com.LTI.Project0.models.User;
import com.LTI.Project0.util.ConnectionUtil;

public class AuthServiceImpl implements AuthService {

	//UserDao upcasted to User-DB?
	
	//@Override
	//public boolean authenticateUser() throws AuthException {
		
		
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
		//return false;
	//}

	@Override
	public boolean authenticateUser(String userName, String password) throws AuthException {
		String sql = "select * from users where acct_userName = ? and acct_password = ?";
		
		try(Connection con = ConnectionUtil.getConnectionFromEnv())
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			else
			{
				//Log4J...
				throw new AuthException();
			}
		}
		catch(SQLException sql_EX)
		{
			//Log4j...
			sql_EX.printStackTrace();
		}
		return false;
	}

}
