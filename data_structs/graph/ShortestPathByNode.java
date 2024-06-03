import java.util.HashMap;

public class ShortestPathByNode <T>{


    private Node<T> n;

    private HashMap<Node<T>, Double> dist;
    private HashMap<Node<T>, Node<T>> prev;


    ShortestPathByNode(Node<T> n){
        this.n = n;
        dist = new HashMap<>();
        prev = new HashMap<>();
    }

    public HashMap<Node<T>, Double> getDist() {
        return dist;
    }
    public HashMap<Node<T>, Node<T>> getPrev() {
        return prev;
    }
    public Node<T> getN() {
        return n;
    }

    @Override
    public String toString() {
        String result = "distances: \n";
        for (Node<T> node : dist.keySet()) {
            result += node.getData() + " " + dist.get(node) + "\n";
        }
        result += "\nprec: \n";
        for (Node<T> node : prev.keySet()) {
            result += node.getData() + " " + prev.get(node).getData() + "\n";
        }
        return result;
    }
}
