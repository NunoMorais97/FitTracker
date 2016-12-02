package dataStructures;

/**
 * Created by nunomorais on 28/10/2016.
 */
public class OrderedDoubleList<K extends Comparable<K>,V>  implements OrderedDictionary<K,V> {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;


    /**
     * Node at the head of the list.
     */
    protected DListNode<Entry<K, V>> head;

    /**
     * Node at the tail of the list.
     */
    protected DListNode<Entry<K, V>> tail;

    /**
     * Number of elements in the list.
     */
    protected int currentSize;


    public OrderedDoubleList() {
        this.currentSize = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public Entry<K,V> minEntry() throws EmptyDictionaryException {
        if(this.isEmpty()) throw new EmptyDictionaryException();
        return this.head.getElement();
    }

    @Override
    public Entry<K,V>  maxEntry() throws EmptyDictionaryException {
        if(this.isEmpty()) throw new EmptyDictionaryException();
        return this.tail.getElement();
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public V find(K key) {
        if(currentSize == 0)
            return null;

        DListNode<Entry<K, V>> node = this.head;
        while (node != null) {
            if (node.getElement().getKey().equals(key))
                return node.getElement().getValue();
            node = node.getNext();
        }
        return null;
    }

    @Override
    public V insert(K key, V value) {


        // insertion if the list is null
        if (currentSize == 0) {
            DListNode<Entry<K, V>> node = new DListNode<Entry<K,V>>(new EntryClass<K,V> (key, value));
            this.head = node;
            this.tail = node;
            currentSize++;
            return null;
        }

        //if the key ins smaller than the key in the head of the list
        if(this.head.getElement().getKey().compareTo(key) > 0){
            DListNode<Entry<K, V>> new_head = new DListNode<>(new EntryClass<K, V>(key,value),null,this.head);
            head.setPrevious(new_head);
            this.head = new_head;
            currentSize++;
            return null;
        }
        //if the key is bigger than the key in the tail of the list
        if(this.tail.getElement().getKey().compareTo(key) < 0){
            DListNode<Entry<K, V>> new_tail = new DListNode<>(new EntryClass<K, V>(key,value),this.tail,null);
            tail.setNext(new_tail);
            this.tail = new_tail;
            currentSize++;
            return null;
        }

        DListNode<Entry<K, V>> node = this.head;


        //insertion in the middle of the list , this while's objective is to find the node who's key is smaller than the inserted key
        while (node != null && node.getElement().getKey().compareTo(key) <= 0) {

            if(node.getElement().getKey().compareTo(key) == 0) {
                //store the old value of the node
                V return_value = node.getElement().getValue();
                node.setElement(new EntryClass<K, V>(key, value));

                return return_value;
            }

            node = node.getNext();
        }

        if(node != null) {
            DListNode<Entry<K, V>> new_node = new DListNode<Entry<K, V>>(new EntryClass<K,V> (key, value),node.getPrevious(),node);
            node.getPrevious().setNext(new_node);
            node.setPrevious(new_node);
            currentSize++;
            return null;
        }
        return null;
    }


    @Override
    public V remove(K key) {

        if(currentSize == 0) return null;

        if(currentSize == 1 && head.getElement().getKey().compareTo(key) == 0){
            V value;
            value = this.tail.getElement().getValue();
            this.tail = null;
            this.head = null;
            currentSize = 0;
            return value;

        }


        if (head.getElement().getKey().compareTo(key) == 0) {
            V value;
            value = head.getElement().getValue();

            this.head =this.head.getNext();
            this.head.setPrevious(null);

            currentSize--;
            return value;
        }

        if (tail.getElement().getKey().compareTo(key) == 0) {
            V value;
            value = tail.getElement().getValue();

            this.tail = this.tail.getPrevious();
            this.tail.setNext(null);

            currentSize--;
            return value;
        }


        DListNode<Entry<K, V>> node = this.head.getNext();

        while (node != null && node.getElement().getKey().compareTo(key) != 0)
            node = node.getNext();

        if (node != null && node.getElement().getKey().compareTo(key) == 0) {
            V value;
            value = node.getElement().getValue();

            node.getNext().setPrevious(node.getPrevious());
            node.getPrevious().setNext(node.getNext());

            currentSize--;

            return value;
        }


        return null;

    }


    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new DoublyLLIterator<>(head, tail);


    }
}