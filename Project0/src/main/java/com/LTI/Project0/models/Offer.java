package com.LTI.Project0.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Offer implements Serializable{
	private int id,for_Item;
	private String for_ItemName,offer_From;
	private BigDecimal offering_ToPay;
	private boolean is_Weekly;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFor_Item() {
		return for_Item;
	}
	public void setFor_Item(int for_Item) {
		this.for_Item = for_Item;
	}
	public String getFor_ItemName() {
		return for_ItemName;
	}
	public void setFor_ItemName(String for_ItemName) {
		this.for_ItemName = for_ItemName;
	}
	public String getOffer_From() {
		return offer_From;
	}
	public void setOffer_From(String offer_From) {
		this.offer_From = offer_From;
	}
	public BigDecimal getOffering_ToPay() {
		return offering_ToPay;
	}
	public void setOffering_ToPay(BigDecimal offering_ToPay) {
		this.offering_ToPay = offering_ToPay;
	}
	public boolean isIs_Weekly() {
		return is_Weekly;
	}
	public void setIs_Weekly(boolean is_Weekly) {
		this.is_Weekly = is_Weekly;
	}

	public Offer()
	{
		super();
	}
	public Offer(int in_id, int in_IID,String in_Name, String in_offerFrom, BigDecimal in_Offering, boolean in_Weekly)
	{
		super();
		this.id = in_id;
		this.for_Item = in_IID;
		this.for_ItemName = in_Name;
		this.offer_From = in_offerFrom;
		this.offering_ToPay = in_Offering;
		this.is_Weekly = in_Weekly;
	}
	
	@Override
	public String toString() {
		return "Offer #" + getId() + ":\n " +
					getOffer_From() + " is willing to pay " + getOffering_ToPay().toString() + 
					" for the " + getFor_ItemName().toString() + "(Item ID # " + getFor_Item() + ")";
	}

}
