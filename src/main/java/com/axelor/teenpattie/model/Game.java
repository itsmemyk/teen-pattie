package com.axelor.teenpattie.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axelor.teenpattie.Main;

enum CARDS_PAIR {
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

public class Game {
	private Player player;
	private List<Card> cards = new ArrayList<>(3);

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	public Game(Player player) {
		this.player = player;
	}
	
	public Game(Player player, List<Card> cards) {
		this.player = player;
		this.cards = cards;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	public String getCardsAsString() {
		String result = "";
		Iterator<Card> cards = this.cards.iterator();
		
		while(cards.hasNext()) {
			Card card = cards.next();
			result += " " + card;
		}
		
		return result;
	}
	
	private static boolean checkTrio(Card first, Card second, Card third) {
		return first.getRank() == second.getRank() && second.getRank() == third.getRank();
	}
	
	private static boolean checkSequence(Card first, Card second, Card third) {
		if (first.getRank() == RANK.Ace && second.getRank() == RANK.TWO && third.getRank() == RANK.THREE) return true;
		return (first.getRank().getValue() + 1 == second.getRank().getValue()) && (second.getRank().getValue() + 1 == third.getRank().getValue());
	}
	
	private static boolean checkColour(Card first, Card second, Card third) {
		return first.getSuit() == second.getSuit() && second.getSuit() == third.getSuit();
	}
	
	private static boolean checkDouble(Card first, Card second, Card third) {
		return first.getRank() == second.getRank() || first.getRank() == third.getRank() || second.getRank() == third.getRank();
	}
		
	public CARDS_PAIR result() {
		CARDS_PAIR result = CARDS_PAIR.HIGHER_ORDER;
		List<Card> myCards = this.cards;
		Collections.sort(myCards, new Comparator<Card>() {
			@Override
			public int compare(Card c1, Card c2) {
				return c1.getRank().getValue() - c2.getRank().getValue();
			}
		});
		Card first = myCards.get(0);
		Card second = myCards.get(1);
		Card third = myCards.get(2);
		
		if (checkTrio(first, second, third)) {
			result = CARDS_PAIR.TRIO;
		} else if (checkSequence(first, second, third) && checkColour(first, second, third)) {
			result = CARDS_PAIR.PURE_SEQUENCE;
		} else if (checkSequence(first, second, third)) {
			result = CARDS_PAIR.SEQUENCE;
		} else if (checkColour(first, second, third)) {
			result = CARDS_PAIR.COLOUR;
		} else if (checkDouble(first, second, third)) {
			result = CARDS_PAIR.DOUBLE;
		}
		
		return result;
	}
	
	public int resultTotal() {
		List<Card> myCards = this.cards;
		Card first = myCards.get(0);
		Card second = myCards.get(1);
		Card third = myCards.get(2);
		
		return first.getDualValue() + second.getDualValue() + third.getDualValue(); 
	}
}
