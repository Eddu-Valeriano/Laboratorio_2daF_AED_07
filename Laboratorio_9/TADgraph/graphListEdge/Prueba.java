package TADgraph.graphListEdge;


public class Prueba {
    public static void main(String[] args) {
        GraphListEdge<String, Object> graph = new GraphListEdge<>();

        // Insertar vértices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");

        // Insertar aristas
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");

        // Buscar vértices
        System.out.println("¿Existe vértice A? " + graph.searchVertex("A")); // true
        System.out.println("¿Existe vértice E? " + graph.searchVertex("E")); // false

        // Buscar aristas
        System.out.println("¿Existe arista A-B? " + graph.searchEdge("A", "B")); // true
        System.out.println("¿Existe arista C-D? " + graph.searchEdge("C", "D")); // false

        // Recorrido BFS desde A
        System.out.println("Recorrido BFS desde A:");
        graph.bfs("A");
    }
}
