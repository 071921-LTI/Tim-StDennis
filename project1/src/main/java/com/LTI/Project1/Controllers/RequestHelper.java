package com.LTI.Project1.Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LTI.Project1.Delegates.AuthDelegate;
import com.LTI.Project1.Delegates.ReimbursementDelegate;
import com.LTI.Project1.Delegates.UserDelegate;

public class RequestHelper {
	
	private UserDelegate ud = new UserDelegate();
	private AuthDelegate ad = new AuthDelegate();
	private ReimbursementDelegate rd = new ReimbursementDelegate();
	public static Logger log = LogManager.getRootLogger();
	
	public void process(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException {
		String path = rq.getPathInfo();
		
		// if no path is found send an error
		if (path != null)
		{	
			/*
			 * remove the initial "/"
			 * 	- path = "users/1"
			 */
			path = path.substring(1);
			/*
			 * if a "/" character is present in path, split at that character and returns the first element
			 * 	- path = "users"
			 * 	- setting a request attribute to the second part of the url: 1
			 */
			if(path.indexOf("/") != -1) {
				String[] paths = path.split("/");
				path = paths[0];
				rq.setAttribute("pathNext", paths[1]);
				log.info("Path Next = " + paths[1]);
				if(paths.length >= 3)
				{
					log.info("Path Second found: " + paths[2]);
					rq.setAttribute("pathSecond", paths[2]);
				}
					
			}
			switch(path) 
			{
				case "User":
				{
					log.info("Processing User");
					ud.process(rq, rs);
					break;
				}
				case "authorize":
				{
					log.info("Processing Authorization");
					ad.process(rq,rs);
					break;
				}
				case "Reimbursement":
				{
					log.info("Processing Reimbursement");
					rd.process(rq,rs);
					break;
				}
				default:
				{
					log.fatal("Website sent a path that is not supported.");
					rs.sendError(400, "Path not supported:" + path);
					break;
				}
			}
		}
		else 
		{
			log.fatal("No Path was found");
			rs.sendError(400, "No path found.");
		}
	}
}
