package com.LTI.Project1.Delegates;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LTI.Project1.Exceptions.ReimbursementNotFoundException;
import com.LTI.Project1.Impls.ReimbursementServiceImpl;
import com.LTI.Project1.Impls.UserServiceImpl;
import com.LTI.Project1.Models.ErsReimbursement;

public class ReimbursementDelegate implements Delegatable {

	private ReimbursementServiceImpl rsi = new ReimbursementServiceImpl();
	
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
		String state = rq.getAttribute("pathNext").toString();
		System.out.println(state);
		if(state.equals("All"))
		{
			log.info("Path Next: All Short Details");
			System.out.println("In All");
			try {
				int AllRecords = rsi.GetCountOFReimbursements();
				System.out.println(AllRecords);
				rs.setHeader("ifo_ReimRecords", String.valueOf(AllRecords));
				rs.setStatus(200);
			}catch(ReimbursementNotFoundException ex)
			{
				ex.printStackTrace();
			}
		}
		else if(state.equals("All-Details"))
		{
			log.info("Path Next: All Full Details");
			System.out.println("All Details");
			String hdr = rq.getHeader("Authorization");
			String[] user = hdr.split(":");
			try {
				List<ErsReimbursement> reimbursements = null;
				if(user[1].equals("1"))
				{
					reimbursements = rsi.GetAllReimbursementsForUser(user[0]);
				}
				else if(user[1].equals("2"))
				{
					reimbursements = rsi.GetAllReimbursements();
				}
				 
				String shortList = "";
				for(ErsReimbursement ers : reimbursements)
				{
					System.out.println("Adding " + ers.toShortString());
					shortList += ers.toShortString();
				}
				rs.setHeader("ifo_ShortReimList", shortList);
			}catch(ReimbursementNotFoundException ex)
			{
				ex.printStackTrace();
			}
		}
		else if(state.equals("Details"))
		{
			
			String lookup = rq.getAttribute("pathSecond").toString();
			System.out.println("Details on " + lookup);
			log.info("Path Next: Details On " + lookup);
			try {
				ErsReimbursement FullDetails = rsi.GetDetailsOn(lookup);
				System.out.println(FullDetails.ToDetailedString());
				rs.setHeader("ifo_DetailedReimList", FullDetails.ToDetailedString());
				rs.setStatus(200);
			}catch(ReimbursementNotFoundException ex)
			{
				ex.printStackTrace();
			}
		}

	}

	@Override
	public void handlePut(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		
	}

	@Override
	public void handlePost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		String state = rq.getAttribute("pathNext").toString();
		if(state.equals("Submit"))
		{
			System.out.println(state);
			SubmitNewReimbursement(rq, rs);
		}
		else if(state.equals("Update"))
		{
			System.out.println(state);
			UpdateReimbursement(rq,rs);
		}
		

	}

	private void UpdateReimbursement(HttpServletRequest rq, HttpServletResponse rs) {
		try {
			log.info("Updating Reimbursement");
			String R_ID = rq.getParameter("ReimID"),R_Status = rq.getParameter("ReimStatus"),R_Resolver= rq.getParameter("ReimResolver");
			rsi.UpdateReimbursement(R_ID,R_Status,R_Resolver);
			rs.setStatus(200);
		} catch(Exception ex) {
			log.error("What happened??");
			ex.printStackTrace();
		}
		
	}

	private void SubmitNewReimbursement(HttpServletRequest rq, HttpServletResponse rs) {
		try {
			log.info("Creating new Reimbursement");
			String R_ID = rq.getParameter("ReimID"),R_Name = rq.getParameter("ReimName"),R_Type = rq.getParameter("ReimType"),
						R_Price = rq.getParameter("ReimPrice"), R_Descr = rq.getParameter("ReimDescr"), R_Submitter = rq.getParameter("ReimSubmitter");
			rsi.AddNewReimbursement(R_ID,R_Name,R_Type,R_Price,R_Descr,R_Submitter);
			rs.setStatus(200);
		} catch(Exception ex) {
			log.error("What happened??");
			ex.printStackTrace();
		}
		
	}

	@Override
	public void handleDelete(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
