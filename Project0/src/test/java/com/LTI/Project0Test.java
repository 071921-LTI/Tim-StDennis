package com.LTI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.LTI.Project0.models.User;
public class Project0Test {

	private static User user;
	
	@BeforeAll
	public static void setUp() {
		user = new User("Test", "test");
	}
	
	@AfterAll
	public static void tearDown() {
		
	}
	
	@Order(1)
	@Test
	public void AttemptLogin_Success() 
	{
		
	}
	
	@Order(2)
	@Test
	public void AttemptLogin_Fail()
	{
		//This one will use Assert Throws to throw an AuthException.
	}
	
	
	
}
