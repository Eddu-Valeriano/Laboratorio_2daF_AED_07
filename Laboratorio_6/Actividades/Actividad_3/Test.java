package Actividades.Actividad_3;
import Actividades.Actividad_2.Nodo;

import Actividades.Actividad_1.ExceptionIsEmpty;

public class Test {
    public static void main(String[] args) {
        try {
            PriorityQueueLinkSort<String, Integer> queue = new PriorityQueueLinkSort<>();

            System.out.println("¿Está vacía?"+ queue.isEmpty());

            queue.enqueue("Tarea baja",1);
            queue.enqueue("Tarea media",5);
            queue.enqueue("Tarea alta",10);

            System.out.println("Contenido de la cola:");
            System.out.println(queue);

            System.out.println("Frente de la cola: "+ queue.front());
            System.out.println("Último en la cola:"+ queue.back());

            System.out.println("Quitando elemento con mayor prioridad:"+ queue.dequeue());
            System.out.println("Nueva cola:");
            System.out.println(queue);

            queue.enqueue("Tarea urgente",20);
            System.out.println("Después de agregar tarea urgente:");
            System.out.println(queue);

        } catch (ExceptionIsEmpty e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
