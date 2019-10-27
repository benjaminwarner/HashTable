public class HashTable<V> {

	private OpenAddressType type;
	private HashObject<V>[] hashTable;
	private int[] frequency;
	private int capacity;
	private float loadFactor;
	private int size;
	private int maxSize;

	private final int DEFAULT_CAPACITY = 13;
	private final float DEFAULT_LOAD_FACTOR = .75;
	private final OpenAddressType DEFAULT_TYPE = OpenAddressType.linear;

	public HashTable() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR, DEFAULT_TYPE);
	}

	public HashTable(int capacity) {
		this(capacity, DEFAULT_LOAD_FACTOR, DEFAULT_TYPE);
	}

	public HashTable(int capacity, float loadFactor) {
		this(capacity, loadFactor, DEFAULT_TYPE);
	}

	public HashTable(int capacity, float loadFactor, OpenAddressType type) {
		this.capacity = capacity;
		this.loadFactor = loadFactor;
		this.type = type;
		this.hashTable = new HashObject<V>[];
		this.frequency = new int[];
		this.size = 0;
	}

	public void put(V value, int key) {
	}

	public V remove(V value, int key) {
		return null;
	}

	public boolean contains(V value, int key) {
		return false;
	}

	public void clear() {
	}

	public int getHash(int something, int somethingElse) {
		return 0;
	}

	private int hash1(int h, int k) {
		return 0;
	}

	private int hash2(int h, int k) {
		return 0;
	}

	public OpenAddressType getType() {
		return type;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getLoadFactor() {
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
		return "";
	}
}
