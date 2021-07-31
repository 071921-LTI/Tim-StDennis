package com.LTI.Project0.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable{
	
	private int id;
	private String name, description, owner;
	private BigDecimal OneTimePrice, WeeklyPrice;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getOneTimePrice() {
		return OneTimePrice;
	}
	public void setOneTimePrice(BigDecimal oneTimePrice) {
		OneTimePrice = oneTimePrice;
	}
	public BigDecimal getWeeklyPrice() {
		return WeeklyPrice;
	}
	public void setWeeklyPrice(BigDecimal weeklyPrice) {
		WeeklyPrice = weeklyPrice;
	}
	
	public Item()
	{
		super();
	}
	
	public Item(int in_id, String in_Name, String in_Description, BigDecimal in_OTPrice, BigDecimal in_WKPrice, String in_Owner)
	{
		super();
		this.id = in_id;
		this.name = in_Name;
		this.description = in_Description;
		this.OneTimePrice = in_OTPrice;
		this.WeeklyPrice = in_WKPrice;
		this.setOwner(in_Owner);
	}
	
	@Override
	public String toString() {
		return "Item: ID#[" + getId() + "] -- " + getName() + "\n----" + getDescription() + "\nBuyout Price: " + getOneTimePrice() + "\nWeekly Price:" + getWeeklyPrice() +"\n";
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
