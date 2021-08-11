package com.LTI.Project1.Models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ers_reimbursement_type database table.
 * 
 */
@Entity
@Table(name="ers_reimbursement_type")
public class ErsReimbursementType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="reimb_type_id")
	private Integer reimbTypeId;

	@Column(name="reimb_type")
	private String reimbType;

	//bi-directional many-to-one association to ErsReimbursement
	@OneToMany(mappedBy="ersReimbursementType")
	private List<ErsReimbursement> ersReimbursements;

	public ErsReimbursementType() {
	}

	public Integer getReimbTypeId() {
		return this.reimbTypeId;
	}

	public void setReimbTypeId(Integer reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	public String getReimbType() {
		return this.reimbType;
	}

	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}

	public List<ErsReimbursement> getErsReimbursements() {
		return this.ersReimbursements;
	}

	public void setErsReimbursements(List<ErsReimbursement> ersReimbursements) {
		this.ersReimbursements = ersReimbursements;
	}

	public ErsReimbursement addErsReimbursement(ErsReimbursement ersReimbursement) {
		getErsReimbursements().add(ersReimbursement);
		ersReimbursement.setErsReimbursementType(this);

		return ersReimbursement;
	}

	public ErsReimbursement removeErsReimbursement(ErsReimbursement ersReimbursement) {
		getErsReimbursements().remove(ersReimbursement);
		ersReimbursement.setErsReimbursementType(null);

		return ersReimbursement;
	}

}