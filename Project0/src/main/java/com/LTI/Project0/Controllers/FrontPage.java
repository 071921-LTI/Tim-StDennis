package com.LTI.Project0.Controllers;

import com.LTI.Project0.models.Menu;

public class FrontPage extends Menu {
	
	static String Role, In;
	
	public static void WhoAmI()
	{
		//TODO: Set Role based on entry in database.
		switch(Role)
		{
			case "Customer":
				CustomerFrontPage();
				break;
			case "Employee":
				EmployeeFrontPage();
				break;
			case "Manager":
				ManagerFrontPage();
				break;
			default: //This should NEVER hit.
				break;
		}
	}

	
	private static void CustomerFrontPage() {
		// TODO Auto-generated method stub
		do
		{
			DisplayOptions("Welcome! Please select an option:", 
				"1:)Browse Items",
				"2:)View Items Purchased",
				"3:)Log Off");
			In = sc.nextLine();
			switch(In)
			{
				case "1":
					break;
				case "2":
					break;
				case "3":
					System.out.println("Have a nice day!");
					break;
				default: 
				{
					//Incorrect input catch-all.
					System.out.println("Invalid Input");
					break;
				}
			}
		}while(!In.equals("3"));	
	}				

	private static void ManagerFrontPage() {
		// TODO Auto-generated method stub
		do
		{
			DisplayOptions("Welcome! Please select an option:", 
				"1:)Create a New Employee account",
				"2:)Fire an Employee",
				"3:)View Payment History",
				"4:)Log Off");
			In = sc.nextLine();
			switch(In)
			{
				case "1":
					break;
				case "2":
					break;
				case "3":
					break;
				case "4":
					System.out.println("Have a nice day!");
					break;
				default: 
				{
					//Incorrect input catch-all.
					System.out.println("Invalid Input");
					break;
				}
			}
		}while(!In.equals("4"));
		
	}
	private static void EmployeeFrontPage() {
		// TODO Auto-generated method stub
		do
		{
			DisplayOptions("Welcome! Please select an option:", 
				"1:)Check Pending Offers",
				"2:)Enter the I.M.S(Item Management System)",
				"3:)View Payment History",
				"4:)Log Off");
			In = sc.nextLine();
			switch(In)
			{
				case "1":
					break;
				case "2":
					EnterIMS();
					break;
				case "3":
					break;
				case "4":
					System.out.println("Have a nice day!");
					break;
				default: 
				{
					//Incorrect input catch-all.
					System.out.println("Invalid Input");
					break;
				}
			}
		}while(!In.equals("4"));
	}


	private static void EnterIMS() {
		// TODO Auto-generated method stub
		do
		{
			DisplayOptions("Please enter an action:", 
				"1:)Add New Item",
				"2:)Remove an Item",
				"4:)Return to Main Menu");
			In = sc.nextLine();
			switch(In)
			{
				case "1":
					break;
				case "2":
					break;
				case "3":
					break;
				default: 
				{
					//Incorrect input catch-all.
					System.out.println("Invalid Input");
					break;
				}
			}
		}while(!In.equals("3"));
		
	}
	
}
