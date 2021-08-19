package com.LTI.Project1.Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LTI.Project1.util.Cors_Headerutil;

public class FrontController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private RequestHelper rh = new RequestHelper();
	public static Logger log = LogManager.getRootLogger();

	protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException{
		log.info("Processing Do Get");
		Cors_Headerutil.addCorsHeader(rq.getRequestURI(), rs);
		rh.process(rq, rs);
	}
	
	protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException{
		log.info("Processing Do Post");
		doGet(rq,rs);
	}
	
	protected void doPut(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException{
		log.info("Processing Do Put");
		doGet(rq,rs);
	}
	
	protected void doDelete(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException{
		log.info("Processing Do Delete");
		doGet(rq,rs);
	}
	
	protected void doOptions(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException{
		log.info("Processing Do Options");
		Cors_Headerutil.addCorsHeader(rq.getRequestURI(), rs);
		super.doOptions(rq,rs);
	}
	
}
