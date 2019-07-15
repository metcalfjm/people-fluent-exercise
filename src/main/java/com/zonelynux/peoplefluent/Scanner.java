package com.zonelynux.peoplefluent;

import java.util.Locale;

public interface Scanner {
	void setLocale(Locale locale);
	void addSpecial(Special special);
	void setMultiItemSpecial(MultiItemSpecial special);
	Ledger checkout(ShoppingCart cart);
}
