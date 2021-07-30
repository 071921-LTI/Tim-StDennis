package com.LTI.Project0.daos;

public class DAOFactory {

	private static DAOFactory df;
	private static ItemPostgres ipg;
	private static UserPostgres upg;
	private DAOFactory() {
	}
	
	public synchronized static DAOFactory getDF() {
		if(df == null)
			df = new DAOFactory();
		return df;
	}
	
	public synchronized static ItemDao getItemDAO() {
		if(ipg == null)
			ipg = new ItemPostgres();
		return ipg;
	}
	
	public synchronized static UserDao getUserDAO() {
		if(upg == null)
			upg = new UserPostgres();
		return upg;
	}
	
}
