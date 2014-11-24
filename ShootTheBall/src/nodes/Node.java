package nodes;

import java.awt.Graphics2D;
import java.util.ArrayList;

import elements.Display;
import elements.MouseClick;

public class Node {
	
	final ArrayList<Node> nodes = new ArrayList<Node>();
	Display display;
	protected Node parent = null;

	public Node(){}
	
	public void update(long delta){
		for(Node node : nodes){
			node.update(delta);
		}
	}
	
	public void draw(Graphics2D g){
		for(Node node : nodes){
			node.draw(g);
		}
	}
	
	public void handleClick(MouseClick click){
		for(Node node : nodes){
			node.handleClick(click);
		}
	}
	
	public void setDisplay(Display display){
		this.display = display;
	}
	
	public void add(Node node){
	    nodes.add(node);
	    node.parent = this;
	}
}
