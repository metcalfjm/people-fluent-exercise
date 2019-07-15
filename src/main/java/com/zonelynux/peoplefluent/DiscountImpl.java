package com.zonelynux.peoplefluent;

public class DiscountImpl implements Discount {

	private float discountedPrice;
	
	public void setDiscountedPrice(float discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	private int nonDiscountedItems;

	public void setNonDiscountedItems(int nonDiscountedItems) {
		this.nonDiscountedItems = nonDiscountedItems;
	}

	@Override
	public float getDiscountedPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNonDiscountedItems() {
		// TODO Auto-generated method stub
		return 0;
	}

}
