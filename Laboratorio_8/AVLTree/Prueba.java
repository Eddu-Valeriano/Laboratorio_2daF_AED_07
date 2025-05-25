
package AVLTree;

import javax.sound.sampled.Line;

import BSTree.LinkedBST;
import Excepciones.ExceptionIsEmpty;
import Excepciones.ItemDuplicated;
import Excepciones.ItemNotFound;

public class Prueba {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        /*
         * try {
         * // 🔁 1. RSR (Rotación Simple a la Derecha)
         * System.out.println("Inserción de los elementos 30, 20 y 10 (RSR esperada)");
         * tree.insert(30);
         * System.out.println("Insertado 30");
         * tree.insert(20);
         * System.out.println("Insertado 20");
         * tree.insert(10); // desequilibrio izquierda-izquierda
         * System.out.println("Insertado 10 -> Se aplica RSR");
         * tree.mostrarPorNiveles();
         * 
         * // 🔁 2. RSL (Rotación Simple a la Izquierda)
         * System.out.println("\nInserción de los elementos 40, 50 y 60 (RSL esperada)"
         * );
         * tree.insert(40);
         * System.out.println("Insertado 40");
         * tree.insert(50);
         * System.out.println("Insertado 50");
         * tree.insert(60); // desequilibrio derecha-derecha
         * System.out.println("Insertado 60 -> Se aplica RSL");
         * tree.mostrarPorNiveles();
         * 
         * // 🔁 3. RDR (Rotación Doble a la Derecha)
         * System.out.println("\nInserción del elemento 25 (RDR esperada)");
         * tree.insert(25); // desequilibrio izquierda-derecha
         * System.out.println("Insertado 25 -> Se aplica RDR");
         * tree.mostrarPorNiveles();
         * 
         * // 🔁 4. RDL (Rotación Doble a la Izquierda)
         * System.out.println("\nInserción del elemento 55 (RDL esperada)");
         * tree.insert(55); // desequilibrio derecha-izquierda
         * System.out.println("Insertado 55 -> Se aplica RDL");
         * tree.mostrarPorNiveles();
         * 
         * // ✅ Ver estructura final
         * System.out.println("\nÁrbol AVL final por niveles:");
         * tree.mostrarPorNiveles();
         * 
         * } catch (ItemDuplicated e) {
         * System.err.println(e.getMessage());
         * }
         */
        /* 
        AVLTree<Integer> arbol = new AVLTree<>();

        try {
            // Inserciones
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            arbol.insert(10);
            arbol.insert(25);
            arbol.insert(35);
            arbol.insert(50);

            System.out.println("Árbol original:");
            arbol.mostrarPorNiveles();

            // Eliminaciones
            System.out.println("\nEliminando 40...");
            arbol.remove(40);
            arbol.mostrarPorNiveles();

            System.out.println("\nEliminando 30...");
            arbol.remove(30);
            arbol.mostrarPorNiveles();

            System.out.println("\nEliminando 20...");
            arbol.remove(20);
            arbol.mostrarPorNiveles();

        } catch (ItemDuplicated | ItemNotFound | ExceptionIsEmpty e) {
            System.out.println("Excepción: " + e.getMessage());
        }*/

        // Ejercicio 1
        /*
         * AVLTree<Integer> avl = new AVLTree<>();
         * LinkedBST<Integer> bst = new LinkedBST<>();
         * 
         * int[] datos = {50, 30, 70, 20, 40, 60, 80};
         * 
         * // Inserción en ambos árboles
         * for (int dato : datos) {
         * try {
         * avl.insert(dato);
         * bst.insert(dato);
         * } catch (ItemDuplicated e) {
         * System.out.println(e.getMessage());
         * }
         * }
         * System.out.println(bst);
         * // Mostrar alturas
         * System.out.println("Altura AVL: " + avl.altura());
         * System.out.println("Altura BST: " + 6);
         * 
         * // Buscar en AVL
         * try {
         * int x = 40;
         * boolean res = avl.searchA(x);
         * System.out.println("Elemento encontrado en AVL: " + res);
         * } catch (ItemNotFound e) {
         * System.out.println(e.getMessage());
         * }
         * 
         * // Comparaciones
         * System.out.println("Comparaciones AVL: " + avl.getComparaciones());
         * System.out.println("Comparaciones BST: " + 6);
         */
        //Ejercicio 5
        /* 
        AVLTree<Integer> avl=new AVLTree<Integer>();
        try {
            System.out.println("Elementos insertados: 50,30,70,20,40,60,80,10,25,65");
            avl.insert(50);
            avl.insert(30);
            avl.insert(70);
            avl.insert(80);
            avl.insert(20);
            avl.insert(40);
            avl.insert(60);
            avl.insert(10);
            avl.insert(25);
            avl.insert(65);
            System.out.println("Recorrido PreOrden ");
            avl.preOrdenAVL();

        } catch (ItemDuplicated  e) {
            System.out.println(e.getMessage());
        }
        */
        //Ejercicio 6
        AVLTree<Integer> avl = new AVLTree<>();

        // RSD (Rotación Simple a la Derecha)
        System.out.println("Insertando: 30, 20, 10");
        try{
        avl.insert(30);
        avl.insert(20);
        avl.insert(10); // RSD en 30
        avl.mostrarPorNiveles();
        // RSI (Rotación Simple a la Izquierda)
        System.out.println("Insertando: 40, 50, 60");
        avl.insert(40);
        avl.insert(50);
        avl.insert(60); // RSI en 40
        avl.mostrarPorNiveles();
        //RDI (Rotación Doble Izquierda-Derecha)
        System.out.println("Insertando: 25");
        avl.insert(25); // RDI en 20
        avl.mostrarPorNiveles();
        }catch(ItemDuplicated e){
            System.err.println();
        }
    }

}
