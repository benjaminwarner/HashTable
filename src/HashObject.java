import java.util.Objects;

public class HashObject<V> {
	
	private V value;
	private int key;

	public HashObject(V value, int key) {
		this.value = value;
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public int getKey() {
		return key;
	}

	public void setValue(V value) {
		this.value = value;
	}

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
		return h.getKey() == key && h.getValue().equals(value);
	}

	public int hashCode() {
		return Objects.hash(key, value);
	}
}
