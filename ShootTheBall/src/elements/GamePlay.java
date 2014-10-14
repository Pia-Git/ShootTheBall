package elements;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import nodes.Game;
import nodes.Menu;

public class GamePlay extends Canvas{

	private static final long serialVersionUID = 1L;
	//Board Size
	private final int B_WIDTH = 350;
	private final int B_HEIGHT = 350;
	//Window
	JFrame win;
	//Buffer
	BufferStrategy buff;
	//Buffer for mouseclicks
	Buffer<MouseClick> mouseBuff;
	//States
	Menu menu;
	Game game;
	
	public GamePlay(){
		setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setIgnoreRepaint(true); //no automatic painting
        mouseBuff = new Buffer<MouseClick>();
        game = new Game(buff);
		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent ev) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent ev) {
				System.out.println("click!");
				mouseBuff.push(new MouseClick(ev.getX(), ev.getY()));
			}
			@Override
			public void mouseReleased(MouseEvent e) {}
        });
		setWindow();
	}
	
	private void setWindow(){
		JFrame window = new JFrame(); //window for canvas
		window.setTitle("Shoot the Ball!");    
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setIgnoreRepaint(true);
    	window.add(this);
    	window.setResizable(false);
    	window.pack();
    	win = window;
	}
	
    public void init() {
    	win.setVisible(true); //show window
    	createBufferStrategy(2); //double buffer
    	buff = getBufferStrategy();
    	gameLoop();
    }
    
    private void gameLoop(){
    	
        long delta = 0; //time difference
        long lastTime = System.currentTimeMillis();
        long currentTime;
        Graphics g = null;

        //infinite loop
        while (true) {
        	
        	//handle mouseclicks
        	while(!mouseBuff.isEmpty()){
        	    game.handleClicks(mouseBuff);
        	}

        	currentTime = System.currentTimeMillis();
        	delta = currentTime - lastTime;
        	
        	game.update(g, delta);

        	lastTime = currentTime;
        }
    }
    
}
