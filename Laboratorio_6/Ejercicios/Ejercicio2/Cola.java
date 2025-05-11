package Ejercicios.Ejercicio2;
import Actividades.Actividad_1.ExceptionIsEmpty;
import Actividades.Actividad_2.Queue;

public class Cola<T> implements Queue<T> {
    private T[] array;
    private int t; 
    private int cantidad;
    private int inicio;
    private int fin;

   
    public Cola(int t) {
        this.t = t;
        this.array = (T[]) new Object[t];
        this.cantidad = 0;
        this.inicio = 0;
        this.fin = -1;
    }

    @Override
    public void enqueue(T dato) {
        if (cantidad == t) {
            System.out.println("Cola llena");
            return;
        }
        fin = (fin + 1) % t;
        array[fin] = dato;
        cantidad++;
    }

    @Override
    public T dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("No existe ningun elemento en la cola");
        }
        T eliminado = array[inicio];
        array[inicio] = null;
        inicio = (inicio + 1) % t;
        cantidad--;
        return eliminado;
    }

    @Override
    public T front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacia");
        }
        return array[inicio];
    }

    @Override
    public T back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacia");
        }
        return array[fin];
    }
    @Override
    public boolean isEmpty() {
        return cantidad == 0;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cola: ");
        for (int i = 0; i < cantidad; i++) {
            int index = (inicio + i) % t;
            sb.append(array[index]).append(" <- ");
        }
        sb.append("null");
        return sb.toString();
    }
}
