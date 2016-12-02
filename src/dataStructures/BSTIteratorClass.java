package dataStructures;

/**
 * Created by nunomorais on 18/11/2016.
 */
public class BSTIteratorClass<K,V> implements Iterator<Entry<K,V>> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BSTNode<K,V> root ;
    private Queue<BSTNode<K,V>> queue;

    public BSTIteratorClass(BSTNode<K,V> node){
        this.root = node;
        rewind();
    }
    @Override
    public Entry<K,V> next() throws NoSuchElementException {

        if(!this.hasNext()) throw new NoSuchElementException();
        BSTNode<K,V> current = queue.dequeue();

        if(current.getLeft() != null)
            queue.enqueue(current.getLeft());
        if(current.getRight()!=null)
            queue.enqueue(current.getRight());
        return current.getEntry();
    }

    @Override
    public void rewind() {

        this.queue = new QueueInList<>();
        this.queue.enqueue(root);

    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
