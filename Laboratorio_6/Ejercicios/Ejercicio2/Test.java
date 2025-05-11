package Ejercicios.Ejercicio2;
import Actividades.Actividad_1.ExceptionIsEmpty;
import Actividades.Actividad_2.Queue;

public class Test {
    public static void main(String[] args) {
        try {
            Queue<String> cola = new Cola<>(5);
            cola.enqueue("A");
            cola.enqueue("B");
            cola.enqueue("C");

            System.out.println(cola); 

            System.out.println("Frente: " + cola.front()); 
            System.out.println("Final: " + cola.back());  

            cola.dequeue();
            System.out.println("Después de dequeue: " + cola);

        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

