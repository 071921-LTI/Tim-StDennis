package com.LTI.Project1.Impls;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.LTI.Project1.DAOs.UserDAO;
import com.LTI.Project1.Exceptions.UserNotFoundException;
import com.LTI.Project1.Models.ErsUser;
import com.LTI.Project1.Models.ErsUserRole;
import com.LTI.Project1.util.HibernateUtil;

public class UserServiceImpl implements UserDAO {

	@Override
	public ErsUser FindUserById(String iD) throws UserNotFoundException {
		ErsUser retval = null;
		int nt_iD = Integer.valueOf(iD);
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			String HQL = "from ErsUser where ersUsersId = :user_ID";
			TypedQuery<ErsUser> tq = s.createQuery(HQL, ErsUser.class);
			tq.setParameter("user_ID", nt_iD);
			retval = tq.getSingleResult();
		}
		return retval;

	}

	@Override
	public ErsUser UpdateUserWithInfo(String iD, String[] info) throws UserNotFoundException {
		int nt_iD = Integer.valueOf(iD);
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

}
