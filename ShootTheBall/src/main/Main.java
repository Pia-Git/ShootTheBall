package main;
import java.awt.EventQueue;

import elements.GamePlay;

public class Main {

public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {                
                GamePlay game = new GamePlay();   
                game.init();
            }
        });
    }
}
