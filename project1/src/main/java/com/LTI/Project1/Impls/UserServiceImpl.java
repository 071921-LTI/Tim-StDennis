package com.LTI.Project1.Impls;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.LTI.Project1.DAOs.UserDAO;
import com.LTI.Project1.Exceptions.UserNotFoundException;
import com.LTI.Project1.Models.ErsUser;
import com.LTI.Project1.Models.ErsUserRole;
import com.LTI.Project1.util.HibernateUtil;

public class UserServiceImpl implements UserDAO {

	public static Logger log = LogManager.getRootLogger();
	
	@Override
	public ErsUser FindUserById(String iD) throws UserNotFoundException {
		ErsUser retval = null;
		log.info("Attempting to get a User by ID " + iD);
		int nt_iD = Integer.valueOf(iD);
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			String HQL = "from ErsUser where ersUsersId = :user_ID";
			TypedQuery<ErsUser> tq = s.createQuery(HQL, ErsUser.class);
			tq.setParameter("user_ID", nt_iD);
			retval = tq.getSingleResult();
		}
		log.info("Attempting to get the number of reimbursements in DB");
		return retval;

	}

	@Override
	public ErsUser UpdateUserWithInfo(String iD, String[] info) throws UserNotFoundException {
		int nt_iD = Integer.valueOf(iD);
		log.info("Attempting to update a User by ID " + iD);
		ErsUser t = new ErsUser();
		t.setErsUsersId(nt_iD);
		t.setUserFirstName(ParseOut(info,"First Name"));
		t.setUserLastName(ParseOut(info, "Last Name"));
		t.setErsUsername(ParseOut(info,"User Name"));
		t.setErsPassword(ParseOut(info,"Password"));
		t.setUserEmail(ParseOut(info,"Email"));
		ErsUserRole role = new ErsUserRole();
		role.setErsUserRoleId(Integer.valueOf(ParseOut(info,"RoleID")));
		t.setErsUserRole(role);
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		}
		log.info("Update Successful!");
		return t;
	}

	private String ParseOut(String[] info, String Search) {
		String infoToSearch = " ";
		for(String st : info)
		{
			if(st.contains(Search))
			{
				infoToSearch = st;
				break;
			}	
		}
		String val = infoToSearch.split(":")[1];
		System.out.println(val);
		return val;
	}

	@Override
	public List<ErsUser> FindAllUsers() throws UserNotFoundException{
		log.info("Attempting to get a list of Users");
		List<ErsUser> retval = new ArrayList<ErsUser>();
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			String HQL = "from ErsUser e WHERE e.ersUserRole = :role";
			ErsUserRole role = new ErsUserRole();
			role.setErsUserRoleId(1);
			TypedQuery<ErsUser> tq = s.createQuery(HQL, ErsUser.class);
			tq.setParameter("role", role);
		    retval = tq.getResultList();
		}
		log.info("Successfully acquired a list!");
		return retval;
	}

}
