/**
 * Binary Search Tree
 * @author Gilgamesh64
 */
public class BST<T extends Comparable<T>> extends BinaryTree<T>{

    BST(T root){
        setRoot(new Node<T>(root, null, null));
    }

    public boolean insertNode(T val, Node<T> current){
        if (getRoot() == null) setRoot(new Node<T>(val, null, null));

        if(current != null){

            if(val.compareTo(current.getValue()) < 0){
                insertNode(val, current.getLeft());
            } 
            if(val.compareTo(current.getValue()) > 0){
                insertNode(val, current.getRight());
            } 
            if(val.compareTo(current.getValue()) < 0 && current.getLeft() == null){
                current.setLeft(new Node<T> (val, null, null));
                return true;
            } 
            if(val.compareTo(current.getValue()) >= 0 && current.getRight() == null){
                current.setRight(new Node<T> (val, null, null));
                return true;
            } 
        }
        return false;
    }

    public Node<T> binarySearch(T val, Node<T> current){
        if(val.equals(current.getValue())) return current;

        if(val.compareTo(current.getValue()) < 0 && current.getLeft() != null) return binarySearch(val, current.getLeft());
        if(val.compareTo(current.getValue()) >= 0 && current.getRight() != null) return binarySearch(val, current.getRight());
        return null;
    }
    
    public Node<T> getParent(T val, Node<T> current){

        if(current.getLeft() != null){
            if(val.equals(current.getLeft().getValue())) return current;
        }
        if(current.getRight() != null){
            if(val.equals(current.getRight().getValue())) return current;
        }

        if(val.compareTo(current.getValue()) < 0 && current.getLeft() != null) return getParent(val, current.getLeft());
        if(val.compareTo(current.getValue()) >= 0 && current.getRight() != null) return getParent(val, current.getRight());
        return null;
    }
    
    /**
     *
     * @param root
     * @param key: this needs to be deleted
     * @return Node
     */
    public Node<T> deleteNode(Node<T> root, T key) {
        if(root == null) return root;
        if(key.compareTo(root.getValue()) > 0){
            root.setRight(deleteNode(root.getRight(), key));
        }else if(key.compareTo(root.getValue()) < 0){
            root.setLeft(deleteNode(root.getLeft(), key));
        }else{
            if(root.getLeft() == null && root.getRight() == null){
                root = null;
            }else if(root.getRight() != null){
                root.setValue(successor(root));
                root.setRight(deleteNode(root.getRight(), root.getValue()));
            }else{
                root.setValue(predecessor(root));
                root.setLeft(deleteNode(root.getLeft(), root.getValue()));
            }
        }
        return root;
    }
    /**
     * Return node's successor value
     * @param root
     * @return
     */
    private T successor(Node<T> root){
        root = root.getRight();
        while(root.getLeft() != null){
            root = root.getLeft();
        }
        return root.getValue();
    }
    /**
     * Return node's predecessor value
     * @param root
     * @return
     */
    private T predecessor(Node<T> root){
        root = root.getLeft();
        while(root.getRight() != null){
            root = root.getRight();
        }
        return root.getValue();
    }
}