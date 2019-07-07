package com.zonelynux.peoplefluent;

import java.util.Iterator;

public interface ShoppingCart extends Iterable<ShoppingCart.Item> {
	int addItems(InventoryItem item, int quantity);
	int removeItems(InventoryItem item, int quantity);
	Iterator<Item> iterator();
	
	public interface Item {
		InventoryItem getInventoryItem();
		int getQuantity();
	}
}
