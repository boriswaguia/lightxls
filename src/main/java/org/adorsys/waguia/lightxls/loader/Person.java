package org.adorsys.waguia.lightxls.loader;

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
