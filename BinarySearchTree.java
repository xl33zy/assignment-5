import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

public class BinarySearchTree<K extends Comparable<K>, V> implements Iterable<BinarySearchTree.Node> {
    private Node root;
    private int size;
    private int height;

    public class Node {
        public K key;
        public V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    //adds a new key-value pair to a binary search tree
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    //adds a new key-value pair to a binary search tree. returns the root node of the updated tree
    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    //takes a key as input and returns the corresponding value in a tree-like data structure, or null if there is no node with the given key
    public V get(K key) {
        Node node = get(root, key);
        return (node == null) ? null : node.value;
    }

    //searches for a node in a binary search tree by recursively comparing the input key to the keys in the nodes of the tree, and returning the node if found or null if not found
    private Node get(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    //deletes the node with a given key from a data structure
    public void delete(K key) {
        root = delete(root, key);
    }

    //deletes a node with a given key and returns the updated root node of the tree. It recursively traverses the tree to find the node and handles three cases depending on whether the node has no, one, or two children
    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                size--;
                return node.right;
            } else if (node.right == null) {
                size--;
                return node.left;
            } else {
                Node minNode = findMinimum(node.right);
                node.key = minNode.key;
                node.value = minNode.value;
                node.right = delete(node.right, minNode.key);
            }
        }
        return node;
    }

    //finds the minimum value of a binary search tree by traversing down to the leftmost node
    private Node findMinimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMinimum(node.left);
    }

    //returns size
    public int size() {
        return size;
    }

    int height() {
        height = 0;
        return height(root);
    }

    int height(Node node) {
        if (node == null) {
            return height;
        }
        height += 1;
        return height(node.left);
    }

    //creates an iterator to traverse a binary search tree in-order
    @Override
    public Iterator<BinarySearchTree.Node> iterator() {
        return new InOrderIterator(root);
    }

    private class InOrderIterator implements Iterator<BinarySearchTree.Node> {
        private List<Node> nodes;
        private int currentIndex;

        //initializes an empty ArrayList called nodes, sets currentIndex to 0, and then calls the inOrderTraversal() method with the root node as its argument
        public InOrderIterator(Node root) {
            nodes = new ArrayList<>();
            currentIndex = 0;
            inOrderTraversal(root);
        }

        //visits the left subtree, the root node, and then the right subtree, and adds each visited node to a list
        private void inOrderTraversal(Node node) {
            if (node == null) {
                return;
            }
            inOrderTraversal(node.left);
            nodes.add(node);
            inOrderTraversal(node.right);
        }

        //checks whether there are remaining elements in a collection to iterate over using the current index
        @Override
        public boolean hasNext() {
            return currentIndex < nodes.size();
        }

        //traverses nodes in a tree structure. If there are still elements left to traverse, it returns the next node in the sequence. If there are no more elements, it throws a NoSuchElementException
        @Override
        public Node next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the tree.");
            }
            return nodes.get(currentIndex++);
        }
    }
}

