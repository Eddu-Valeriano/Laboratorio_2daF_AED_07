package btree;

public class PruebaEjer3 {
    public static void main(String[] args) {
        BTree<Integer> arbol = new BTree<>(0); // el orden se define al leer el archivo
        arbol.cargarDesdeArchivo("C:\\Users\\La maquina\\OneDrive\\Documentos\\UCSM\\Semestre 5\\Algoritmos y estructura de datos\\Practica\\Laboratorio_10\\btree\\BTree.txt");

        System.out.println("Árbol cargado:");
        arbol.imprimir();
    }
}
