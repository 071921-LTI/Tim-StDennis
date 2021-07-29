package com.LTI.Project0.Controllers;

import java.util.List;

import com.LTI.Project0.models.Item;
import com.LTI.Project0.models.Menu;

public class FrontPage extends Menu {
	
	static String Role, In;
	
	public static void WhoAmI()
	{
		//Role = us.GetRole();
		//TODO: Set Role based on entry in database.
		switch(Role)
		{
			case "CUSTOMER":
				CustomerFrontPage();
				break;
			case "EMPLOYEE":
				EmployeeFrontPage();
				break;
			case "MANAGER":
				ManagerFrontPage();
				break;
			case "SYSTEM":
				AdminFrontPage();
				break;
			default: //This should NEVER hit.
				break;
		}
	}

	
	private static void AdminFrontPage() {
		// TODO Auto-generated method stub
		do
		{
			DisplayOptions("Welcome System Admin. Please select an option:",
					"1:)View as Customer",
					"2:)View as Employee",
					"3:)View as Manager",
					"4:)Log Off");
			In = sc.nextLine();
			switch(In)
			{
				case "1":
					CustomerFrontPage();
					break;
				case "2":
					EmployeeFrontPage();
					break;
				case "3":
					ManagerFrontPage();
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
					BrowseItems();
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

	private static void BrowseItems() {
		// TODO Auto-generated method stub
		List<Item> itemsAvailable = is.getItems();
		
		for(Item i : itemsAvailable)
		{
			System.out.println(i.toString());
		}
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
