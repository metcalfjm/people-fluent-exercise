package com.zonelynux.peoplefluent.special;

import com.zonelynux.peoplefluent.Discount;
import com.zonelynux.peoplefluent.DiscountImpl;
import com.zonelynux.peoplefluent.InventoryItem;

public class BuyOneGetOneFreeSpecial extends InventoryItemSpecial {

	@Override
	public Discount computeDiscount(int quantity) {
		DiscountImpl discount = new DiscountImpl();
		InventoryItem item = assertInventoryItem();
		float price = item.getPrice();
		int multiple = quantity/2;
		discount.setDiscountedPrice(price * multiple);
		discount.setNonDiscountedItems(quantity%2);
		return discount;
	}
}
