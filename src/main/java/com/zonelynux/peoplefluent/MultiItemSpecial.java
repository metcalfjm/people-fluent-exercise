package com.zonelynux.peoplefluent;

import java.util.List;

public interface MultiItemSpecial {
	float computeDiscount(List<ShoppingCart.Item> remainingItems);
}
