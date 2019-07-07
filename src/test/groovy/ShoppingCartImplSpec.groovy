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


class ShoppingCartImplSpec extends Specification {
	def cart = new ShoppingCartImpl()
	
	def setup() {
		cart.addItems(InventoryItem.APPLE, 2)
		cart.addItems(InventoryItem.ORANGE, 2)
	}
	
	def "simple add two apples"() {
      expect:
    	cart.addItems(InventoryItem.APPLE, 2) == 4
	}
	
	def "simple add one apple and one orange"() {
	  expect:
		cart.addItems(InventoryItem.APPLE, 1) == 3
		cart.addItems(InventoryItem.ORANGE, 1) == 3
	  }
	
	def "simple remove one orange"() {
	  expect:
		cart.removeItems(InventoryItem.ORANGE, 1) == 1
	}
	
	def "simple remove two oranges"() {
	  expect:
		cart.removeItems(InventoryItem.ORANGE, 2) == 0
	}
	
	def "corner case remove three apples"() {
	  expect:
		cart.removeItems(InventoryItem.APPLE, 3) == 0
	}
	
	def "verify iterator after simple add two apples"() {
	  when:
	  	cart.addItems(InventoryItem.APPLE, 2)
		def it = cart.iterator()
		
	// Ordered then blocks will iterate through items in natural enum order
	  then:
		it.hasNext()
		def apples = it.next()
		apples.inventoryItem == InventoryItem.APPLE
		apples.quantity == 4
	  
	  then:
		it.hasNext()
		def oranges = it.next()
		oranges.inventoryItem == InventoryItem.ORANGE
		oranges.quantity == 2
		
	  then:
		it.hasNext() == false
		
	}
}  
