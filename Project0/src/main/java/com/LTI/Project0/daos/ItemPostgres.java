package com.LTI.Project0.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.LTI.Project0.models.Item;
import com.LTI.Project0.util.ConnectionUtil;

public class ItemPostgres implements ItemDao {

	@Override
	public Item getItemByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getItems() {
		List<Item> items = new ArrayList<>();
		
		String sql = "select * from items";
		
		try(Connection con = ConnectionUtil.getConnectionFromEnv())
		{
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next())
			{
				int id = rs.getInt("item_id");
				String name = rs.getString("item_name");
				String description = rs.getString("item_description");
				double OTPrice = rs.getDouble("item_otprice");
				double WKPrice = rs.getDouble("item_wkprice");
				
				Item new_Item = new Item(id,name,description,OTPrice,WKPrice);
				items.add(new_Item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return items;
	}

	@Override
	public int addItem(Item in_Item) {
		// TODO Auto-generated method stub
		return 0;
	}

}
