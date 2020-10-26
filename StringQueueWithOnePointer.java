import java.io.PrintStream;
import java.util.NoSuchElementException; 

/**
 * This class implements a circular queue with one pointer that uses nodes with "previous" and "next" field.
 */
public class StringQueueWithOnePointer<T>  {
	private DoublePointerNode<T> pointer;
	
	public StringQueueWithOnePointer() {}
	
	public boolean isEmpty() {
		return pointer==null;
	}
	
	public void put(T item) {
        DoublePointerNode<T> node=new DoublePointerNode(item);
		if (isEmpty()) {
			pointer=node;
			pointer.nextNode=node;
			pointer.previousNode=node;
		}
		else if (pointer.previousNode==pointer) {
			pointer.nextNode=node;
			pointer.previousNode=node;
			node.nextNode=pointer;
			node.previousNode=pointer;
		}
		else {
			pointer.previousNode.nextNode=node;
			node.previousNode=pointer.previousNode;
			node.nextNode=pointer;
			node.nextNode=pointer;
			pointer.previousNode=node;
		}
	}
	
	public T get() throws NoSuchElementException {

		if (isEmpty())
			throw new NoSuchElementException();
		
		T removedItem=pointer.nodeData;
        if (pointer.previousNode==pointer)
			pointer=null;
		else {
			pointer.previousNode.nextNode=pointer.nextNode;
			pointer=pointer.nextNode;
		}
		
		
		return removedItem;
	}
	
	public T peek() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();
		
		return pointer.nodeData;
	}
	
	public void printQueue(PrintStream stream) {
        DoublePointerNode<T> current=pointer;
		
		while (true) {
			stream.println(current.nodeData);
			current=current.nextNode;
			if (current==pointer)
				break;
		stream.flush();
		}
	}
	
	public int size() {
		int elements=0;
		DoublePointerNode<T> current=pointer;
        if (!isEmpty()) {		
		    while (true) {
			    elements++;
			    current=current.nextNode;
			    if (current==pointer)
				    break;
		    }
		}
		return elements;
	}
}



    