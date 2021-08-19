package com.LTI.Project1.DAOs;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.LTI.Project1.Exceptions.ReimbursementNotFoundException;
import com.LTI.Project1.Impls.ReimbursementServiceImpl;
import com.LTI.Project1.Models.*;
import com.LTI.Project1.util.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class ReimbursementHibernateTest {

	@BeforeAll
	public static void init() throws SQLException {
	}

	@AfterAll
	public static void end() {
	}

	ReimbursementDAO rd = new ReimbursementServiceImpl();
	
	ErsReimbursementStatus PENDING = new ErsReimbursementStatus(0,"PENDING");
	ErsReimbursementStatus ACCEPT = new ErsReimbursementStatus(1,"ACCEPTED");
	ErsReimbursementStatus REJECT = new ErsReimbursementStatus(2,"REJECTED");
	ErsReimbursementType LODGE = new ErsReimbursementType(0,"LODGING");
	ErsReimbursementType TRAVEL = new ErsReimbursementType(1,"TRAVEL");
	ErsReimbursementType FOOD = new ErsReimbursementType(2,"FOOD");
	ErsReimbursementType OTHER = new ErsReimbursementType(3,"OTHER");
	ErsUserRole empl = new ErsUserRole(1,"EMPLOYEE");
	ErsUserRole man = new ErsUserRole(2, "MANAGER");
	ErsUser emplUser = new ErsUser(1,"alpha","Test","Test@alpha.com","Test","Computer",empl);
	ErsUser manUser = new ErsUser(2,"manager","TestMan","TestMan@ERiS.com","Important","Manager",man);
	
	@Test
	@Order(1)
	//Check the connection
	public void connectionTest() {
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			assertNotNull(s);
		}
	}
	
	@Test
	@Order(2)
	//Because Add does not return a Reimbursement, add one, then get it.
	public void addAndGetReimb() {
		rd.AddNewReimbursement("-1", "Dummy", LODGE.getReimbTypeId().toString(), "10", "TEst", emplUser.getErsUsersId().toString());
		try {
			ErsReimbursement expected = rd.GetDetailsOn("-1");
			assertNotNull(expected);
		} catch (ReimbursementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	//Get the number of Reimbs. There are some already in the DB, so it should NOT be 0.
	public void GetCountOfReimb() {
		try {
			int Actual = rd.GetCountOFReimbursements();
			int Unexpected = 0;
			assertNotSame(Unexpected,Actual);
		} catch (ReimbursementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Order(4)
	//Get the Reimbs of User ID 1, there are some already attached to them, so it should NOT be null.
	public void GetReimbOfUser() {
		try {
			List<ErsReimbursement> usersReimb = rd.GetAllReimbursementsForUser(emplUser.getErsUsersId().toString());
			assertNotNull(usersReimb);
		} catch (ReimbursementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}