public class Main {
 
    public static void main(String[] args) {
        BinaryTree<String> tree = new BinaryTree<>("Catanzaro");
        tree.addNode("Trapani", "Catanzaro", true);
        tree.addNode("Gallipoli", "Catanzaro", false);
        tree.addNode("Avellino", "Trapani", false);
        tree.addNode("Benevento", "Trapani", true);
        tree.print();
        System.out.println(tree.search("Trapani", tree.getRoot()));
        System.out.println(tree.countLeaves());
    }
}
