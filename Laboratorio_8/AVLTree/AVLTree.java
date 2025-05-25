package AVLTree;

import Excepciones.ExceptionIsEmpty;
import Excepciones.ItemDuplicated;
import Excepciones.ItemNotFound;

import BSTree.BSTTree;

public class AVLTree<E extends Comparable<E>> extends BSTTree<E> {
    class NodeAVL extends Nodo {
        protected int bf;
        E data;
        public Nodo right, left;

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

    private NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.right;
        switch (hijo.bf) {
            case 1:
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSL(node);
                break;
            case -1:
                NodeAVL nieto = (NodeAVL) hijo.left;
                switch (nieto.bf) {
                    case -1:
                        node.bf = 0;
                        hijo.bf = 1;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case 1:
                        node.bf = 1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                node.right = rotateSR(hijo);
                node = rotateSL(node);
                break;
        }
        return node;
    }
    private NodeAVL rotateSL(NodeAVL node){
        NodeAVL p=(NodeAVL)node.right;
        node.right=p.left;
        p.left=node;
        node=p;
        return node;
    }
    private NodeAVL rotateSR(NodeAVL node){
        NodeAVL p=(NodeAVL)node.left;
        node.left=p.right;
        p.right=node;
        node=p;
        return node;
    }

}
