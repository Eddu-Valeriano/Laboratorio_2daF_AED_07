package bstreelinklistinterfgeneric;


import Excepciones.ExceptionIsEmpty;
import Excepciones.ItemDuplicated;
import Excepciones.ItemNotFound;

public class Prueba {
    
    public static boolean sameArea(LinkedBST<?> bst1, LinkedBST<?> bst2) {
        return bst1.areaBST() == bst2.areaBST();
    }
    public static void main(String[] args) {
         /* ACTIVIDAD 6
        LinkedBST<Integer> bst = new LinkedBST<>();
       
        try {
            // Verificar si el árbol está vacío
            System.out.println("¿El árbol está vacío? " + bst.isEmpty());

            // Insertar elementos
            System.out.println("\n--- Inserciones ---");
            bst.insert(10);
            System.out.println("Insertado: 10");
            bst.insert(5);
            System.out.println("Insertado: 5");
            bst.insert(15);
            System.out.println("Insertado: 15");

            // Verificar si el árbol ya no está vacío
            System.out.println("¿El árbol está vacío? " + bst.isEmpty());

            // Buscar un elemento existente
            System.out.println("\n--- Búsqueda ---");
            int buscado = 5;
            Integer resultado = bst.search(buscado);
            System.out.println("Elemento encontrado: " + resultado);

            // Eliminar un elemento
            System.out.println("\n--- Eliminación ---");
            bst.delete(10);
            System.out.println("Eliminado: 10");

            // Intentar buscar el elemento eliminado
            System.out.println("Buscando el elemento eliminado (10):");
            bst.search(10); // Esto lanzará una excepción

        } catch (ItemDuplicated e) {
            System.out.println("Error de duplicado: " + e.getMessage());
        } catch (ItemNotFound e) {
            System.out.println("Error de búsqueda: " + e.getMessage());
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error de eliminación: " + e.getMessage());
        }
        */
        //Actividad 7
        /* 
        LinkedBST<Integer> bst2=new LinkedBST<>();
        try{
            bst2.insert(400);
            bst2.insert(100);
            bst2.insert(700);
            bst2.insert(50);
            bst2.insert(200);
            bst2.insert(75);
            
            System.out.println("Recorrido InOrden");
            bst2.inOrderTraversal();
            System.out.println("Recorrido PreOrden");
            bst2.inOrderTraversal();
            System.out.println("Recorrido PostOrden");
            bst2.postOrderTraversal();
        }catch(ItemDuplicated e){
            System.out.println(e);
        }
        */ 
        /*Ejercicio 1 
        LinkedBST<Integer> bst = new LinkedBST<>();
        try {
            System.out.println("Insertando nodos...");
            bst.insert(50);
            bst.insert(30);
            bst.insert(70);
            bst.insert(20);
            bst.insert(40);
            bst.insert(60);
            bst.insert(80);

            System.out.println("Buscar 40: "+bst.search(40));
            System.out.println("Está vacío?: " +bst.isEmpty());
            System.out.println("Total de nodos: "+ bst.countNodes());
            System.out.println("Nodos no hoja: " +bst.countAllNodes());
            System.out.println("Altura del subárbol con raíz 30: " + bst.height(30));
            System.out.println("Amplitud en nivel 2: " + bst.amplitude(2));
            System.out.println("Eliminando nodo 70...");
            bst.delete(70);
            System.out.println("Destruyendo el árbol...");
            bst.destroyNodes();

            System.out.println("Está vacío ahora?: " + bst.isEmpty());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        */
        //Ejercicio 2
        /* 
        try{
        LinkedBST<Integer> a = new LinkedBST<>();
        LinkedBST<Integer> b = new LinkedBST<>();

        a.insert(5); a.insert(3); a.insert(7);
        b.insert(10); b.insert(8); b.insert(12);

        System.out.println("Área árbol A: " + a.areaBST());
        System.out.println("Área árbol B: " + b.areaBST());

        System.out.println("¿Misma área? " + sameArea(a, b));

        System.out.println("\nDibujo árbol A:");
        a.drawBST();

        System.out.println("\nDibujo árbol B:");
        b.drawBST();
        }catch(ItemDuplicated e){
            System.out.println(e);
        }
    */
    //Ejercicio 3
    LinkedBST<String> bst = new LinkedBST<>();
    try{
    bst.insert("Sales");
    bst.insert("Domestic");
    bst.insert("International");
    bst.insert("Canada");
    bst.insert("S. America");
    bst.insert("Overseas");
    bst.insert("Africa");
    bst.insert("Europe");
    bst.insert("Asia");
    bst.insert("Australia");
    }catch(ItemDuplicated e){
        System.out.println(e);
    }
    System.out.println("Representacion entre parentesis del arbol:");
    bst.parenthesize();


    }
    
}
