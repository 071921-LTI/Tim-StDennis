package com.LTI.Project0.Controllers;

import java.math.BigDecimal;
import java.util.List;

import com.LTI.Project0.models.Item;
import com.LTI.Project0.models.Menu;

public class FrontPage extends Menu {
	
	static String In;
	private static int incre=0, limit=10;
	public static void WhoAmI(String Role)
	{
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

	//Front Pages
	
	private static void AdminFrontPage() {
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

	
	private static void BrowseItems() {
		List<Item> itemsAvailable = is.getItems();
		do
		{
			for(int i = incre; i < limit; i++)
			{
				try
				{
					System.out.println(itemsAvailable.get(i).toString());
				}
				catch(IndexOutOfBoundsException ndx_EX)
				{
					//Log it.
					break;
				}
					
			}
			DisplayOptions("Browsing Screen", 
					"1:)Next 10.", 
					"2:)Previous 10.", 
					"3:)Search for specific ID",
					"4:)Select Item",
					"5:)Back to Main Menu");
			In = sc.nextLine();
			switch(In)
			{
				case "1":
					Show10(itemsAvailable.size(), "NEXT");
					break;
				case "2":
					Show10(itemsAvailable.size(), "PREVIOUS");
					break;
				case "3":
					SearchMenu();
					break;
				case "4":
					InspectItem(itemsAvailable);
					break;
				default:
					System.out.println("Invalid input");
					break;
			}
		}while(!In.equals("5"));
		
	}
	
	private static void InspectItem(List<Item> itemsAvailable) {
		String ins_ID = "0", opt_in = "0";
		
		System.out.println("Enter the ID of the item you want to look at.");
		ins_ID = sc.nextLine();
		int InspectedID = Integer.valueOf(ins_ID);
		InspectedID--;
		if(!itemsAvailable.get(InspectedID).equals(null))
		{
			Item selected = itemsAvailable.get(InspectedID);
			do
			{
				System.out.println(selected.toString());
				DisplayOptions("What would you like to do?",
						"1:)Buy it out",
						"2:)Bid on it",
						"3:)Exit");
				opt_in = sc.nextLine();
				switch(opt_in)
				{
					case "1":
						//is.addTransaction
						break;
					case "2":
						//is.addOffer
						break;
					case "3":
						break;
					default:
						System.out.println("Invalid input");
						break;
				}
			}while(!opt_in.equals("3"));
		}
		else
		{
			System.out.println("The item could not be found, or is not in the system.");
		}
	}
	private static void InspectItem(Item selectedItem) {
		String opt_in = "0";
		do
		{
			System.out.println(selectedItem.toString());
			DisplayOptions("What would you like to do?",
					"1:)Buy it out",
					"2:)Bid on it",
					"3:)Exit");
			opt_in = sc.nextLine();
			switch(opt_in)
			{
				case "1":
					//is.addTransaction
					break;
				case "2":
					//is.addOffer
					break;
				case "3":
					break;
				default:
					System.out.println("Invalid input");
					break;
			}
		}while(!opt_in.equals("3"));
	}

	private static void SearchMenu() {
		String in_ID = "0";
		System.out.println("Please enter the ID you would like to search for");
		in_ID = sc.nextLine();
		Item searched_Item = is.getItemByID(Integer.valueOf(in_ID));
		if(searched_Item == null)
		{
			System.out.println("Sorry, that item does not exist.");
		}
		else
		{
			System.out.println("Item Found:");
			InspectItem(searched_Item);
		}
	}

	private static void Show10(int size, String dir) {
		switch(dir)
		{
			case "NEXT":
				incre+=10;
				limit+=10;
				break;
			case "PREVIOUS":
				incre-=10;
				limit-=10;
				break;
			default:
				break;
		}	
		if(incre >= size)
		{
			incre -= 10;
			limit -= 10;
		}
		if(incre <= 0)
		{
			incre = 0;
			limit = 10;
		}
	}



	private static void EnterIMS() {
		// TODO Auto-generated method stub
		do
		{
			DisplayOptions("Please enter an action:", 
				"1:)Add New Item",
				"2:)Remove an Item",
				"3:)Edit an Item",
				"4:)Return to Main Menu");
			In = sc.nextLine();
			switch(In)
			{
				case "1":
					AddNewItem();
					break;
				case "2":
					RemoveItem();
					break;
				case "3":
					EditItem();
					break;
				case "4":
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

	private static void RemoveItem() {
		System.out.println("Enter the ID of the item you wish to remove");
		In = sc.nextLine();
		Item to_Remove = is.getItemByID(Integer.valueOf(In));
		System.out.println(to_Remove);
		System.out.println("Is this the item you want removed?(Y/N)");
		In = sc.nextLine();
		if(In == "Y" || In == "y")
		{
			System.out.println("Understood.");
			is.removeItem(to_Remove);
		}
		else if(In == "N" || In == "n")
		{
			System.out.println("Returning to menu");
		}	
	}

	private static void AddNewItem() {
		System.out.println("Please enter the following details:");
		int id = is.getItems().size();
		String item_Name, item_Descr;
		BigDecimal item_otPrice, item_wkPrice;
		System.out.println("Name of the Item:");
		In = sc.nextLine();
		item_Name = In;
		System.out.println("Description of the Item:");
		In = sc.nextLine();
		item_Descr = In;
		System.out.println("Buy Out Price:");
		item_otPrice = sc.nextBigDecimal();
		System.out.println("Weekly Price:");
		item_wkPrice = sc.nextBigDecimal();
		Item add_Item = new Item(id,item_Name,item_Descr,item_otPrice,item_wkPrice);
		is.addItem(add_Item);
	}

	private static void EditItem() {
		System.out.println("Please enter the ID of the item you want to update.");
		In = sc.nextLine();
		int edited = Integer.valueOf(In);
		Item to_Edit = is.getItemByID(edited);
		if(to_Edit.equals(null))
		{
			System.out.println("Incorrect ID");
		}
		else
			is.editItem(to_Edit);
	}
	
}
