package com.LTI.Project1.Delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LTI.Project1.Exceptions.UserNotFoundException;
import com.LTI.Project1.Impls.AuthServiceImpl;
import com.LTI.Project1.Models.ErsUser;

public class AuthDelegate implements Delegatable {

	private AuthServiceImpl as = new AuthServiceImpl();
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
		// TODO Auto-generated method stub

	}

	@Override
	public void handlePut(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void handlePost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		log.info("Attempting to Authenticate");
		String username = rq.getParameter("username");
		String password = rq.getParameter("password");

		try {
			ErsUser user = as.login(username, password);
			if (user != null) {
				String token = user.getErsUsersId() + ":" + user.getErsUserRole().getErsUserRoleId();
				rs.setHeader("Authorization", token);
				rs.setStatus(200);
				log.info("Authentication Sucessful");
			} else {

			}
		} catch (UserNotFoundException e) {
			log.error("Failed to Authenticate. Wrong UserName/Password?");
			rs.sendError(404);
		}
	}

	@Override
	public void handleDelete(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
