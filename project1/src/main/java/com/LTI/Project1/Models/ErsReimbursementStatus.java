package com.LTI.Project1.Models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ers_reimbursement_status database table.
 * 
 */
@Entity
@Table(name="ers_reimbursement_status")
public class ErsReimbursementStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="reimb_status_id")
	private Integer reimbStatusId;

	@Column(name="reimb_status")
	private String reimbStatus;

	//bi-directional many-to-one association to ErsReimbursement
	@OneToMany(mappedBy="ersReimbursementStatus")
	private List<ErsReimbursement> ersReimbursements;

	public ErsReimbursementStatus() {
	}

	public Integer getReimbStatusId() {
		return this.reimbStatusId;
	}

	public void setReimbStatusId(Integer reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public String getReimbStatus() {
		return this.reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public List<ErsReimbursement> getErsReimbursements() {
		return this.ersReimbursements;
	}

	public void setErsReimbursements(List<ErsReimbursement> ersReimbursements) {
		this.ersReimbursements = ersReimbursements;
	}

	public ErsReimbursement addErsReimbursement(ErsReimbursement ersReimbursement) {
		getErsReimbursements().add(ersReimbursement);
		ersReimbursement.setErsReimbursementStatus(this);

		return ersReimbursement;
	}

	public ErsReimbursement removeErsReimbursement(ErsReimbursement ersReimbursement) {
		getErsReimbursements().remove(ersReimbursement);
		ersReimbursement.setErsReimbursementStatus(null);

		return ersReimbursement;
	}

}