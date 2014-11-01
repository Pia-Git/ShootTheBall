package nodes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import elements.MouseClick;

public class Countdown extends Node{
	
	//countdown
	long counter;
	boolean isRunning = true;
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
		if(isRunning){
			counter = counter-delta;
			millisecondsToMinute(counter);
			System.out.println(counter);
			if(counter <= 0){
				isRunning = false;
				//spiel anhalten!
				System.out.println("STOP!");
			}
		}
	}
	
	@Override
	public void draw(Graphics2D g){
		super.draw(g);
		//countdown text
		if(isRunning){
			g.setColor(Color.YELLOW);
		}else{
			g.setColor(Color.RED);
		}
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16)); 
		String number = String.valueOf(count);
		g.drawString(number, 310, 20);
		
	}
	
	private void millisecondsToMinute(long milli){
		long seconds = milli/1000;
		long minute = seconds/60;
		if(seconds < 10){
			count = minute+":0"+seconds;
		}else{
			count = minute+":"+seconds;
		}
	}

}
