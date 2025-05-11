package Actividades.Actividad_1;

public interface Stack<E> {
    void push(E x);
    E pop() throws  ExceptionIsEmpty;
    E top() throws  ExceptionIsEmpty;
    boolean isEmpty();
}
