package bstreeInterface;
import Excepciones.ExceptionIsEmpty;
import Excepciones.ItemDuplicated;
import Excepciones.ItemNotFound;

public interface BinarySearchTree<E extends Comparable<E>> {
    void insert(E data)throws ItemDuplicated, ItemDuplicated;
    E search(E data)throws ItemNotFound;
    void delete(E data) throws ExceptionIsEmpty;
    boolean isEmpty();
}
