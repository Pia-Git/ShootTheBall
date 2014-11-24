package elements;
import java.awt.Color;


public class Ball {

	//Board Size
    private final int B_WIDTH = 350;
	private int init_x;
	private float init_y;
	private int radius;
	boolean visible;
	boolean killed;
	private Color color;
	private float xPosition;
	private float yPosition;
	private int points;
	private int speed;
    
    public Ball(Color color, int speed, int radius, int points, int x, float y){
    	
    	this.color = color;
		init_x = x;
		init_y = y;
		this.radius = radius;
		this.speed = speed;
		this.points = points;
    	//start 
    	xPosition = init_x;
    	yPosition = init_y;
    	visible = true;
    	killed = false;
    }
    
    public Color getColor(){
    	return color;
    }
    
    public int getRadius(){
    	return radius;
    }
    
    public boolean isVisible() {
        return visible;
    }
    
    public boolean isKilled() {
        return killed;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    public void setKilled(Boolean killed) {
        this.killed = killed;
    }
    
    public float getXPosition(){
    	return xPosition;
    }

    public float getYPosition(){
    	return yPosition;
    }
    
    public int getPoints(){
    	return points;
    }
    
    public void move(long delta) {
    	//posX += (float)velocityInPixelPerSecond * delta * 0.001f
    	xPosition += speed * delta * 0.001f;
        if (xPosition > (B_WIDTH+radius) || xPosition < -radius)
            visible = false; //disappear
    }
}
