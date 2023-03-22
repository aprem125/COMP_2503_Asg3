import java.util.Comparator; 
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;


public class BST<T extends Comparable<T>> implements Iterable<T> {

    private Node<T> root;
    private Comparator<T> comparator;
    private int size = 0;

    public BST() {
        root = null;
        comparator = null;
    }

    public BST(Comparator<T> comparator) {
        root = null;
        this.comparator = comparator;
    }

    public void add(T value) {
        root = add(root, value);
        size++;
    }

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

    public boolean remove(T value) {
        int originalSize = size;
        root = remove(root, value);
        return originalSize != size;
    }

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

    private T findMin(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    public T find(T value) {
        Node<T> node = find(root, value);
        return node == null ? null : node.value;
    }

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

    public int size() {
        return size;
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int optimalHeight() {
        return (int) (Math.log(size + 1) / Math.log(2));
    }

    private int compare(T a, T b) {
        if (comparator != null) {
            return comparator.compare(a, b);
        }
        return a.compareTo(b);
    }

    @Override
    public Iterator<T> iterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<T> {
        private  Queue <Node> q = new LinkedList<>();
// not correct 
        public InOrderIterator() {
        	q = new LinkedList<>();
            Node<T> node = root;
            inOrderTraversal(root);
            
        }
        private void inOrderTraversal(Node r) {
    		if (r == null)
    			return;
    		else {
    			inOrderTraversal(r.left);
    			visit(r);
    			inOrderTraversal(r.right);
    		}
    	}

        private void visit(Node v) {
        	q.add(v);
        }
        @Override
        public boolean hasNext() {
            return !q.isEmpty();
        }

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