package AVLTree;

import Excepciones.ExceptionIsEmpty;
import Excepciones.ItemDuplicated;
import Excepciones.ItemNotFound;

import BSTree.BSTTree;

public class AVLTree<E extends Comparable<E>> extends BSTTree <E>{
    class NodeAVL extends Nodo {
        protected int bf;
        E data;
        public Nodo right,left;
        public NodeAVL(E data) {
            super(data);
        }

        public String toString() {
            return data.toString();
        }
    }
    
    private boolean height;

    public void insert(E x) throws ItemDuplicated {
        this.height = false;
        this.root = insert(x, (NodeAVL) this.root);
    }

    protected Nodo insert(E x, NodeAVL node) throws ItemDuplicated {
        NodeAVL fat = node;

        if (node == null) {
            this.height = true;
            fat = new NodeAVL(x);
        } else {
            int resC = node.data.compareTo(x);
            if (resC == 0)
                throw new ItemDuplicated(x + " ya se encuentra en el árbol...");

            if (resC < 0) {
                fat.right = insert(x, (NodeAVL) node.right);
                if (this.height) {
                    switch (fat.bf) {
                        case -1:
                            fat.bf = 0;
                            this.height = false;
                            break;
                        case 0:
                            fat.bf = 1;
                            this.height = true;
                            break;
                        case 1:
                            fat = balanceToLeft(fat);
                            this.height = false;
                            break;
                    }
                }
            } else {
                // Aquí va el código para insertar a la izquierda
                // if (resC > 0) { ... }
            }
        }

        return fat;
    }

}
