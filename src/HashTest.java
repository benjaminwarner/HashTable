import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashTest {
    
    public static void main(String[] args) throws FileNotFoundException {
		try {
			if (args.length < 4 || args.length > 5)
				throw new Exception("invalid number of arguments");
		} catch (Exception ex) {
			usage();
			System.out.println(ex.getMessage());
			return;
		}

		float loadFactor = Float.parseFloat(args[0]);
		int capacity = Integer.parseInt(args[1]);
		String filePath = args[2];
		int dataType = Integer.parseInt(args[3]);
		boolean debug = (args.length == 5 && Integer.parseInt(args[4]) == 1) ? true : false;

		HashTable<String> linearTable = new HashTable<String>(capacity, loadFactor, OpenAddressType.linear);
		HashTable<String> doubleTable = new HashTable<String>(capacity, loadFactor, OpenAddressType.double_hash);
		HashTable<String> quadraticTable = new HashTable<String>(capacity, loadFactor, OpenAddressType.quadratic);

		int linearProbes = 0;
		int doubleProbes = 0;
		int quadraticProbes = 0;
		int insertCount = 0;

		Scanner sc = new Scanner(new File(filePath));
		while (sc.hasNextLine()) {
			String line = sc.nextLine().trim();
			String[] data = line.split(" ");
			String word = data[0].trim();
			int key = Integer.parseInt(data[1]);
	
			int linearProbesCurrent = linearTable.getNumProbes();
			int doubleProbesCurrent = doubleTable.getNumProbes();
			int quadraticProbesCurrent = quadraticTable.getNumProbes();
		
			linearTable.put(word, key);
			doubleTable.put(word, key);
			quadraticTable.put(word, key);

			linearProbes += linearTable.getNumProbes() - linearProbesCurrent;
			doubleProbes += doubleTable.getNumProbes() - doubleProbesCurrent;
			quadraticProbes += quadraticTable.getNumProbes() - quadraticProbesCurrent;
			insertCount++;
		}
		insertCount -= linearTable.getNumDuplicates();
		
		System.out.println("Using Linear Probing...");
		System.out.println(String.format("Inserted %d values with %d duplicates", insertCount, linearTable.getNumDuplicates()));
		System.out.println(String.format("Average number of probes: %f", (double)linearTable.getNumProbes() / (double)insertCount));
		if (debug)
			System.out.println("\n" + linearTable.toString());

		System.out.println("");
		System.out.println("Using Double Hashing...");
		System.out.println(String.format("Inserted %d values with %d duplicates", insertCount, doubleTable.getNumDuplicates()));
		System.out.println(String.format("Average number of probes: %f", (double)doubleProbes / (double)insertCount));
		if (debug)
			System.out.println("\n" + doubleTable.toString());

		System.out.println("");
		System.out.println("Using Quadratic Probing...");
		System.out.println(String.format("Inserted %d values with %d duplicates", insertCount, quadraticTable.getNumDuplicates()));
		System.out.println(String.format("Average number of probes: %f", (double)quadraticProbes / (double)insertCount));
		if (debug)
			System.out.println("\n" + quadraticTable.toString());
	}

	public static void usage() {
		System.out.println("HashTest [load factor] [hash table capacity] [input file] [input type] [ | debug level]");
	}
}
