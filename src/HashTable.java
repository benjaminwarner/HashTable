import java.lang.Math;
import java.util.NoSuchElementException;
import java.util.Objects;

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

	public HashTable() {
		setupFields(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR, DEFAULT_TYPE);
	}

	public HashTable(int capacity) {
		setupFields(capacity, DEFAULT_LOAD_FACTOR, DEFAULT_TYPE);
	}

	public HashTable(int capacity, float loadFactor) {
		setupFields(capacity, loadFactor, DEFAULT_TYPE);
	}

	public HashTable(int capacity, float loadFactor, OpenAddressType type) {
		setupFields(capacity, loadFactor, type);	
	}

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

	public void put(V value, int key) {
		if (this.size + 1 > this.maxSize)
			throw new IllegalStateException();
		HashObject<V> element = new HashObject<V>(value, key);
		for (int i = 0; i < this.capacity; ++i) {
			int index = getHash(key, i);
			++this.numProbes;
			if (this.hashTable[index] != null && this.hashTable[index].equals(element)) {
				this.frequency[index] += 1;
				return;
			} 
			if (this.hashTable[index] == null) {
				this.hashTable[index] = new HashObject<V>(value, key);
				break;
			}
		}
		++this.size;
	}

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

	public boolean contains(V value, int key) {
		return indexOf(value, key) != -1;
	}

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

	public void clear() {
		for (int i = 0; i < this.capacity; ++i) {
			this.hashTable[i] = null;
			this.frequency[i] = 0;
		}
		this.numProbes = 0;
		this.size = 0;
	}

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

	public OpenAddressType getType() {
		return type;
	}

	public int getCapacity() {
		return capacity;
	}

	public float getLoadFactor() {
		return loadFactor;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int getNumProbes() {
		return numProbes;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public int getFrequency(V value, int key) {
		int index = indexOf(value, key);
		if (index != -1)
			return this.frequency[index];
		else
			return -1;
	}

	public int getNumDuplicates() {
		int sum = 0;
		for (int i = 0; i < this.frequency.length; ++i)
			sum += this.frequency[i];
		return sum;
	}

	public String toString() {
		String s = "[";
		for (int i = 0; i < this.capacity - 1; ++i) {
			if (this.hashTable[i] == null)
				s += "N/A, ";
			else
				s += this.hashTable[i].toString() + ", ";
		}
		s += this.hashTable[this.capacity - 1] == null ? "N/A" : this.hashTable[this.capacity - 1].toString();
		s += "]";
		return s;
	}
}
