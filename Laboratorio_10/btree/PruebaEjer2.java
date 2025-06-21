package btree;

public class PruebaEjer2 {
    public static void main(String[] args) {
        System.out.println("Lista de numeros ingresados:10,20,5,6,12,13,14,23,22,21");
        int[]lista={10,20,5,6,12,13,14,23,22,21};
        BTree<Integer> arbol=new BTree<>(4);
        for(int i=0;i<10;i++){
            arbol.insert(lista[i]);
        }
        System.out.println("Eliminando el elemento 12");
        arbol.remove(12);
        System.out.println("Busqueda del numero 12:"+arbol.search(12));
        System.out.println(arbol.toString());
    }
    
}
