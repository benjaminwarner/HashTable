import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Test {
	
	public static void main(String[] args) throws IOException {
		HashTable<String> table = new HashTable<String>(584, 0.25f, OpenAddressType.linear);
		List<String> lines = Files.readAllLines(Paths.get("C:\\Projects\\school\\cs321\\HashTable\\HashTest\\sample.txt"));
		for (String line : lines) {
			String[] data = line.trim().split(" ");
			String value = data[0];
			int key = Integer.parseInt(data[1]);
			System.out.println(table.contains(value, key));
			table.put(value, key);
		}

		System.out.println(table.toString());
		System.out.println(table.getNumDuplicates());
	}
}
