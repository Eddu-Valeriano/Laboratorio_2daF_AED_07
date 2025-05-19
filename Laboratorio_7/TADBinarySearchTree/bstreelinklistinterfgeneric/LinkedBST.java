package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import Excepciones.ItemDuplicated;

import java.util.ArrayList;
import java.util.List;

import Excepciones.ExceptionIsEmpty;
import Excepciones.ItemNotFound;

@SuppressWarnings("unchecked")
public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    class Node {
        public E data;
        public Node left;
        public Node right;

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    public LinkedBST() {
        this.root = null;
    }

    public void insert(E data) throws ItemDuplicated {
        root = insert(root, data);
    }

    private Node insert(Node node, E data) throws ItemDuplicated {
        if (node == null)
            return new Node(data);
        int cmp = data.compareTo(node.data);
        if (cmp < 0)
            node.left = insert(node.left, data);
        else if (cmp > 0)
            node.right = insert(node.right, data);
        else
            throw new ItemDuplicated("Elemento duplicado");

        return node;
    }

    public void delete(E data) throws ExceptionIsEmpty {// Este metodo solo se realizara un llamado
        // Podria referirse como un metodo que hace llamado a un proceso
        if (root == null)
            throw new ExceptionIsEmpty("El árbol está vacío");
        root = delete(root, data);
    }

    private Node delete(Node node, E data) {// Este metodo ya representaria el proceso en gereal para el delete
        if (node == null)// Esta condicion detiene la recursividad cuando llegue a un punto donde el
                         // elemento ya no existe
            return null;

        int cmp = data.compareTo(node.data);
        if (cmp < 0)
            node.left = delete(node.left, data);
        else if (cmp > 0)
            node.right = delete(node.right, data);
        else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            Node temp = findMin(node.right);
            node.data = temp.data;
            node.right = delete(node.right, temp.data);
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    private Node findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public E search(E data) throws ItemNotFound {
        Node result = search(root, data);
        if (result == null)
            throw new ItemNotFound("Elemento no encontrado");
        return result.data;
    }

    private Node search(Node node, E data) {
        if (node == null)
            return null;
        int cmp = data.compareTo(node.data);
        if (cmp < 0)
            return search(node.left, data);
        else if (cmp > 0)
            return search(node.right, data);
        else
            return node;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left); // Visita el subárbol izquierdo
            System.out.println(node.data); // Visita el nodo actual (raíz)
            inOrderTraversal(node.right); // Visita el subárbol derecho
        }
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.println(node.data);// Visitar la raíz
            preOrderTraversal(node.left);// Subárbol izquierdo
            preOrderTraversal(node.right); // Subárbol derecho
        }
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left); // Subárbol izquierdo
            postOrderTraversal(node.right); // Subárbol derecho
            System.out.println(node.data); // Visitar la raíz
        }
    }

    // a.Metodo destroyNodes
    public void destroyNodes() throws ExceptionIsEmpty {
        if (root == null)
            throw new ExceptionIsEmpty("El árbol está vacío");
        root = null; // El recolector de basura de Java se encargará del resto
    }

    // b. MtodocountAllNodes (nodos no hoja)
    public int countAllNodes() {
        return countAllNodes(root);
    }

    private int countAllNodes(Node node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;
        return 1 + countAllNodes(node.left) + countAllNodes(node.right);
    }

    // c. Método countNodes(numero total de nodosincluyendo hojas)
    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node node) {
        if (node == null)
            return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // d. Método height(x)iterativo
    public int height(E x) {
        Node subRoot = search(root, x);
        if (subRoot == null)
            return -1;

        ArrayList<Node> currentLevel = new ArrayList<>();
        currentLevel.add(subRoot);
        int height = -1;

        while (!currentLevel.isEmpty()) {
            height++;
            ArrayList<Node> nextLevel = new ArrayList<>();
            for (Node node : currentLevel) {
                if (node.left != null)
                    nextLevel.add(node.left);
                if (node.right != null)
                    nextLevel.add(node.right);
            }
            currentLevel = nextLevel;
        }

        return height;
    }

    // e. Método amplitude(nivel)
    public int amplitude(int nivel) {
        if (root == null || nivel < 0)
            return 0;
        ArrayList<Node> currentLevel = new ArrayList<>();
        currentLevel.add(root);
        int currentDepth = 0;

        while (currentDepth < nivel && !currentLevel.isEmpty()) {
            ArrayList<Node> nextLevel = new ArrayList<>();
            for (Node node : currentLevel) {
                if (node.left != null)
                    nextLevel.add(node.left);
                if (node.right != null)
                    nextLevel.add(node.right);
            }
            currentLevel = nextLevel;
            currentDepth++;
        }

        return currentLevel.size(); // número de nodos en ese nivel
    }

    public int areaBST() {
        if (root == null)
            return 0; // Si el arbol esta vacio, el area es 0

        List<Node> cola = new ArrayList<>(); // Lista para recorrer los nodos como si fuera una cola
        cola.add(root); // Comenzamos desde la raiz

        int hojas = 0; // Contador de nodos hoja
        int altura = -1; // Altura del arbol, comienza en -1 porque se incrementa antes de revisar

        while (!cola.isEmpty()) {
            int nivelSize = cola.size(); // Cantidad de nodos en el nivel actual
            altura++; // Aumentamos la altura porque estamos por recorrer un nuevo nivel
            for (int i = 0; i < nivelSize; i++) {
                Node actual = cola.remove(0); // Sacamos el primer nodo de la lista

                // Si el nodo no tiene hijos, es una hoja
                if (actual.left == null && actual.right == null) {
                    hojas++;
                }
                // Si tiene hijos, los agregamos a la lista para revisarlos en el siguiente
                // nivel
                if (actual.left != null)
                    cola.add(actual.left);
                if (actual.right != null)
                    cola.add(actual.right);
            }
        }
        return hojas * altura; // El area es la cantidad de hojas por la altura del arbol
    }

    // Metodo toString que llama a un recorrido en orden y devuelve los elementos
    // como cadena
    @Override
    public String toString() {
        List<E> elementos = new ArrayList<>();
        inOrdenList(root, elementos); // Recorre en orden y guarda en la lista
        return elementos.toString(); // Devuelve los elementos como texto
    }

    private void inOrdenList(Node node, List<E> lista) {
        if (node != null) {
            inOrdenList(node.left, lista); // Visita el hijo izquierdo
            lista.add(node.data); // Visita el nodo actual
            inOrdenList(node.right, lista); // Visita el hijo derecho
        }
    }

    // Metodo drawBST que usa toString para "mostrar" el arbol
    public void drawBST() {
        System.out.println("Arbol BST (recorrido in-order): " + toString());
    }
    
    public static boolean sameArea(LinkedBST<?> bst1, LinkedBST<?> bst2) {
    return bst1.areaBST() == bst2.areaBST();
}

}