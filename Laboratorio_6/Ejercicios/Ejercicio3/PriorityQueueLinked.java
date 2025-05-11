package Ejercicios.Ejercicio3;

import Actividades.Actividad_1.ExceptionIsEmpty;
import Actividades.Actividad_3.PriorityQueue;
import Actividades.Actividad_2.QueueLink;


public class PriorityQueueLinked<E> implements PriorityQueue<E, Integer> {
    private QueueLink<E>[] colas;
    private int prioridades;

    @SuppressWarnings("unchecked")
    public PriorityQueueLinked(int prioridades) {
        this.prioridades = prioridades;
        colas = new QueueLink[prioridades];
        for (int i = 0; i < prioridades; i++) {
            colas[i] = new QueueLink<>();
        }
    }

    @Override
    public void enqueue(E x, Integer pr) {
        if (pr < 0 || pr >= prioridades) {
            throw new IllegalArgumentException("Prioridad fuera de rango");
        }
        colas[pr].enqueue(x);
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        for (int i = prioridades - 1; i >= 0; i--) {
            if (!colas[i].isEmpty()) {
                return colas[i].dequeue();
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía");
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        for (int i = prioridades - 1; i >= 0; i--) {
            if (!colas[i].isEmpty()) {
                return colas[i].front();
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía");
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        for (int i = 0; i < prioridades; i++) {
            if (!colas[i].isEmpty()) {
                return colas[i].back();
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía");
    }

    @Override
    public boolean isEmpty() {
        for (QueueLink<E> cola : colas) {
            if (!cola.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = prioridades - 1; i >= 0; i--) {
            sb.append("Prioridad ").append(i).append(": ").append(colas[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
