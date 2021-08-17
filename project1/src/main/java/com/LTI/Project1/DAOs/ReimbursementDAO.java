package com.LTI.Project1.DAOs;

import com.LTI.Project1.Exceptions.ReimbursementNotFoundException;

public interface ReimbursementDAO {
	public int GetCountOFReimbursements() throws ReimbursementNotFoundException;
}
