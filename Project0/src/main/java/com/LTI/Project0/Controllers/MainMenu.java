package com.LTI.Project0.Controllers;

import java.sql.SQLException;

import com.LTI.Project0.exceptions.AuthException;
import com.LTI.Project0.exceptions.UserNotFoundException;
import com.LTI.Project0.models.Menu;
import com.LTI.Project0.models.User;
import com.LTI.Project0.services.UserService;
import com.LTI.Project0.services.UserServiceImpl;

public class MainMenu extends Menu {
	
	public static void Display()
	{
		String in = "0";
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
					//Log In Existing User
					LogInExisting();
					break;
				}
				case "2": 
				{
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
					//Quit Application
					System.out.println("Thank you for visiting Tabletop & More! Have a nice day!");
					break;
				}					
				default: 
				{
					//Incorrect input catch-all.
					System.out.println("Invalid Input");
					break;
				}
			}
		}while(!in.equals("3"));
	}

	private static void LogInExisting() {
		String userName, passWord;
		
		System.out.println("Please enter your User Name:");
		
		userName = sc.nextLine();
		
		System.out.println("Please enter your password:");
		
		passWord = sc.nextLine();
		
		/*
		 * Check the database if the UserName and Password are corrected.
		 * TODO: Check Database Logic
		 */
		try {
			boolean IsUser = as.authenticateUser(userName, passWord);
			if(IsUser)
			{
				FrontPage.Role="SYSTEM";
				FrontPage.WhoAmI();
				//User exist_User = us.getUser(userName);
				//FrontPage.WhoAmI();
			}
		}
		catch (UserNotFoundException unf_EX)
		{
			System.out.println("The User Name and/or PassWord is incorrect.");	
		}
		catch (AuthException au_EX)
		{
			System.out.println("The User Name and/or PassWord is incorrect.");	
		}	
	}
	
	private static void RegisterNewCustomer() throws SQLException {
		
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
			System.out.println("Welcome " + firstName + "! You have successfully been registered!");
			FrontPage.Role = "CUSTOMER";
			FrontPage.WhoAmI();
		}
		else
		{
			//Log Error here.
		}
	}
}
