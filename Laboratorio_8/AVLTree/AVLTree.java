package AVLTree;

import Excepciones.ExceptionIsEmpty;
import Excepciones.ItemDuplicated;
import Excepciones.ItemNotFound;
import java.util.LinkedList;
import java.util.Queue;

import BSTree.LinkedBST;

public class AVLTree<E extends Comparable<E>> extends LinkedBST<E> {
    class NodeAVL extends Node {
        protected int bf;
        E data;
        public Node right, left;

        public NodeAVL(E data) {
            super(data);
            this.data = data;
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

    protected Node insert(E x, NodeAVL node) throws ItemDuplicated {
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
            } else { // resC > 0
                fat.left = insert(x, (NodeAVL) node.left);
                if (this.height) {
                    switch (fat.bf) {
                        case 1:
                            fat.bf = 0;
                            this.height = false;
                            break;
                        case 0:
                            fat.bf = -1;
                            this.height = true;
                            break;
                        case -1:
                            fat = balanceToRight(fat);
                            this.height = false;
                            break;
                    }
                }
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

    private NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.left;
        switch (hijo.bf) {
            case -1:
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSR(node);
                break;
            case 1:
                NodeAVL nieto = (NodeAVL) hijo.right;
                switch (nieto.bf) {
                    case 1:
                        node.bf = 0;
                        hijo.bf = -1;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case -1:
                        node.bf = 1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                node.left = rotateSL(hijo);
                node = rotateSR(node);
                break;
        }
        return node;
    }

    private NodeAVL rotateSL(NodeAVL node) {
        NodeAVL p = (NodeAVL) node.right;
        node.right = p.left;
        p.left = node;
        node = p;
        return node;
    }

    private NodeAVL rotateSR(NodeAVL node) {
        NodeAVL p = (NodeAVL) node.left;
        node.left = p.right;
        p.right = node;
        node = p;
        return node;
    }

    public void mostrarPorNiveles() {
        if (root == null) {
            System.out.println("Árbol vacío.");
            return;
        }

        Queue<Node> cola = new LinkedList<>();
        cola.add(root);
        int nivel = 0;

        while (!cola.isEmpty()) {
            int cantidadEnNivel = cola.size();
            System.out.print("Nivel " + nivel + ": ");

            for (int i = 0; i < cantidadEnNivel; i++) {
                Node actual = cola.poll();
                System.out.print(actual + " ");

                if (actual instanceof NodeAVL) {
                    NodeAVL nodoAVL = (NodeAVL) actual;
                    if (nodoAVL.left != null)
                        cola.add(nodoAVL.left);
                    if (nodoAVL.right != null)
                        cola.add(nodoAVL.right);
                }
            }

            System.out.println();
            nivel++;
        }
    }

    public int altura() {
        return altura((NodeAVL) this.root);
    }

    private int altura(NodeAVL node) {
        if (node == null)
            return -1;
        int altIzq = altura((NodeAVL) node.left);
        int altDer = altura((NodeAVL) node.right);
        return 1 + Math.max(altIzq, altDer);
    }

    public void remove(E x) throws ItemNotFound, ExceptionIsEmpty {
        if (root == null)
            throw new ExceptionIsEmpty("Árbol vacío");
        this.height = false;
        this.root = remove((NodeAVL) this.root, x);
    }

    // Metodo para poder remover un elemento del AVL
    private NodeAVL remove(NodeAVL node, E x) throws ItemNotFound {
        if (node == null)
            throw new ItemNotFound("Elemento no encontrado");

        NodeAVL fat = node;
        int resC = x.compareTo(node.data);

        if (resC < 0) {
            fat.left = remove((NodeAVL) node.left, x);
            if (this.height)
                fat = balanceToLeftDeletion(fat);
        } else if (resC > 0) {
            fat.right = remove((NodeAVL) node.right, x);
            if (this.height)
                fat = balanceToRightDeletion(fat);
        } else {
            if (node.left == null || node.right == null) {
                fat = (NodeAVL) (node.left != null ? node.left : node.right);
                this.height = true;
            } else {
                NodeAVL min = findMin((NodeAVL) node.right);
                node.data = min.data;
                node.right = remove((NodeAVL) node.right, min.data);
                if (this.height)
                    fat = balanceToRightDeletion(node);
            }
        }

        return fat;
    }

    private NodeAVL findMin(NodeAVL node) {
        while (node.left != null)
            node = (NodeAVL) node.left;
        return node;
    }

    private NodeAVL balanceToLeftDeletion(NodeAVL node) {
        switch (node.bf) {
            case 1:
                node.bf = 0;
                break;
            case 0:
                node.bf = -1;
                this.height = false;
                break;
            case -1:
                NodeAVL right = (NodeAVL) node.right;
                switch (right.bf) {
                    case 0:
                        node = rotateSL(node);
                        right.bf = 1;
                        node.bf = -1;
                        this.height = false;
                        break;
                    case 1:
                        node = rotateSL(node);
                        node.bf = 0;
                        break;
                    case -1:
                        node.right = rotateSR(right);
                        node = rotateSL(node);
                        break;
                }
                break;
        }
        return node;
    }

    private NodeAVL balanceToRightDeletion(NodeAVL node) {
        switch (node.bf) {
            case -1:
                node.bf = 0;
                break;
            case 0:
                node.bf = 1;
                this.height = false;
                break;
            case 1:
                NodeAVL left = (NodeAVL) node.left;
                switch (left.bf) {
                    case 0:
                        node = rotateSR(node);
                        left.bf = -1;
                        node.bf = 1;
                        this.height = false;
                        break;
                    case -1:
                        node = rotateSR(node);
                        node.bf = 0;
                        break;
                    case 1:
                        node.left = rotateSL(left);
                        node = rotateSR(node);
                        break;
                }
                break;
        }
        return node;
    }

}
