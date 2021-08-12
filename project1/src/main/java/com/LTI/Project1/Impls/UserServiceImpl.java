package com.LTI.Project1.Impls;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.LTI.Project1.DAOs.UserDAO;
import com.LTI.Project1.Exceptions.UserNotFoundException;
import com.LTI.Project1.Models.ErsUser;
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

}
