package nodes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.ListIterator;

import elements.Ball;
import elements.Buffer;
import elements.MouseClick;

public class Game extends Node{
	
	//Balls
	LinkedList<Ball> balls;
	//Highscore
	int highscore = 0;
	long counterRed = 0;
    long counterBlue = 0;
    long counterGreen = 0;
	
	public Game(BufferStrategy buffer){
		buff = buffer;
		balls = new LinkedList<Ball>();
	}
	
	public void handleClicks(Buffer<MouseClick> mouseBuff){
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

	public void update(Graphics g, long delta){
		
		counterRed += delta;
    	counterBlue += delta;
    	counterGreen += delta;
    	
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
    	
    	for(ListIterator<Ball> i = balls.listIterator(); i.hasNext();){
    	    Ball ball = i.next();
    	    if(ball.isVisible() && !ball.isKilled())
    	    	ball.move(delta);
    	    else
    	        i.remove();
    	}
		
		draw(g);
	}
	
	public void draw(Graphics g){
		
		//Graphics g = null;
	     
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
