package TADgraph.graph;

public class Prueba {
    public static void main(String[] args) {
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
        */
        //Ejercicios
        System.out.println("Ejercicios");
        System.out.println();
        System.out.println("Recorrido BFS desde A:");
        graph.bfs("A");

        System.out.println("Camino de A a E:");
        System.out.println(graph.bfsPath("A", "E"));
    }
}
