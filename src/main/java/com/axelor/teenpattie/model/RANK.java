package com.axelor.teenpattie.model;

public enum RANK {
	Ace(14, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K"),
    ;
	
    private final int value;
    private final String identity;
	
	RANK(int value, String identity) {
		this.value = value;
		this.identity = identity;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String getIdentity() {
		return this.identity;
	}
}
