import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl<T> implements StringStack<T> {
	private MyList<T> list;
	
	/** 
	 * Constructor creates an empty list for the stack.
	 */
	public StringStackImpl(){
		list=new MyList();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void push(T item) {
		list.insertAtFront(item);
	}
	
	public T pop() throws NoSuchElementException {
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
	
	public void printStack(PrintStream stream) {
        list.printStack(stream);
	}
	
	public int size() {
		return list.size();
	}
}


