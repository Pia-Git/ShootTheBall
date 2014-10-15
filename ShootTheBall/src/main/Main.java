package main;

import nodes.Game;
import elements.Display;

public class Main {
	
	public static void main(String[] args) {
		Display game = new Display();  
		game.setRoot(new Game());
		game.init();
	}

}
