package dataStructures;

/**
 * Chained Hash table implementation
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value 
 */

public class ChainedHashTable<K extends Comparable<K>, V>
        extends HashTable<K,V>
{
    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;

    /**
     * The array of dictionaries.
     */
    protected Dictionary<K,V>[] table;


    /**
     * Constructor of an empty chained hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public ChainedHashTable( int capacity )
    {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ ) {
            table[i] = new OrderedDoubleList<>();
        }
        maxSize = capacity;
        currentSize = 0;
    }


    public ChainedHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Returns the hash value of the specified key.
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }

    @Override
    public V find( K key )
    {
        return table[ this.hash(key) ].find(key);
    }

    @Override
    public V insert( K key, V value )
    {
        if ( this.isFull() ) {
            this.rehash();
        }

        int i = hash(key);

        //only adds to the current size such element did not exist there previously
        V old_value =this.table[i].insert(key,value);

        if(old_value == null)
            currentSize++;

        return old_value;
    }

    /**
     * resizes the table to be the size of the next prime and inserts the old elements in the new table
     */
    private void rehash() {

        Iterator<Entry<K,V>> it = this.iterator();
        ChainedHashTable<K,V> new_table = new ChainedHashTable<>(this.size()+1);

        while(it.hasNext()) {
            Entry<K,V> next = it.next();
            new_table.insert(next.getKey(),next.getValue());
        }

        this.table = new_table.table;
        this.currentSize = new_table.size();
    }

    @Override
    public V remove( K key )
    {
        int i ;
        i = this.hash(key);
        V value = table[i].remove(key);

        if(value != null)
            currentSize--;

        return value;
    }

    @Override
    public Iterator<Entry<K,V>> iterator( )
    {
        return new HashIterator<K,V>(this);
    }
}
































