package com.LTI.Project0.models;

import java.io.Serializable;

public class User implements Serializable {
	private int id;
	private String userName, password, firstName, lastName, Role, email;
	
	//Constructors
	public User()
	{
		super();
	}
	
	public User(String in_firstName, String in_LastName, String in_Role, String in_UserName, String in_Password, String in_Email)
	{
		super();
		this.firstName = in_firstName;
		this.lastName = in_LastName;
		this.Role = in_Role;
		this.userName = in_UserName;
		this.password = in_Password;
		this.email = in_Email;
	}
	
	//Getters and Setters
	public String getUsername() {
		return userName;
	}
	public void setUsername(String in_userName) {
		this.userName = in_userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String in_Password) {
		this.password = in_Password;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}





}
