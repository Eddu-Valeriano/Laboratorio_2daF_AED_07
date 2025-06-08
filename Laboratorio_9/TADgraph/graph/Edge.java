package TADgraph.graph;

public class Edge <E>{
    private Vertex<E> refDest;// Referencia del vertice al que se apunta
    private int weight;//es seria como una medida del vertice como tamanio
    public Edge(Vertex<E> refDest){//Cuando se crea el vertice apuntando a otro vertice sin el valor de medida
        this(refDest,-1);
    }
    public Edge(Vertex<E> refDest, int weight){//Cuando se crea la arista con medida yel vertice de destino
        this.refDest=refDest;
        this.weight=weight;
    }
    public boolean equals(Object o){
        if(o instanceof Edge<?>){
            Edge<E> e=(Edge<E>)o;
            return this.refDest.equals(e.refDest);
        }
        return false;
    }
    public Vertex<E> getRefDest(){
        return refDest;
    }
    public String toString(){
        if(this.weight>-1) return refDest.getData()+" ["+this.weight+"],";
        else return refDest.getData()+",";
    }
}
