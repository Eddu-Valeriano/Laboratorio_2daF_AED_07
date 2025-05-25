package AVLTree;

import BSTree.BSTTree;

public class AVLTree<E> extends BSTTree{
    class NodeAVL extends Nodo{
        protected int bf;
        public NodeAVL(E valor){
            super(valor);
        }
        public String toString(){
            String cadena=""+valor;
            return cadena;
        }
    }

    private boolean height;
    
}
