package com.LTI.Project1.Delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LTI.Project1.Exceptions.ReimbursementNotFoundException;
import com.LTI.Project1.Impls.ReimbursementServiceImpl;
import com.LTI.Project1.Impls.UserServiceImpl;

public class ReimbursementDelegate implements Delegatable {

	private ReimbursementServiceImpl rsi = new ReimbursementServiceImpl();
	
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

	}

	@Override
	public void handlePut(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		// TODO Auto-generated method stub

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
