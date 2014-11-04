package elements;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import nodes.Node;

public class Display extends Canvas{

	private static final long serialVersionUID = 1L;
	//Board Size
	public static final int B_WIDTH = 350;
	public static final int B_HEIGHT = 350;
	//Window
	JFrame win;
	//Gameloop
	boolean isRunning = true;
	//Buffer
	BufferStrategy buff;
	//Buffer for mouseclicks
	Buffer<MouseClick> mouseBuff;
	//States
	Node root;
	
	public Display(){
		
		setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setIgnoreRepaint(true); //no automatic painting
        mouseBuff = new Buffer<MouseClick>();
		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent ev) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent ev) {
				mouseBuff.push(new MouseClick(ev.getX(), ev.getY()));
			}
			@Override
			public void mouseReleased(MouseEvent e) {}
        });
		setWindow();
	}
	
	public void exit(){
		isRunning = false;
		win.dispose();
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
	}
	
    public void init() {
    	win.setVisible(true); //show window
    	createBufferStrategy(2); //double buffer
    	buff = getBufferStrategy();
    	gameLoop();
    }
    
    public void setRoot(Node node) {
    	this.root = node;
    	node.setDisplay(this);
    }
    
    private void gameLoop(){
    	
        long delta = 0; //time difference
        long lastTime = System.currentTimeMillis();
        long currentTime;

        //infinite loop
        while (isRunning) {

        	currentTime = System.currentTimeMillis();
        	delta = currentTime - lastTime;
        	lastTime = currentTime;
        	
        	//handle mouseclicks
        	while(!mouseBuff.isEmpty()){
        		root.handleClick(mouseBuff.pop());
        	}
        	//update node
        	root.update(delta);
        	//drawing
        	Graphics2D g = (Graphics2D)buff.getDrawGraphics();
        	try {
        		g.setColor(Color.BLACK);
        		g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
        		root.draw(g);
        	} finally {
        			g.dispose();
        	}
        	buff.show();
        	Toolkit.getDefaultToolkit().sync();	
        }
    }
    
}
