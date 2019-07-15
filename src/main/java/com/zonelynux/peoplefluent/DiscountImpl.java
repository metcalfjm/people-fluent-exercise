package com.zonelynux.peoplefluent;

public class DiscountImpl implements Discount {

	private float discountedValue;
	
	public void setDiscountedValue(float discountedValue) {
		this.discountedValue = discountedValue;
	}

	private int nonDiscountedItems;

	public void setNonDiscountedItems(int nonDiscountedItems) {
		this.nonDiscountedItems = nonDiscountedItems;
	}

	@Override
	public float getDiscountedValue() {
		return discountedValue;
	}

	@Override
	public int getNonDiscountedItems() {
		return nonDiscountedItems;
	}

}
