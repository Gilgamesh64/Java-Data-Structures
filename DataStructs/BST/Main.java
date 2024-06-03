public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>(10);
        bst.insertNode(5, bst.getRoot());
        bst.insertNode(15, bst.getRoot());
        bst.insertNode(3, bst.getRoot());
        bst.insertNode(7, bst.getRoot());
        bst.insertNode(13, bst.getRoot());
        bst.insertNode(17, bst.getRoot());
        bst.print();
        bst.deleteNode(bst.getRoot(), 10);
        bst.print();
    }
}
