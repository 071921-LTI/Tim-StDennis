package com.LTI.Project1.Impls;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.LTI.Project1.DAOs.AuthDAO;
import com.LTI.Project1.Exceptions.UserNotFoundException;
import com.LTI.Project1.Models.ErsUser;
import com.LTI.Project1.util.HibernateUtil;

public class AuthServiceImpl implements AuthDAO {

	@Override
	public ErsUser login(String username, String password) throws UserNotFoundException {
		
		ErsUser retVal = null;
		
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			String HQL = "from ErsUser where ersUsername = :username AND ersPassword = :password";
			TypedQuery<ErsUser> tq = s.createQuery(HQL, ErsUser.class);
			tq.setParameter("username", username);
			tq.setParameter("password", password);
			retVal = tq.getSingleResult();
			System.out.println(retVal.toString());
		}
		return retVal;
	}

	@Override
	public boolean authorize(String token) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

}
