package TADgraph.ListLinked;

import java.util.Iterator;
public class ListLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int count;
    public ListLinked() {
        head = null;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        count++;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            count++;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            count++;
        }
    }

    public boolean remove(T data) {
        if (head == null)
            return false;

        if (head.data.equals(data)) {
            head = head.next;
            count--;
            return true;
        }

        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            count--;
            return true;
        }

        return false;
    }

    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data))
                return true;
            current = current.next;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data).append(" -> ");
            current = current.next;
        }
        sb.append("null");
        return sb.toString();
    }

    public T get(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }
    public int size(){
        return count;
    }
    @Override
    public Iterator<T> iterator(){
        return new ListIterator();
    }
    private class ListIterator implements Iterator<T>{
        private Node<T> current=head;
        @Override
        public boolean hasNext(){
            return current!=null;
        }
        @Override
        public T next(){
            T data=current.data;
            current=current.next;
            return data;
        }
    }
    public boolean isEmpty(){
        return head==null;
    }
    public T getPosition(int index) {
    if (index < 0 || index >= count) {
        throw new IndexOutOfBoundsException("Índice fuera de rango.");
    }
    Node<T> current = head;
    for (int i = 0; i < index; i++) {
        current = current.next;
    }
    return current.data;
}

}
