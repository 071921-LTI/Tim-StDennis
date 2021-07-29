package com.LTI.Project0.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.LTI.Project0.exceptions.UserNotFoundException;
import com.LTI.Project0.models.User;
import com.LTI.Project0.util.ConnectionUtil;

public class UserPostgres implements UserDao{

	@Override
	public User getUser(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
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
			e.printStackTrace();
		}
		
		return Worked;
	}

}
