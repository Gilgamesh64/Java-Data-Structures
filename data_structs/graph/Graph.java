import java.util.*;
/**
 * Graph manager
 * @author Gilgamesh64
 */
public class Graph <T>{

    private final ArrayList<Node<T>> nodes = new ArrayList<>();
    private final ArrayList<Edge<T>> edges = new ArrayList<>();
    private ShortestPathByNode<T> shortestPathByNode;

    /**connects two vertex, it creates them if they don't exist*/
    public void connect(T vertex1, T vertex2, boolean bidirectional, double weight){
        Node<T> v1 = getNode(vertex1), v2 = getNode(vertex2);
        
        if(v1 == null){
            v1 = new Node<T>(vertex1);
            nodes.add(v1);
        } 
        if(v2 == null){
            v2 = new Node<T>(vertex2);
            nodes.add(v2);
        } 

        if(!bidirectional) edges.add(new Edge<>(v1, v2, weight));
        else{
            edges.add(new Edge<>(v1, v2, weight));
            edges.add(new Edge<>(v2, v1, weight));
        }
    }

    /**returns true if the graph is fully connected */
    public boolean isFullyConnected(){
        if(edges.size() < nodes.size()*(nodes.size()-1)) return false;
        return true;
    }

    /**returns the node corrisponding to vertex */
    private Node<T> getNode(T vertex){
        if(nodes.size()<=1) return null;
        
        for (Node<T> n : nodes) {
            if(n.getData() == vertex) return n;
        }
        return null;
    }

    /**returns a flat representation of the graph */
    public String toString(){
        String s = "";
        for (Node<T> n : nodes) {
            s += n.getData() + "\n";
        }
        s += "\n";
        for (Edge<T> a : edges) {
            s += a.toString() + "\n";
        }
        return s;
    }

    /**returns true if the graph is connected */
    public boolean isConnected(){
        for (Node<T> firstKey : nodes) {
            for (Node<T> secondKey : nodes) {
                if(firstKey == secondKey) continue;
                
                if(!areConnected(firstKey, secondKey, new HashMap<>())) return false;
            }
        }
        return true;
    }

    /**returns true if there is any connection between node1 and node2 */
    private boolean areConnected(Node<T> node1, Node<T> node2, HashMap<Node<T>, Boolean> mapThingsFounds){
        if(existsEdge(node1, node2)) return true;

        mapThingsFounds.put(node1, true);
        for (Edge<T> key : edgesExitingBy(node1)) {

            if(mapThingsFounds.get(key.getNode2()) != null) continue; 

            if(areConnected(key.getNode2(), node2, mapThingsFounds)) return true;
        }
        return false;
    }

    /**returns true if there is a direct edge betweeen the two nodes */
    private boolean existsEdge(Node<T> nodo1, Node<T> nodo2){
        for (Edge<T> Edge : edges) {
            if(Edge.getNode1() == nodo1 && Edge.getNode2() == nodo2) return true;
        }
        return false;
    }

    /**returns a collection of the edges extiting by node */
    private ArrayList<Edge<T>> edgesExitingBy(Node<T> Node){
        ArrayList<Edge<T>> a = new ArrayList<>();
        for (Edge<T> Edge : edges) {
            if(Edge.getNode1() == Node) a.add(Edge);
        }
        return a;
    }

    private Node<T> minDist(ArrayList<Node<T>> q){
        int pos = 0;
        double min = shortestPathByNode.getDist().get(q.get(0));
        int i =0;
        for (Node<T> t : q) {
            if(shortestPathByNode.getDist().get(t) < min){
                pos = i;
                min = shortestPathByNode.getDist().get(t);
            }
            i++;
        }
        return q.get(pos);
    }
    

    /**
     * Dijkstra algorithm
     * @param source
     * @return ShortestPathByNode object, it can then be printed
     */
    public ShortestPathByNode<T> Dijkstra(T data){
        Node<T> source = getNode(data);
        if(source == null) return null;
        double alt = 0d;
        shortestPathByNode = new ShortestPathByNode<>(source);
        ArrayList<Node<T>> q = new ArrayList<>();
        for (Node<T> vertex : nodes) {
            Double positiveInfinity = Double.POSITIVE_INFINITY;
            shortestPathByNode.getDist().put(vertex, positiveInfinity);
            q.add(vertex);
        }
        shortestPathByNode.getDist().put(source, 0d);
        
        while(q.size() != 0){
            Node<T> u = minDist(q);
            q.remove(u);
            for (Edge<T> n : edgesExitingBy(u)) {
                Node<T> neighbor = n.getNode2();
                if(!q.contains(neighbor)) continue;
                alt = shortestPathByNode.getDist().get(u) + n.getValue();
                if(alt < shortestPathByNode.getDist().get(neighbor)){
                    shortestPathByNode.getDist().put(neighbor, alt);
                    shortestPathByNode.getPrev().put(neighbor, u);
                }
            }
        }
        return shortestPathByNode;
    }

    /**
     * Bellman Ford algorithm
     * @param data
     * @return ShortestPathByNode, empty if negative cycle
     */
    public ShortestPathByNode<T> bellmanFord(T data){
        Node<T> source = getNode(data);
        if(source == null) return null;

        shortestPathByNode = new ShortestPathByNode<>(source);
        ArrayList<Node<T>> q = new ArrayList<>();
        for (Node<T> vertex : nodes) {
            shortestPathByNode.getDist().put(vertex, Double.MAX_VALUE);
            q.add(vertex);
        }
        shortestPathByNode.getDist().put(source, 0d);
        for (int i = 1; i < nodes.size(); i++) {
            for (Edge<T> edge : edges) {
                
                Node<T> u = edge.getNode1();
                Node<T> v = edge.getNode2();
                double w = edge.getValue();
                if(shortestPathByNode.getDist().get(u) != Double.MAX_VALUE && shortestPathByNode.getDist().get(u) + w < shortestPathByNode.getDist().get(v)){
                    shortestPathByNode.getDist().put(edge.getNode2(), shortestPathByNode.getDist().get(u) + w);
                    shortestPathByNode.getPrev().put(edge.getNode2(), edge.getNode1());
                }
            }
        }
            for (Edge<T> edge : edges) {
                Node<T> u = edge.getNode1();
                Node<T> v = edge.getNode2();
                double w = edge.getValue();
                if(shortestPathByNode.getDist().get(u) != Double.MAX_VALUE && shortestPathByNode.getDist().get(u) + w < shortestPathByNode.getDist().get(v)){
                    shortestPathByNode = new ShortestPathByNode<>(source);
                    return shortestPathByNode;
                }
            }
        return shortestPathByNode;
    }

    private void connectSets(Node<T> node1, Node<T> node2, HashMap<Node<T>, ArrayList<Node<T>>> hm){
        hm.get(node1).addAll(hm.get(node2));
        hm.get(node1).add(node2);
        hm.remove(node2);
    }

    private Node<T> getFatherIfPresent(Node<T> node, HashMap<Node<T>, ArrayList<Node<T>>> hm){
        if(hm.containsKey(node)) return node;
        for (Node<T> key : hm.keySet()) {
            for (Node<T> n : hm.get(key)) {
                if(n.getData() == node.getData()){
                    Node<T> father = key;
                    return father;
                }
            }
        }
        return node;
    }
    public ArrayList<Edge<T>> kruskal(){
        HashMap<Node<T>, ArrayList<Node<T>>> hm = new HashMap<Node<T>, ArrayList<Node<T>>>();
        ArrayList<Edge<T>> result = new ArrayList<>(); 
        
        ArrayList<Edge<T>> sortedEdges = new ArrayList<>(); 
        for (Edge<T> edge : edges) {
            sortedEdges.add(new Edge<>(edge.getNode1(), edge.getNode2(), edge.getValue()));
        }
        Collections.sort(sortedEdges, (b, a) -> new Double(b.getValue()).compareTo(a.getValue()));


        for (Node<T> n : nodes) {
            hm.put(n, new ArrayList<>());
        }
        for (Edge<T> edge : sortedEdges) {
            if(hm.keySet().size() == 1) return result;

            Node<T> node1 = edge.getNode1();
            Node<T> node2 = edge.getNode2();

            if(hm.get(getFatherIfPresent(node1, hm)).contains(node2) || hm.get(getFatherIfPresent(node2, hm)).contains(node1)) continue;

            connectSets(getFatherIfPresent(node1, hm), getFatherIfPresent(node2, hm), hm);
            result.add(edge);
            
            for (Node<T> key : hm.keySet()) {
                System.out.print(key.getData() + ": ");
                for (Node<T> node : hm.get(key)) {
                    System.out.print(node.getData() + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        return result;
    }
}
