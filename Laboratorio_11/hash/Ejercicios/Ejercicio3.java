package Ejercicios;

import hashO.HashO;
import hashO.Register;

public class Ejercicio3 {
    public static void main(String[] args) {
        HashO tabla = new HashO(5); // tamaño 5

        tabla.insert(new Register(10, "Juan"));
        tabla.insert(new Register(15, "Ana"));
        tabla.insert(new Register(20, "Luis"));
        tabla.insert(new Register(25, "Rosa"));

        System.out.println("Tabla después de las inserciones:");
        tabla.printTable();

        System.out.println("\nBuscar clave 20:");
        Register r1 = tabla.search(20);
        System.out.println(r1 != null ? r1.getName() : "No encontrado");

        System.out.println("\nBuscar clave 30:");
        Register r2 = tabla.search(30);
        System.out.println(r2 != null ? r2.getName() : "No encontrado");
    }
}

