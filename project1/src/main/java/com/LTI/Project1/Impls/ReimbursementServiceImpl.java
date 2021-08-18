package com.LTI.Project1.Impls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.Session;
import org.hibernate.type.EntityType;

import com.LTI.Project1.DAOs.ReimbursementDAO;
import com.LTI.Project1.Exceptions.ReimbursementNotFoundException;
import com.LTI.Project1.Models.ErsReimbursement;
import com.LTI.Project1.Models.ErsReimbursementStatus;
import com.LTI.Project1.Models.ErsReimbursementType;
import com.LTI.Project1.Models.ErsUser;
import com.LTI.Project1.util.HibernateUtil;

public class ReimbursementServiceImpl implements ReimbursementDAO {

	@Override
	public int GetCountOFReimbursements() throws ReimbursementNotFoundException {
		int retVal = 0;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			long count = (long)s.createQuery("SELECT COUNT(e) FROM ErsReimbursement e").getSingleResult();
			retVal = Math.toIntExact(count);
			System.out.println(count);
		}catch(Exception e)
		{
			System.out.println("No rows found, moving on.");
			return 0;
		}
		
		return retVal;
	}
	
	@Override
	public void AddNewReimbursement(String r_ID, String r_Name, String r_Type, String r_Price, String r_Descr, String r_Submitter) {
		ErsReimbursement n_Reim = new ErsReimbursement();
		n_Reim.setReimbId(Integer.valueOf(r_ID));
		n_Reim.setReimbReceipt(r_Name);
		n_Reim.setReimbDescription(r_Descr);
		n_Reim.setReimbAmount(Double.valueOf(r_Price));
		Date d = new Date();
		n_Reim.setReimbSubmitted(new Timestamp(d.getTime()));
		ErsReimbursementStatus status = new ErsReimbursementStatus();
		status.setReimbStatusId(0);
		n_Reim.setErsReimbursementStatus(status);
		ErsReimbursementType ers_Type = new ErsReimbursementType();
		ers_Type.setReimbTypeId(Integer.valueOf(r_Type));
		n_Reim.setErsReimbursementType(ers_Type);
		ErsUser submitter = new ErsUser();
		submitter.setErsUsersId(Integer.valueOf(r_Submitter));
		n_Reim.setErsUser1(submitter);
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			s.beginTransaction();
			s.save(n_Reim);
			s.getTransaction().commit();
		}
	}

	@Override
	public List<ErsReimbursement> GetAllReimbursementsForUser(String parameter) throws ReimbursementNotFoundException  {
		List<ErsReimbursement> retval = new ArrayList<ErsReimbursement>();
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			ErsUser user = new ErsUser();
			user.setErsUsersId(Integer.valueOf(parameter));
			String HQL = "from ErsReimbursement e WHERE e.ersUser1 = :username";
			TypedQuery<ErsReimbursement> tq = s.createQuery(HQL, ErsReimbursement.class);
			tq.setParameter("username", user);
		    retval = tq.getResultList();
		}
		return retval;
	}

	@Override
	public ErsReimbursement GetDetailsOn(String lookup) throws ReimbursementNotFoundException {
		ErsReimbursement retval = null;
		int lookup_id = Integer.valueOf(lookup);
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			String HQL = "from ErsReimbursement where reimbId = :reimb_ID";
			TypedQuery<ErsReimbursement> tq = s.createQuery(HQL, ErsReimbursement.class);
			tq.setParameter("reimb_ID", lookup_id);
			retval = tq.getSingleResult();
		}
		return retval;
	}

	@Override
	public List<ErsReimbursement> GetAllReimbursements() throws ReimbursementNotFoundException {
		List<ErsReimbursement> retval = new ArrayList<ErsReimbursement>();
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			String HQL = "from ErsReimbursement e";
			TypedQuery<ErsReimbursement> tq = s.createQuery(HQL, ErsReimbursement.class);
		    retval = tq.getResultList();
		}
		return retval;
	}

	@Override
	public void UpdateReimbursement(String r_ID, String r_Status, String r_Resolver) throws ReimbursementNotFoundException  {
		ErsReimbursement retval = null;
		int lookup_id = Integer.valueOf(r_ID);
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			String HQL = "from ErsReimbursement where reimbId = :reimb_ID";
			TypedQuery<ErsReimbursement> tq = s.createQuery(HQL, ErsReimbursement.class);
			tq.setParameter("reimb_ID", lookup_id);
			retval = tq.getSingleResult();
			System.out.println("Found Reimbursement....");
			ErsReimbursementStatus status = new ErsReimbursementStatus();
			HQL = "from ErsReimbursementStatus where reimb_status = :status";
			TypedQuery<ErsReimbursementStatus> eTQ = s.createQuery(HQL,ErsReimbursementStatus.class);
			eTQ.setParameter("status", r_Status);
			status = eTQ.getSingleResult();
			System.out.println("Found Status Code....");
			retval.setErsReimbursementStatus(status);
			ErsUser resolver = new ErsUser();
			resolver.setErsUsersId(Integer.valueOf(r_Resolver));
			retval.setErsUser2(resolver);
			System.out.println("Found Resolver...");
			Date d = new Date();
			retval.setReimbResolved(new Timestamp(d.getTime()));
			s.beginTransaction();
			s.save(retval);
			s.getTransaction().commit();
		}
		
	}
}
