package com.vending.enums;

public enum Coins {
	Quater("0.25"),Dime("0.15"),Nickel("0.05"),Penny("0.01");

	
	String val;

	

	public String getValue() {
		return val;
	}
	
	
	Coins(String d) {

		this.val=d;
	}

}
