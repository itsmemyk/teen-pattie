package com.axelor.teenpattie.model;

public enum CARDS_PAIR {
	HIGHER_ORDER(0),
	DOUBLE(50000),
	COLOUR(40000000),
	SEQUENCE(80000000),
	PURE_SEQUENCE(160000000),
	TRIO(320000000),
	;
	
	private final int value;
	
	CARDS_PAIR(int value) {
		this.value = value;
	}
	
	int getValue() {
		return this.value;
	}
}
