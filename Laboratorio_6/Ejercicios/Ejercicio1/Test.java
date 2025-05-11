package Ejercicios.Ejercicio1;
import Actividades.Actividad_2.Nodo;
import Actividades.Actividad_1.ExceptionIsEmpty;
import Actividades.Actividad_1.Stack;
public class Test {
    public static void main(String[] args) {
        try {
            Stack<Integer> pila = new StackLink<>();
            pila.push(1);
            pila.push(2);
            pila.push(3);

            System.out.println("Pila actual: " + pila);
            System.out.println("Tope:"+ pila.top());
            System.out.println("Pop:" + pila.pop());
            System.out.println("Pila después de pop:"+ pila);

            System.out.println("¿Está vacía? "+ pila.isEmpty());
            pila.pop();
            pila.pop();
            System.out.println("¿Está vacía ahora? "+ pila.isEmpty());
            pila.pop(); 

        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción:"+ e.getMessage());
        }
    }
}
