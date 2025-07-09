package Ejercicios;

import hashC.HashC;
import hashC.Register;

public class Ejercicio4 {
    public static void main(String[] args) {
        HashC tabla = new HashC(7);

        tabla.insert(new Register(5, "A"));
        tabla.insert(new Register(12, "B"));
        tabla.insert(new Register(19, "C"));

        System.out.println("Tabla luego de las inserciones:");
        tabla.printTable();

        tabla.delete(12);
        System.out.println("\nTabla después de eliminar la clave 12:");
        tabla.printTable();

        System.out.println("\nBuscar la clave 19:");
        Register r = tabla.search(19);
        System.out.println(r != null ? "Encontrado: " + r : "No encontrado");
    }
}
