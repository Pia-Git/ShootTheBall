package elements;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GamePlay extends Canvas{

	private static final long serialVersionUID = 1L;
	//Board Size
	private final int B_WIDTH = 350;
	private final int B_HEIGHT = 350;
	//Window
	JFrame win;
	//Buffer
	BufferStrategy buff;
	//Balls
	ArrayList<Ball> balls;
	
	public GamePlay(){
		setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setIgnoreRepaint(true); //no automatic painting
        balls = new ArrayList<Ball>();
		setWindow();
	}
	
	private void setWindow(){
		JFrame window = new JFrame(); //window for canvas
		window.setTitle("Shoot the Ball!");    
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setIgnoreRepaint(true);
    	window.add(this);
    	window.setResizable(false);
    	window.pack();
    	win = window;
    	
    	/*this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent ev) {
				//balls durchlaufen
				if(ev.getX() > (x-RADIUS_RED) && ev.getX() < (x+RADIUS_RED)){
					if(ev.getY() > (y-RADIUS_RED) && ev.getY() < (y+RADIUS_RED))
						System.out.println("TREFFER!");
					else
						System.out.println("kein Treffer!");			
				}
				else{
					System.out.println("kein Treffer!");
				}
			}
        });*/
	}
	
    public void init() {
    	win.setVisible(true); //show window
    	createBufferStrategy(2); //double buffer
    	buff = getBufferStrategy();
    	gameLoop();
    }
    
    private void gameLoop(){
    	
        long delta = 0; //time difference
        long lastTime = System.currentTimeMillis();
        long currentTime;
        long counter = 0;

        //infinite loop
        while (true) {

        	currentTime = System.currentTimeMillis();
        	delta = currentTime - lastTime;
        	
        	counter += delta;
        	//every 2 seconds
        	if( counter >= 2000 ) {
        		Ball redBall = new Ball("red");
        		balls.add(redBall);
        	    counter -= 2000;
        	}
        	lastTime = currentTime;
        	//move balls
        	for(Ball ball : balls){
	    		if(ball.isVisible()){
	    			System.out.println("move ball");
	    			ball.move(delta);
	    		}
	    	}
        	drawStuff();
        }
    }
    
    private void drawStuff() {
    	Graphics g = null;
     
    	try {
    		g = buff.getDrawGraphics();
    		g.setColor(Color.BLACK);
    		g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
    		//System.out.println("draw balls!");
    		for(Ball ball : balls){
	    		if(ball.isVisible()){
	    			System.out.println("draw ball!");
	    			g.setColor(ball.getColor());
		    		g.fillOval((int)ball.getX()-ball.getRadius(), (int)ball.getY()-ball.getRadius(), ball.getRadius()*2, ball.getRadius()*2);
	    		}
	    	}
    	} finally {
    		g.dispose();
    	}
    	buff.show();
        Toolkit.getDefaultToolkit().sync();	
    }
}
