package nodes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import elements.MouseClick;

public class Game extends Node{
	
	//Highscore
	int highscore = 0;
    //Countdown
    Countdown cd;
    boolean isEnd = false;
	
	public Game(){
		cd = new Countdown();
		nodes.add(cd);
		//add balltypes
		add(new BallController(1000, 1500, Color.RED, 100, 7, 10));  
		add(new BallController(1000, 1000, Color.BLUE, 70, 5, 20));  
		add(new BallController(1000, 2500, Color.GREEN, 90, 9, 5));  
	}
	
	@Override
	public void handleClick(MouseClick click){
		//game freeze if countdown is elapsed
		if(!cd.getElapsed()){
			super.handleClick(click);
		}
	}

	@Override
	public void update(long delta){
		//game freeze if countdown is elapsed
		if(!cd.getElapsed()){
			super.update(delta);
		}
		else{
			if(!isEnd){
				isEnd = true;
			}
		}
	}
	
	public void addPoints(int points){
		highscore=highscore+points;
	}
	
	@Override
	public void draw(Graphics2D g){
		if(!isEnd){
			super.draw(g);
			g.setColor(Color.YELLOW);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16)); 
			String number = String.valueOf(highscore);
			g.drawString(number, 310, 340);
		}
		else{
			//after game go to menu
			display.setRoot(new Menu(highscore));
		}
	}
}
