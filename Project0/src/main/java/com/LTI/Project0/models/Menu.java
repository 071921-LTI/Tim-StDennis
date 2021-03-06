package com.LTI.Project0.models;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LTI.Project0.services.AuthService;
import com.LTI.Project0.services.AuthServiceImpl;
import com.LTI.Project0.services.ItemService;
import com.LTI.Project0.services.ItemServiceImpl;
import com.LTI.Project0.services.OfferService;
import com.LTI.Project0.services.OfferServiceImpl;
import com.LTI.Project0.services.UserService;
import com.LTI.Project0.services.UserServiceImpl;

public abstract class Menu {
	
	protected static Scanner sc = new Scanner(System.in);
	protected static UserService us = new UserServiceImpl();
	protected static AuthService as = new AuthServiceImpl();
	protected static ItemService is = new ItemServiceImpl();
	protected static OfferService os = new OfferServiceImpl();
	protected static String logged_In_UserName = null;

	
	protected static void DisplayOptions(String initialMessage, String...options)
	{
		System.out.println(initialMessage);
		for(String i:options) {
			System.out.println(i);
		}
	}
}
