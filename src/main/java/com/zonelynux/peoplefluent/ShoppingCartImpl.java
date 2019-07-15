package com.zonelynux.peoplefluent;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ShoppingCartImpl implements ShoppingCart {
	private Map<InventoryItem, Integer> items = new TreeMap<>();
	
	@Override
	public int addItems(InventoryItem item, int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("The quantity must be > 0");
		}
		Integer q = items.getOrDefault(item, 0);
		items.put(item, q + quantity);
		return q + quantity;
	}
	
	@Override
	public int removeItems(InventoryItem item, int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("The quantity must be > 0");
		}
		Integer q = items.get(item);
		if (q != null) {
			if (q > quantity) {
				items.put(item, q - quantity);
				return q - quantity;
			}
			else {
				items.remove(item);
			}
		}
		return 0;
	}

	@Override
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {

			Iterator<Entry<InventoryItem, Integer>> it = items.entrySet().iterator();
			
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public Item next() {
				Entry<InventoryItem, Integer> next = it.next();
				return new ItemImpl(next.getKey(), next.getValue());
			}
		};
	}
}
