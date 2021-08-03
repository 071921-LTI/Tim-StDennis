package com.LTI.Project0.services;

import java.math.BigDecimal;
import java.util.List;

import com.LTI.Project0.daos.DAOFactory;
import com.LTI.Project0.models.Item;
import com.LTI.Project0.models.Offer;

public class OfferServiceImpl implements OfferService {
	
	@Override
	public int addTransaction(BigDecimal payment, Item selected,String buyer) {
		return DAOFactory.getDF().getOfferDAO().addTransaction(payment, selected,buyer);
		
	}

	@Override
	public int offerBid(BigDecimal bid, Item selected, String bidder) {
		return DAOFactory.getDF().getOfferDAO().offerBid(bid, selected,bidder);
		
	}

	@Override
	public int offerBid(BigDecimal bid, Item selected, String bidder,  boolean auto_Reject) {
		return DAOFactory.getDF().getOfferDAO().offerBid(bid, selected, bidder, auto_Reject);
		
	}

	@Override
	public boolean rejectAllOtherBids(int item_ID, String ownerID) {
		return DAOFactory.getDF().getOfferDAO().rejectAllOtherBids(item_ID, ownerID);
	}

	@Override
	public int acceptBid(Item selected, String ownerID) {
		return DAOFactory.getDF().getOfferDAO().acceptBid(selected, ownerID);
		
	}

	@Override
	public BigDecimal getWeeklyPayment(Item Selected) {
		return DAOFactory.getDF().getOfferDAO().getWeeklyPayment(Selected);
	}

	@Override
	public List<Offer> getAllPEndingOffers() {
		return DAOFactory.getDF().getOfferDAO().getAllPEndingOffers();
	}

	@Override
	public int rejectBid(Item selected, String ownerID) {
		return DAOFactory.getDF().getOfferDAO().rejectBid(selected, ownerID);
		
	}

	@Override
	public void ViewAllPAyments() {
		DAOFactory.getDF().getOfferDAO().ViewAllPAyments();
		
	}

	@Override
	public Offer getOfferByID(Integer valueOf) {
		return DAOFactory.getDF().getOfferDAO().getOfferByID(valueOf);
	}

}
