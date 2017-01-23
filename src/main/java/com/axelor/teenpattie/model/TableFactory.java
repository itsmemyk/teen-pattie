package com.axelor.teenpattie.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableFactory {
	private static List<Card> cards = new ArrayList<>(52);
	
	public static Table createTable() {
		if (cards.size() == 0) {
			for(SUIT suit  : SUIT.values()) {
				for(RANK rank : RANK.values()) {
					cards.add(new Card(suit, rank));
				}
			}
		}		
		return new Table(cards);
	} 
}
