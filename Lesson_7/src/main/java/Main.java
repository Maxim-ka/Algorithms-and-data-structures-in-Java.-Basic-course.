import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        MyGraph<Character> myGraph = new MyGraph<>(32);

        myGraph.addVertex('a', 0);
        myGraph.addVertex('b', 1);
        myGraph.addVertex('c', 2);
        myGraph.addVertex('d', 3);
        myGraph.addVertex('e', 4);
        myGraph.addVertex('f', 5);
        myGraph.addVertex('g', 6);
        myGraph.addVertex('h', 7);
        myGraph.addVertex('i', 8);
        myGraph.addVertex('j', 9);
        myGraph.addEdge('a', 'b');
        myGraph.addEdge('a', 'c');
        myGraph.addEdge('b', 'd');
        myGraph.addEdge('b', 'e');
        myGraph.addEdge('d', 'a');
        myGraph.addEdge('c', 'f');
        myGraph.addEdge('f', 'g');
        myGraph.addEdge('d', 'h');
        myGraph.addEdge('g', 'i');
        myGraph.addEdge('g', 'j');
        myGraph.addEdge('h', 'j');

        LinkedList path = myGraph.findShortestPath('a', 'j');
        if (path != null){
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i) + " - ");
            }
        }
    }
}
