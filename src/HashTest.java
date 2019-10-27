public class HashTest {
    
    public static void main(String[] args) {
		HashTable<String> table = new HashTable<String>();
		table.put("John", 2);
		table.put("Sara", 13);
		table.put("Mary", 22);
		table.put("Sue", 1);
		table.put("Bob", 2);
		table.put("Joseph", 14);
		System.out.println(table.toString());
    }
}
