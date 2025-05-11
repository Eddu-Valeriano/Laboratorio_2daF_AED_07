package Actividades.Actividad_2;
import Actividades.Actividad_1.ExceptionIsEmpty;

public class QueueLink<E> implements Queue<E> {
    private Nodo<E> first;
    private Nodo<E> last;

    public QueueLink() {
        this.first = null;
        this.last = null;
    }
    @Override
    public void enqueue(E x) {
        Nodo<E> aux = new Nodo<E>(x);
        if (this.isEmpty()) {
            this.first = aux;
        } else
            this.last.next=aux;
        this.last = aux;
    }
    @Override
    public E dequeue () throws ExceptionIsEmpty{
        if( isEmpty()){
            throw new ExceptionIsEmpty("La cola se encuentra vacia");
        }
        E enviado=first.dato;
        first=first.next;
        if(first==null){last=null;}
        return enviado;
    }
    @Override
    public E back() throws ExceptionIsEmpty{
        if(last==null){
            throw new ExceptionIsEmpty("La cola se encuentra vacia ");
        }
        return this.last.dato;
    }
    @Override
    public E front()throws ExceptionIsEmpty{
        if(first==null){
            throw new ExceptionIsEmpty("La cola se encuentra vacia");
        }  
        return this.first.dato;
    }
    @Override
    public boolean isEmpty(){
        return this.first==null;
    }
    
    public String toString(){
        String cadena=" ";
        Nodo<E> actual;
        actual=first;
        while(actual!=null){
            cadena+=first.dato+ "  ";
            actual=actual.next;
        }
        return cadena;
        
    }
}