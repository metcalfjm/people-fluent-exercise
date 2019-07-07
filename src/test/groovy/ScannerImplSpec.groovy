/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.zonelynux.peoplefluent.*
import com.zonelynux.peoplefluent.special.BuyOneGetOneFreeSpecial
import com.zonelynux.peoplefluent.special.ThreeForThePriceOfTwoSpecial

import spock.lang.Specification


class ScannerImplSpec extends Specification {
	def cart = new ShoppingCartImpl()
	def scanner = new ScannerImpl()
	def bogoApple = new BuyOneGetOneFreeSpecial()
	def tfpotOrange = new ThreeForThePriceOfTwoSpecial()
	def currencySymbol
	
	def setup() {
		scanner.setLocale(Locale.UK)
		bogoApple.setInventoryItem(InventoryItem.APPLE)
		tfpotOrange.setInventoryItem(InventoryItem.ORANGE)
		currencySymbol = "\u00A3" // British pound sterling symbol
	}
	
	def "simple load cart then checkout"() {
      when:
    	cart.addItems(InventoryItem.APPLE, 2)
		cart.addItems(InventoryItem.APPLE, 1)
		cart.addItems(InventoryItem.ORANGE, 1)
		
	  then:
		scanner.checkout(cart).displayBalance() == currencySymbol + "2.05"; // Three apples, one orange
	}
	
	def "load cart, remove an item then checkout"() {
		when:
		  cart.addItems(InventoryItem.APPLE, 2)
		  cart.addItems(InventoryItem.ORANGE, 3)
		  cart.removeItems(InventoryItem.ORANGE, 1)
		  
		then:
		  scanner.checkout(cart).displayBalance() == currencySymbol + "1.70"; // Two apples, two oranges
	}
	
	def "load cart, try removing more items than are available"() {
		when:
		  cart.addItems(InventoryItem.APPLE, 2)
		  cart.addItems(InventoryItem.ORANGE, 3)
		  cart.removeItems(InventoryItem.APPLE, 3)
		  
		then:
		  scanner.checkout(cart).displayBalance() == currencySymbol + "0.75"; // Only 3 oranges left
	}
	
	def "load cart, then remove all items"() {
		when:
		  cart.addItems(InventoryItem.APPLE, 2)
		  cart.addItems(InventoryItem.ORANGE, 3)
		  cart.removeItems(InventoryItem.APPLE, 2)
		  cart.removeItems(InventoryItem.ORANGE, 3)
		  
		then:
		  scanner.checkout(cart).displayBalance() == currencySymbol + "0.00"; // Nothing left in cart
	}
	
	def "Buy 0 apples and 1 orange and apply apple BOGO special"() {
		when:
		  cart.addItems(InventoryItem.ORANGE, 1)
		  scanner.addSpecial(bogoApple);
		  
		then:
		  scanner.checkout(cart).displayBalance() == currencySymbol + "0.25"; // Three apples, one orange
	}
	
	def "Buy 1 apple and 1 orange and apply apple BOGO special"() {
		when:
		  cart.addItems(InventoryItem.APPLE, 1)
		  cart.addItems(InventoryItem.ORANGE, 1)
		  scanner.addSpecial(bogoApple);
		  
		then:
		  scanner.checkout(cart).displayBalance() == currencySymbol + "0.85"; // Three apples, one orange
	}
	
	def "Buy 2 apples and 1 orange and apply apple BOGO special"() {
		when:
		  cart.addItems(InventoryItem.APPLE, 2)
		  cart.addItems(InventoryItem.ORANGE, 1)
		  scanner.addSpecial(bogoApple);
		  
		then:
		  scanner.checkout(cart).displayBalance() == currencySymbol + "0.85"; // Three apples, one orange
	}
	
	def "Buy 3 apples and 1 orange and apply apple BOGO special"() {
		when:
		  cart.addItems(InventoryItem.APPLE, 3)
		  cart.addItems(InventoryItem.ORANGE, 1)
		  scanner.addSpecial(bogoApple);
		  
		then:
		  scanner.checkout(cart).displayBalance() == currencySymbol + "1.45"; // Three apples, one orange
	}
	
	def "Buy 4 apples and 1 orange and apply apple BOGO special"() {
		when:
		  cart.addItems(InventoryItem.APPLE, 3)
		  cart.addItems(InventoryItem.ORANGE, 1)
		  scanner.addSpecial(bogoApple);
		  
		then:
		  scanner.checkout(cart).displayBalance() == currencySymbol + "1.45"; // Three apples, one orange
	}
	
	def "Buy 0 apples and 0 oranges and apply the orange three-for-price-of-two special"() {
		when:
		  scanner.addSpecial(tfpotOrange);
		  
		then:
		  scanner.checkout(cart).displayBalance() == currencySymbol + "0.00"; // Three apples, one orange
	}
	
	def "Buy 1 apple and 2 oranges and apply the orange three-for-price-of-two special"() {
		when:
		  cart.addItems(InventoryItem.APPLE, 1)
		  cart.addItems(InventoryItem.ORANGE, 2)
		  scanner.addSpecial(tfpotOrange);
		  
		then:
		  scanner.checkout(cart).displayBalance() == currencySymbol + "1.10"; // Three apples, one orange
	}
	
	def "Buy 1 apple and 3 oranges and apply the orange three-for-price-of-two special"() {
		when:
		  cart.addItems(InventoryItem.APPLE, 1)
		  cart.addItems(InventoryItem.ORANGE, 2)
		  scanner.addSpecial(tfpotOrange);
		  
		then:
		  scanner.checkout(cart).displayBalance() == currencySymbol + "1.10"; // Three apples, one orange
	}
	
	def "Buy 1 apple and 4 oranges and apply the orange three-for-price-of-two special"() {
		when:
		  cart.addItems(InventoryItem.APPLE, 1)
		  cart.addItems(InventoryItem.ORANGE, 4)
		  scanner.addSpecial(tfpotOrange);
		  
		then:
		  scanner.checkout(cart).displayBalance() == currencySymbol + "1.35"; // Three apples, one orange
	}
	
	def "Buy 2 apples and 5 oranges and apply the orange three-for-price-of-two special"() {
		when:
		  cart.addItems(InventoryItem.APPLE, 2)
		  cart.addItems(InventoryItem.ORANGE, 5)
		  scanner.addSpecial(tfpotOrange);
		  
		then:
		  scanner.checkout(cart).displayBalance() == currencySymbol + "2.20"; // Three apples, one orange
	}
	
	def "Buy 2 apples and 6 oranges and apply the orange three-for-price-of-two special"() {
		when:
		  cart.addItems(InventoryItem.APPLE, 2)
		  cart.addItems(InventoryItem.ORANGE, 6)
		  scanner.addSpecial(tfpotOrange);
		  
		then:
		  scanner.checkout(cart).displayBalance() == currencySymbol + "2.20"; // Three apples, one orange
	}
	
	def "Buy 3 apples and 7 oranges and apply the orange three-for-price-of-two special"() {
		when:
		  cart.addItems(InventoryItem.APPLE, 3)
		  cart.addItems(InventoryItem.ORANGE, 7)
		  scanner.addSpecial(tfpotOrange);
		  
		then:
		  scanner.checkout(cart).displayBalance() == currencySymbol + "3.05"; // Three apples, one orange
	}
	
	def "Buy 3 apples and 7 oranges and apply both specials"() {
		when:
		  cart.addItems(InventoryItem.APPLE, 3)
		  cart.addItems(InventoryItem.ORANGE, 7)
		  scanner.addSpecial(bogoApple);
		  scanner.addSpecial(tfpotOrange);
		  
		then:
		  scanner.checkout(cart).displayBalance() == currencySymbol + "2.45"; // Three apples, one orange
	}
}  