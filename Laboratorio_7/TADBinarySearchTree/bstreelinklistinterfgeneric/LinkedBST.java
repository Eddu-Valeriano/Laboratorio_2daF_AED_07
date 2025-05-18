package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import Excepciones.ItemDuplicated;

import java.util.ArrayList;

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
        if (cmp < 0)node.left = delete(node.left, data);
        else if (cmp > 0)node.right = delete(node.right, data);
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
        if (cmp < 0)return search(node.left, data);
        else if (cmp > 0)return search(node.right, data);
        else
            return node;
    }
    public boolean isEmpty() {
        return root == null;
    }
}