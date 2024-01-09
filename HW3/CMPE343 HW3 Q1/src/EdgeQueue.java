//-----------------------------------------------------
// Title: Edge Queue class
// Author: Doðukan Us
// ID: 47113751436
// Section: 03
// Assignment: 3
// Description: This class provides a queue structure for Edge objects, inspired from textbook and previous course's slides
//-----------------------------------------------------

public class EdgeQueue {
	
	private int length; //number of elements in the queue
	QNode front,rear; //front and rear nodes of the queue
	
	//constructor that initializes the length and the nodes
	public EdgeQueue() {
		length = 0;
		front = null;
		rear = null;
	}
	
	//adds the edge given as parameter to the queue
	public void enQueue(Edge e) {
		
		QNode n = new QNode(e);
		
		if(isEmpty()) {
			front = n;
		}
		else {
			rear.next = n;
		}
		rear = n;
		length++;
	}
	
	// pops the front element from the queue and returns it
	public Edge deQueue() throws Exception{
		
        if (isEmpty()) {
        	throw new Exception ("Empty");
        }
        
        Edge dequeued = front.e;
        front = front.next;
        length--;
        
        if (isEmpty()) {
        	rear = null;
        }
        
        return dequeued;
    }
	
	// returns true if there are no elements in the queue
	public boolean isEmpty(){
        return (length == 0);
	}
	
	// returns the number of elements
	public int length() {
		return this.length;
	}

}

//-----------------------------------------------------
//Title: QNode class
//Author: Doðukan Us
//ID: 47113751436
//Section: 03
//Assignment: 3
//Description: This class is a helper class that provides Node structure for the queue
//-----------------------------------------------------

class QNode {
	
	Edge e;
	QNode next;
	
	public QNode(Edge e){
		this.e = e;
		next = null;
		
	}
}