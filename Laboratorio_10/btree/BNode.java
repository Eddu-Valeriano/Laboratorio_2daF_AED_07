package btree;

import java.util.ArrayList;
class BNode<E extends Comparable<E>> {
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;
    protected int idNode;
    private static int serialNodeCounter = 0;
    protected int orden;

    public BNode(int n) {
        this.orden = n;
        this.keys = new ArrayList<>();
        this.childs = new ArrayList<>();
        // Inicializar claves (tamaño orden) y hijos (tamaño orden+1) para permitir desplazamiento temporal
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
        }
        for (int i = 0; i < n + 1; i++) {
            this.childs.add(null);
        }
        this.count = 0;
        this.idNode = serialNodeCounter++;
    }

    public boolean nodeFull() {
        return count == orden - 1;
    }

    public boolean nodeEmpty() {
        return count == 0;
    }

    public boolean esHoja() {
        for (BNode<E> c : childs) {
            if (c != null) return false;
        }
        return true;
    }

    public boolean searchNode(E codigo, int[] posicion) {
        for (int i = 0; i < count; i++) {
            E key = keys.get(i);
            int cmp = codigo.compareTo(key);
            if (cmp == 0) {
                posicion[0] = i;
                return true;
            } else if (cmp < 0) {
                posicion[0] = i;
                return false;
            }
        }
        posicion[0] = count;
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("idNode:").append(idNode).append(" [");
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));
            if (i < count - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}