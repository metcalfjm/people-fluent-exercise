package com.zonelynux.peoplefluent;

public enum InventoryItem {
	APPLE(.60f), ORANGE(.25f), BANANA(.2f);
	
	private float price;
	
	InventoryItem(float price)
	{
		this.price = price;
	}
	
	public float getPrice()
	{
		return price;
	}
}
