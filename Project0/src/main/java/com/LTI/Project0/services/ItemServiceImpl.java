package com.LTI.Project0.services;

import java.util.List;

import com.LTI.Project0.daos.DAOFactory;
import com.LTI.Project0.exceptions.ItemNotFoundException;
import com.LTI.Project0.models.Item;

public class ItemServiceImpl implements ItemService {

	@Override
	public Item getItemByID(int id,boolean owned) {
		return DAOFactory.getDF().getItemDAO().getItemByID(id,owned);
	}

	@Override
	public List<Item> getItems() {
		return DAOFactory.getDF().getItemDAO().getItems();
	}

	@Override
	public int addItem(Item in_Item) {
		return DAOFactory.getDF().getItemDAO().addItem(in_Item);
	}
	
	@Override
	public int editItem(Item to_Edit)
	{
		 return DAOFactory.getDF().getItemDAO().editItem(to_Edit);
	}

	@Override
	public int removeItem(Item to_Remove) {
		return DAOFactory.getDF().getItemDAO().removeItem(to_Remove);
		
		
	}

	@Override
	public List<Item> getItems(String ownerID) {
		return DAOFactory.getDF().getItemDAO().getItems(ownerID);
	}
	
	
}
