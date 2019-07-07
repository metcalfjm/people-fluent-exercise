package com.zonelynux.peoplefluent.special;

import com.zonelynux.peoplefluent.InventoryItem;

public class BuyOneGetOneFreeSpecial extends InventoryItemSpecial {

	@Override
	public float computeDiscount(int quantity) {
		InventoryItem item = assertInventoryItem();
		float price = item.getPrice();
		int multiple = quantity/2;
		return price * multiple;
	}
}
