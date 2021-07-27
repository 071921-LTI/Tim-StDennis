package com.LTI.Project0.Controllers;

import com.LTI.Project0.exceptions.AuthException;
import com.LTI.Project0.exceptions.UserNotFoundException;
import com.LTI.Project0.models.Menu;
import com.LTI.Project0.models.User;

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
					RegisterNewCustomer();
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
		
		userName = sc.next();
		
		System.out.println("Please enter your password:");
		
		passWord = sc.next();
		
		/*
		 * Check the database if the UserName and Password are corrected.
		 * TODO: Check Database Logic
		 */
		try {
			User loggingIn = us.getUser(userName);
			loggingIn = new User(userName,passWord);
			if(as.login(loggingIn))
			{
				FrontPage.WhoAmI();
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
	
	private static void RegisterNewCustomer() {
		String firstName, lastName, middleName, n_UserName, n_Password, mICheck;
		
		System.out.println("What is your First Name?");
		firstName = sc.nextLine();
		System.out.println("What is your Last Name?");
		lastName = sc.nextLine();
		System.out.println("Do you have a Middle Name?(Y/N)");
		mICheck = sc.nextLine();
		if(mICheck.equals("Y"))
		{
			System.out.println("What is your Middle Name?");
			middleName = sc.nextLine();
		}
		System.out.println("Enter your User Name:");
		n_UserName = sc.nextLine();
		System.out.println("Enter your password:");
		n_Password = sc.nextLine();
		
	}
}
