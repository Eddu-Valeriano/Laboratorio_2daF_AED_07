package TADgraph.graph;

import TADgraph.ListLinked.ListLinked;

public class Vertex<E> {
    private E data;// Este es el dato que almacena el vertice
    public boolean visit=false;
    protected ListLinked<Edge<E>> listAdj;// Aqui es encuentra todas las aristas que tiene el vertice(Edge)

    public Vertex(E data) {//Constructor cuando se crea el vertice y su lista de aristas vacia
        this.data = data;//valor guardado
        listAdj = new ListLinked<Edge<E>>();
    }

    public E getData() {//retornar el valor guardado en el vertice
        return data;
    }

    // Verifica si dos objetos de valores 
    public boolean equals(Object o) {//Se recibe como un objeto de la clase Object
        if (o instanceof Vertex<?>) {// Se verifica si es instancia de la clase Vertice
            Vertex<E> v = (Vertex<E>) o;//Se castea el objeto para que sea de la clase vertice cosa que era inicialmente
            return this.data.equals(v.data); // Se compara el valor del vertice actual y se compara con el vertice recibido
        }
        return false;
    }

    public String toString() {
        return this.data + "-->" + this.listAdj.toString() + "\n";
    }
}
