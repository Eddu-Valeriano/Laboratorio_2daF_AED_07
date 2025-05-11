package Actividades.Actividad_3;
import Actividades.Actividad_2.Nodo;


import Actividades.Actividad_1.ExceptionIsEmpty;

public class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> {

    class EntryNode {
        E data;
        N priority;

        EntryNode(E data, N priority) {
            this.data = data;
            this.priority = priority;
        }
    }

    private Nodo<EntryNode> first;
    private Nodo<EntryNode> last;

    public PriorityQueueLinkSort() {
        this.first = null;
        this.last = null;
    }

    public void enqueue(E x, N pr) {
    Nodo<EntryNode> nuevo = new Nodo<>(new EntryNode(x, pr));

    if (isEmpty()) {
        first = last = nuevo;
    } else if (pr.compareTo(first.dato.priority) > 0) {
        
        nuevo.next = first;
        first = nuevo;
    } else {
        Nodo<EntryNode> actual = first;
        while (actual.next != null && pr.compareTo(actual.next.dato.priority) <= 0) {
            actual = actual.next;
        }
        nuevo.next = actual.next;
        actual.next = nuevo;

        if (nuevo.next == null) {
            last = nuevo; 
        }
    }
}



    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Cannot remove from an empty queue");
        E aux = this.first.dato.data;
        this.first = this.first.next;
        if (this.first == null)
            this.last = null;
        return aux;
    }

    public E back() throws ExceptionIsEmpty {
        if(isEmpty()){
            throw new ExceptionIsEmpty(null);
        }
        return last.dato.data;
    }

    public E front() throws ExceptionIsEmpty {
        if(isEmpty()){
            throw new ExceptionIsEmpty(null);
        }
        return first.dato.data;
    }

    public boolean isEmpty() {
        
        return this.first==null;
    }

    public String toString() {
        
        String cadena=" ";
        Nodo<EntryNode> actual=first;
        while(actual!=null){
            actual=actual.next;
            cadena+=actual.dato.data+"------>";
        }
        return cadena;
    }
}
