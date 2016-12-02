/*
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */

package dataStructures;

/**
 * iterates through all the elements of a tree through descendent order of key
 * and consequently iterates through all the lists in the tree
 */
public class BSTVKeyOrderValueIterator<K extends Comparable<K>,V,E> implements Iterator<E> {


    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	Iterator<Entry<K,V>> tree_iterator;
    DoublyLinkedList<E> next_list;
    DoublyLLIterator<E> next_list_iterator;

    public BSTVKeyOrderValueIterator(OrderedDictionary<K,V> tree){
        tree_iterator = tree.iterator();
        this.rewind();

    }
    @Override
    public boolean hasNext() {
        return next_list_iterator.hasNext();
    }

    @Override
    public E next() throws NoSuchElementException {
        if(!hasNext()) throw new NoSuchElementException();

        E next = next_list_iterator.next();
        this.goToNext();
        return next;
    }

    @SuppressWarnings("unchecked")
	private void goToNext(){

        while(!next_list_iterator.hasNext() && tree_iterator.hasNext()){
            next_list = ((DoublyLinkedList<E>) tree_iterator.next().getValue());
            next_list_iterator = (DoublyLLIterator<E>) next_list.iterator();
        }
    }

    @Override
    public void rewind() {
        tree_iterator.rewind();
        next_list = new DoublyLinkedList<E>();
        next_list_iterator = (DoublyLLIterator<E>) next_list.iterator();
        this.goToNext();
    }
}
