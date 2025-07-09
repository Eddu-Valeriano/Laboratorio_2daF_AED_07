package Ejercicios;
import hashC.HashC;
import hashC.Register;
public class Ejercicio1 {
    public static void main(String[] args) {
        // Crear una tabla hash de tamaño 7 (para que se vean colisiones fácilmente)
        HashC tabla = new HashC(7);

        // Insertar los registros
        tabla.insert(new Register(3, "Elemento3"));
        tabla.insert(new Register(10, "Elemento10"));
        tabla.insert(new Register(17, "Elemento17"));
        tabla.insert(new Register(24, "Elemento24"));

        // Imprimir el estado de la tabla
        System.out.println("Contenido de la tabla hash:");
        tabla.printTable();
    }
}

