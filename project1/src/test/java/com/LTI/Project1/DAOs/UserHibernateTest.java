package com.LTI.Project1.DAOs;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.LTI.Project1.Exceptions.UserNotFoundException;
import com.LTI.Project1.Impls.ReimbursementServiceImpl;
import com.LTI.Project1.Impls.UserServiceImpl;
import com.LTI.Project1.Models.*;
import com.LTI.Project1.util.*;


@TestMethodOrder(OrderAnnotation.class)
public class UserHibernateTest {

	@BeforeAll
	public static void init() throws SQLException {
	}

	@AfterAll
	public static void end() {
	}

	UserDAO usi = new UserServiceImpl();
		
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
	//Get User ID 1 from DB
	public void GetUserFromID() {
		try {
			ErsUser actual = usi.FindUserById("1");
			assertNotNull(actual);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	//Find All Users in DB, should not be null
	public void GetAllUsers() {
		try {
			assertNotNull(usi.FindAllUsers());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}