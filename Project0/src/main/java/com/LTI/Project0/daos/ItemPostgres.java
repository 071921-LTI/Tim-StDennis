package com.LTI.Project0.daos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
		Item selected_item = null;
		String sql = "select * from items where item_id = ?";
		
		try(Connection con = ConnectionUtil.getConnectionFromEnv())
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int _id = rs.getInt("item_id");
				String name = rs.getString("item_name");
				String description = rs.getString("item_description");
				BigDecimal OTPrice = rs.getBigDecimal("item_otprice");
				BigDecimal WKPrice = rs.getBigDecimal("item_wkprice");
				
				selected_item = new Item(_id,name,description,OTPrice,WKPrice);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return selected_item;
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
				BigDecimal OTPrice = rs.getBigDecimal("item_otprice");
				BigDecimal WKPrice = rs.getBigDecimal("item_wkprice");
				
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

	@Override
	public int editItem(Item to_Edit) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeItem(Item to_Remove) {
		// TODO Auto-generated method stub
		
	}

}
