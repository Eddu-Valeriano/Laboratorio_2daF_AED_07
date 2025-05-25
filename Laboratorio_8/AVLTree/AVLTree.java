package AVLTree;

import Excepciones.ExceptionIsEmpty;
import Excepciones.ItemDuplicated;
import Excepciones.ItemNotFound;

import java.util.LinkedList;
import java.util.Queue;

import BSTree.BSTTree;

public class AVLTree<E extends Comparable<E>> extends BSTTree<E> {
    class NodeAVL extends Nodo {
        protected int bf;
        E data;
        public Nodo right, left;

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

        Queue<Nodo> cola = new LinkedList<>();
        cola.add(root);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
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

    private int comparaciones;

    public int getComparaciones() {
        return comparaciones;
    }

    public boolean search(E x) {
        comparaciones = 0;
        return search((NodeAVL) this.root, x);
    }

    private boolean search(NodeAVL node, E x) {
        if (node == null)
            return false;

        comparaciones++; // se cuenta cada comparación
        int cmp = x.compareTo(node.data);
        if (cmp == 0)
            return true;
        if (cmp < 0)
            return search((NodeAVL) node.left, x);
        else
            return search((NodeAVL) node.right, x);
    }

}
