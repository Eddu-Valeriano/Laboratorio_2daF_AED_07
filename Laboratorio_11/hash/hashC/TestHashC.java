package hashC;

public class TestHashC {
    public static void main(String[] args) {
        HashC hash = new HashC(13); // tamaño pequeño para forzar colisiones

        // Insertar los valores
        hash.insert(new Register(34, "Ana")); 
        hash.insert(new Register(3, "Luis"));
        hash.insert(new Register(7, "Eva"));
        hash.insert(new Register(30, "Carlos"));
        hash.insert(new Register(11, "Pedro"));
        hash.insert(new Register(8, "Lucía"));
        hash.insert(new Register(7, "Mario"));     // clave repetida
        hash.insert(new Register(23, "Laura"));
        hash.insert(new Register(41, "Diego"));
        hash.insert(new Register(16, "Nora"));
        hash.insert(new Register(34, "Silvia"));   // clave repetida

        System.out.println("Tabla hash antes de eliminar:");
        hash.printTable();

        System.out.println("\nEliminando clave 30...");
        hash.delete(30);

        System.out.println("\nTabla hash después de eliminar:");
        hash.printTable();

        System.out.println("\nBuscando clave 23:");
        Register r = hash.search(23);
        if (r != null) {
            System.out.println("Encontrado: " + r);
        } else {
            System.out.println("No encontrado.");
        }
    }
}
