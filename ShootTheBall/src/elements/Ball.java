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
	private Color color;
	private int x;
	private int y;
    //
    public Ball(String ballcolor){
    	switch (ballcolor){
    	case "red":
    		color = Color.red;
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
    	x = init_x;
    	y = init_y;
    	visible = true;
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

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    public int getX(){
    	return x;
    }

    public int getY(){
    	return y;
    }
    
    public void move(long delta) {
    	//posX += (float)velocityInPixelPerSecond * delta * 0.001f
    	x += 30 * delta * 0.001f;
    	System.out.println(x);
        if (x > B_WIDTH)
            visible = false; //disapear
    }
}
