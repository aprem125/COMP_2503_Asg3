import java.util.Comparator;

public class SLL<T extends Comparable<T>> {
	private Node<T> head;
	private Node<T> tail;
	Node<T> mover;
	private int size;
	private Comparator<T> comparator;

	public SLL() {
		head = null;
		tail = null;
		size = 0;
		comparator = null;

	}

	public SLL(Comparator<T> externalComp) {
		head = null;
		tail = null;
		size = 0;
		this.comparator = externalComp;
	}

	private int compare(T avenger, T avenger2) {
		if (comparator == null)
			return avenger.compareTo(avenger2);
		else
			return comparator.compare(avenger, avenger2);
	}

	public void addInOrder(T data) {
		Node<T> newNode = new Node<>(data);

		if (isEmpty()) { // if list is empty, add new node as head
			addHead(data);
		} else {
			Node<T> current = head;
			Node<T> previous = null;

			while (current != null && compare(current.getData(), data) < 0) {
				previous = current;
				current = current.getNext();
			}

			if (previous == null) { // add new node as head
				newNode.setNext(head);
				head = newNode;
			} else if (current == null) { // add new node as tail
				previous.setNext(newNode);
				tail = newNode;
			} else { // add new node in the middle of the list
				previous.setNext(newNode);
				newNode.setNext(current);
			}

			size++;
		}
	}

	public void addLast(T data) {
		Node<T> node = new Node<>(data);
		if (isEmpty()) {
			head = tail = node;
		} else {
			tail.setNext(node);
			tail = node;
		}
		size++;
	}

	public void addHead(T data) {
		Node<T> newNode = new Node<>(data);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.setNext(head);
			head = newNode;
		}
		size++;
	}

	/**
	 * Return the number of elements in the list.
	 * 
	 * @return int number of elements in the list.
	 */
	public int size() {
		return size;
	}

	/**
	 * Empty the list.
	 */
	public void emptyList() {
		head = null;
		tail = null;
		size = 0;
	}

	public boolean isEmpty() {
		return (head == null);
	}

	public Node<T> delete(T key) {
		// implement delete
		Node<T> mover = head;
		Node<T> prevNode = head;
		while (mover != null) {
			if (mover.getData().equals(key)) {
				if (mover == head && mover == tail) {
					head = tail = null;
					return mover;
				}
				if (mover == head) {
					head = mover.getNext();
				} else {
					prevNode.setNext(mover.getNext());
				}
				if (mover == tail) {
					tail = prevNode;
				}

				return mover;
			} else {
				prevNode = mover;
				mover = mover.getNext();

			}

		}

		return null;
	}

	/**
	 * Return head value
	 * 
	 * @return head value, null if list is empty
	 */
	public Node<T> getHead() {
		if (head == null)
			return null;
		else
			return head;
	}

	public Node<T> getNextSLL() {

		if (head == null)
			return null;
		else
			return head.getNext();

	}

	/**
	 * Return tail value
	 * 
	 * @return tail value, null if list is empty
	 */
	public Node<T> getTail() {
		if (tail == null)
			return null;
		else
			return tail;
	}

	public void printList() {
		Node<T> current = head;
		while (current != null) {
			System.out.print(current.getData() + " ");
			current = current.getNext();
		}
		System.out.println();
	}

	public void printFour() {
		Node<T> current = head;
		int i = 0;
		while (current != null & i < 4) {
			System.out.print(current.getData() + " ");
			current = current.getNext();
			i++;
		}
		System.out.println();

	}

	public boolean contains(T data) {
		Node<T> current = head;
		while (current != null) {
			if (current.getData().equals(data)) {
				return true;

			} else {
				current = current.getNext();
			}

		}
		return false;
	}

	public int indexOf(T data) {
		Node<T> current = head;
		int index = 0;
		while (current != null) {
			if (current.getData().equals(data)) {
				return index;
			}
			index++;
			current = current.getNext();
		}
		return -1;
	}

	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getData();
	}

}
