package com.zonelynux.peoplefluent;

import java.util.Iterator;

public interface ShoppingCart extends Iterable<Item> {
	int addItems(InventoryItem inventoryItem, int quantity);
	int removeItems(InventoryItem inventoryItem, int quantity);
	Iterator<Item> iterator();
}
