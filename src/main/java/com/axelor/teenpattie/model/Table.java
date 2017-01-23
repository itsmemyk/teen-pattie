package com.axelor.teenpattie.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
	
	private static final int TOTAL_CARDS = 52;
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
	
	public void printCards() {
		cards.forEach((card) -> {			
			LOGGER.info(card.getSuit().getSymbol() + card.getRank().getIdentity());
		});
	}
	
	private int getShuffleValue(int maxSize) {
		Random r = new Random();
		return r.nextInt(maxSize);
	}
	
	private int getShuffleValue() {
		return this.getShuffleValue(TOTAL_CARDS);
	}
	
	public void shuffle() {
		int offset = this.getShuffleValue();
		List<Card> newCards = new ArrayList<>(TOTAL_CARDS);
		
		List<Card> upCards = this.cards.stream().skip(offset).collect(Collectors.toList());
		List<Card> downCards = this.cards.stream().limit(offset).collect(Collectors.toList());
		Collections.reverse(downCards);		
		
		newCards.addAll(upCards);
		newCards.addAll(downCards);
		
		this.cards = newCards;
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
		Optional<Game> winner = this.games.stream().max(Comparator.comparing((g) -> g.result().getValue() + g.resultTotal()));
		
		this.games.forEach((game) -> {
			LOGGER.info("=======================================");
			LOGGER.info("Player : " + game.getPlayer().getName());
			LOGGER.info("Game : " + game.getCardsAsString());
			LOGGER.info("Result : " + game.result().toString());
			LOGGER.info("=======================================");
		});
		
		LOGGER.info("***********************************************");
		LOGGER.info("Winner : " + winner.get().getPlayer().getName());
		LOGGER.info("Card : " + winner.get().getCardsAsString());
		LOGGER.info("Result : " + winner.get().result());
		LOGGER.info("***********************************************");
	}
}
