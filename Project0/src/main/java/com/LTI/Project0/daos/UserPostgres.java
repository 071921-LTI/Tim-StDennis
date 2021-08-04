package com.LTI.Project0.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LTI.Project0.exceptions.UserNotFoundException;
import com.LTI.Project0.models.User;
import com.LTI.Project0.util.ConnectionUtil;

public class UserPostgres implements UserDao{

	public static Logger log = LogManager.getRootLogger();
	
	@Override
	public User getUser(String in_username) throws UserNotFoundException {
		User gotten_User = null;
		String first_Name, last_Name, role,username,password,email;
		String sql = "select * from users where acct_username = ?";
		
		try(Connection con = ConnectionUtil.getConnectionFromEnv())
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, in_username);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				first_Name = rs.getString("first_name");
				last_Name = rs.getString("last_name");
				role = rs.getString("user_Role");
				username = rs.getString("acct_username");
				password = rs.getString("acct_password");
				email = rs.getString("acct_email");
				gotten_User = new User(first_Name,last_Name,role,username,password,email);
			}
			else
				throw new UserNotFoundException();
		}catch(SQLException e) {
			log.error("SQL Exception Thrown. {User Postgres: Get User}. Stack Trace below:" + e.getMessage());
			e.printStackTrace();
		}
		
		return gotten_User;
	}

	@Override
	public int addUser(User user) throws SQLException {
		int Worked = -1;
		String sql = "insert into users "
				+ "(first_name, "
				+ "last_name, "
				+ "user_role, "
				+ "acct_username, "
				+ "acct_password, "
				+ "acct_email) values ("
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?) returning user_id;";
		
		try(Connection con = ConnectionUtil.getConnectionFromEnv())
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, "CUSTOMER");
			ps.setString(4, user.getUsername());
			ps.setString(5, user.getPassword());
			ps.setString(6, user.getEmail());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Worked = rs.getInt("user_id");
			}
		}catch(SQLException e) {
			log.error("SQL Exception Thrown. {User Postgres: Add User}. Stack Trace below:" + e.getMessage());
			e.printStackTrace();
		}
		
		return Worked;
	}

	@Override
	public String getRole(String userName) throws UserNotFoundException {
		User role = null;
		try {
			role = getUser(userName);
		} catch (UserNotFoundException e) {
			log.warn("User Not Found Exception thrown. {User Postgres: Get Role} (Probably invaild input...)");
			throw new UserNotFoundException();
		}
		return role.getRole();
	}

}
