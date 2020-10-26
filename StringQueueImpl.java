import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringQueueImpl<T> implements StringQueue<T> {
	private MyList<T> list;
	
	/** 
	 * Constructor creates an empty list for the queue.
	 */
	public StringQueueImpl(){
		list=new MyList();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void put(T item) {
		list.insertAtBack(item);
	}
	
	public T get() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();
		
		T removedItem=list.firstNode.nodeData;
		list.removeFromFront();
		
		return removedItem;
	}
	
	public T peek() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();
		
		return list.firstNode.nodeData;
	}
	
	public void printQueue(PrintStream stream) {
        list.printStack(stream);
	}
	
	public int size() {
		return list.size();
	}
}

