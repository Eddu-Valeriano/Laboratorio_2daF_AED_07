package Ejercicios.Ejercicio3;
import Ejercicios.Ejercicio3.PriorityQueueLinked;
public class Test {
    public static void main(String[] args) {
        PriorityQueueLinked<String> cola = new PriorityQueueLinked<>(3);

        // Insertamos elementos con distintas prioridades
        cola.enqueue("Correo común", 0);      // Prioridad baja
        cola.enqueue("Notificación", 1);      // Prioridad media
        cola.enqueue("Alarma de incendio", 2); // Prioridad alta
        cola.enqueue("Publicidad", 0);        // Prioridad baja
        cola.enqueue("Actualización crítica", 2); // Prioridad alta
        System.out.println("Cantidad de elementos por nivel de prioridad");
        System.out.println(cola);
        // Ahora visualizamos el primero
        System.out.println("Front: " + cola.front());  // Debería ser "Alarma de incendio"

        // Quitamos uno por uno
        System.out.println("Dequeue: " + cola.dequeue()); // "Alarma de incendio"
        System.out.println("Dequeue: " + cola.dequeue()); // "Actualización crítica"
        System.out.println("Dequeue: " + cola.dequeue()); // "Notificación"
        System.out.println("Dequeue: " + cola.dequeue()); // "Correo común"
        System.out.println("Dequeue: " + cola.dequeue()); // "Publicidad"
        
    }
}
