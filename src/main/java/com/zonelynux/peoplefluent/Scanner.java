package com.zonelynux.peoplefluent;

import java.util.Locale;

public interface Scanner {
	void setLocale(Locale locale);
	Leger checkout(ShoppingCart cart);
}
