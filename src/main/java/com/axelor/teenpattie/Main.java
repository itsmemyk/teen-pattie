package com.axelor.teenpattie;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axelor.teenpattie.model.Player;
import com.axelor.teenpattie.model.Table;
import com.axelor.teenpattie.model.TableFactory;

public class Main {
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		Player p1 = new Player("Mayank");
		Player p2 = new Player("Umesh");
		Player p3 = new Player("Amit");
		Player p4 = new Player("Bhavik");
		Player p5 = new Player("Akshay");
		
		Table teenPattie = TableFactory.createTable();
		
		// set 5 players
		teenPattie.setPlayers(Arrays.asList(p1, p2, p3, p4, p5));
		
		// shuffle for 100 times
		teenPattie.shuffle(100);
		
		// lets play		
		teenPattie.play();
		
		// show on pot
		teenPattie.show();
		
    }
}

