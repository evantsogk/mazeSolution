/**
 * DoublePointerNode object is like MyNode but has a previous node pointer too.
 */
public class DoublePointerNode<T> {
	public DoublePointerNode<T> previousNode, nextNode;
	public T nodeData;
	
	public DoublePointerNode (T nodeData) {
		this.nodeData=nodeData;
		this.previousNode=null;
		this.nextNode=null;
	}
}

