package com.zonelynux.peoplefluent.special;

import com.zonelynux.peoplefluent.InventoryItem;
import com.zonelynux.peoplefluent.Special;

public abstract class InventoryItemSpecial implements Special {
	
	private InventoryItem inventoryItem;
	
	protected InventoryItem assertInventoryItem()
	{
		if (inventoryItem == null)
		{
			throw new IllegalStateException("No InventoryItem initialized");
		}
		return inventoryItem;
	}

	public void setInventoryItem(InventoryItem item) {
		this.inventoryItem = item;
	}
	
	@Override
	public InventoryItem getInventoryItem() {
		return assertInventoryItem();
	}
}
