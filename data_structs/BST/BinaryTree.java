/**
 * binary tree
 * @author Gilgamesh64
 */
public class BinaryTree <T extends Comparable<T>>{
    
    private Node<T> root;
    private int nLeaves;

    /**
     * inner class node with "value" as the value stored in the node
     */
    @SuppressWarnings("hiding") class Node<T>{
        private T value;
        private Node<T> left;
        private Node<T> right;
        Node(T value, Node<T> left, Node<T> right){
            this.value = value;
            this.left = left;
            this.right = right;
        }
        public T getValue() {
            return value;
        }
        public void setValue(T value) {
            this.value = value;
        }
        public Node<T> getLeft() {
            return left;
        }
        public Node<T> getRight() {
            return right;
        }
        public void setLeft(Node<T> left) {
            this.left = left;
        }
        public void setRight(Node<T> right) {
            this.right = right;
        }
    }

    /**blank constructor */
    public BinaryTree(){
        root = null;
    }
    /**
     * @param root the root node of the tree
     */
    public BinaryTree(T root){
        this.root = new Node<T>(root, null, null);
    }


    /**
     * sets value as root if it does not exsists
     * @param value the value of the node to add
     * @param father the value of the father
     * @param left true if the inserted node should be on the left
     * @return -1 if father is null
     * @return -2 if root is null
     * @return 0
     */
    public int addNode(T value, T father, boolean left){
        if(root == null){
            root = new Node<T>(value, null, null);
            return -2;
        } 
        Node<T> grandFather = searchNode(father, root);
        if(grandFather == null) return -1;
        
        Node<T> n = new Node<T>(value, null, null);
        if(left) grandFather.setLeft(n);
        if(!left) grandFather.setRight(n);
        return 0;
    }

    public void print() {
        System.out.println("(" + printR(root) + ")"); 
    }

    private String printR(Node<T> c) {
        if (c == null) return "";
        // recursive print
        return (c.getLeft() != null ? "(" + printR(c.getLeft()) + ")" : "") + c.getValue() + (c.getRight() != null ? "(" + printR(c.getRight()) + ")" : "");
    }

    /**
     * @param value
     * @param current
     * @return true if a node with the passed value exsists
     * @return false if a node with the passed value does not exsists
     */
    public boolean search(T value, Node<T> current){
        if(value.compareTo(current.getValue()) == 0) return true;
        if(current.getLeft() != null) return false || search(value, current.getLeft());
        if(current.getRight() != null) return false || search(value, current.getRight());
        return false;

    }

    /**
     * @param value
     * @param current
     * @return the node with the passed value
     */
    private Node<T> searchNode(T value, Node<T> current){
        if (root == null) return null;
        if(value.compareTo(current.getValue()) == 0) return current;
        if(current.getLeft() != null) return searchNode(value, current.getLeft());
        if(current.getRight() != null) return searchNode(value, current.getRight());
        return null;
    }

    /**counts the numbers of leaves */
    public int countLeaves(){
        countLeaves(root);
        return nLeaves;
    }
    /**updates "nLeaves" */
    private void countLeaves(Node<T> current){
        if(isLeaf(current)) nLeaves ++;
        if(current.getLeft() != null) countLeaves(current.getLeft());
        if(current.getRight() != null) countLeaves(current.getRight());
    }
    
    public Node<T> getRoot() {
        return root;
    }
    public void setRoot(Node<T> root) {
        this.root = root;
    }

    /**
     * @return true if current is leaf
     * @return false if current is not leaf
     */
    private boolean isLeaf(Node<T> current){
        if(current.getLeft() == null && current.getRight() == null) return true;
        return false;
    }
}
