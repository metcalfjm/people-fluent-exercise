package com.zonelynux.peoplefluent;

import java.util.Locale;

public interface Scanner {
	void setLocale(Locale locale);
	Ledger checkout(ShoppingCart cart);
}
