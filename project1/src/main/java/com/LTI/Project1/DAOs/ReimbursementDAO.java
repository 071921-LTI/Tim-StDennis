package com.LTI.Project1.DAOs;

import java.util.List;

import com.LTI.Project1.Exceptions.ReimbursementNotFoundException;
import com.LTI.Project1.Models.ErsReimbursement;

public interface ReimbursementDAO {
	public int GetCountOFReimbursements() throws ReimbursementNotFoundException;
	public void AddNewReimbursement(String r_ID, String r_Name, String r_Type, String r_Price, String r_Descr, String r_Submitter);
	public List<ErsReimbursement> GetAllReimbursementsForUser(String parameter) throws ReimbursementNotFoundException;
	public ErsReimbursement GetDetailsOn(String lookup) throws ReimbursementNotFoundException;
	public List<ErsReimbursement> GetAllReimbursements() throws ReimbursementNotFoundException;
	public void UpdateReimbursement(String r_ID, String r_Status, String r_Resolver) throws ReimbursementNotFoundException;
}
