
class Node<T extends Comparable<T>> {
	private T data;
	Node<T> next;

	public Node(T d) {
		this.data = d;
		next = null;
	}

//getter and setters
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public String toString() {
		return "Node: " + getData().toString();
	}

}
