package Ejercicios;
import hashC.HashC;
import hashC.Register;

public class Ejercicio2 {
    public static void main(String[] args) {
        HashC tabla = new HashC(6); // Tamaño pequeño para provocar colisiones

        // Insertar 12
        System.out.println("Insertando (12, A):");
        tabla.insert(new Register(12, "A"));
        tabla.printTable();
        System.out.println();

        // Insertar 18
        System.out.println("Insertando (18, B):");
        tabla.insert(new Register(18, "B"));
        tabla.printTable();
        System.out.println();

        // Insertar 24
        System.out.println("Insertando (24, C):");
        tabla.insert(new Register(24, "C"));
        tabla.printTable();
        System.out.println();

        // Insertar 30
        System.out.println("Insertando (30, D):");
        tabla.insert(new Register(30, "D"));
        tabla.printTable();
        System.out.println();
    }
}
