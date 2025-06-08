package TADgraph.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import TADgraph.ListLinked.ListLinked;

public class GraphLink<E> {

    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<Vertex<E>>();
    }

    public void insertVertex(E data) {
        Vertex<E> v = new Vertex<>(data);
        if (listVertex.contains(v)) {
            System.out.println("Elemento repetido");
            return;
        }
        listVertex.add(v);
    }

    public void insertEdge(E verOri, E verDes) {
        Vertex<E> verO = getVertex(verOri);
        Vertex<E> verD = getVertex(verDes);

        if (verD == null || verO == null) {
            System.out.println("Uno o los dos vertices no existen");
            return;
        }
        Edge<E> arista = new Edge<>(verD);
        if (verD.listAdj.contains(arista)) {
            System.out.println("La arista a ese vertice ya existe");
        }
        verO.listAdj.add(arista);
        verD.listAdj.add(new Edge<>(verO));
    }

    public boolean searchVertex(E data) {
        return listVertex.contains(new Vertex<>(data));
    }

    public boolean searchEdge(E v, E z) {
        Vertex<E> Ov = getVertex(v);
        Vertex<E> Dz = getVertex(z);
        if (Ov == null || Dz == null) {
            return false;
        }
        return Ov.listAdj.contains(new Edge<E>(Dz));

    }

    public Vertex<E> getVertex(E data) {
        Vertex<E> busqueda = new Vertex<>(data);
        return listVertex.get(busqueda);
    }

    public String toString() {
        return this.listVertex.toString();
    }

    public void removeVertex(E data) {
        Vertex<E> tmp = getVertex(data);

        if (tmp == null) {
            System.out.println("El vertice no existe");
        }

        for (Vertex<E> ve : listVertex) {
            ve.listAdj.remove(new Edge<>(tmp));
        }
        listVertex.remove(tmp);
    }

    public void removeEdge(E v, E z) {
        Vertex<E> verV = getVertex(v);
        Vertex<E> verZ = getVertex(z);

        if (verV == null || verZ == null) {
            System.out.println("Uno o los dos vertices no existen");
            return;
        }
        verV.listAdj.remove(new Edge<>(verZ));
        verZ.listAdj.remove(new Edge<>(verV));
    }

    public String dfsI() {
        String cadena = "";
        for (Vertex<E> ve : listVertex) {
            cadena = dfs(cadena, ve); // Guarda el resultado
            break;
        }

        // Reinicia visitados
        for (Vertex<E> ver : listVertex) {
            ver.visit = false;
        }
        return cadena;
    }

    public String dfs(String cadena, Vertex<E> ver) {
        ver.visit = true;
        cadena += ver.getData() + " "; // muestra solo el dato, no todo toString()

        for (Edge<E> arista : ver.listAdj) {
            Vertex<E> vecino = arista.getRefDest();
            if (!vecino.visit) { // Corrección aquí
                cadena = dfs(cadena, vecino);
            }
        }

        return cadena;
    }

    public void resetVisit() {
        for (Vertex<E> ve : listVertex) {
            ve.visit = false;
        }
    }

    public void bfs(E data) {
        Vertex<E> start = getVertex(data);
        if (start == null) {
            System.out.println("Vértice no encontrado");
            return;
        }

        Queue<Vertex<E>> cola = new LinkedList<>();
        resetVisit(); // Asegura que todos estén no visitados
        start.visit = true;
        cola.add(start);

        while (!cola.isEmpty()) {
            Vertex<E> actual = cola.poll();
            System.out.print(actual.getData() + " ");

            for (Edge<E> arista : actual.listAdj) {
                Vertex<E> vecino = arista.getRefDest();
                if (!vecino.visit) {
                    vecino.visit = true;
                    cola.add(vecino);
                }
            }
        }
        System.out.println();
    }

    public ArrayList<E> bfsPath(E origen, E destino) {
        Vertex<E> start = getVertex(origen);
        Vertex<E> end = getVertex(destino);
        if (start == null || end == null)
            return null;

        Queue<Vertex<E>> cola = new LinkedList<>();
        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>();
        resetVisit();

        start.visit = true;
        cola.add(start);
        prev.put(start, null);

        while (!cola.isEmpty()) {
            Vertex<E> actual = cola.poll();

            if (actual.equals(end))
                break;

            for (Edge<E> arista : actual.listAdj) {
                Vertex<E> vecino = arista.getRefDest();
                if (!vecino.visit) {
                    vecino.visit = true;
                    cola.add(vecino);
                    prev.put(vecino, actual);
                }
            }
        }

        // Reconstruir el camino
        ArrayList<E> path = new ArrayList<>();
        for (Vertex<E> at = end; at != null; at = prev.get(at)) {
            path.add(0, at.getData());
        }

        if (path.isEmpty() || !path.get(0).equals(origen))
            return null;
        return path;
    }

    public void insertEdgeWeight(E verOri, E verDes, int weight) {
        Vertex<E> verO = getVertex(verOri);
        Vertex<E> verD = getVertex(verDes);

        if (verO == null || verD == null) {
            System.out.println("Uno o ambos vértices no existen");
            return;
        }

        // Verificar si ya existe la arista
        if (verO.listAdj.contains(new Edge<>(verD))) {
            System.out.println("La arista ya existe");
            return;
        }

        verO.listAdj.add(new Edge<>(verD, weight));
        verD.listAdj.add(new Edge<>(verO, weight)); // No dirigido
    }

    public ArrayList<E> shortPath(E origen, E destino) {
        Vertex<E> start = getVertex(origen);
        Vertex<E> end = getVertex(destino);
        if (start == null || end == null)
            return null;

        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>();
        Queue<Vertex<E>> cola = new LinkedList<>();
        Set<Vertex<E>> visitados = new HashSet<>();

        cola.offer(start);
        visitados.add(start);

        while (!cola.isEmpty()) {
            Vertex<E> actual = cola.poll();
            if (actual.equals(end))
                break;

            for (Edge<E> edge : actual.listAdj) {
                Vertex<E> vecino = edge.getRefDest();
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    prev.put(vecino, actual);
                    cola.offer(vecino);
                }
            }
        }

        ArrayList<E> camino = new ArrayList<>();
        if (!prev.containsKey(end))
            return camino; // No hay camino

        for (Vertex<E> at = end; at != null; at = prev.get(at)) {
            camino.add(0, at.getData());
        }

        return camino;
    }

    public boolean isConexo() {
        if (listVertex.isEmpty())
            return true;

        Set<Vertex<E>> visitados = new HashSet<>();
        Queue<Vertex<E>> cola = new LinkedList<>();

        Vertex<E> inicio = listVertex.getPosition(0);
        cola.offer(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            Vertex<E> actual = cola.poll();
            for (Edge<E> edge : actual.listAdj) {
                Vertex<E> vecino = edge.getRefDest();
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.offer(vecino);
                }
            }
        }

        return visitados.size() == listVertex.size();
    }

    public Stack<E> Dijkstra(E origen, E destino) {
        Vertex<E> start = getVertex(origen);
        Vertex<E> end = getVertex(destino);
        if (start == null || end == null)
            return null;

        Map<Vertex<E>, Integer> dist = new HashMap<>();
        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>();
        PriorityQueue<Vertex<E>> queue = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        for (Vertex<E> v : listVertex) {
            dist.put(v, v.equals(start) ? 0 : Integer.MAX_VALUE);
            queue.add(v);
        }

        while (!queue.isEmpty()) {
            Vertex<E> u = queue.poll();
            for (Edge<E> edge : u.listAdj) {
                Vertex<E> v = edge.getRefDest();
                int alt = dist.get(u) + edge.getWeight();
                if (alt < dist.get(v)) {
                    dist.put(v, alt);
                    prev.put(v, u);
                    // Actualizar prioridad (reinsertar)
                    queue.remove(v);
                    queue.add(v);
                }
            }
        }

        Stack<E> path = new Stack<>();
        if (!prev.containsKey(end))
            return path; // No hay camino

        for (Vertex<E> at = end; at != null; at = prev.get(at)) {
            path.push(at.getData());
        }

        // Invertir el stack para que esté en orden inicio -> destino
        Stack<E> finalPath = new Stack<>();
        while (!path.isEmpty())
            finalPath.push(path.pop());

        return finalPath;
    }

}
