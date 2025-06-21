package btree;

import java.io.*;
import java.util.*;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
        this.nDes = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E cl) {
        up = false;
        System.out.println("=== Insertar " + cl + " ===");
        E mediana = push(this.root, cl);
        if (up) {
            // Crear nueva raíz
            System.out.println("División en la raíz, mediana: " + mediana);
            BNode<E> oldRoot = this.root;
            BNode<E> pnew = new BNode<>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, oldRoot);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }

    }

    private E push(BNode<E> current, E cl) {
        int[] pos = new int[1];
        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        }
        boolean found = current.searchNode(cl, pos);
        if (found) {
            System.out.println("Item duplicado: " + cl);
            up = false;
            return null;
        }
        E mediana = push(current.childs.get(pos[0]), cl);
        if (up) {
            if (current.nodeFull()) {
                mediana = dividedNode(current, mediana, pos[0]);
                return mediana;
            } else {
                putNode(current, mediana, nDes, pos[0]);
                up = false;
                return null;
            }
        }
        return null;
    }

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        // Desplazar claves e hijos
        for (int i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        // Insertar la nueva clave en el nodo antes de dividir
        putNode(current, cl, nDes, k);
        // Calcular posición media tras inserción
        int mid = (current.count - 1) / 2;
        E median = current.keys.get(mid);
        // Nuevo nodo derecho
        nDes = new BNode<>(orden);
        // Mover claves e hijos derechos
        int j = 0;
        for (int i = mid + 1; i < current.count; i++) {
            nDes.keys.set(j, current.keys.get(i));
            nDes.childs.set(j + 1, current.childs.get(i + 1));
            current.keys.set(i, null);
            current.childs.set(i + 1, null);
            j++;
        }
        // Ajustar hijo 0 de nDes
        nDes.childs.set(0, current.childs.get(mid + 1));
        if (current.childs.get(mid + 1) != null) {
        }
        current.childs.set(mid + 1, null);
        // Ajustar counts
        nDes.count = j;
        current.count = mid;
        up = true;
        return median;
    }

    @Override
    public String toString() {
        if (root == null)
            return "(vacío)";
        StringBuilder sb = new StringBuilder();
        writeTree(this.root, 0, sb);
        return sb.toString();
    }

    private void writeTree(BNode<E> node, int nivel, StringBuilder sb) {
        if (node == null)
            return;
        String indent = "  ".repeat(nivel);
        sb.append(indent).append(node).append("\n");
        for (int i = 0; i <= node.count; i++) {
            writeTree(node.childs.get(i), nivel + 1, sb);
        }
    }
    //Metodo principal donde se recibe la clave
    public boolean search(E clave) {
        return search(clave, root);
    }
    // Metodo de busqueda recursiva
    public boolean search(E data, BNode<E> current) {
        if (current == null) {//retorna null en el caso de que ya no exista un nodo que buscar
            return false;
        }
        int[] pos = new int[1];
        boolean fl = current.searchNode(data, pos);
        if (fl == false) {
            return search(data, current.childs.get(pos[0]));
        }
        return true;
    }

    public void remove(E clave) {
        if (root == null)
            return;
        remove(root, clave);
        // Si la raíz queda vacía, ajustamos el árbol
        if (root.count == 0) {
            if (!root.esHoja()) {
                root = root.childs.get(0);
            } else {
                root = null;
            }
        }
    }

    private void remove(BNode<E> node, E clave) {
        int[] pos = new int[1];
        boolean found = node.searchNode(clave, pos);

        if (found) {
            if (node.esHoja()) {
                // Eliminar directamente si es hoja
                for (int i = pos[0]; i < node.count - 1; i++)
                    node.keys.set(i, node.keys.get(i + 1));
                node.keys.set(node.count - 1, null);
                node.count--;
            } else {
                // Reemplazar con el predecesor si es nodo interno
                E pred = getPredecessor(node.childs.get(pos[0]));
                node.keys.set(pos[0], pred);
                remove(node.childs.get(pos[0]), pred);
                fixChild(node, pos[0]);
            }
        } else {
            BNode<E> child = node.childs.get(pos[0]);
            if (child == null)
                return;
            remove(child, clave);
            fixChild(node, pos[0]);
        }
    }

    private E getPredecessor(BNode<E> node) {
        // Buscar el predecesor más grande (último en subárbol izquierdo)
        while (!node.esHoja())
            node = node.childs.get(node.count);
        return node.keys.get(node.count - 1);
    }

    private void fixChild(BNode<E> parent, int idx) {
        BNode<E> child = parent.childs.get(idx);
        // Si el hijo tiene suficientes claves, no hacemos nada
        if (child.count >= (orden - 1) / 2)
            return;
        // Intentar tomar clave prestada del hermano izquierdo
        if (idx > 0 && parent.childs.get(idx - 1).count > (orden - 1) / 2) {
            borrowFromPrev(parent, idx);
        }
        // Si no, intentar del hermano derecho
        else if (idx < parent.count && parent.childs.get(idx + 1).count > (orden - 1) / 2) {
            borrowFromNext(parent, idx);
        }
        // Si no se puede prestar, fusionar nodos
        else {
            if (idx < parent.count) {
                merge(parent, idx);
            } else {
                merge(parent, idx - 1);
            }
        }
    }

    private void borrowFromPrev(BNode<E> parent, int idx) {
        // Tomar una clave del hermano izquierdo
        BNode<E> child = parent.childs.get(idx);
        BNode<E> sibling = parent.childs.get(idx - 1);
        for (int i = child.count; i > 0; i--) {
            child.keys.set(i, child.keys.get(i - 1));
        }
        child.keys.set(0, parent.keys.get(idx - 1));
        if (!sibling.esHoja()) {
            for (int i = child.count + 1; i > 0; i--) {
                child.childs.set(i, child.childs.get(i - 1));
            }
            child.childs.set(0, sibling.childs.get(sibling.count));
        }
        parent.keys.set(idx - 1, sibling.keys.get(sibling.count - 1));
        sibling.keys.set(sibling.count - 1, null);
        sibling.count--;
        child.count++;
    }

    private void borrowFromNext(BNode<E> parent, int idx) {
        // Tomar una clave del hermano derecho
        BNode<E> child = parent.childs.get(idx);
        BNode<E> sibling = parent.childs.get(idx + 1);
        child.keys.set(child.count, parent.keys.get(idx));
        if (!sibling.esHoja()) {
            child.childs.set(child.count + 1, sibling.childs.get(0));
        }
        parent.keys.set(idx, sibling.keys.get(0));
        for (int i = 0; i < sibling.count - 1; i++) {
            sibling.keys.set(i, sibling.keys.get(i + 1));
        }
        sibling.keys.set(sibling.count - 1, null);
        if (!sibling.esHoja()) {
            for (int i = 0; i < sibling.count; i++) {
                sibling.childs.set(i, sibling.childs.get(i + 1));
            }
            sibling.childs.set(sibling.count, null);
        }
        sibling.count--;
        child.count++;
    }

    private void merge(BNode<E> parent, int idx) {
        // Fusionar el hijo con su hermano derecho
        BNode<E> child = parent.childs.get(idx);
        BNode<E> sibling = parent.childs.get(idx + 1);
        child.keys.set(child.count, parent.keys.get(idx));
        for (int i = 0; i < sibling.count; i++) {
            child.keys.set(child.count + 1 + i, sibling.keys.get(i));
        }
        if (!child.esHoja()) {
            for (int i = 0; i <= sibling.count; i++) {
                child.childs.set(child.count + 1 + i, sibling.childs.get(i));
            }
        }
        for (int i = idx; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }
        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
        child.count += sibling.count + 1;
        parent.count--;
    }

    public void cargarDesdeArchivo(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            // Leer el orden del árbol (primera línea)
            String linea = br.readLine();
            this.orden = Integer.parseInt(linea.trim());

            Map<Integer, BNode<E>> mapaNodos = new HashMap<>();
            Map<Integer, List<BNode<E>>> niveles = new TreeMap<>(); // TreeMap para mantener orden por nivel

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.trim().split(",");
                int nivel = Integer.parseInt(partes[0].trim());
                int idNodo = Integer.parseInt(partes[1].trim());

                BNode<E> nodo = new BNode<>(orden);
                nodo.idNode = idNodo;
                nodo.count = partes.length - 2;

                // Agregar claves
                for (int i = 0; i < nodo.count; i++) {
                    nodo.keys.set(i, parseClave(partes[i + 2]));
                }

                mapaNodos.put(idNodo, nodo);
                niveles.computeIfAbsent(nivel, k -> new ArrayList<>()).add(nodo);
            }

            // Armar jerarquía de nodos
            for (int nivel = 0; nivel < niveles.size() - 1; nivel++) {
                List<BNode<E>> padres = niveles.get(nivel);
                List<BNode<E>> hijos = niveles.get(nivel + 1);
                int indiceHijo = 0;

                for (BNode<E> padre : padres) {
                    int cantidadHijos = padre.count + 1;
                    for (int i = 0; i < cantidadHijos && indiceHijo < hijos.size(); i++) {
                        padre.childs.set(i, hijos.get(indiceHijo++));
                    }
                }
            }

            // Definir raíz (único nodo de nivel 0)
            this.root = niveles.get(0).get(0);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Método auxiliar para convertir clave de texto a tipo E (solo Integer
    // soportado aquí)
    @SuppressWarnings("unchecked")
    private E parseClave(String texto) {
        try {
            return (E) Integer.valueOf(texto.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Clave inválida: " + texto);
        }
    }

    public void imprimir() {
        imprimirNodo(root, 0);
    }

    public void imprimirNodo(BNode<E> nodo, int nivel) {
        if (nodo == null)
            return;
        System.out.println("  ".repeat(nivel) + nodo);
        for (int i = 0; i <= nodo.count; i++) {
            imprimirNodo(nodo.childs.get(i), nivel + 1);
        }
    }

    // Metodo auxiliar para la clase RegistroEstudiante
    public String buscarNombre(E codigo) {
        String mensaje = buscarNombreRecursivo(root, codigo);
        if (mensaje != null) {
            return mensaje;
        }
        return "No encontrado";
    }

    // Metodo que va a efectuar la busqueda a traves de la recursividad(similar al
    // metodo search)
    public String buscarNombreRecursivo(BNode<E> nodo, E codigo) {
        if (nodo == null) {
            return null;
        }
        int[] pos = new int[1];
        boolean resp = nodo.searchNode(codigo, pos);
        if (resp == false) {
            return buscarNombreRecursivo(nodo.childs.get(pos[0]), codigo);
        }
        return (nodo.keys.get(pos[0])).toString();
    }

}