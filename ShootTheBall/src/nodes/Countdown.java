package nodes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import elements.MouseClick;

public class Countdown extends Node{
	
	//countdown
	private long counter;
	private boolean isElapsed = false;
	String count = "1:00";
	
	public Countdown(){
		//60 seconds
		counter = 60000; 
	}
	
	@Override
	public void handleClick(MouseClick click){
		super.handleClick(click);
	}

	@Override
	public void update(long delta){
		super.update(delta);
		if(!isElapsed){
			counter = counter-delta;
			count = millisecondsToMinute(counter);
			if(counter <= 0){
				isElapsed = true;
				//stop game
				System.out.println("countdown elapsed");
			}
		}
	}
	
	@Override
	public void draw(Graphics2D g){
		super.draw(g);
		if(!isElapsed){
			g.setColor(Color.YELLOW);
		}else{
			g.setColor(Color.RED);
		}
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16)); 
		String number = String.valueOf(count);
		g.drawString(number, 310, 20);
	}
	
	private String millisecondsToMinute(long milli){
		long seconds = milli/1000;
		long minute = seconds/60;
		if(seconds < 10){
			return minute+":0"+seconds;
		}else{
			return minute+":"+seconds;
		}
	}
	
	public boolean getElapsed(){
		return isElapsed;
	}

}
