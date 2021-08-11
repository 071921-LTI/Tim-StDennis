package com.LTI.Project1.Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LTI.Project1.util.Cors_Headerutil;

public class FrontController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private RequestHelper rh = new RequestHelper();
	
	protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException{
		Cors_Headerutil.addCorsHeader(rq.getRequestURI(), rs);
		rh.process(rq, rs);
	}
	
	protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException{
		doGet(rq,rs);
	}
	
	protected void doPut(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException{
		doGet(rq,rs);
	}
	
	protected void doDelete(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException{
		doGet(rq,rs);
	}
	
	protected void doOptions(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException{
		Cors_Headerutil.addCorsHeader(rq.getRequestURI(), rs);
		super.doOptions(rq,rs);
	}
	
}
