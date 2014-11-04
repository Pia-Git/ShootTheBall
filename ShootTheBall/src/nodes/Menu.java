package nodes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import elements.MenuButton;
import elements.MouseClick;

public class Menu extends Node{

	Font f;
	Font recordF;
	private int padding;
	LinkedList<MenuButton> buttons;
	Boolean isSetting = false;
	FontMetrics metrics;
	int record = 0;
	
	public Menu(int highscore){
		f = new Font(Font.SANS_SERIF, Font.BOLD, 16);
		recordF = new Font(Font.SANS_SERIF, Font.BOLD, 14);
		padding = 2;
		record = highscore;
		buttons = new LinkedList<MenuButton>();
		setButtons();
	}

	public void setButtons(){
		buttons.add(new MenuButton("START", 150, 140));
		buttons.add(new MenuButton("EXIT", 150, 170));
	}
	
	@Override
	public void handleClick(MouseClick click){
		super.handleClick(click);
	    for(MenuButton button : buttons){
	    	//cursor coordinates = menu coordinates
			if(click.getX() > button.getX() && click.getX() < (button.getX() + button.getDimension().width)){
				if(click.getY() > button.getY()-metrics.getMaxAscent() && click.getY() < (button.getY()-metrics.getMaxAscent() + button.getDimension().height)){
					System.out.println("Click on " + button.getName());
					if(button.getName() == "START" || button.getName() == "NEW GAME"){
						display.setRoot(new Game());
					}
					if(button.getName() == "EXIT"){
						display.exit();
					}
					break;
				}		
			}
	    }
	}

	@Override
	public void update(long delta){
		super.update(delta);
	}
	
	@Override
	public void draw(Graphics2D g){
		super.draw(g);
	    
		g.setColor(Color.YELLOW);
		g.setFont(recordF);
		g.drawString("LAST RECORD:", 80, 80);
		g.drawString(String.valueOf(record), 200, 80);
		g.setFont(f); 
		
		for(MenuButton button : buttons){
			g.drawString(button.getName(), button.getX(), button.getY());
			metrics = g.getFontMetrics(f);
			if(!isSetting){
				int hgt = metrics.getHeight();
				int adv = metrics.stringWidth(button.getName());
				button.setDimension(new Dimension(adv+padding, hgt+padding));
			}
	    }
		isSetting = true;
	}
}
