package com.axelor.teenpattie.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Table {
	private Date playAt;
	private List<Player> players = new ArrayList<>();
	private List<Game> games = new ArrayList<>();
	private List<Card> cards = new ArrayList<>(52);
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Table.class);
	
	public Table(List<Card> cards) {
		this.cards = cards;
		this.playAt = new Date();
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	private static int getShuffleValue(int maxSize) {
		Random r = new Random();
		return r.nextInt(maxSize);
	}
	
	private static List<Card> shuffleCards(List<Card> cards) {
		if (cards.size() <= 2) {
			return cards;
		}
		
		int suffleOffset = getShuffleValue(cards.size());
		List<Card> newCards = new ArrayList<>(cards.size());
		
		// retrieving down to up moved fix cards
		List<Card> upCards = cards.subList(suffleOffset, cards.size());
		newCards.addAll(shuffleCards(upCards));
		
		// reversing up to down moved fix cards
		List<Card> downCards = cards.subList(0, suffleOffset);
		newCards.addAll(downCards);
		
		return newCards;
	}
	
	public void shuffle() {
		this.cards = shuffleCards(this.cards);
	}
	
	public void shuffle(int times) {
		for(int i = 0; i < times; i++) {
			this.shuffle();
		}
	}
	
	public void play() {
		int totalPlayers = this.players.size();
		
		for(int i=0; i < totalPlayers; i++) {
			Player player = this.players.get(i);
			Card first = this.cards.get(i);
			Card second = this.cards.get(i+(totalPlayers*1));
			Card third = this.cards.get(i+(totalPlayers*2));
			this.games.add(new Game(player, Arrays.asList(first, second, third)));
		}
		
	}
	
	public void show() {
		LOGGER.info("Start At" + this.playAt.toLocaleString());
		Game winner = Collections.max(this.games, Comparator.comparing(g -> g.getResult().getValue() + g.getResultTotal()));
		
		this.games.forEach((game) -> {
			LOGGER.info("=======================================");
			LOGGER.info("Player : " + game.getPlayer().getName());
			LOGGER.info("Game : " + game.getCardsAsString());
			LOGGER.info("Result : " + game.getResult().toString());
			LOGGER.info("=======================================");
		});
		
		LOGGER.info("***********************************************");
		LOGGER.info("Winner : " + winner.getPlayer().getName());
		LOGGER.info("Card : " + winner.getCardsAsString());
		LOGGER.info("Result : " + winner.getResult());
		LOGGER.info("***********************************************");
	}
	
	public void printCards(List<Card> cards) {
		LOGGER.info("Total " + cards.size());
		String str = "";
		Iterator<Card> cardIterator = cards.iterator();
		
		while(cardIterator.hasNext()) {
			str += cardIterator.next().toString() + " ";
		}
		
		LOGGER.info(str);
	}
	
	public void printCards() {
		this.printCards(this.cards);
	}
}
