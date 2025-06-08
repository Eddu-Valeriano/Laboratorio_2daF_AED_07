
package TADgraph.graphListEdge;

import java.util.*;

public class GraphListEdge<V, E> {
    ArrayList<VertexObj<V, E>> secVertex;
    ArrayList<EdgeObj<V, E>> secEdge;

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    public void insertVertex(V v) {
        if (!searchVertex(v)) {
            secVertex.add(new VertexObj<>(v, secVertex.size()));
        }
    }

    public void insertEdge(V v, V z) {
        if (!searchEdge(v, z)) {
            VertexObj<V, E> vert1 = getVertex(v);
            VertexObj<V, E> vert2 = getVertex(z);
            if (vert1 != null && vert2 != null) {
                secEdge.add(new EdgeObj<>(vert1, vert2, null, secEdge.size()));
            }
        }
    }

    public boolean searchVertex(V v) {
        return secVertex.stream().anyMatch(vertex -> vertex.getInfo().equals(v));
    }

    public boolean searchEdge(V v, V z) {
        VertexObj<V, E> vert1 = getVertex(v);
        VertexObj<V, E> vert2 = getVertex(z);
        if (vert1 == null || vert2 == null) return false;

        return secEdge.stream().anyMatch(edge ->
            (edge.getEndVertex1().equals(vert1) && edge.getEndVertex2().equals(vert2)) ||
            (edge.getEndVertex1().equals(vert2) && edge.getEndVertex2().equals(vert1))
        );
    }

    public void bfs(V start) {
        VertexObj<V, E> inicio = getVertex(start);
        if (inicio == null) {
            System.out.println("Vértice no encontrado.");
            return;
        }

        Set<VertexObj<V, E>> visitados = new HashSet<>();
        Queue<VertexObj<V, E>> cola = new LinkedList<>();
        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            VertexObj<V, E> actual = cola.poll();
            System.out.println(actual.getInfo());

            for (EdgeObj<V, E> edge : secEdge) {
                VertexObj<V, E> vecino = null;
                if (edge.getEndVertex1().equals(actual)) {
                    vecino = edge.getEndVertex2();
                } else if (edge.getEndVertex2().equals(actual)) {
                    vecino = edge.getEndVertex1();
                }

                if (vecino != null && !visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }
    }

    private VertexObj<V, E> getVertex(V value) {
        for (VertexObj<V, E> v : secVertex) {
            if (v.getInfo().equals(value))
                return v;
        }
        return null;
    }
}

