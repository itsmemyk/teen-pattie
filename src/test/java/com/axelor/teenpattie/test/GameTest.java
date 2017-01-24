package com.axelor.teenpattie.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.axelor.teenpattie.model.Card;
import com.axelor.teenpattie.model.Game;
import com.axelor.teenpattie.model.Player;
import com.axelor.teenpattie.model.RANK;
import com.axelor.teenpattie.model.SUIT;
import com.axelor.teenpattie.model.Table;
import com.axelor.teenpattie.model.TableFactory;

public class GameTest {
	Player p1;
	Player p2;
	
	Table teenPattie;
	
	public GameTest() {
		this.p1 = new Player("Player1");
		this.p2 = new Player("Player2");
		
		this.teenPattie = TableFactory.createTable();
		
		this.teenPattie.setPlayers(Arrays.asList(p1, p2));
	}
	
	@Test
	public void checkTrio() {
		
		teenPattie.setGames(Arrays.asList(
			new Game(p1, Arrays.asList(new Card(SUIT.HEART, RANK.ACE), new Card(SUIT.CLUB, RANK.ACE), new Card(SUIT.SPADE, RANK.ACE))),
			new Game(p2, Arrays.asList(new Card(SUIT.HEART, RANK.KING), new Card(SUIT.CLUB, RANK.KING), new Card(SUIT.SPADE, RANK.KING)))
		));
		
		Game winner = teenPattie.getWinner();
		
		assertEquals("Triple Logic", p1, winner.getPlayer());
	}
	
	@Test
	public void checkSequence() {
		
		teenPattie.setGames(Arrays.asList(
			new Game(p1, Arrays.asList(new Card(SUIT.HEART, RANK.ACE), new Card(SUIT.CLUB, RANK.TWO), new Card(SUIT.SPADE, RANK.THREE))),
			new Game(p2, Arrays.asList(new Card(SUIT.HEART, RANK.KING), new Card(SUIT.CLUB, RANK.JACK), new Card(SUIT.SPADE, RANK.QUEEN)))
		));
		
		Game winner = teenPattie.getWinner();
		
		assertEquals("Sequence Logic", p1, winner.getPlayer());
	}

	@Test
	public void checkPureSequence() {
		
		teenPattie.setGames(Arrays.asList(
			new Game(p1, Arrays.asList(new Card(SUIT.HEART, RANK.ACE), new Card(SUIT.CLUB, RANK.TWO), new Card(SUIT.SPADE, RANK.THREE))),
			new Game(p2, Arrays.asList(new Card(SUIT.HEART, RANK.KING), new Card(SUIT.HEART, RANK.JACK), new Card(SUIT.HEART, RANK.QUEEN)))
		));
		
		Game winner = teenPattie.getWinner();
		
		assertEquals("Pure Sequence Logic", p2, winner.getPlayer());
	}
	
	@Test
	public void checkColour() {
		
		teenPattie.setGames(Arrays.asList(
			new Game(p1, Arrays.asList(new Card(SUIT.HEART, RANK.TWO), new Card(SUIT.HEART, RANK.THREE), new Card(SUIT.HEART, RANK.FIVE))),
			new Game(p2, Arrays.asList(new Card(SUIT.HEART, RANK.ACE), new Card(SUIT.CLUB, RANK.KING), new Card(SUIT.CLUB, RANK.ACE)))
		));
		
		Game winner = teenPattie.getWinner();
		
		assertEquals("Colour Logic", p1, winner.getPlayer());
	}
	
	@Test
	public void checkDouble() {
		
		teenPattie.setGames(Arrays.asList(
			new Game(p1, Arrays.asList(new Card(SUIT.HEART, RANK.TWO), new Card(SUIT.CLUB, RANK.TWO), new Card(SUIT.SPADE, RANK.ACE))),
			new Game(p2, Arrays.asList(new Card(SUIT.HEART, RANK.THREE), new Card(SUIT.CLUB, RANK.THREE), new Card(SUIT.SPADE, RANK.FOUR)))
		));
		
		Game winner = teenPattie.getWinner();
		
		assertEquals("Double Logic", p2, winner.getPlayer());
	}
	
	@Test
	public void checkHigherOrder() {
		
		teenPattie.setGames(Arrays.asList(
			new Game(p1, Arrays.asList(new Card(SUIT.HEART, RANK.TWO), new Card(SUIT.CLUB, RANK.FOUR), new Card(SUIT.SPADE, RANK.ACE))),
			new Game(p2, Arrays.asList(new Card(SUIT.HEART, RANK.KING), new Card(SUIT.CLUB, RANK.QUEEN), new Card(SUIT.SPADE, RANK.TEN)))
		));
		
		Game winner = teenPattie.getWinner();
		
		assertEquals("Higher order Logic", p1, winner.getPlayer());
	}
}
