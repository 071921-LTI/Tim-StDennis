package com.LTI.Project1.Models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ers_reimbursement database table.
 * 
 */
@Entity
@Table(name="ers_reimbursement")
public class ErsReimbursement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="reimb_id")
	private Integer reimbId;

	@Column(name="reimb_amount")
	private double reimbAmount;

	@Column(name="reimb_description")
	private String reimbDescription;

	@Column(name="reimb_receipt")
	private String reimbReceipt;

	@Column(name="reimb_resolved")
	private Timestamp reimbResolved;

	@Column(name="reimb_submitted")
	private Timestamp reimbSubmitted;

	//bi-directional many-to-one association to ErsReimbursementStatus
	@ManyToOne
	@JoinColumn(name="reimb_status_id")
	private ErsReimbursementStatus ersReimbursementStatus;

	//bi-directional many-to-one association to ErsReimbursementType
	@ManyToOne
	@JoinColumn(name="reimb_type_id")
	private ErsReimbursementType ersReimbursementType;

	//bi-directional many-to-one association to ErsUser
	@ManyToOne
	@JoinColumn(name="reimb_author")
	private ErsUser ersUser1;

	//bi-directional many-to-one association to ErsUser
	@ManyToOne
	@JoinColumn(name="reimb_resolver")
	private ErsUser ersUser2;

	public ErsReimbursement() {
	}

	public Integer getReimbId() {
		return this.reimbId;
	}

	public void setReimbId(Integer reimbId) {
		this.reimbId = reimbId;
	}

	public double getReimbAmount() {
		return this.reimbAmount;
	}

	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public String getReimbDescription() {
		return this.reimbDescription;
	}

	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}

	public String getReimbReceipt() {
		return this.reimbReceipt;
	}

	public void setReimbReceipt(String reimbReceipt) {
		this.reimbReceipt = reimbReceipt;
	}

	public Timestamp getReimbResolved() {
		return this.reimbResolved;
	}

	public void setReimbResolved(Timestamp reimbResolved) {
		this.reimbResolved = reimbResolved;
	}

	public Timestamp getReimbSubmitted() {
		return this.reimbSubmitted;
	}

	public void setReimbSubmitted(Timestamp reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}

	public ErsReimbursementStatus getErsReimbursementStatus() {
		return this.ersReimbursementStatus;
	}

	public void setErsReimbursementStatus(ErsReimbursementStatus ersReimbursementStatus) {
		this.ersReimbursementStatus = ersReimbursementStatus;
	}

	public ErsReimbursementType getErsReimbursementType() {
		return this.ersReimbursementType;
	}

	public void setErsReimbursementType(ErsReimbursementType ersReimbursementType) {
		this.ersReimbursementType = ersReimbursementType;
	}

	public ErsUser getErsUser1() {
		return this.ersUser1;
	}

	public void setErsUser1(ErsUser ersUser1) {
		this.ersUser1 = ersUser1;
	}

	public ErsUser getErsUser2() {
		return this.ersUser2;
	}

	public void setErsUser2(ErsUser ersUser2) {
		this.ersUser2 = ersUser2;
	}

}