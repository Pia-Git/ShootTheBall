package elements;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JFrame;

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
	//Balls
	LinkedList<Ball> balls;
	//Highscore
	int highscore = 0;
	
	public GamePlay(){
		setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setIgnoreRepaint(true); //no automatic painting
        balls = new LinkedList<Ball>();
        mouseBuff = new Buffer<MouseClick>();
		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent ev) {
				System.out.println("click!");
				mouseBuff.push(new MouseClick(ev.getX(), ev.getY()));
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
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
        long counterRed = 0;
        long counterBlue = 0;
        long counterGreen = 0;

        //infinite loop
        while (true) {
        	
        	//handle mouseclicks
        	while(!mouseBuff.isEmpty()){
        	    MouseClick click = mouseBuff.pop();
        	    for(Ball ball : balls){
		    		if(ball.isVisible()){
		    			//cursor coordinates = ball coordinates
		    			if(click.getX() > (ball.getXPosition()-ball.getRadius()) && click.getX() < (ball.getXPosition()+ball.getRadius())){
							if(click.getY() > (ball.getYPosition()-ball.getRadius()) && click.getY() < (ball.getYPosition()+ball.getRadius())){
								System.out.println("TREFFER!");
								ball.setKilled(true);
								highscore+= ball.getPoints();
								break;
							}		
						}
		    		}
		    	}
        	}

        	currentTime = System.currentTimeMillis();
        	delta = currentTime - lastTime;
        	
        	counterRed += delta;
        	counterBlue += delta;
        	counterGreen += delta;

        	//every seconds
        	if(counterBlue >= 1500) {
        		Ball blueBall = new Ball("blue");
        		balls.add(blueBall);
        	    counterBlue -= 1500;
        	}
        	if(counterRed >= 2000) {
        		Ball redBall = new Ball("red");
        		balls.add(redBall);
        	    counterRed -= 2000;
        	}
        	if(counterGreen >= 3000) {
        		Ball greenBall = new Ball("green");
        		balls.add(greenBall);
        	    counterGreen -= 3000;
        	}
        	lastTime = currentTime;
        	//move balls
        	for(ListIterator<Ball> i = balls.listIterator(); i.hasNext();){
        	    Ball ball = i.next();
        	    if(ball.isVisible() && !ball.isKilled())
        	    	ball.move(delta);
        	    else
        	        i.remove();
        	}
        	drawStuff();
        }
    }
    
    private void drawStuff() {
    	Graphics g = null;
     
    	try {
    		g = buff.getDrawGraphics();
    		g.setColor(Color.BLACK);
    		g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
    		g.setColor(Color.YELLOW);
    		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
    		String number = String.valueOf(highscore);
    		g.drawString(number, 310, 340);
    		for(Ball ball : balls){
	    		if(ball.isVisible()){
	    			g.setColor(ball.getColor());
		    		g.fillOval((int)ball.getXPosition()-ball.getRadius(), (int)ball.getYPosition()-ball.getRadius(), ball.getRadius()*2, ball.getRadius()*2);
	    		}
	    	}
    	} finally {
    		g.dispose();
    	}
    	buff.show();
        Toolkit.getDefaultToolkit().sync();	
    }
}
