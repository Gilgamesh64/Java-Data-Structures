public class Edge <T>{
        private Node<T> node1;
        private Node<T> node2;
        private double value;

        Edge(Node<T> node1, Node<T> node2, double value){
            this.node1 = node1;
            this.node2 = node2;
            this.value = value;
        }
        @Override
        public String toString() {
            return node1.getData() + " " + node2.getData() + " " + value;
        }

        public Node<T> getNode1() {
            return node1;
        }
        public Node<T> getNode2() {
            return node2;
        }
        public double getValue() {
            return value;
        }
        public Object[] split(String string) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'split'");
        }
    }