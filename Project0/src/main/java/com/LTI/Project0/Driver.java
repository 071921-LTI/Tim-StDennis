package com.LTI.Project0;

import com.LTI.Project0.Controllers.MainMenu;
import com.LTI.Project0.exceptions.UserNotFoundException;

public class Driver {
	
	public static void main(String[] args) 
	{
		System.out.println("Welcome to Tabletop & More\nNow Loading Menu...\n\n");
		try {
			MainMenu.Display();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
