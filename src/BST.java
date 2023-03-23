import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Represents a Binary Search Tree (BST) with methods for adding, removing,
 * finding, and iterating through the elements.
 *
 * @param <T> The type of elements held in the BST, which should implement
 *            Comparable<T>.
 */

public class BST<T extends Comparable<T>> implements Iterable<T> {

	private Node<T> root;
	private Comparator<T> comparator;
	private int size = 0;

	/**
	 * Constructs an empty BST with a natural order.
	 */
	public BST() {
		root = null;
		comparator = null;
	}

	/**
	 * Constructs an empty BST with a specified comparator.
	 *
	 * @param comparator The comparator to use for comparing elements.
	 */

	public BST(Comparator<T> comparator) {
		root = null;
		this.comparator = comparator;
	}

	/**
	 * Adds a value to the BST.
	 *
	 * @param value The value to add.
	 */
	public void add(T value) {
		root = add(root, value);
		size++;
	}

	/**
	 * Adds a value to the BST.
	 *
	 * @param value The value to add.
	 */
	private Node<T> add(Node<T> node, T value) {
		if (node == null) {
			return new Node<>(value);
		}

		int cmp = compare(value, node.value);
		if (cmp < 0) {
			node.left = add(node.left, value);
		} else if (cmp > 0) {
			node.right = add(node.right, value);
		} else {
			size--; // Value already exists, decrement size to cancel increment
		}

		return node;
	}

	/**
	 * Removes a value from the BST.
	 *
	 * @param value The value to remove.
	 * @return True if the value was removed, otherwise it will be false.
	 */

	public boolean remove(T value) {
		int originalSize = size;
		root = remove(root, value);
		return originalSize != size;
	}

	/**
	 * Removes a value from the BST.
	 *
	 * @param value The value to remove.
	 * @return True if the value was removed, otherwise it will be false.
	 */

	private Node<T> remove(Node<T> node, T value) {
		if (node == null) {
			return null;
		}

		int cmp = compare(value, node.value);
		if (cmp < 0) {
			node.left = remove(node.left, value);
		} else if (cmp > 0) {
			node.right = remove(node.right, value);
		} else {
			size--;

			if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			} else {
				node.value = findMin(node.right);
				node.right = remove(node.right, node.value);
				size++;
			}
		}

		return node;
	}

	/**
	 * Finds the smallest value in the BST on the left side.
	 *
	 * @param value The value to find.
	 * @return The found smallest value.
	 */
	private T findMin(Node<T> node) {
		while (node.left != null) {
			node = node.left;
		}
		return node.value;
	}

	/**
	 * Finds a value in the BST.
	 *
	 * @param value The value to find.
	 * @return The found value, or null if not found.
	 */
	public T find(T value) {
		Node<T> node = find(root, value);
		return node == null ? null : node.value;
	}

	/**
	 * Finds a value in the BST.
	 *
	 * @param value The value to find.
	 * @return The found value, or null if not found.
	 */
	private Node<T> find(Node<T> node, T value) {
		if (node == null) {
			return null;
		}

		int cmp = compare(value, node.value);
		if (cmp < 0) {
			return find(node.left, value);
		} else if (cmp > 0) {
			return find(node.right, value);
		} else {
			return node;
		}
	}

	/**
	 * Returns the size of the BST.
	 *
	 * @return The number of elements in the BST.
	 */

	public int size() {
		return size;
	}

	/**
	 * Returns the height of the BST.
	 *
	 * @return The height of the BST.
	 */

	public int height() {
		return height(root);
	}

	/**
	 * Returns the height of the BST.
	 *
	 * @return The height of the BST.
	 */

	private int height(Node<T> r) {
		int h = -1;
		int lh;
		int rh;
		if (r == null) {
			return h;
		} else {
			lh = height(r.left);
			rh = height(r.right);

		}
		if (lh > rh) {
			return lh + 1;
		} else

			return rh + 1;
	}

	/**
	 * Returns the optimal height of the BST.
	 *
	 * @return The optimal height of the BST.
	 */

	public int optimalHeight() {
		return optimalHeight(root);
	}

	private int optimalHeight(Node<T> r) {
		if (r == null) {
			return -1;
		} else {
			return (int) (Math.log(size + 1) / Math.log(2));
		}
	}

	/**
	 * compare 2 values .
	 * 
	 * @param T a, T b
	 * @return number depending on the compared values.
	 */
	private int compare(T a, T b) {
		if (comparator != null) {
			return comparator.compare(a, b);
		}
		return a.compareTo(b);
	}

	/**
	 * Returns an iterator for the BST elements in in-order.
	 *
	 * @return An iterator for the BST elements.
	 */

	@Override
	public Iterator<T> iterator() {
		return new InOrderIterator();
	}

	private class InOrderIterator implements Iterator<T> {
		private Queue<Node> q = new LinkedList<>();

		/**
		 * Constructs an in-order iterator for the BST.
		 */
		public InOrderIterator() {
			q = new LinkedList<>();
			Node<T> node = root;
			inOrderTraversal(root);

		}

		/**
		 * implements an in-order iterator for the BST.
		 */

		private void inOrderTraversal(Node r) {
			if (r == null)
				return;
			else {
				inOrderTraversal(r.left);
				visit(r);
				inOrderTraversal(r.right);
			}
		}

		/**
		 * add value to Queue
		 */
		private void visit(Node v) {
			q.add(v);
		}

		/**
		 * Determines whether there are more elements in the BST.
		 *
		 * @return True if there are more elements, false otherwise.
		 */

		@Override
		public boolean hasNext() {
			return !q.isEmpty();
		}

		/**
		 * Returns the next element in the BST in in-order.
		 *
		 * @return The next element in the BST.
		 * @throws NoSuchElementException if there are no more elements.
		 */
		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			Node<T> node = q.remove();
			T value = node.value;

			return value;
		}
	}

	/**
	 * Represents a node in the BST.
	 *
	 * @param <T> The type of the value held in the node.
	 */
	private static class Node<T> {
		T value;
		Node<T> left;
		Node<T> right;

		public Node(T value) {
			this.value = value;
			left = null;
			right = null;
		}
	}
}