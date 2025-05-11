package Ejercicios.Ejercicio1;
import Actividades.Actividad_2.Nodo;
import Actividades.Actividad_1.ExceptionIsEmpty;
import Actividades.Actividad_1.Stack;


public class StackLink<E> implements Stack<E> {

    private Nodo<E> top;

    public StackLink() {
        this.top = null;
    }

    @Override
    public void push(E x) {
        Nodo<E> nuevo = new Nodo<>(x);
        nuevo.next = top;
        top = nuevo;
    }

    @Override
    public E pop() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Pila vacía");
        }
        E valor = top.dato;
        top = top.next;
        return valor;
    }

    @Override
    public E top() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Pila vacía");
        }
        return top.dato;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tope -> ");
        Nodo<E> actual = top;
        while (actual != null) {
            sb.append(actual.dato).append(" -> ");
            actual = actual.next;
        }
        sb.append("null");
        return sb.toString();
    }
}