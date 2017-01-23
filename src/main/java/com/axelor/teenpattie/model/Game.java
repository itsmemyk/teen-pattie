package com.axelor.teenpattie.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axelor.teenpattie.Main;


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
	
	public int resultTotal() {
		List<Card> myCards = this.cards;
		Card first = myCards.get(0);
		Card second = myCards.get(1);
		Card third = myCards.get(2);
		
		return first.getDualValue() + second.getDualValue() + third.getDualValue(); 
	}
}
