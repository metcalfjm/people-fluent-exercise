package com.zonelynux.peoplefluent;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ScannerImpl implements Scanner {
	
	private Locale locale = Locale.getDefault();
	
	private Map<InventoryItem, Special> specials = new HashMap<>();
	
	private MultiItemSpecial multiItemSpecial;
	
	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	@Override
	public void addSpecial(Special special) {
		specials.put(special.getInventoryItem(), special);
	}
	
	@Override
	public void setMultiItemSpecial(MultiItemSpecial special) {
		this.multiItemSpecial = special;
	}

	
	@Override
	public Ledger checkout(ShoppingCart cart) {
		final Ledger cl = new LedgerImpl();
		List<Item> fullPriceItems = new ArrayList<>();
		for (Item shoppingCartItem : cart) {
			InventoryItem inventoryItem = shoppingCartItem.getInventoryItem(); 
			int quantity = shoppingCartItem.getQuantity();
			cl.charge(inventoryItem.getPrice(), quantity);
	
			// Now apply any discounts for specials on the item
			Special special = specials.get(inventoryItem);
			if (special != null) {
				Discount d = special.computeDiscount(quantity);
				cl.applyDiscount(d.getDiscountedValue());
				if (d.getNonDiscountedItems() > 0) {
					fullPriceItems.add(new ItemImpl(shoppingCartItem.getInventoryItem(), d.getNonDiscountedItems()));
				}
			}
		}
		if (multiItemSpecial != null) {
			cl.applyDiscount(multiItemSpecial.computeDiscount(fullPriceItems));
		}
		return cl;
	}
	
	public class LedgerImpl implements Ledger {
		float balance = 0.0f;
		
		@Override
		public void charge(float price, int quantity) {
			balance += price*quantity;
		}
		
		@Override
		public void applyDiscount(float discount) {
			balance -= discount;
		}
		
		@Override
		public String displayBalance() {
			NumberFormat format = NumberFormat.getCurrencyInstance(locale);
			return format.format(balance > 0.0 ? balance : 0.0);
		}
	}

	
}
