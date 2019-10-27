import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Class representing a hash table object. 
 * 
 * @author CS 321
 * @param <V> - generic type of objects in the hash table
 */
@SuppressWarnings("unchecked")
public class HashTable<V>
{
	private OpenAddressType type; 
	private HashObject<V>[] hashTable; 
	private int capacity; 
	private float loadFactor; 
	private int size; 
	private int[] frequency; 
	private int numProbes; 
	private int maxSize; 
	
	private final int DEFAULT_CAPACITY = 13; 
	private final float DEFAULT_LOAD_FACTOR = 0.75f;
	private final OpenAddressType DEFAULT_TYPE = OpenAddressType.linear;
	
	/**
	 * Default constructor 
	 */
	public HashTable()
	{
		setCapacity(DEFAULT_CAPACITY); 
		setLoadFactor(DEFAULT_LOAD_FACTOR);
		setType(DEFAULT_TYPE);
		frequency  = new int[capacity]; 
		hashTable = new HashObject[capacity]; 
		setSize(0); 
		setNumProbes(0); 
		setMaxSize((int)(getLoadFactor() * getCapacity())); 
	}
	
	/**
	 * Constructor with given capacity.  
	 */
	public HashTable(int capacity)
	{
		setCapacity(capacity); 
		setLoadFactor(DEFAULT_LOAD_FACTOR);
		setType(DEFAULT_TYPE);
		frequency  = new int[capacity]; 
		hashTable = new HashObject[capacity]; 
		setSize(0); 
		setNumProbes(0); 
		setMaxSize((int)(getLoadFactor() * getCapacity())); 
	}

	/**
	 * Constructor with given capacity and load factor. 
	 */
	public HashTable(int capacity, float loadFactor)
	{
		setCapacity(capacity); 
		setLoadFactor(loadFactor);
		setType(DEFAULT_TYPE);
		frequency  = new int[capacity]; 
		hashTable = new HashObject[capacity]; 
		setSize(0); 
		setNumProbes(0); 
		setMaxSize((int)(getLoadFactor() * getCapacity())); 
	}

	/**
	 * Constructor with given capacity, load factor, and open address type. 
	 */
	public HashTable(int capacity, float loadFactor, OpenAddressType type)
	{
		setCapacity(capacity); 
		setLoadFactor(loadFactor);
		setType(type);
		frequency  = new int[capacity]; 
		hashTable = new HashObject[capacity]; 
		setSize(0); 
		setNumProbes(0); 
		setMaxSize((int)(getLoadFactor() * getCapacity())); 
	}
	
	/**
	 * Returns hash value for given key with given offset. 
	 * @return - int  value (hash)
	 */
	public int getHash(int key, int i)
	{
		int index = -1; 
		if(type == OpenAddressType.linear)
		{
			index = hash1(hash1(key, capacity) + i, capacity); 
		}
		else if(type == OpenAddressType.double_hash)
		{
			index = hash1(hash1(key, capacity) + i*hash2(key, capacity), capacity); 
		}
		return index; 
	}
	
	/**
	 * Generates simple hash value for key and capacity.
	 * @param k - int value
	 * @param m - int value 
	 * @return - int value 
	 */
	private int hash1(int k, int m)
	{
		return k % m; 
	}
	
	/**
	 * Generates more complex hash value for key k and capacity m.
	 * @param k - int value
	 * @param m - int value 
	 * @return - int value 
	 */
	private int hash2(int k, int m)
	{
		return 1 + hash1(k, m - 2); 
	}
	
	/**
	 * Whether hash table contains given value with given key 
	 * 	
	 * @param key - int value
	 * @param value - object of type V 
	 * @return - boolean value 
	 */
	public boolean contains(V value, int key)
	{
		// create HashObject instance for comparison 
		HashObject<V> object = new HashObject<V>(value, key); 
		
		int i = 0; 
		int index = getHash(key, i); 
		boolean found = false;
		
		// search until find value or open slot
		while(!found && hashTable[index] != null)
		{
			// if found 
			if(Objects.equals(object, hashTable[index]))
			{
				found = true; 
			}
			// otherwise, go to next index 
			else
			{
				i++; 
				index = getHash(key, i);  
			}
		}
		
		return found; 
	}
	
	/**
	 * Puts given value with given key into the hash table. 
	 * If already in the hash table, increments count for this value.
	 * @param key - int value 
	 * @param value - object of type V 
	 * @throws IllegalStateException - if hash table if full 
	 */
	public void put(V value, int key)
	{
		// if hash table is at max size 
		if(size() == getMaxSize())
		{
			throw new IllegalStateException("HashTable - put"); 
		}
		
		// create HashObject instance for insertion  
		HashObject<V> object = new HashObject<V>(value, key); 

		int i = 0; 
		// get hash value of key 
		int index = getHash(key, i); 
		boolean found = false;
		
		// search until find value or open slot
		while(!found && hashTable[index] != null)
		{
			// if found
			if(Objects.equals(object, hashTable[index]))
			{
				frequency[index]++; 
				found = true; 
			}
			// otherwise, go to next index 
			else
			{
				i++; 
				index = getHash(key, i);  
			}
		}
		// put value into the hash table 
		if(!found)
		{
			hashTable[index] = object; 
			size++; 
			//frequency[index]++;
		}
		numProbes += i + 1; 
	}
	
	/**
	 * Removes given value with given key from table. 
	 * @param key - int value 
	 * @param value - object of type V
	 * @return - object of type V 
	 * @throws NoSuchElementException - if key/value not found 
	 */
	public V remove(V value, int key)
	{
		V instance = null; 
		
		// create HashObject instance for comparison  
		HashObject<V> object = new HashObject<V>(value, key); 

		int i = 0; 
		int index = getHash(key, i); 
		boolean found = false;
		
		// search until find value or open slot
		while(!found && hashTable[index] != null)
		{
			// if found 
			if(Objects.equals(object, hashTable[index]))
			{
				found = true; 
			}
			// otherwise, go to next position 
			else
			{
				i++; 
				index = getHash(key, i);  
			}
		}	
		// if not found, throw exception 
		if(!found)
		{
			throw new NoSuchElementException("HashTable - remove"); 
		}
		
		// get return value, set slot to null, reset frequency 
		instance = hashTable[index].getValue(); 
		hashTable[index] = null; 
		frequency[index] = 0;
		size--; 
		
		return instance; 
		
	}
		
	/**
	 * Clears contents of the hash table.
	 * Capacity / load factor are not changed. 
	 */
	public void clear()
	{
		// sets all values to null
		for(int i = 0; i < capacity; i++)
		{
			hashTable[i] = null; 
			frequency[i] = 0; 
		}
		setSize(0); 
		setNumProbes(0); 
	}
	
	/**
	 * @return type - OpenAddressType enum 
	 */
	public OpenAddressType getType()
	{
		return type;
	}
	
	/**
	 * @param type - OpenAddressType enum 
	 */
	private void setType(OpenAddressType type)
	{
		this.type = type;
	}
	
	/**
	 * @return capacity - int value 
	 */
	public int getCapacity()
	{
		return capacity;
	}
	
	/**
	 * @param capacity - int value 
	 */
	private void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}
	
	/**
	 * @return loadFactor - float value 
	 */
	public float getLoadFactor()
	{
		return loadFactor;
	}
	
	/**
	 * @param loadFactor - float value 
	 */
	private void setLoadFactor(float loadFactor)
	{
		this.loadFactor = loadFactor;
	}
	
	/**
	 * @return size - int value 
	 */
	public int size()
	{
		return size;
	}
	
	/**
	 * @param size - int value 
	 */
	private void setSize(int size)
	{
		this.size = size;
	}
	
	/**
	 * Whether hash table is empty. 
	 * @return - boolean value 
	 */
	public boolean isEmpty()
	{
		return size() == 0; 
	}
	
	@Override
	public String toString()
	{
		String str = ""; 
		// build string from toString of lists 
		for(int i = 0; i < capacity; i ++)
		{
			// only add to string if has elements 
			if(hashTable[i] != null)
			{
				str += hashTable[i].toString();	 
			}
		}
		
		return str; 
	}

	/**
	 * Return number of probes performed. 
	 * @return numProbes - int value 
	 */
	public int getNumProbes()
	{
		return numProbes;
	}

	/**
	 * Set number of probes. 
	 * @param numProbes - int value 
	 */
	private void setNumProbes(int numProbes)
	{
		this.numProbes = numProbes;
	}

	/**
	 *  Returns max size of hash table. 
	 * @return maxSize - int value 
	 */
	public int getMaxSize()
	{
		return maxSize;
	}

	/**
	 * Sets max size to given value.
	 * @param maxSize - int value 
	 */
	private void setMaxSize(int maxSize)
	{
		this.maxSize = maxSize;
	}

	/**
	 * Returns frequency of a given value/key pair. 
	 * 
	 * @param value - V object
	 * @param key - int value 
	 * @return - int 
	 */
	public int getFrequency(V value, int key)
	{
		// create HashObject instance for comparison 
		HashObject<V> object = new HashObject<V>(value, key); 
		
		int i = 0; 
		int index = getHash(key, i); 
		boolean found = false;
		int freq = -1; 

		// search until find value
		while(!found && hashTable[index] != null)
		{
			// if found 
			if(Objects.equals(object, hashTable[index]))
			{
				found = true; 
				freq = frequency[index]; 
			}
			// otherwise, go to next index 
			else
			{
				i++; 
				index = getHash(key, i);  
			}
		}

		return freq; 
	}
	
	/**
	 * Returns total number of probes for 
	 * values already in table. 
	 * @return - int value 
	 */
	public int getNumDuplicates()
	{
		int total = 0; 
		// add up frequencies of values 
		for(int i = 0; i < capacity; i++)
		{
			total += frequency[i]; 
		}
		
		return total; 
	}
	
	/**
	 * Returns frequency at a given index
	 * @param index - int index in frequency table
	 * @return int - frequency at given index 
	 */
	public int getFrequency(int index)
	{
		return frequency[index]; 
	}
	
	/**
	 * Returns value at a given index 
	 * @param index - int index in hash table 
	 * @return - value at that index 
	 */
	public V getValue(int index)
	{
		V value = null;
		if(hashTable[index] != null)
		{
			value = hashTable[index].getValue(); 
		}
		
		return value; 
	}
}
