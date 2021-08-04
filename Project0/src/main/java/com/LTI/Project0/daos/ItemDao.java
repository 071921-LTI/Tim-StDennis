package com.LTI.Project0.daos;

import java.util.List;

import com.LTI.Project0.exceptions.ItemNotFoundException;
import com.LTI.Project0.models.Item;

public interface ItemDao {
	public abstract Item getItemByID(int id,boolean owned);
	public abstract List<Item> getItems();
	public abstract List<Item> getItems(String ownerID);
	public abstract int addItem(Item in_Item);
	public abstract int editItem(Item to_Edit);
	public abstract int removeItem(Item to_Remove);
}
