package nodes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import elements.Ball;
import elements.MouseClick;

public class BallController extends Node{

	//Balls
	LinkedList<Ball> balls;
	long minSpawnInterval;
	long maxSpawnInterval;
	Color color;
	int speed;
	int radius;
	int points;
	long counter = 0;
	Random random;
	
	public BallController(long minSpawnInterval, long maxSpawnInterval, Color color, int speed, int radius, int points){
		balls = new LinkedList<Ball>();
		random = new Random();
		this.minSpawnInterval = minSpawnInterval; //milliseconds
		this.maxSpawnInterval = maxSpawnInterval;
		this.color = color;
		this.speed = speed;
		this.radius = radius;
		this.points = points;
	}
	
	@Override
	public void handleClick(MouseClick click){
		super.handleClick(click);
		for(Ball ball : balls){
    		if(ball.isVisible()){
    			//cursor coordinates = ball coordinates
    			if(click.getX() > (ball.getXPosition()-radius) && click.getX() < (ball.getXPosition()+radius)){
					if(click.getY() > (ball.getYPosition()-radius) && click.getY() < (ball.getYPosition()+radius)){
						ball.setKilled(true);
						((Game)this.parent).addPoints(points);
						break;
					}		
				}
    		}
    	}
	}

	@Override
	public void update(long delta){
		super.update(delta);
		counter += delta;
		
		long rndIntervall = (long)(random.nextFloat() * (maxSpawnInterval - minSpawnInterval) + minSpawnInterval);
		
    	if(counter >= rndIntervall) {
    		float rndY = random.nextFloat() * (250 - 45) + 45;
    		Ball ball = new Ball(color, speed, radius, points, -radius, rndY); 
    		balls.add(ball);
    	    counter -= rndIntervall;
    	}
    	
    	for(ListIterator<Ball> i = balls.listIterator(); i.hasNext();){
    	    Ball ball = i.next();
    	    if(ball.isVisible() && !ball.isKilled())
    	    	ball.move(delta);
    	    else
    	        i.remove();
    	}
	}
	
	@Override
	public void draw(Graphics2D g){
		super.draw(g);
	    
		for(Ball ball : balls){
    		if(ball.isVisible()){
    			g.setColor(color);
	    		g.fillOval((int)ball.getXPosition()-radius, (int)ball.getYPosition()-radius, radius*2, radius*2);
    		}
    	}
	}
	
	public void deleteBalls(){
		for(ListIterator<Ball> i = balls.listIterator(); i.hasNext();){
    	    Ball ball = i.next();
    	    if(ball.isVisible() && !ball.isKilled())
    	        i.remove();
    	}
	}
		
}
