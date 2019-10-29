import java.lang.Math;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * An implementation of the HashTable abstract
 * data type. Allows use of linear or quadratic probing
 * as well as double hashing for open addressing.
 *
 * @author Benjamin Warner
 *
 * @param <V> store data of type V
 */

public class HashTable<V> {

	private OpenAddressType type;
	private HashObject<V>[] hashTable;
	private int[] frequency;
	private int capacity;
	private float loadFactor;
	private int size;
	private int numProbes;
	private int maxSize;

	private final int DEFAULT_CAPACITY = 13;
	private final float DEFAULT_LOAD_FACTOR = (float).75;
	private final OpenAddressType DEFAULT_TYPE = OpenAddressType.linear;

	/**
	 * Create an empty table with default values.
	 */
	public HashTable() {
		setupFields(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR, DEFAULT_TYPE);
	}

	/**
	 * Create an empty table with user defined capacity.
	 */
	public HashTable(int capacity) {
		setupFields(capacity, DEFAULT_LOAD_FACTOR, DEFAULT_TYPE);
	}

	/**
	 * Create an empty table with user defined capacity and load factor (i.e alpha).
	 */
	public HashTable(int capacity, float loadFactor) {
		setupFields(capacity, loadFactor, DEFAULT_TYPE);
	}

	/**
	 * Create an empty table with user defined capacity, load factor, and addressing type.
	 */
	public HashTable(int capacity, float loadFactor, OpenAddressType type) {
		setupFields(capacity, loadFactor, type);	
	}

	/**
	 * Initializes all the fields of the class.
	 */
	@SuppressWarnings("unchecked")
	private void setupFields(int capacity, float loadFactor, OpenAddressType type) {
		this.capacity = capacity;
		this.loadFactor = loadFactor;
		this.type = type;
		this.hashTable = new HashObject[this.capacity];
		this.frequency = new int[this.capacity];
		for (int i = 0; i < this.frequency.length; ++i)
			this.frequency[i] = 0;
		this.size = 0;
		this.numProbes = 0;
		this.maxSize = (int)(this.loadFactor * this.capacity);
	}

	/**
	 * Puts the value with key into the table.
	 * If a duplicate value, with the same key already exists, increments the frequency
	 * and doesn't insert it.
	 * @param value - object of type V
	 * @param key - key of the value
	 */
	public void put(V value, int key) {
		if (this.size + 1 > this.maxSize)
			throw new IllegalStateException();
		HashObject<V> element = new HashObject<V>(value, key);
		for (int i = 0; i < this.capacity; ++i) {
			int index = getHash(key, i);
			if (this.hashTable[index] != null && this.hashTable[index].equals(element)) {
				this.frequency[index] += 1;
				return;
			} 
			if (this.hashTable[index] == null) {
				this.hashTable[index] = new HashObject<V>(value, key);
				break;
			}
			++this.numProbes;
		}
		++this.size;
	}

	/**
	 * Removes the value with key from the table.
	 * @throws NoSuchElementException if such a value doesn't exist.
	 * @param value - object of type V
	 * @param key - key of the value
	 * @return V value
	 */
	public V remove(V value, int key) {
		HashObject<V> element = new HashObject<V>(value, key);
		int index = indexOf(value, key);
		if (index != -1) {
			this.hashTable[index] = null;
			--this.size;
			return value;
		}
		throw new NoSuchElementException();
	}

	/**
	 * Checks to see if the value with key exists in the
	 * table.
	 * @param value - object of type V
	 * @param key - key of the value
	 * @return boolean
	 */
	public boolean contains(V value, int key) {
		return indexOf(value, key) != -1;
	}

	/**
	 * Returns the index of the value with key if it exists in the table.
	 * If the value with key doesn't exist, returns -1 instead.
	 * @param value - object of type V
	 * @param key - key of the value
	 * @return int
	 */
	private int indexOf(V value, int key) {
		HashObject<V> element = new HashObject<V>(value, key);
		for (int i = 0; i < this.capacity; ++i) {
			int index = getHash(key, i);
			if (this.hashTable[index] != null && this.hashTable[index].equals(element)) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * Removes all elements from the table and resets the frequency,
	 * number of probes, and the size.
	 */
	public void clear() {
		for (int i = 0; i < this.capacity; ++i) {
			this.hashTable[i] = null;
			this.frequency[i] = 0;
		}
		this.numProbes = 0;
		this.size = 0;
	}

	/**
	 * Calculates the hash value for the key and number of current probes i.
	 * @param key - the key of the object
	 * @param i - current number of probes (not total)
	 * @return int
	 */
	public int getHash(int key, int i) {
		float c1 = (float)0.5;
		float c2 = (float)0.5;
		switch (this.type) {
			case linear:
				return (hash1(key) + i) % this.capacity;
			case quadratic:
				return (hash1(key) + (int)(c1 * i) + (int)(c2 * Math.pow(i, 2))) % this.capacity;
			case double_hash:
			case doubleHashing:
				return (hash1(key) + i * hash2(key)) % this.capacity;
			default:
				break;
		}
		return 0;
	}

	private int hash1(int key) {
		return key % this.capacity;
	}

	private int hash2(int key) {
		return 1 + (key % (this.capacity - 2));
	}

	/**
	 * Get the open addressing type for this table.
	 * @return OpenAddressType
	 */
	public OpenAddressType getType() {
		return type;
	}

	/**
	 * Get the capacity of the table (i.e number of open slots).
	 * @return int
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Get the load factor.
	 * @return float
	 */
	public float getLoadFactor() {
		return loadFactor;
	}

	/**
	 * Get the number of elements in the table.
	 * @return int
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Check to see if the table is empty.
	 * @return boolean
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Get the total number of probes.
	 * @return int
	 */
	public int getNumProbes() {
		return numProbes;
	}

	/**
	 * Get the max size of the table.
	 * @return int
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * Get the frequency of the given value with key.
	 * Returns -1 if no such pair exists.
	 * @param value - object of type V
	 * @param key - key of the object
	 * @return int
	 */
	public int getFrequency(V value, int key) {
		int index = indexOf(value, key);
		if (index != -1)
			return this.frequency[index];
		else
			return -1;
	}

	/**
	 * Get the total number of duplicates.
	 * @return int
	 */
	public int getNumDuplicates() {
		int sum = 0;
		for (int i = 0; i < this.frequency.length; ++i)
			sum += this.frequency[i];
		return sum;
	}

	/**
	 * Get the string representation of the table.
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < this.capacity; ++i) {
			if (this.hashTable[i] == null)
				continue;
			s += String.format("table[%d]: %s %d\n", i, this.hashTable[i].getValue().toString(), this.frequency[i]);
		}
		return s;
	}
}
