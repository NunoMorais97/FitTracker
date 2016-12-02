package dataStructures;

/*
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
public class HashIterator<K,V> implements Iterator<Entry<K,V> > {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Iterator<Entry<K,V> > current_iterator;

    private Dictionary<K,V>[] table;
    int size;
    private int current; // current iterator index
    private int returned; // counts the number of returned entries


    @SuppressWarnings({ "rawtypes", "unchecked" })
	public HashIterator(ChainedHashTable table){
        this.table = table.table;
        this.size = table.size();
        this.rewind();
    }

    @Override
    public boolean hasNext() { return returned != size;  } // checks if all entries have been returned


    @Override
    public Entry<K,V> next() throws NoSuchElementException {

        if(!this.hasNext())
            throw new NoSuchElementException();

        Entry<K,V> next = (Entry<K,V>) current_iterator.next();
        returned++; // increments the counter  of returned elements
        findNext();

        return next;
    }
    
    /**
     * finds the next iterator in the table that has a "NEXT" element
     * only does so while there are entries to be returned
     */
    private void findNext(){
    	while(!current_iterator.hasNext() && (current < table.length)) {
            current_iterator = table[current].iterator();
            current++;
        }
    	
    	
    }

    @Override
    public void rewind() {
        current = 0;
        returned = 0;
        current_iterator = table[0].iterator();
        findNext();
    }
}

