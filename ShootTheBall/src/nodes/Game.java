package nodes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.ListIterator;

import elements.Ball;
import elements.MouseClick;

public class Game extends Node{
	
	//Balls
	LinkedList<Ball> balls;
	//Highscore
	int highscore = 0;
	long counterRed = 0;
    long counterBlue = 0;
    long counterGreen = 0;
    long counterEnd = 0;
    //Countdown
    Countdown cd;
    boolean isEnd = false;
	
	public Game(){
		balls = new LinkedList<Ball>();
		cd = new Countdown();
		nodes.add(cd);
	}
	
	@Override
	public void handleClick(MouseClick click){
		//game freeze if countdown is elapsed
		if(!cd.getElapsed()){
			super.handleClick(click);
		    for(Ball ball : balls){
	    		if(ball.isVisible()){
	    			//cursor coordinates = ball coordinates
	    			if(click.getX() > (ball.getXPosition()-ball.getRadius()) && click.getX() < (ball.getXPosition()+ball.getRadius())){
						if(click.getY() > (ball.getYPosition()-ball.getRadius()) && click.getY() < (ball.getYPosition()+ball.getRadius())){
							ball.setKilled(true);
							highscore+= ball.getPoints();
							break;
						}		
					}
	    		}
	    	}
		}
	}

	@Override
	public void update(long delta){
		//game freeze if countdown is elapsed
		if(!cd.getElapsed()){
			super.update(delta);
			
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
		}
		else{
			if(!isEnd){
				counterEnd += delta;
				if(counterEnd >= 2000) {
					//delete balls
					deleteBalls();
		    	}
			}
		}
	}
	
	public void deleteBalls(){
		isEnd = true;
		for(ListIterator<Ball> i = balls.listIterator(); i.hasNext();){
    	    Ball ball = i.next();
    	    if(ball.isVisible() && !ball.isKilled())
    	        i.remove();
    	}
	}
	
	@Override
	public void draw(Graphics2D g){
		if(!isEnd){
			super.draw(g);
		     
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
		}
		else{
			//after game go to menu
			display.setRoot(new Menu(highscore));
		}
	}
}
