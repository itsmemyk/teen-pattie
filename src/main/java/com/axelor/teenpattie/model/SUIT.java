package com.axelor.teenpattie.model;

public enum SUIT {
	HEART('♥'),
	DIAMOND('♦'),
	SPADE('♠'),
	CLUB('♣')
	;
	
    private final char symbol;
    
    SUIT(char symbol) {
    	this.symbol = symbol;
    }
    
    public char getSymbol() {
    	return this.symbol;
    }
}
