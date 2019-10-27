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
		this.size = 0;
		this.numProbes = 0;
		this.maxSize = (int)(this.loadFactor * this.capacity);
	}

	public void put(V value, int key) {
		if (this.size + 1 > this.maxSize)
			throw new IllegalStateException();
		HashObject<V> element = new HashObject<V>(value, key);
		int index = this.probe(key);
		this.hashTable[index] = element;
		++this.size;
	}

	public V remove(V value, int key) {
		return null;
	}

	public boolean contains(V value, int key) {
		return false;
	}

	private int probe(int key) {
		int index = 0;
		HashObject<V> element = null;
		for (this.numProbes = 0; this.numProbes < this.capacity; ++numProbes) {
			index = getHash(key, this.numProbes);
			element = this.hashTable[index];
			if (element == null)
				break;
		}
		if (element != null)
			return -1;
		return index;
	}

	public void clear() {
	}

	public int getHash(int key, int numProbes) {
		switch (this.type) {
			case linear:
				return (hash1(key) + numProbes) % this.capacity;
			case quadratic:
				return -1;
			case doubleHashing:
				return (hash1(key) + numProbes * hash2(key)) % this.capacity;
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
		return 0;
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
		return 0;
	}

	public int getNumDuplicates() {
		return 0;
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
