package com.axelor.teenpattie.model;

import java.util.List;
import java.util.Map;

public class Card {
	private SUIT suit;
	private RANK rank;
	private static int[] rankDualValues = new int[14];
	
	static {
		initDuals();
	}
	
	private static void initDuals() {
		int c = 1;
		int n = 1;
		while (c <= RANK.values().length) {
			rankDualValues[c] = n;
			c++;
			n = n * 2;
		}
		
	}
	
	public Card(SUIT suit, RANK rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public SUIT getSuit() {
		return suit;
	}

	public void setSuit(SUIT suit) {
		this.suit = suit;
	}

	public RANK getRank() {
		return rank;
	}

	public void setRank(RANK rank) {
		this.rank = rank;
	}
	
	public String toString() {
		return this.suit.getSymbol() + this.rank.getIdentity();
	}
	
	public int getDualValue() {
		return rankDualValues[this.rank.getValue()-1];
	}
}
