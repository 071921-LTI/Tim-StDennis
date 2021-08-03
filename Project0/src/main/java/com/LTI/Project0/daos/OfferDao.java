package com.LTI.Project0.daos;

import java.math.BigDecimal;
import java.util.List;

import com.LTI.Project0.models.Item;
import com.LTI.Project0.models.Offer;

public interface OfferDao {
	public abstract int addTransaction(BigDecimal payment, Item selected,String buyer);
	public abstract int offerBid(BigDecimal bid, Item selected, String bidder);
	public abstract int offerBid(BigDecimal bid, Item selected, String bidder, boolean auto_Reject);
	public abstract boolean rejectAllOtherBids(int item_ID, String ownerID);
	public abstract int acceptBid(Item selected, String ownerID);
	public abstract BigDecimal getWeeklyPayment(Item Selected);
	public abstract List<Offer> getAllPEndingOffers();
	public abstract int rejectBid(Item selected, String ownerID);
	public abstract void ViewAllPAyments();
	public abstract Offer getOfferByID(Integer valueOf);
}
