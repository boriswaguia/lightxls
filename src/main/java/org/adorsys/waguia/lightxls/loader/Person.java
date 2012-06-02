/**
 * Copyright (C) 2012 Waguia W. Boris boris.waguia@adorsys.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.adorsys.waguia.lightxls.loader;
/**
 * 
 * @author w2b
 *
 */
public class Person {
	private String personKeyBk;
	private String firstName;
	private String address ;
	
	private String ckeck ;
	public String getPersonKeyBk() {
		return personKeyBk;
	}
	public void setPersonKeyBk(String personKeyBk) {
		this.personKeyBk = personKeyBk;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCkeck() {
		return ckeck;
	}
	public void setCkeck(String ckeck) {
		this.ckeck = ckeck;
	}
	@Override
	public String toString() {
		return "Person [personKeyBk=" + personKeyBk + ", firstName="
				+ firstName + ", address=" + address + ", ckeck=" + ckeck + "]";
	}
	
	
}
