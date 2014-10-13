package elements;

import java.util.LinkedList;

public class Buffer<T> {

	private final LinkedList<T> buffer = new LinkedList<>();
	
	public synchronized T pop(){
		while(buffer.isEmpty())
			try {wait();} catch(InterruptedException ex) {}
		
		T content = buffer.remove(0);
		notifyAll();
		
		return content;
	}

	public synchronized void push(T data) {
		buffer.add(data);
		notifyAll();
	}
	
	public synchronized boolean isEmpty() {
		return buffer.isEmpty();
	}

	public int getSize() {
		return buffer.size();
	}
	
}
