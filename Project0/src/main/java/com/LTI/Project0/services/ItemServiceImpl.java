package com.LTI.Project0.services;

import java.util.List;

import com.LTI.Project0.daos.ItemPostgres;
import com.LTI.Project0.models.Item;

public class ItemServiceImpl implements ItemService {

	private ItemPostgres i_postgres = new ItemPostgres();

	@Override
	public Item getItemByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getItems() {
		return i_postgres.getItems();
	}

	@Override
	public int addItem(Item in_Item) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
