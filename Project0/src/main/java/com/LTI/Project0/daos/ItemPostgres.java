package com.LTI.Project0.daos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LTI.Project0.exceptions.ItemNotFoundException;
import com.LTI.Project0.models.Item;
import com.LTI.Project0.util.ConnectionUtil;

public class ItemPostgres implements ItemDao {
	
	public static Logger log = LogManager.getRootLogger();
	@Override
	public Item getItemByID(int id, boolean owned) {
		Item selected_item = null;
		String sql = "select * from items where item_id = ?";
		if(owned == false)
			sql = sql.concat(" AND item_owner = 'N/A'");
		else
			sql = sql.concat(" AND NOT item_owner = 'N/A'");
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
				String owner = rs.getString("item_owner");
				
				selected_item = new Item(_id,name,description,OTPrice,WKPrice, owner);
			}
		}catch(SQLException e) {
			log.error("SQL Exception thrown.{Item Postgres:Get Item By ID} Stack Trace is below" + e.getStackTrace());
			e.printStackTrace();
		}
		if(selected_item == null)
		{
			log.error("Item Not Found Exception.{Item Postgres:Get Item By ID}");
		}
		return selected_item;
	}
	
	@Override
	public List<Item> getItems(String ownerID) {
		List<Item> items = new ArrayList<>();
		
		String sql = "select * from items where item_owner = ?";
		
		try(Connection con = ConnectionUtil.getConnectionFromEnv())
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ownerID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				int id = rs.getInt("item_id");
				String name = rs.getString("item_name");
				String description = rs.getString("item_description");
				BigDecimal OTPrice = rs.getBigDecimal("item_otprice");
				BigDecimal WKPrice = rs.getBigDecimal("item_wkprice");
				String owner = rs.getString("item_owner");
				
				Item new_Item = new Item(id,name,description,OTPrice,WKPrice, owner);
				items.add(new_Item);
			}
		} catch (SQLException e) {
			log.error("SQL Exception thrown.{Item Postgres:Get Items w/Owner ID} Stack Trace is below" + e.getStackTrace());
			e.printStackTrace();
		}
		
		
		return items;
	}
	@Override
	public List<Item> getItems() {
		List<Item> items = new ArrayList<>();
		
		String sql = "select * from items where item_owner = 'N/A'";
		
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
				String owner = rs.getString("item_owner");
				
				Item new_Item = new Item(id,name,description,OTPrice,WKPrice, owner);
				items.add(new_Item);
			}
		} catch (SQLException e) {
			log.error("SQL Exception thrown.{Item Postgres:Get Items} Stack Trace is below" + e.getStackTrace());
			e.printStackTrace();
		}
		
		
		return items;
	}

	@Override
	public int addItem(Item in_Item) {
		int id = -1;
		String sql = "insert into items "
				+ "(item_id,"
				+ " item_name,"
				+ " item_description,"
				+ " item_otprice,"
				+ " item_wkprice)"
				+ " values (?,?,?,?,?) returning item_id;";
		
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, in_Item.getId());
			ps.setString(2, in_Item.getName());
			ps.setString(3, in_Item.getDescription());
			ps.setBigDecimal(4, in_Item.getOneTimePrice());
			ps.setBigDecimal(5, in_Item.getWeeklyPrice());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt("item_id");
			}
		} catch (SQLException e) {
			log.error("SQL Exception thrown.{Item Postgres:Add Item} Stack Trace is below" + e.getStackTrace());
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public int editItem(Item to_Edit) {
		String sql = "update items set "
				+ "item_name = ?,"
				+ " item_description = ?,"
				+ " item_otprice = ?,"
				+ " item_wkprice = ?"
				+ " where item_id = ?;";
		int rowsChanged = -1;
				
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, to_Edit.getName());
			ps.setString(2, to_Edit.getDescription());
			ps.setBigDecimal(3, to_Edit.getOneTimePrice());
			ps.setBigDecimal(4, to_Edit.getWeeklyPrice());
			ps.setInt(5, to_Edit.getId());
			
			rowsChanged = ps.executeUpdate();
		} catch (SQLException e) {
			log.error("SQL Exception thrown.{Item Postgres:Edit Item} Stack Trace is below" + e.getStackTrace());
			e.printStackTrace();
		}
		return rowsChanged;
	}

	@Override
	public int removeItem(Item to_Remove) {
		String sql = "delete from items where item_id = ?;";
		int rowsChanged = -1;
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, to_Remove.getId());
			
			rowsChanged = ps.executeUpdate();
			} catch (SQLException e) {
				log.error("SQL Exception thrown.{Item Postgres:Remove Item} Stack Trace is below" + e.getStackTrace());
			e.printStackTrace();
		}
		return rowsChanged;
	}

}
