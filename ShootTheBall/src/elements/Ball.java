package elements;
import java.awt.Color;


public class Ball {

	//Board Size
    private final int B_WIDTH = 350;
    //RED Ball
	private int init_x;
	private int init_y;
	private int radius;
	boolean visible;
	boolean killed;
	private Color color;
	private float xPosition;
	private float yPosition;
    
    public Ball(String ballcolor){
    	//
    	switch (ballcolor){
    	case "red":
    		color = Color.RED;
    		init_x = -40;
    		init_y = 25;
    		radius = 8;
    		break;
    	case "blue":
    		color = Color.blue;
    		break;
    	case "yellow":
    		color = Color.yellow;
    		break;
    	}
    	//Startwerte
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
    
    public void move(long delta) {
    	//posX += (float)velocityInPixelPerSecond * delta * 0.001f
    	xPosition += 50 * delta * 0.001f;
        if (xPosition > (B_WIDTH+40))
            visible = false; //disapear
    }
}
