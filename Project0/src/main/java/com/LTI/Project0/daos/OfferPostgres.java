package com.LTI.Project0.daos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.LTI.Project0.models.Item;
import com.LTI.Project0.models.Offer;
import com.LTI.Project0.util.ConnectionUtil;

public class OfferPostgres implements OfferDao {

	@Override
	public int addTransaction(BigDecimal payment, Item selected, String buyer) {
		String sql = "insert into item_offers("
				+ "for_itemid,"
				+ "for_itemname,"
				+ "offer_from,"
				+ "offering_to_pay,"
				+ "is_weekly,"
				+ "accept_offer,"
				+ "date_of_offer,"
				+ "date_of_accept)"
				+ "values(?,?,?,?,false,'ACCEPTED',current_date,current_date) returning offer_id";
		int id = -1;
		String sql_ChgOwner = "update items set "
				+ "item_owner = ?"
				+ " where item_id = ?";
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, selected.getId());
			ps.setString(2, selected.getName());
			ps.setString(3, buyer);
			ps.setBigDecimal(4, payment);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt("offer_id");
				PreparedStatement ps2 = con.prepareStatement(sql_ChgOwner);
				ps2.setString(1, buyer);
				ps2.setInt(2, selected.getId());
				
				id = ps2.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}

	@Override
	public int offerBid(BigDecimal bid, Item selected, String bidder) {
		String sql = "insert into item_offers("
				+ "for_itemid,"
				+ "for_itemname,"
				+ "offer_from,"
				+ "offering_to_pay,"
				+ "is_weekly,"
				+ "date_of_offer)"
				+ "values(?,?,?,?,true,current_date) returning offer_id";
		int id = -1;
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, selected.getId());
			ps.setString(2, selected.getName());
			ps.setString(3, bidder);
			ps.setBigDecimal(4, bid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt("offer_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public int offerBid(BigDecimal bid, Item selected, String bidder, boolean auto_Reject) {
		String sql = "insert into item_offers("
				+ "for_itemid,"
				+ "for_itemname,"
				+ "offer_from,"
				+ "offering_to_pay,"
				+ "is_weekly,"
				+ "accept_offer,"
				+ "date_of_offer)"
				+ "values(?,?,?,?,true,'REJECTED',current_date) returning offer_id";
		int id = -1;
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, selected.getId());
			ps.setString(2, selected.getName());
			ps.setString(3, bidder);
			ps.setBigDecimal(4, bid);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt("offer_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public boolean rejectAllOtherBids(int item_ID, String owner_ID) {
		String sql = "update item_offers set "
				+ "accept_offer = 'REJECTED' "
				+ "where for_itemId = ? AND "
				+ "NOT offer_from = ?";
		int rowsChanged = -1;
		
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, item_ID);
			ps.setString(2, owner_ID);
			
			rowsChanged = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(rowsChanged >0) {
			return true;
		} 
		return false;
	}

	@Override
	public int acceptBid(Item selected, String ownerID) {
		String sql_ChgOwner = "update items set "
				+ "item_owner = ? "
				+ "where item_id = ?";
		String sql_UpdOffers = "update item_offers set "
				+ "accept_offer = 'ACCEPTED' "
				+ "where for_itemId = ? AND "
				+ "offer_from = ?";
		int rowsChanged = -1;
		try(Connection con = ConnectionUtil.getConnectionFromEnv())
		{
			PreparedStatement ps_ChgOwner = con.prepareStatement(sql_ChgOwner);
			ps_ChgOwner.setString(1, ownerID);
			ps_ChgOwner.setInt(2, selected.getId());
			
			rowsChanged = ps_ChgOwner.executeUpdate();
			
			PreparedStatement ps_UpdOffers = con.prepareStatement(sql_UpdOffers);
			ps_UpdOffers.setInt(1, selected.getId());
			ps_UpdOffers.setString(2, ownerID);
			
			rowsChanged = ps_UpdOffers.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		rejectAllOtherBids(selected.getId(),ownerID);
		return rowsChanged;
	}
	@Override
	public int rejectBid(Item selected, String ownerID) {
		String sql_UpdOffers = "update item_offers set "
				+ "accept_offer = 'REJECTED'"
				+ "where for_itemId = ? AND "
				+ "offer_from = ?";
		int rowsChanged = -1;
		try(Connection con = ConnectionUtil.getConnectionFromEnv())
		{			
			PreparedStatement ps_UpdOffers = con.prepareStatement(sql_UpdOffers);
			ps_UpdOffers.setInt(1, selected.getId());
			ps_UpdOffers.setString(2, ownerID);
			
			rowsChanged = ps_UpdOffers.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rowsChanged;
	}
	@Override
	public BigDecimal getWeeklyPayment(Item Selected) {
		BigDecimal retval = null;
		
		String sql = "select offering_to_pay from item_offers where for_itemid = ? AND is_weekly = true";
		try(Connection con = ConnectionUtil.getConnectionFromEnv())
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Selected.getId());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				retval = rs.getBigDecimal("offering_to_pay");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return retval;
	}

	@Override
	public List<Offer> getAllPEndingOffers() {
		String sql = "select * from item_offers where accept_offer = 'PENDING'";
		List<Offer> offers = new ArrayList<>();
		try(Connection con = ConnectionUtil.getConnectionFromEnv())
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
				while(rs.next()) {
					int offer_ID = rs.getInt("offer_id");
					int item_ID = rs.getInt("for_itemid");
					String item_Name = rs.getString("for_itemname");
					String Payer = rs.getString("offer_from");
					BigDecimal Offering = rs.getBigDecimal("offering_to_pay");
					boolean is_Weekly = rs.getBoolean("is_weekly");
					Offer new_deal = new Offer(offer_ID,item_ID,item_Name,Payer,Offering,is_Weekly);
					offers.add(new_deal);
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return offers;
		
	}

	@Override
	public void ViewAllPAyments() {
		String sql = "select * from payment_history";
		try(Connection con = ConnectionUtil.getConnectionFromEnv())
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				while(rs.next()) {
					int Trans_ID = rs.getInt("Transaction ID");
					String item_Name = rs.getString("Item Name");
					BigDecimal payment = rs.getBigDecimal("Payment");
					Date datePaid = rs.getDate("Date Paid/Start Of Payment");
					boolean weekly = rs.getBoolean("Weekly?");
					String payer = rs.getString("Payer");
					String status = rs.getString("Status");
					String output = "Transaction ID:" + Trans_ID + "\nItem Name: " + 
							item_Name + " sold for $" + payment.toString();
					if(weekly)
						output += "(weekly)";
					output += " to " + payer + " On " + datePaid.toString();
					System.out.println(output);
						
				}	
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Offer getOfferByID(Integer valueOf) {
		Offer retval = new Offer();
		String sql = "select * from item_offers where offer_id = ?";
		try(Connection con = ConnectionUtil.getConnectionFromEnv())
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, valueOf);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {			
				retval.setId(rs.getInt("offer_id"));
				retval.setFor_Item(rs.getInt("for_itemid"));
				retval.setFor_ItemName(rs.getString("for_itemname"));
				retval.setOffer_From(rs.getString("offer_from"));
				retval.setOffering_ToPay(rs.getBigDecimal("offering_to_pay"));
				retval.setIs_Weekly(rs.getBoolean("is_weekly"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}	
		
		
		return retval;
	}

}
