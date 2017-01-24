package com.axelor.teenpattie.model;

public class Card {
	private SUIT suit;
	private RANK rank;
	
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
	
	public int getRankValue() {
		return this.rank.getValue();
	}
	
	public int getDualValue() {
		int sqUp = this.getRankValue() - 2;
		return (int) Math.pow(2, sqUp);
	}
}
