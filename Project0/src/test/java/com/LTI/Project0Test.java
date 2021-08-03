package com.LTI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.LTI.Project0.exceptions.AuthException;
import com.LTI.Project0.exceptions.UserNotFoundException;
import com.LTI.Project0.models.Item;
import com.LTI.Project0.models.Offer;
import com.LTI.Project0.models.User;
import com.LTI.Project0.services.AuthService;
import com.LTI.Project0.services.AuthServiceImpl;
import com.LTI.Project0.services.ItemService;
import com.LTI.Project0.services.ItemServiceImpl;
import com.LTI.Project0.services.OfferService;
import com.LTI.Project0.services.OfferServiceImpl;
import com.LTI.Project0.services.UserService;
import com.LTI.Project0.services.UserServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
public class Project0Test {

	protected static UserService us = new UserServiceImpl();
	protected static AuthService as = new AuthServiceImpl();
	protected static ItemService is = new ItemServiceImpl();
	protected static OfferService os = new OfferServiceImpl();

	
	
	@BeforeAll
	public static void setUp() {
		
	}
	
	@AfterAll
	public static void tearDown() {
		Item testItem = new Item(-1,"ItemTest","DummyItem",new BigDecimal(404),new BigDecimal(404),"NoOne");
		is.removeItem(testItem);
	}
	
	/*
	 * Authentication Service Implementation Tests.
	 */
	
	@Order(1)
	@Test
	public void Attempt_LoginSuccess() throws AuthException
	{
		//Test to make certain we login correctly.
		String user = "null", pass = "andVoid";
		boolean expected = true, actual = as.authenticateUser(user, pass);
		assertEquals(expected,actual,"This is the correct login credentials for the System Admin");
	}
	
	@Order(2)
	@Test
	public void Attempt_LoginFail()
	{
		//This one will use Assert Throws to throw an AuthException.
		String user = "fail", pass = "andVoid";
		assertThrows(AuthException.class, () -> as.authenticateUser(user, pass));
	}
	
	/*
	 * Item Service Implementation Tests
	 */
	
	@Order(2)
	@Test
	public void Attempt_GetItemByIdSuccess()
	{
		int iD = 1;
		boolean owned = false;
		Item test_Item = is.getItemByID(iD, owned);
		assertTrue(test_Item != null);
	}
	
	@Order(3)
	@Test
	public void Attempt_GetItemByIdFail()
	{
		int iD = 1;
		boolean owned = true;
		Item test_Item = is.getItemByID(iD, owned);
		assertNull(test_Item);
	}
	
	@Order(4)
	@Test
	public void Attempt_GetAllItems()
	{
		List<Item> items_InDB = null;
		items_InDB = is.getItems();
		assertTrue(items_InDB != null);
	}
	
	@Order(5)
	@Test
	public void Attempt_GetItemsOwned()
	{
		List<Item> items_InDB = null;
		items_InDB = is.getItems("N/A");
		assertTrue(items_InDB != null);
	}
	
	@Order(6)
	@Test
	public void Attempt_AddItem()
	{
		Item testItem = new Item(0,"ItemTest","DummyItem",new BigDecimal(404),new BigDecimal(404),"NoOne");
		int Expected = -1;
		int Actual = is.addItem(testItem);
		assertTrue(Actual > Expected);
	}
	
	
	@Order(7)
	@Test
	public void Attempt_EditItem()
	{
		Item testItem = new Item(0,"Changed ItemName","Changed Description",new BigDecimal(505),new BigDecimal(404),"NoOne");
		int Expected = 1;
		int Actual = is.editItem(testItem);
		assertEquals(Expected,Actual);
	}
	
	@Order(22)
	@Test
	public void Attempt_RemoveItem()
	{
		Item testItem = new Item(0,"ItemTest","DummyItem",new BigDecimal(404),new BigDecimal(404),"NoOne");
		int Expected = 1;
		int Actual = is.removeItem(testItem);
		assertEquals(Expected,Actual);
	}
	
	/*
	 * User Service Implementation Tests
	 */
	
	@Order(9)
	@Test
	public void Attempt_AddUser() throws SQLException
	{
		User testUser = new User("Test","Drone","TestCase","NoOne","CaresAboutthis","Test@Junit5.com");
		boolean Expected = true, actual = us.addUser(testUser);
		assertEquals(Expected,actual);
	}
	
	@Order(10)
	@Test
	public void Attempt_GetUserSuccess() throws UserNotFoundException
	{
		User testUser = null;
		String testName = "NoOne";
		testUser = us.getUser(testName);
		assertTrue(testUser != null);
	}
	
	@Order(11)
	@Test
	public void Attempt_GetUserFailure()
	{
		String testName = "NeverExisted";
		assertThrows(UserNotFoundException.class, () -> us.getUser(testName));
	}
	
	@Order(12)
	@Test
	public void Attempt_GetUserRoleSuccess() throws UserNotFoundException
	{
		String testName = "NoOne";
		String expected = "CUSTOMER", actual = us.GetRole(testName);
		assertEquals(expected,actual);
	}
	
	@Order(13)
	@Test
	public void Attempt_GetUserRoleFailure()
	{
		String testName = "NeverExisted";
		assertThrows(UserNotFoundException.class, () -> us.GetRole(testName));
	}
	
	/*
	 * Offer Service Implementation Tests
	 */
	
	@Order(14)
	@Test
	public void Attempt_OfferBid()
	{
		BigDecimal BuyOut = new BigDecimal(405);
		Item testItem = new Item(0,"Changed ItemName","Changed Description",new BigDecimal(505),new BigDecimal(404),"NoOne");
		int Expected = -1, actual = os.offerBid(BuyOut, testItem, "NoOne");
		assertTrue(actual > Expected);
	}
	
	@Order(15)
	@Test
	public void Attempt_OfferBidWithAutoReject()
	{
		BigDecimal BuyOut = new BigDecimal(1);
		Item testItem = new Item(0,"Changed ItemName","Changed Description",new BigDecimal(505),new BigDecimal(404),"NoOne");
		int Expected = -1, actual = os.offerBid(BuyOut, testItem, "NoOne2", true);
		assertTrue(actual > Expected);
	}
	
	@Order(16)
	@Test
	public void Attempt_RejectBid()
	{
		Item testItem = new Item(0,"Changed ItemName","Changed Description",new BigDecimal(505),new BigDecimal(404),"NoOne");
		int Expected = 1, actual = os.rejectBid(testItem, "NoOne");
		assertEquals(Expected, actual);
	}
	
	@Order(17)
	@Test
	public void Attempt_BuyOutAndRejectAllOtherBids()
	{
		BigDecimal BuyOut = new BigDecimal(505);
		Item testItem = new Item(0,"Changed ItemName","Changed Description",new BigDecimal(505),new BigDecimal(404),"NoOne");
		int Expected = 1, actual = os.addTransaction(BuyOut, testItem, "NoOne");
		assertEquals(Expected,actual);
	}

	@Order(18)
	@Test
	public void Attempt_AcceptBid()
	{
		BigDecimal BuyOut = new BigDecimal(405);
		Item testItem = new Item(-1,"ItemTest","DummyItem",new BigDecimal(404),new BigDecimal(404),"NoOne");
		is.addItem(testItem);
		os.offerBid(BuyOut, testItem, "NoOne");
		int Expected = 1, actual = os.acceptBid(testItem, "NoOne");
		assertTrue(actual >= Expected);
	}
	
	@Order(19)
	@Test
	public void Attempt_GetWeeklyPayments()
	{
		Item testItem = new Item(-1,"ItemTest","DummyItem",new BigDecimal(404),new BigDecimal(404),"NoOne");
		BigDecimal expected = new BigDecimal(-1.00), actual = os.getWeeklyPayment(testItem);
		assertTrue(actual.compareTo(expected) == 1);
	}
	
	@Order(20)
	@Test
	public void Attempt_GetOfferByID()
	{
		Offer test_Offer = null;
		test_Offer = os.getOfferByID(13);
		assertTrue(test_Offer != null);
	}
	
	@Order(21)
	@Test
	public void Attempt_GetPendingOffers()
	{
		List<Offer> listofOffers = null;
		listofOffers = os.getAllPEndingOffers();
		assertTrue(listofOffers != null);
	}
	
}
