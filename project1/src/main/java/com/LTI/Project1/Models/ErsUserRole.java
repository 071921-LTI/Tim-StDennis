package com.LTI.Project1.Models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ers_user_roles database table.
 * 
 */
@Entity
@Table(name="ers_user_roles")
public class ErsUserRole implements Serializable {
	public ErsUserRole(Integer ersUserRoleId, String userRole) {
		super();
		this.ersUserRoleId = ersUserRoleId;
		this.userRole = userRole;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ers_user_role_id")
	private Integer ersUserRoleId;

	@Column(name="user_role")
	private String userRole;

	//bi-directional many-to-one association to ErsUser
	@OneToMany(mappedBy="ersUserRole")
	private List<ErsUser> ersUsers;

	public ErsUserRole() {
	}

	public Integer getErsUserRoleId() {
		return this.ersUserRoleId;
	}

	public void setErsUserRoleId(Integer ersUserRoleId) {
		this.ersUserRoleId = ersUserRoleId;
	}

	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public List<ErsUser> getErsUsers() {
		return this.ersUsers;
	}

	public void setErsUsers(List<ErsUser> ersUsers) {
		this.ersUsers = ersUsers;
	}

	public ErsUser addErsUser(ErsUser ersUser) {
		getErsUsers().add(ersUser);
		ersUser.setErsUserRole(this);

		return ersUser;
	}

	public ErsUser removeErsUser(ErsUser ersUser) {
		getErsUsers().remove(ersUser);
		ersUser.setErsUserRole(null);

		return ersUser;
	}

}