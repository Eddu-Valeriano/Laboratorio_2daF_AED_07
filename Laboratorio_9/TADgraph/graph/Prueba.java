package TADgraph.graph;

import java.util.ArrayList;
import java.util.Stack;

public class Prueba {
    public static void main(String[] args) {
        /* 
        GraphLink<String> graph = new GraphLink<>();
        System.out.println("Inserciones A,B,C,D,E");
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        System.out.println("Conexion entre A-B, A-C, B-D, C-E");
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "E");
        
        //Actividades
        /* 
        System.out.println("Actividades");
        graph.bfs("A");
        System.out.println();
        String recorrido = graph.dfsI();

        System.out.println("Recorrido DFS: " + recorrido);

        System.out.println("Eliminacion del vertice E");
        graph.removeVertex("E");
        String recorrido2 = graph.dfsI();
        System.out.println("Recorrido DFS: " + recorrido2);

        System.out.println("Eliminacion de la arista entre B y D");
        graph.removeEdge("B","D");
        System.out.println("Recorrido DFS: "+ graph.dfsI());
        
        //Ejercicios
        
        System.out.println();
        System.out.println("Recorrido BFS desde A:");
        graph.bfs("A");

        System.out.println("Camino de A a E:");
        System.out.println(graph.bfsPath("A", "E"));
*/
        GraphLink<String> grafo = new GraphLink<>();
        
        // Insertar vértices
        grafo.insertVertex("A");
        grafo.insertVertex("B");
        grafo.insertVertex("C");
        grafo.insertVertex("D");
        grafo.insertVertex("E");

        // Insertar aristas ponderadas
        grafo.insertEdgeWeight("A", "B", 2);
        grafo.insertEdgeWeight("A", "C", 4);
        grafo.insertEdgeWeight("B", "C", 1);
        grafo.insertEdgeWeight("B", "D", 7);
        grafo.insertEdgeWeight("C", "E", 3);
        grafo.insertEdgeWeight("D", "E", 1);

        // Probar isConexo
        System.out.println("¿Grafo conexo?: " + grafo.isConexo());

        // Probar shortPath (camino más corto sin pesos, estilo BFS)
        ArrayList<String> camino = grafo.shortPath("A", "E");
        System.out.println("shortPath (A -> E): " + camino);

        // Probar Dijkstra (camino más corto con pesos)
        Stack<String> caminoDijkstra = grafo.Dijkstra("A", "E");
        System.out.print("Dijkstra (A -> E): ");
        while (!caminoDijkstra.isEmpty()) {
            System.out.print(caminoDijkstra.pop() + " ");
        }
        System.out.println();
    }
}
