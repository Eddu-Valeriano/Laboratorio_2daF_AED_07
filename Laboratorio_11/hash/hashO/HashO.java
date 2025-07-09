package hashO;

import java.util.LinkedList;

public class HashO {
    private LinkedList<Register>[] table;
    private int size;

    public HashO(int size) {
        this.size = size;
        this.table = new LinkedList[size];

        // Inicializar cada lista enlazada
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Función hash
    private int hash(int key) {
        return key % size;
    }

    // Insertar un registro
    public void insert(Register reg) {
        int index = hash(reg.getKey());
        table[index].add(reg);
    }

    // Buscar un registro por su clave
    public Register search(int key) {
        int index = hash(key);
        for (Register r : table[index]) {
            if (r.getKey() == key) {
                return r;
            }
        }
        return null;
    }

    // Eliminar un registro por su clave
    public void delete(int key) {
        int index = hash(key);
        table[index].removeIf(r -> r.getKey() == key);
    }

    // Imprimir el contenido de la tabla
    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.print("[" + i + "]: ");
            for (Register r : table[i]) {
                System.out.print(r + " ");
            }
            System.out.println();
        }
    }
}

