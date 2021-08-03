package com.LTI.Project0.Controllers;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LTI.Project0.exceptions.AuthException;
import com.LTI.Project0.exceptions.UserNotFoundException;
import com.LTI.Project0.models.Menu;
import com.LTI.Project0.models.User;
import com.LTI.Project0.services.UserService;
import com.LTI.Project0.services.UserServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainMenu extends Menu {
	
	public static Logger log = LogManager.getRootLogger();
	
	public static void Display() throws UserNotFoundException
	{
		String in = "0";
		log.info("Entered Display(Main Menu)");
		do
		{
			DisplayOptions("Please select an option:", 
							"1:)Log In",
							"2:)Register New User",
							"3:)Quit Application" );
			in = sc.nextLine();
			switch(in)
			{
				case "1": 
				{
					log.info("User selected to Log In");
					//Log In Existing User
					LogInExisting();
					break;
				}
				case "2": 
				{
					log.info("User selected to register as a customer");
					//Register New User as Customer
					try {
						RegisterNewCustomer();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}	
				case "3":
				{
					log.info("User selected to exit App");
					//Quit Application
					System.out.println("Thank you for visiting Tabletop & More! Have a nice day!");
					break;
				}					
			}
			logged_In_UserName = " ";
		}while(!in.equals("3"));
	}

	private static void LogInExisting() {
		String userName, passWord;
		
		System.out.println("Please enter your User Name:");
		
		userName = sc.nextLine();
		
		System.out.println("Please enter your password:");
		
		passWord = sc.nextLine();
		
		/*
		 * Check the database if the UserName and Password are correct.
		 */
		log.debug("User entered values:" + userName + "-" + passWord);
		try {
			boolean IsUser = as.authenticateUser(userName, passWord);
			if(IsUser)
			{
				log.info("User entered correct credentials");
				logged_In_UserName = userName;
				FrontPage.WhoAmI(us.getUser(logged_In_UserName).getRole());
			}
		}
		catch (AuthException au_EX)
		{
			log.warn("User entered incorrect credentials");
			System.out.println("The User Name and/or PassWord is incorrect.");	
		}	
	}
	
	private static void RegisterNewCustomer() throws SQLException, UserNotFoundException {
		
		String firstName, lastName, n_UserName, n_Password, n_Email;
		
		System.out.println("What is your First Name?");
		firstName = sc.nextLine();
		System.out.println("What is your Last Name?");
		lastName = sc.nextLine();
		System.out.println("Enter your User Name:");
		n_UserName = sc.nextLine();
		System.out.println("Enter your password:");
		n_Password = sc.nextLine();
		System.out.println("Enter your email:");
		n_Email = sc.nextLine();
		if(us.addUser(new User(firstName, lastName, "CUSTOMER", n_UserName,n_Password, n_Email)))
		{
			//Success. Bring the new customer to entering payment information.
			log.info("User has successfully registered themselves[User Name:" + n_UserName + "]");
			System.out.println("Welcome " + firstName + "! You have successfully been registered!");
			logged_In_UserName = n_UserName;
			FrontPage.WhoAmI(us.getUser(logged_In_UserName).getRole());
		}
		else
		{
			log.error("Program has encountered an error with registering a new user");
			//Log Error here.
		}
	}
}
