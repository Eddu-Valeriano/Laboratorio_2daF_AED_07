package hashC;

public class HashC {
    private static class Element {
        Register register;
        boolean isAvailable;

        public Element() {
            this.register = null;
            this.isAvailable = true;
        }
    }

    private Element[] table; // Tabla hash
    private int size;        // Tamaño de la tabla

    public HashC(int size) {
        this.size = size;
        this.table = new Element[size];
        for (int i = 0; i < size; i++) {
            this.table[i] = new Element();
        }
    }

    private int hash(int key) {
        return key % size;  
    }

    public void insert(Register reg) {
        int index = hash(reg.getKey());
        int startIndex = index;

        do {
            if (table[index].register == null || table[index].isAvailable) {
                table[index].register = reg;
                table[index].isAvailable = false;
                return;
            }
            index = (index + 1) % size;
        } while (index != startIndex);

        System.out.println("Tabla llena. No se pudo insertar.");
    }

    public Register search(int key) {
        int index = hash(key);
        int startIndex = index;

        do {
            if (table[index].register == null && table[index].isAvailable) {
                return null; // No existe
            }
            if (!table[index].isAvailable && table[index].register.getKey() == key) {
                return table[index].register;
            }
            index = (index + 1) % size;
        } while (index != startIndex);

        return null; // No encontrado
    }

    public void delete(int key) {
        int index = hash(key);
        int startIndex = index;

        do {
            if (!table[index].isAvailable && table[index].register != null &&
                table[index].register.getKey() == key) {
                table[index].register = null;
                table[index].isAvailable = true;
                return;
            }
            index = (index + 1) % size;
        } while (index != startIndex);

        System.out.println("Elemento no encontrado para eliminar.");
    }

    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": ");
            if (!table[i].isAvailable && table[i].register != null) {
                System.out.println(table[i].register);
            } else {
                System.out.println("(vacío)");
            }
        }
    }
}
