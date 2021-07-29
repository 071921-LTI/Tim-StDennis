package com.LTI.Project0.daos;

import java.util.List;

import com.LTI.Project0.models.Item;

public interface ItemDao {
	public abstract Item getItemByID(int id);
	public abstract List<Item> getItems();
	public abstract int addItem(Item in_Item);
}