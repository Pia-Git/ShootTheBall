package elements;

import java.awt.Dimension;

public class MenuButton {

	private String name;
	private int x;
	private int y;
	private Dimension size;
	
	public MenuButton(String name, int x, int y){
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public String getName(){
		return name;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Dimension getDimension(){
		return size;
	}
	
	public void setDimension(Dimension size){
		this.size = size;
	}
}
