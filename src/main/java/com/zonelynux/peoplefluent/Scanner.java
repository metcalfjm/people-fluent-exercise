package com.zonelynux.peoplefluent;

import java.util.Locale;

public interface Scanner {
	void setLocale(Locale locale);
	void addSpecial(Special special);
	Ledger checkout(ShoppingCart cart);
}
