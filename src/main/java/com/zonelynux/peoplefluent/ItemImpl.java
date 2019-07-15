package com.zonelynux.peoplefluent;

import com.zonelynux.peoplefluent.ShoppingCart.Item;

public class ItemImpl implements Item {
	private InventoryItem item;
	private int quantity;
	
	public ItemImpl(InventoryItem item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	@Override
	public InventoryItem getInventoryItem() {
		return item;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}
}