package hashO;


public class TestHashO {
    public static void main(String[] args) {
        HashO tabla = new HashO(5); // Tamaño pequeño para forzar colisiones

        // Insertar registros (varios colisionan al usar key % 5)
        tabla.insert(new Register(1, "Ana"));    // 1 % 5 = 1
        tabla.insert(new Register(6, "Luis"));   // 6 % 5 = 1 (colisión)
        tabla.insert(new Register(11, "Eva"));   // 11 % 5 = 1 (colisión)
        tabla.insert(new Register(2, "Carlos")); // 2 % 5 = 2
        tabla.insert(new Register(7, "Lucía"));  // 7 % 5 = 2 (colisión)
        tabla.insert(new Register(3, "Pedro"));  // 3 % 5 = 3

        System.out.println("Contenido de la tabla hash:");
        tabla.printTable();
    }
}
