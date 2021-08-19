package com.LTI.Project1.Models;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the ers_users database table.
 * 
 */
@Entity
@Table(name="ers_users")
public class ErsUser implements Serializable {
	public ErsUser(Integer ersUsersId, String ersPassword, String ersUsername, String userEmail, String userFirstName,
			String userLastName, ErsUserRole ersUserRole) {
		super();
		this.ersUsersId = ersUsersId;
		this.ersPassword = ersPassword;
		this.ersUsername = ersUsername;
		this.userEmail = userEmail;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.ersUserRole = ersUserRole;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ers_users_id")
	private Integer ersUsersId;

	@Column(name="ers_password")
	private String ersPassword;

	@Column(name="ers_username")
	private String ersUsername;

	@Column(name="user_email")
	private String userEmail;

	@Column(name="user_first_name")
	private String userFirstName;

	@Column(name="user_last_name")
	private String userLastName;

	//bi-directional many-to-one association to ErsReimbursement
	@OneToMany(mappedBy="ersUser1")
	private List<ErsReimbursement> ersReimbursements1;

	//bi-directional many-to-one association to ErsReimbursement
	@OneToMany(mappedBy="ersUser2")
	private List<ErsReimbursement> ersReimbursements2;

	//bi-directional many-to-one association to ErsUserRole
	@ManyToOne
	@JoinColumn(name="ers_user_role_id")
	private ErsUserRole ersUserRole;

	public ErsUser() {
	}

	public Integer getErsUsersId() {
		return this.ersUsersId;
	}

	public void setErsUsersId(Integer ersUsersId) {
		this.ersUsersId = ersUsersId;
	}

	public String getErsPassword() {
		return this.ersPassword;
	}

	public void setErsPassword(String ersPassword) {
		this.ersPassword = ersPassword;
	}

	public String getErsUsername() {
		return this.ersUsername;
	}

	public void setErsUsername(String ersUsername) {
		this.ersUsername = ersUsername;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserFirstName() {
		return this.userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return this.userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public List<ErsReimbursement> getErsReimbursements1() {
		return this.ersReimbursements1;
	}

	public void setErsReimbursements1(List<ErsReimbursement> ersReimbursements1) {
		this.ersReimbursements1 = ersReimbursements1;
	}

	public ErsReimbursement addErsReimbursements1(ErsReimbursement ersReimbursements1) {
		getErsReimbursements1().add(ersReimbursements1);
		ersReimbursements1.setErsUser1(this);

		return ersReimbursements1;
	}

	public ErsReimbursement removeErsReimbursements1(ErsReimbursement ersReimbursements1) {
		getErsReimbursements1().remove(ersReimbursements1);
		ersReimbursements1.setErsUser1(null);

		return ersReimbursements1;
	}

	public List<ErsReimbursement> getErsReimbursements2() {
		return this.ersReimbursements2;
	}

	public void setErsReimbursements2(List<ErsReimbursement> ersReimbursements2) {
		this.ersReimbursements2 = ersReimbursements2;
	}

	public ErsReimbursement addErsReimbursements2(ErsReimbursement ersReimbursements2) {
		getErsReimbursements2().add(ersReimbursements2);
		ersReimbursements2.setErsUser2(this);

		return ersReimbursements2;
	}

	public ErsReimbursement removeErsReimbursements2(ErsReimbursement ersReimbursements2) {
		getErsReimbursements2().remove(ersReimbursements2);
		ersReimbursements2.setErsUser2(null);

		return ersReimbursements2;
	}

	public ErsUserRole getErsUserRole() {
		return this.ersUserRole;
	}

	public void setErsUserRole(ErsUserRole ersUserRole) {
		this.ersUserRole = ersUserRole;
	}
	
	@Override
	public String toString()
	{
		return "User Details:\nID#:" + getErsUsersId() + 
				"\nPassword:" + getErsPassword() +
				 "\nUserName:" + getErsUsername() + 
				 "\nName:" + getUserLastName() + " " + getUserFirstName() + 
				 "\nUser Role:" + getErsUserRole().getErsUserRoleId(); 
	}
	
	public String toStringDetailed()
	{
		return "ID#: " + getErsUsersId() + 
				",UserName: " + getErsUsername() + 
				",Name: " + getUserFirstName() + " " + getUserLastName() + "|";
	}
}