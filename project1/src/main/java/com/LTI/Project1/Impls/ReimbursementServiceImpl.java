package com.LTI.Project1.Impls;

import org.hibernate.Session;

import com.LTI.Project1.DAOs.ReimbursementDAO;
import com.LTI.Project1.Exceptions.ReimbursementNotFoundException;
import com.LTI.Project1.util.HibernateUtil;

public class ReimbursementServiceImpl implements ReimbursementDAO {

	@Override
	public int GetCountOFReimbursements() throws ReimbursementNotFoundException {
		int retVal = 0;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			int rows = Integer.valueOf(s.createQuery("SELECT e FROM ErsReimbursement e").getSingleResult().toString());
			System.out.println(rows);
		}catch(Exception e)
		{
			System.out.println("No rows found, moving on.");
			return 0;
		}
		
		return retVal;
	}

}
