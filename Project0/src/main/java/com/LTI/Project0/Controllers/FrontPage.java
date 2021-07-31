package com.LTI.Project0.Controllers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import com.LTI.Project0.exceptions.UserNotFoundException;
import com.LTI.Project0.models.Item;
import com.LTI.Project0.models.Menu;
import com.LTI.Project0.models.Offer;

public class FrontPage extends Menu {
	
	/*Static declarations
	 * In: A string that will receive most of the Scanner's inputs.
	 * incre: integer used to "browse" through the items.
	 * limit: integer used to limit the items displayed.
	 */
	static String In;
	private static int incre=0, limit=10;
	
	
	public static void WhoAmI(String Role)
	{
		//Each user has a different role, and every role has defined actions.
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
					System.out.println("Logging Out.");
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
					ViewInventory();
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
					CheckPendingOffers();
					break;
				case "2":
					EnterIMS();
					break;
				case "3":
					ViewAllPayments();
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

	
	private static void ViewAllPayments() {
		os.ViewAllPAyments();
	}

	private static void CheckPendingOffers() {
		// TODO Auto-generated method stub
		List<Offer> offersAvailable = os.getAllPEndingOffers();
		int numOfItems = offersAvailable.size();
		do
		{
			for(int i = incre; i < limit; i++)
			{
				try
				{
					System.out.println(i + ":) " + offersAvailable.get(i).toString());
				}
				catch(IndexOutOfBoundsException ndx_EX)
				{
					//Log it.
					break;
				}
					
			}
			DisplayOptions("Browsing Screen", 
					"1:)Next 10 items.", 
					"2:)Previous 10 item.", 
					"3:)Select Offer",
					"4:)Back to Main Menu");
			In = sc.nextLine();
			switch(In)
			{
				case "1":
					Show10(numOfItems, "NEXT");
					break;
				case "2":
					Show10(numOfItems, "PREVIOUS");
					break;
				case "3":
					SelectOffer(offersAvailable);
					break;
				case "4":
					break;
				default:
					System.out.println("Invalid input");
					break;
			}
		}while(!In.equals("5"));
		
	}

	private static void SelectOffer(List<Offer> offersAvailable) {
		// TODO Auto-generated method stub
		String in_ID = "0";
		System.out.println("Please enter the Offer ID you would like to select");
		in_ID = sc.nextLine();
		Offer selected_Offer = offersAvailable.get(Integer.valueOf(in_ID)-1);
		if(selected_Offer == null)
		{
			System.out.println("Sorry, that offer does not exist.");
		}
		else
		{
			System.out.println("Offer Found:");
			AcceptOrReject(selected_Offer);
		}

	}

	private static void AcceptOrReject(Offer selected_Offer) {
		System.out.println(selected_Offer.toString());
		DisplayOptions("What will you do with this offer?",
						"1:)Accept Offer",
						"2:)Reject Offer",
						"3:)Go Back");
		In = sc.nextLine();
		switch(In)
		{
			case "1":
				os.acceptBid(is.getItemByID(selected_Offer.getFor_Item(), false), selected_Offer.getOffer_From());
				break;
			case "2":
				os.rejectBid(is.getItemByID(selected_Offer.getFor_Item(), false), selected_Offer.getOffer_From());
				break;
			case "3":
				break;
			default:
				break;
		}
		
	}

	private static void BrowseItems() {
		List<Item> itemsAvailable = is.getItems();
		int numOfItems = itemsAvailable.size();
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
					"1:)Next 10 items.", 
					"2:)Previous 10 item.", 
					"3:)Search for an item with a specific ID",
					"4:)Select Item",
					"5:)Back to Main Menu");
			In = sc.nextLine();
			switch(In)
			{
				case "1":
					Show10(numOfItems, "NEXT");
					break;
				case "2":
					Show10(numOfItems, "PREVIOUS");
					break;
				case "3":
					SearchMenu();
					break;
				case "4":
					InspectItem(itemsAvailable,true);
					break;
				case "5":
					break;
				default:
					System.out.println("Invalid input");
					break;
			}
		}while(!In.equals("5"));
		
	}
	
	private static void ViewInventory() {
		List<Item> itemsAvailable = null;
		try {
			itemsAvailable = is.getItems(us.getUser(logged_In_UserName).getUsername());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int numOfItems = itemsAvailable.size();
		if(numOfItems <= 0)
		{
			System.out.println("There are no items in your inventory.");
			return;
		}
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
					"1:)Next 10 items.", 
					"2:)Previous 10 item.",
					"3:)Select Item",
					"4:)Back to Main Menu");
			In = sc.nextLine();
			switch(In)
			{
				case "1":
					Show10(numOfItems, "NEXT");
					break;
				case "2":
					Show10(numOfItems, "PREVIOUS");
					break;
				case "3":
					InspectItem(itemsAvailable, false);
					break;					
				case "4":
					break;
				default:
					System.out.println("Invalid input");
					break;
			}
		}while(!In.equals("4"));
		
	}
	
	private static void InspectItem(List<Item> itemsAvailable, boolean shopping) {
		String ins_ID = "0";
		boolean finished = false;
		System.out.println("Enter the ID of the item you want to look at.");
		ins_ID = sc.nextLine();
		Item selected = null;
		int InspectedID = Integer.valueOf(ins_ID);
		if(shopping)
			selected = is.getItemByID(InspectedID, false);
		else
			selected = is.getItemByID(InspectedID, true);
		
		if(selected.getId() == InspectedID)
		{
			if(shopping)
			{
				do
				{
					System.out.println(selected.toString());
					DisplayOptions("What would you like to do?",
							"1:)Buy it out",
							"2:)Bid on it",
							"3:)Exit");
					In = sc.nextLine();
					switch(In)
					{
						case "1":
							BuyOutItem(selected);
							finished = true;
							break;
						case "2":
							BidOnItem(selected.getOneTimePrice(), selected.getWeeklyPrice(), selected);
							finished = true;
							break;
						case "3":
							finished = true;
							break;
						default:
							System.out.println("Invalid input");
							break;
					}
				}while(!finished);
				System.out.println("Press Enter to continue");
				sc.nextLine();
			}
			else
			{
				System.out.println(selected.toString());
				System.out.println("You are currently paying " + os.getWeeklyPayment(selected).toString());
				ViewPayments(selected);
			}
		}
		else
		{
			System.out.println("We're sorry, this item is owned by someone else, or it is not in the system.");
		}
	}
	
	private static void ViewPayments(Item selected) {
		BigDecimal remainingPayments;
		remainingPayments = os.getWeeklyPayment(selected);
		BigDecimal originalPrice = selected.getOneTimePrice();
		BigDecimal result = originalPrice.divide(remainingPayments);
		result = result.round(new MathContext(0,RoundingMode.UP));
		System.out.println("You have " + result.toString() + " payments remaining on this item.");
	}

	private static void BuyOutItem(Item selected) {
		try {
			os.addTransaction(selected.getOneTimePrice(), selected, us.getUser(logged_In_UserName).getUsername());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private static void BidOnItem(BigDecimal otPrice, BigDecimal wkPrice, Item itemToBid) {
		BigDecimal bd_Offer = new BigDecimal(0);
		System.out.println("How much would you like to pay for this item?");
		bd_Offer = sc.nextBigDecimal();
		if(bd_Offer.compareTo(otPrice) == -1)
		{
			//Alright, it's less than the One Time Price. Now let's compare against the weekly price.
			if(bd_Offer.compareTo(wkPrice) == -1)
			{
				//...But you want to pay less than the listed price.
				System.out.println("Bid is less than the listed Weekly price. Bid automatically rejected.");
				try {
					os.offerBid(bd_Offer,itemToBid,us.getUser(logged_In_UserName).getUsername(),true);
				} catch (UserNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(bd_Offer.compareTo(wkPrice) == 0 || bd_Offer.compareTo(wkPrice) == 1)
			{
				try {
					os.offerBid(bd_Offer,itemToBid,us.getUser(logged_In_UserName).getUsername());
				} catch (UserNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(bd_Offer.compareTo(otPrice) == 0 || bd_Offer.compareTo(otPrice) == 1)
		{
			//Why didn't you just outright buy it then?
			System.out.println("Bid is greater than or equal to the listed One-Time Price. Would you like to buy it instead?*(Y/N)");
			In = sc.nextLine();
			if(In.equals("Y") || In.equals("y"))
			{
				BuyOutItem(itemToBid);
			}
		}	
	}

	private static void InspectItemFromSearch(Item selectedItem) {
		String opt_in = "0";
		boolean finished = false;
		do {
			System.out.println(selectedItem.toString());
			DisplayOptions("What would you like to do?",
					"1:)Buy it out",
					"2:)Bid on it",
					"3:)Exit");
			In = sc.nextLine();
			switch(In)
			{
				case "1":
					BuyOutItem(selectedItem);
					finished = true;
					break;
				case "2":
					BidOnItem(selectedItem.getOneTimePrice(),selectedItem.getWeeklyPrice(), selectedItem);
					finished = true;
					break;
				case "3":
					finished = true;
					break;
				default:
					System.out.println("Invalid input");
					break;
			}
		}while(!finished);
		System.out.println("Press Enter to continue");
		sc.nextLine();
	}

	private static void SearchMenu() {
		String in_ID = "0";
		System.out.println("Please enter the ID you would like to search for");
		in_ID = sc.nextLine();
		Item searched_Item = is.getItemByID(Integer.valueOf(in_ID),true);
		if(searched_Item == null)
		{
			System.out.println("Sorry, that item does not exist.");
		}
		else
		{
			System.out.println("Item Found:");
			InspectItemFromSearch(searched_Item);
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
		Item to_Remove = is.getItemByID(Integer.valueOf(In),false);
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
		Item add_Item = new Item(id,item_Name,item_Descr,item_otPrice,item_wkPrice,"N/A");
		is.addItem(add_Item);
	}

	private static void EditItem() {
		System.out.println("Please enter the ID of the item you want to update.");
		In = sc.nextLine();
		int edited = Integer.valueOf(In);
		Item to_Edit = is.getItemByID(edited,false);
		if(to_Edit.equals(null))
		{
			System.out.println("Incorrect ID");
		}
		else
			is.editItem(to_Edit);
	}
	
}
