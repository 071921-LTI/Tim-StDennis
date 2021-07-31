package com.LTI.Project0.daos;

import java.math.BigDecimal;
import java.util.List;

import com.LTI.Project0.models.Item;
import com.LTI.Project0.models.Offer;

public interface OfferDao {
	public abstract void addTransaction(BigDecimal payment, Item selected,String buyer);
	public abstract void offerBid(BigDecimal bid, Item selected, String bidder);
	public abstract void offerBid(BigDecimal bid, Item selected, String bidder, boolean auto_Reject);
	public abstract boolean rejectAllOtherBids(int item_ID, String ownerID);
	public abstract void acceptBid(Item selected, String ownerID);
	public abstract BigDecimal getWeeklyPayment(Item Selected);
	public abstract List<Offer> getAllPEndingOffers();
	public abstract void rejectBid(Item selected, String ownerID);
	public abstract void ViewAllPAyments();
}
