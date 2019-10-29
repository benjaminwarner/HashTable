import java.util.Objects;

/**
 * An element of a HashTable. Holds a value along with its key.
 *
 * @author Benjamin Warner
 *
 * @param <V> store data of type V
 */

public class HashObject<V> {
	
	private V value;
	private int key;

	/**
	 * Create a new HashObject.
	 * @param value - object of type V
	 * @param key - the key of the object
	 */
	public HashObject(V value, int key) {
		this.value = value;
		this.key = key;
	}

	/**
	 * Get the value of the object.
	 * @return V
	 */
	public V getValue() {
		return value;
	}

	/**
	 * Get the key of the object.
	 * @return int
	 */
	public int getKey() {
		return key;
	}

	/**
	 * Set the value of the object.
	 * @param value - object of type V
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/**
	 * Set the key of the object.
	 */
	public void setKey(int key) {
		this.key = key;
	}

	public String toString() {
		return String.format("%s %s", value.toString(), key);
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof HashObject))
			return false;
		HashObject h = (HashObject) o;
		return h.getKey() == key && Objects.equals(this.value, h.getValue());
	}

	public int hashCode() {
		return Objects.hash(key, value);
	}
}
