package com.zonelynux.peoplefluent.special;

import java.util.List;

import com.zonelynux.peoplefluent.InventoryItem;
import com.zonelynux.peoplefluent.Item;
import com.zonelynux.peoplefluent.MultiItemSpecial;

public class ExtraAppleAndBananaMultiItemSpecialImpl implements MultiItemSpecial {

	@Override
	public float computeDiscount(List<Item> remainingItems) {
		boolean hasOneBanana = false;
		boolean hasOneApple = false;
		for (Item item : remainingItems) {
			if (item.getInventoryItem() == InventoryItem.APPLE) {
				hasOneApple = true;
			}
			if (item.getInventoryItem() == InventoryItem.BANANA) {
				hasOneBanana = true;
			}
		}
		return hasOneApple && hasOneBanana ? InventoryItem.APPLE.getPrice() : 0;
	}
}
