package com.LTI.Project0.models;

import java.io.Serializable;

public class Item implements Serializable{
	
	private int id;
	private String name, description;
	private double OneTimePrice, WeeklyPrice;
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
	public double getOneTimePrice() {
		return OneTimePrice;
	}
	public void setOneTimePrice(double oneTimePrice) {
		OneTimePrice = oneTimePrice;
	}
	public double getWeeklyPrice() {
		return WeeklyPrice;
	}
	public void setWeeklyPrice(double weeklyPrice) {
		WeeklyPrice = weeklyPrice;
	}
	
	public Item()
	{
		super();
	}
	
	public Item(int in_id, String in_Name, String in_Description, double in_OTPrice, double in_WKPrice)
	{
		super();
		this.id = in_id;
		this.name = in_Name;
		this.description = in_Description;
		this.OneTimePrice = in_OTPrice;
		this.WeeklyPrice = in_WKPrice;
	}
	
	@Override
	public String toString() {
		return "Item [" + getId() + "] -- " + getName() + "\n----" + getDescription() + "\nBuyout Price: " + getOneTimePrice() + "\nWeekly Price:" + getWeeklyPrice() +"\n";
	}
	
}