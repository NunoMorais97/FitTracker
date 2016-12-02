/*
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */


package dataStructures;

/**
 * Created by nunomorais on 18/11/2016.
 */
public class BSTKeyOrderIterator<K,V> implements Iterator<Entry<K,V>> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BSTNode<K,V> root ;
    private StackInList<BSTNode<K,V>> stack;

    public BSTKeyOrderIterator(BSTNode<K,V> root){
        this.root = root;
        this.rewind();
    }

    @Override
    public boolean hasNext() {
        return !this.stack.isEmpty();
    }

    @Override
    public Entry<K,V> next() throws NoSuchElementException {

        if(!hasNext()) throw new NoSuchElementException();

        BSTNode<K,V> current = this.stack.pop();

        if( current.getRight() != null)
            stackLeft(current.getRight());

        return current.getEntry();
    }

    private void stackLeft(BSTNode<K,V> current){
        while(current != null){
            stack.push(current);
            current = current.getLeft();
        }
    }


    @Override
    public void rewind() {
        this.stack = new StackInList<>();
        this.stackLeft(root);
    }
}
