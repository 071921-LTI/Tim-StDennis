package com.LTI.Project1.Delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LTI.Project1.Exceptions.UserNotFoundException;
import com.LTI.Project1.Impls.UserServiceImpl;
import com.LTI.Project1.Models.ErsReimbursement;
import com.LTI.Project1.Models.ErsUser;

public class UserDelegate implements Delegatable{

	private UserServiceImpl us = new UserServiceImpl();
	public static Logger log = LogManager.getRootLogger();
	
	@Override
	public void process(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String method = rq.getMethod();
		
		switch(method)
		{
			case "GET":
				handleGet(rq,rs);
				break;
			case "PUT":
				handlePut(rq,rs);
				break;
			case "POST":
				handlePost(rq,rs);
				break;
			case "DELETE":
				handleDelete(rq,rs);
				break;
			default:
				rs.sendError(405);
				break;
		}
		
	}

	@Override
	public void handleGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String iD = rq.getAttribute("pathNext").toString();
		if(iD.equals("All"))
		{
			log.info("Processing All Users GET");
			System.out.println("Getting All Users");
			GetAllUsers(rq,rs);
		}
		else
		{
			log.info("Processing Single User GET");
			System.out.println("Getting User Details");
			GetUserDetail(rq,rs);
		}
	}

	private void GetAllUsers(HttpServletRequest rq, HttpServletResponse rs) {
		try {
			List<ErsUser> foundUsers = us.FindAllUsers();
			String shortList = "";
			for(ErsUser ers : foundUsers)
			{
				System.out.println("Adding " + ers.toStringDetailed());
				shortList += ers.toStringDetailed();
			}
			rs.setHeader("ifo_EmployeeList", shortList);
		} catch(UserNotFoundException ex)
		{
			ex.printStackTrace();
		}
		
	}

	private void GetUserDetail(HttpServletRequest rq, HttpServletResponse rs) {
		String iD = rq.getAttribute("pathNext").toString();
		try {
			ErsUser foundUser = us.FindUserById(iD);
		    rs.setHeader("ifo_UserInfo", "yes");
		    rs.setHeader("ifo_UserInfo", foundUser.getUserFirstName() + ",\n" +
		    			  foundUser.getUserLastName() + ",\n" +
		    			  foundUser.getErsUsername() + ",\n" +
		    			  foundUser.getErsPassword() + ",\n" +
		    			  foundUser.getUserEmail());
			System.out.println(foundUser);
			rs.setStatus(200);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void handlePut(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String iD = rq.getAttribute("pathNext").toString(), info = "";
		info = AttemptRead(rq.getReader());
		String[] information = info.split("\n");
		try {
			ErsUser upd = us.UpdateUserWithInfo(iD,information);
			String token = upd.getErsUsersId() + ":" + upd.getErsUserRole();
			rs.setHeader("Authorization", token);
			rs.setStatus(200);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private String AttemptRead(BufferedReader reader) throws IOException {
		StringBuffer retVal = new StringBuffer();
		BufferedReader br = null;
		try {
	        br =  reader;
	        char[] charBuffer = new char[128];
	        int bytesRead;
	        while ( (bytesRead = br.read(charBuffer)) != -1 ) {
	            retVal.append(charBuffer, 0, bytesRead);
	        }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }
		return retVal.toString();
	}

	@Override
	public void handlePost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleDelete(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
