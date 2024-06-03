public class Main {
    public static void main(String[] args)  {
        Graph<String> g = new Graph<>();
        g.connect("Torino", "Milano", false, 2);
        g.connect("Milano", "Mestre", false, 8);
        g.connect("Milano", "Bologna", false, 1);
        g.connect("Bologna", "Torino", false, 2);
        g.connect("Torino", "Messina", false, 3);
        g.connect("Messina", "Catanzaro", false, 1);
        g.connect("Catanzaro", "Bologna", false, 9);
        g.connect("Mestre", "Catanzaro", false, 4);


        /*g.connect("Torino", "Milano", true, 1);
        g.connect("Bologna", "Oriago", true, 2);
        g.connect("Mestre", "Milano", true, 2);
        g.connect("Mestre", "Catanzaro", true, 5);
        g.connect("Catanzaro", "Messina", true, 1);
        g.connect("Mestre", "Oriago", true, 7);
        g.connect("Torino", "Catanzaro", true, 2);*/
        //System.out.println(g.toString());
        //System.out.println(g.isConnected());
        //System.out.println("Belman Ford:\n" + g.bellmanFord("Mestre").toString());
        //System.out.println("Dijkstra:\n" + g.Dijkstra("Mestre").toString());
        System.out.println(g.kruskal());

    }
}
