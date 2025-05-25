
package AVLTree;

import Excepciones.ItemDuplicated;

public class Prueba {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        try {
            //1.RSR(Rotación Simple a la Derecha)
            tree.insert(30);
            tree.insert(20);
            tree.insert(10);//desequilibrio izquierda-izquierda

            // 2.RSL(Rotación Simple a la Izquierda)
            tree.insert(40);
            tree.insert(50);
            tree.insert(60); //desequilibrio derecha-derecha

            tree.insert(25); 

            // 4.RDL(Rotación Doble a la Izquierda)
            tree.insert(55); //desequilibrio derecha-izquierda (en subarbol derecho)

            //Ver estructura
            System.out.println("Árbol AVL por niveles:");
            tree.mostrarPorNiveles();

        } catch (ItemDuplicated e) {
            System.err.println(e.getMessage());
        }
    }
}





