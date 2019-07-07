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

import spock.lang.Specification


class ScannerImplSpec extends Specification {
	def cart = new ShoppingCartImpl()
	def scanner = new ScannerImpl()
	def currencySymbol
	
	def setup() {
		scanner.setLocale(Locale.UK)
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
}  
