package com.axelor.teenpattie.model;

public enum CARDS_PAIR {
	HIGHER_ORDER(50000),
	DOUBLE(100000),
	COLOUR(200000),
	SEQUENCE(400000),
	PURE_SEQUENCE(800000),
	TRIO(1600000),
	;
	
	private final int value;
	
	CARDS_PAIR(int value) {
		this.value = value;
	}
	
	int getValue() {
		return this.value;
	}
}
