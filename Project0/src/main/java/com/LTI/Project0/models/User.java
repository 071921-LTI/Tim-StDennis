package com.LTI.Project0.models;

import java.io.Serializable;

public class User implements Serializable {
	private String userName, password, firstName, lastName, middleInitial, Role;
	
	//Constructors
	public User()
	{
		super();
	}
	
	public User(String in_UserName, String in_Password)
	{
		super();
		this.userName = in_UserName;
		this.password = in_Password;
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

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
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
