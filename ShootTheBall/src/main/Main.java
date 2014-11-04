package main;

import nodes.Menu;
import elements.Display;

public class Main {
	
	public static void main(String[] args) {
		Display game = new Display();  
		game.setRoot(new Menu(0));
		game.init();
	}

}
