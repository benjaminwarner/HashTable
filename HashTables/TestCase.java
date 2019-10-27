import org.testng.Assert;

/**
 * TestCase class for testing HashTable implementation. 
 * 
 * @author CS 321
 *
 */
public class TestCase
{
		// HashObjects with Character values, used in HashTable tests 
		public static final HashObject<Character> A0 = new HashObject<Character>('A', 0);
		public static final HashObject<Character> A7 = new HashObject<Character>('A', 7);
		public static final HashObject<Character> A14 = new HashObject<Character>('A', 14);
		public static final HashObject<Character> A21 = new HashObject<Character>('A', 21);
		public static final HashObject<Character> A28 = new HashObject<Character>('A', 28);
		public static final HashObject<Character> A35 = new HashObject<Character>('A', 35);
		public static final HashObject<Character> A42 = new HashObject<Character>('A', 42);

		public static final HashObject<Character> B1 = new HashObject<Character>('B', 1);
		public static final HashObject<Character> B8 = new HashObject<Character>('B', 8);
		public static final HashObject<Character> B15 = new HashObject<Character>('B', 15);
		public static final HashObject<Character> B22 = new HashObject<Character>('B', 22);

		public static final HashObject<Character> C2 = new HashObject<Character>('C', 2);
		public static final HashObject<Character> C9 = new HashObject<Character>('C', 9);

		public static final HashObject<Character> D3 = new HashObject<Character>('D', 3);
		public static final HashObject<Character> D10 = new HashObject<Character>('D', 10);
		
		public static final HashObject<Character> E4 = new HashObject<Character>('E', 4);
		public static final HashObject<Character> F5 = new HashObject<Character>('F', 5);
		public static final HashObject<Character> G6 = new HashObject<Character>('G', 6);
		public static final HashObject<Character> H7 = new HashObject<Character>('H', 7);		
		public static final HashObject<Character> I8 = new HashObject<Character>('I', 8);
		
		public static final HashObject<Character> E11 = new HashObject<Character>('E', 11);
		public static final HashObject<Character> F12 = new HashObject<Character>('F', 12);
		public static final HashObject<Character> G13 = new HashObject<Character>('G', 13);
		public static final HashObject<Character> H14 = new HashObject<Character>('H', 14);		
		public static final HashObject<Character> I15 = new HashObject<Character>('I', 15);
										
		public static final HashObject<Character> J9 = new HashObject<Character>('J', 9);
		public static final HashObject<Character> K10 = new HashObject<Character>('K', 10);
		public static final HashObject<Character> L11 = new HashObject<Character>('L', 11);
		public static final HashObject<Character> M12 = new HashObject<Character>('M', 12);		
		public static final HashObject<Character> N13 = new HashObject<Character>('N', 13);
												
		public static final HashObject<Character> J16 = new HashObject<Character>('J', 16);
		public static final HashObject<Character> K17 = new HashObject<Character>('K', 17);
		public static final HashObject<Character> L18 = new HashObject<Character>('L', 18);
		public static final HashObject<Character> M19 = new HashObject<Character>('M', 19);		
		public static final HashObject<Character> N20 = new HashObject<Character>('N', 20);
		
		public static final HashObject<Character> Y0 = new HashObject<Character>('Y', 0);		
		public static final HashObject<Character> Z12 = new HashObject<Character>('Z', 12);

		// Objects for HashObject tests
		public static final String S_AB = "AB"; 
		public static final String S_CD = "CD";
		public static final String S_EF = "EF";
		public static final Long L_12 = (long) 12; 
		public static final Long L_34 = (long) 34;
		public static final Long L_56 = (long) 56;
		public static final Integer I_12 = 12; 
		public static final Integer I_34 = 34;
		public static final Integer I_56 = 56;
		public static final Double D_12 = 1.2; 
		public static final Double D_34 = 3.4;
		public static final Double D_56 = 5.6;
		public static final Float F_12	= (float) 1.2; 
		public static final Float F_34	= (float) 3.4; 
		public static final Float F_56	= (float) 5.6;
		public static final Character C_A = 'A';
		public static final Character C_B = 'B';
		public static final Character C_C = 'C';
		public static final Boolean B_true = true; 
		public static final Boolean B_false = false;
		public static final Byte B_12 = (byte) 12; 
		public static final Byte B_34 = (byte) 34;
		public static final Byte B_56 = (byte) 56;
		
		// Keys for HashObjects tests
		public static final int K_1 = 1; 
		public static final int K_2 = 2;
		public static final int K_3 = 3;
		public static final int K_4 = 4;
		public static final int K_5 = 5;
		public static final int K_6 = 6;
		public static final int K_7 = 7;
		public static final int K_8 = 8;
		
		//******************************* HashTable Tests *****************************// 
												
		/**
		 * Constructs new HashTable with HashObject<Character> objects for testing. 
		 * Uses default values.
		 *
		 * @param hashType - String representing type of hashing.  
		 * @return new HashTable<Character> 
		 */
		public static HashTable<Character> newHashTable(String hashType)
		{
			// HashTable object to run tests on 
			HashTable<Character> newHashTable = null;
			
			if(hashType.equals("linear"))
			{
				newHashTable = new HashTable<Character>(); 
			}
			else if(hashType.equals("double"))
			{
				newHashTable = new HashTable<Character>(13, 0.75f, OpenAddressType.double_hash);
			}
			else
			{
				newHashTable = new HashTable<Character>(13, 0.75f, OpenAddressType.quadratic);
			}
	
			return newHashTable; 
		}
		
		/**
		 * Constructs new HashTable with Character objects for testing. 
		 * Uses default alpha, OpenAddressType
		 * 
		 * @param capacity - size of HashTable 
		 * @return - new HashTable<Character> 
		 */
		public static HashTable<Character> newHashTable(int capacity)
		{
			// HashTable object to run tests on 
			HashTable<Character> newHashTable = new HashTable<Character>(capacity); 
		
			return newHashTable; 
		}
		
		/**
		 * Constructs new HashTable with Character objects for testing. 
		 * Uses default OpenAddressType
		 * 
		 * @param capacity - size of HashTable 
		 * @param alpha - load factor of HashTable  
		 * @return - new HashTable<Character> 
		 */
		public static HashTable<Character> newHashTable(int capacity, float alpha)
		{
			// HashTable object to run tests on 
			HashTable<Character> newHashTable = new HashTable<Character>(capacity, alpha); 
		
			return newHashTable; 
		}
		
		/**
		 * Constructs new HashTable with HashObject<Character> objects for testing.
		 *  
		 * @param capacity - size of HashTable 
		 * @param alpha - load factor of HashTable  
		 * @param type - type of Open Addressing probing of HashTable  
		 * @return - new HashTable<Character> 
		 */
		public static HashTable<Character> newHashTable(int capacity, float alpha, OpenAddressType type)
		{
			// HashTable object to run tests on 
			HashTable<Character> newHashTable = new HashTable<Character>(capacity, alpha, type); 
		
			return newHashTable; 
		}		
		
		/**
		 * Tests put method. 
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param key - key for given Character
		 * @param value - Character value 
		 */
		public static void put(HashTable<Character> hashTable, Character value, int key)
		{
			hashTable.put(value, key); 
		}
		
		/**
		 * Tests remove method. 
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param key - key for given Character
		 * @param expectedValue - Character value expect to remove  
		 */
		public static void remove(HashTable<Character> hashTable, Character expectedValue, int key)
		{
			Character result = hashTable.remove(expectedValue, key);
			Assert.assertEquals(result, expectedValue); 
		}
		
		/**
		 * Test contains method. 
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param key - key for given Character
		 * @param value - Character value 
		 * @param expectedResult - whether expect to find key/value pair
		 */
		public static void contains(HashTable<Character> hashTable, Character value, int key, boolean expectedResult)
		{
			boolean result = hashTable.contains(value, key);
			Assert.assertEquals(result, expectedResult);
		}


		/**
		 * Tests clear method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 */
		public static void clear(HashTable<Character> hashTable)
		{
			hashTable.clear(); 
		}

		/**
		 * Tests getHash method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param key - key for given Character
		 * @param i - int value, attempts to put an object 
		 * @param expectedHash - what expect hash to be for given key, number of attempts 
		 */
		public static void getHash(HashTable<Character> hashTable, int key, int i, int expectedHash)
		{
			int hash = hashTable.getHash(key, i);
			Assert.assertEquals(hash, expectedHash);
		}

		/**
		 * Tests getType method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param expectedType - expected OpenAddressType 
		 */
		public static void getType(HashTable<Character> hashTable, OpenAddressType expectedType)
		{
			OpenAddressType type = hashTable.getType();
			Assert.assertEquals(type, expectedType);
		}

		/**
		 * Tests getCapacity method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param expectedCapacity - expected capacity 
		 */
		public static void getCapacity(HashTable<Character> hashTable, int expectedCapacity)
		{
			int capacity = hashTable.getCapacity();
			Assert.assertEquals(capacity, expectedCapacity);
		}
		
		/**
		 * Tests getLoadFactor method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param expectedLoadFactor - expected load factor 
		 */
		public static void getLoadFactor(HashTable<Character> hashTable, float expectedLoadFactor)
		{
			float loadFactor = hashTable.getLoadFactor();
			Assert.assertEquals(loadFactor, expectedLoadFactor);
		}	
		
		/**
		 * Tests size method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param expectedSize - expected size (number of objects in table)  
		 */
		public static void size(HashTable<Character> hashTable, int expectedSize)
		{
			int size = hashTable.size();
			Assert.assertEquals(size, expectedSize);
		}		
		
		/**
		 * Tests isEmpty method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param exceptedResult - whether is empty or not 
		 */
		public static void isEmpty(HashTable<Character> hashTable, boolean expectedResult)
		{
			boolean result = hashTable.isEmpty(); 
			Assert.assertEquals(result, expectedResult);
		}	

		/**
		 * Tests getNumProbes method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param expectedNumProbes - expected number of probes 
		 */
		public static void getNumProbes(HashTable<Character> hashTable, int expectedNumProbes)
		{
			int numProbes = hashTable.getNumProbes();
			Assert.assertEquals(numProbes, expectedNumProbes);
		}		
		
		/**
		 * Tests getMaxSize method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param expectedMaxSize - expected maximum size  
		 */
		public static void getMaxSize(HashTable<Character> hashTable, int expectedMaxSize)
		{
			int maxSize = hashTable.getMaxSize();
			Assert.assertEquals(maxSize, expectedMaxSize);
		}		
		
		/**
		 * Tests getFrequency method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param value - Character value 
		 * @param key - key for given Character
		 * @param expectedFrequency - expected frequency for given key/value pair 
		 */
		public static void getFrequency(HashTable<Character> hashTable, Character value, int key, int expectedFrequency)
		{
			int frequency = hashTable.getFrequency(value, key);
			Assert.assertEquals(frequency, expectedFrequency);
		}		
		
		/**
		 * Tests getNumDuplicates method.
		 * 
		 * @param hashTable - HashTable<Character> object 
		 * @param expectedNumDuplicates - expected number of duplicates 
		 */
		public static void getNumDuplicates(HashTable<Character> hashTable, int expectedNumDuplicates)
		{
			int maxSize = hashTable.getNumDuplicates();
			Assert.assertEquals(maxSize, expectedNumDuplicates);
		}		
		
		
		//******************************* HashObject Tests *****************************// 
		
		/**
		 * Tests getValue method for String objects 
		 * @param hashObject - HashObject<String> object
		 * @param expectedValue - expected String value 
		 */
		public static void getValue(HashObject<String> hashObject, String expectedValue)
		{
			String value = hashObject.getValue(); 
			Assert.assertEquals(value, expectedValue);
		}
		
		/**
		 * Tests getValue method for Long objects
		 * @param hashObject - HashObject<Long> object
		 * @param expectedValue - expected Long value 
		 */
		public static void getValue(HashObject<Long> hashObject, Long expectedValue)
		{
			Long value = hashObject.getValue(); 
			Assert.assertEquals(value, expectedValue);
		}
		
		/**
		 * Tests getValue method for Integer objects
		 * @param hashObject - HashObject<Integer> object
		 * @param expectedValue - expected Integer value 
		 */
		public static void getValue(HashObject<Integer> hashObject, Integer expectedValue)
		{
			Integer value = hashObject.getValue(); 
			Assert.assertEquals(value, expectedValue);
		}
		
		/**
		 * Tests getValue method for Double objects 
		 * @param hashObject - HashObject<Double> object
		 * @param expectedValue - expected Double value 
		 */
		public static void getValue(HashObject<Double> hashObject, Double expectedValue)
		{
			Double value = hashObject.getValue(); 
			Assert.assertEquals(value, expectedValue);
		}
		
		/**
		 * Tests getValue method for Float objects 
		 * @param hashObject - HashObject<Float> object
		 * @param expectedValue - expected Float value 
		 */
		public static void getValue(HashObject<Float> hashObject, Float expectedValue)
		{
			Float value = hashObject.getValue(); 
			Assert.assertEquals(value, expectedValue);
		}
		
		/**
		 * Tests getValue method for Character objects
		 * @param hashObject - HashObject<Character> object
		 * @param expectedValue - expected Character value 
		 */
		public static void getValue(HashObject<Character> hashObject, Character expectedValue)
		{
			Character value = hashObject.getValue(); 
			Assert.assertEquals(value, expectedValue);
		}
		
		/**
		 * Tests getValue method for Boolean objects
		 * @param hashObject - HashObject<Boolean> object
		 * @param expectedValue - expected Boolean value 
		 */
		public static void getValue(HashObject<Boolean> hashObject, Boolean expectedValue)
		{
			Boolean value = hashObject.getValue(); 
			Assert.assertEquals(value, expectedValue);
		}
		
		/**
		 * Tests getValue method for Byte objects
		 * @param hashObject - HashObject<Byte> object
		 * @param expectedValue - expected Byte value 
		 */
		public static void getValue(HashObject<Byte> hashObject, Byte expectedValue)
		{
			Byte value = hashObject.getValue(); 
			Assert.assertEquals(value, expectedValue);
		}
				
		/**
		 * Tests getKey method for String objects 
		 * @param hashObject - HashObject<String> object 
		 * @param value - String object
		 * @param expectedKey - int value 
		 */
		public static void getKey(HashObject<String> hashObject, String value, int expectedKey)
		{
			int key = hashObject.getKey(); 
			Assert.assertEquals(key, expectedKey);
		}
		
		/**
		 * Tests getKey method for Long objects 
		 * @param hashObject - HashObject<Long> object 
		 * @param value - Long object
		 * @param expectedKey - int value 
		 */
		public static void getKey(HashObject<Long> hashObject, Long value, int expectedKey)
		{
			int key = hashObject.getKey(); 
			Assert.assertEquals(key, expectedKey);
		}
		
		/**
		 * Tests getKey method for Integer objects 
		 * @param hashObject - HashObject<Integer> object 
		 * @param value - Integer object
		 * @param expectedKey - int value 
		 */
		public static void getKey(HashObject<Integer> hashObject, Integer value, int expectedKey)
		{
			int key = hashObject.getKey(); 
			Assert.assertEquals(key, expectedKey);
		}
		
		/**
		 * Tests getKey method for Double objects 
		 * @param hashObject - HashObject<Double> object 
		 * @param value - Double object
		 * @param expectedKey - int value 
		 */
		public static void getKey(HashObject<Double> hashObject, Double value, int expectedKey)
		{
			int key = hashObject.getKey(); 
			Assert.assertEquals(key, expectedKey);
		}
		
		/**
		 * Tests getKey method for Float objects 
		 * @param hashObject - HashObject<Float> object 
		 * @param value - Float object
		 * @param expectedKey - int value 
		 */
		public static void getKey(HashObject<Float> hashObject, Float value, int expectedKey)
		{
			int key = hashObject.getKey(); 
			Assert.assertEquals(key, expectedKey);
		}
		
		/**
		 * Tests getKey method for Character objects 
		 * @param hashObject - HashObject<Character> object 
		 * @param value - Character object
		 * @param expectedKey - int value 
		 */
		public static void getKey(HashObject<Character> hashObject, Character value, int expectedKey)
		{
			int key = hashObject.getKey(); 
			Assert.assertEquals(key, expectedKey);
		}
		
		/**
		 * Tests getKey method for Boolean objects 
		 * @param hashObject - HashObject<Boolean> object
		 * @param value - Boolean object
		 * @param expectedKey - int value 
		 */
		public static void getKey(HashObject<Boolean> hashObject, Boolean value, int expectedKey)
		{
			int key = hashObject.getKey(); 
			Assert.assertEquals(key, expectedKey);
		}
		
		/**
		 * Tests getKey method for Byte objects 
		 * @param hashObject - HashObject<Byte> object 
		 * @param value - Byte object
		 * @param expectedKey - int value 
		 */
		public static void getKey(HashObject<Byte> hashObject, Byte value, int expectedKey)
		{
			int key = hashObject.getKey(); 
			Assert.assertEquals(key, expectedKey);
		}
		
		/**
		 * Tests setValue method for String objects
		 * @param hashObject - HashObject<String> object 
		 * @param value - String object
		 */
		public static void setValue(HashObject<String> hashObject, String value)
		{
			hashObject.setValue(value);
			String expectedValue = hashObject.getValue(); 
			Assert.assertEquals(value, expectedValue); 
		}
		
		/**
		 * Tests setValue method for Long objects
		 * @param hashObject - HashObject<Long> object 
		 * @param value - Long object
		 */
		public static void setValue(HashObject<Long> hashObject, Long value)
		{
			hashObject.setValue(value);
			Long expectedValue = hashObject.getValue(); 
			Assert.assertEquals(value, expectedValue); 
		}
		
		/**
		 * Tests setValue method for Integer objects
		 * @param hashObject - HashObject<Integer> object 
		 * @param value - Integer object
		 */
		public static void setValue(HashObject<Integer> hashObject, Integer value)
		{
			hashObject.setValue(value);
			Integer expectedValue = hashObject.getValue(); 
			Assert.assertEquals(value, expectedValue); 
		}
		
		/**
		 * Tests setValue method for Double objects
		 * @param hashObject - HashObject<Double> object 
		 * @param value - Double object
		 */
		public static void setValue(HashObject<Double> hashObject, Double value)
		{
			hashObject.setValue(value);
			Double expectedValue = hashObject.getValue(); 
			Assert.assertEquals(value, expectedValue); 
		}
		
		/**
		 * Tests setValue method for Float objects
		 * @param hashObject - HashObject<Float> object 
		 * @param value - Float object
		 */
		public static void setValue(HashObject<Float> hashObject, Float value)
		{
			hashObject.setValue(value);
			Float expectedValue = hashObject.getValue(); 
			Assert.assertEquals(value, expectedValue); 
		}
		
		/**
		 * Tests setValue method for Character objects
		 * @param hashObject - HashObject<Character> object 
		 * @param value - Character object
		 */
		public static void setValue(HashObject<Character> hashObject, Character value)
		{
			hashObject.setValue(value);
			Character expectedValue = hashObject.getValue(); 
			Assert.assertEquals(value, expectedValue); 
		}
		
		/**
		 * Tests setValue method for Boolean objects
		 * @param hashObject - HashObject<Boolean> object 
		 * @param value - Boolean object
		 */
		public static void setValue(HashObject<Boolean> hashObject, Boolean value)
		{
			hashObject.setValue(value);
			Boolean expectedValue = hashObject.getValue(); 
			Assert.assertEquals(value, expectedValue); 
		}
		
		/**
		 * Tests setValue method for Byte objects
		 * @param hashObject - HashObject<Byte> object 
		 * @param value - Byte object
		 */
		public static void setValue(HashObject<Byte> hashObject, Byte value)
		{
			hashObject.setValue(value);
			Byte expectedValue = hashObject.getValue(); 
			Assert.assertEquals(value, expectedValue); 
		}
		
		/**
		 * Tests setKey method for String objects 
		 * @param hashObject - HashObject<String> object 
		 * @param value - String object
		 * @param key - int value 
		 */
		public static void setKey(HashObject<String> hashObject, String value, int key)
		{
			hashObject.setKey(key);
			int expectedKey = hashObject.getKey(); 
			Assert.assertEquals(key, expectedKey); 
		}
		
		/**
		 * Tests setKey method for Long objects 
		 * @param hashObject - HashObject<Long> object 
		 * @param value - Long object
		 * @param key - int value 
		 */
		public static void setKey(HashObject<Long> hashObject, Long value, int key)
		{
			hashObject.setKey(key);
			int expectedKey = hashObject.getKey(); 
			Assert.assertEquals(key, expectedKey); 
		}
		
		/**
		 * Tests setKey method for Integer objects 
		 * @param hashObject - HashObject<Integer> object 
		 * @param value - Integer object
		 * @param key - int value 
		 */
		public static void setKey(HashObject<Integer> hashObject, Integer value, int key)
		{
			hashObject.setKey(key);
			int expectedKey = hashObject.getKey(); 
			Assert.assertEquals(key, expectedKey); 
		}
		
		/**
		 * Tests setKey method for Double objects 
		 * @param hashObject - HashObject<Double> object 
		 * @param value - Double object
		 * @param key - int value 
		 */
		public static void setKey(HashObject<Double> hashObject, Double value, int key)
		{
			hashObject.setKey(key);
			int expectedKey = hashObject.getKey(); 
			Assert.assertEquals(key, expectedKey); 
		}
		
		/**
		 * Tests setKey method for Float objects 
		 * @param hashObject - HashObject<Float> object 
		 * @param value - Float object
		 * @param key - int value 
		 */
		public static void setKey(HashObject<Float> hashObject, Float value, int key)
		{
			hashObject.setKey(key);
			int expectedKey = hashObject.getKey(); 
			Assert.assertEquals(key, expectedKey); 
		}
		
		/**
		 * Tests setKey method for Character objects 
		 * @param hashObject - HashObject<Character> object 
		 * @param value - Character object
		 * @param key - int value 
		 */
		public static void setKey(HashObject<Character> hashObject, Character value, int key)
		{
			hashObject.setKey(key);
			int expectedKey = hashObject.getKey(); 
			Assert.assertEquals(key, expectedKey); 
		}
		
		/**
		 * Tests setKey method for Boolean objects 
		 * @param hashObject - HashObject<Boolean> object 
		 * @param value - Boolean object
		 * @param key - int value 
		 */
		public static void setKey(HashObject<Boolean> hashObject, Boolean value, int key)
		{
			hashObject.setKey(key);
			int expectedKey = hashObject.getKey(); 
			Assert.assertEquals(key, expectedKey); 
		}
		
		/**
		 * Tests setKey method for Byte objects 
		 * @param hashObject - HashObject<Byte> object 
		 * @param value - Byte object
		 * @param key - int value 
		 */
		public static void setKey(HashObject<Byte> hashObject, Byte value, int key)
		{
			hashObject.setKey(key);
			int expectedKey = hashObject.getKey(); 
			Assert.assertEquals(key, expectedKey); 
		}
		
		/**
		 * Tests equals method for String objects
		 * @param hashObject1 - HashObject<String> object 
		 * @param hashObject2 - HashObject<String> object 
		 * @param value - String object 
		 * @param expectedResult - boolean value 
		 */
		public static void equals(HashObject<String> hashObject1, HashObject<String> hashObject2, String value, boolean expectedResult)
		{
			boolean result = hashObject1.equals(hashObject2); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests equals method for Long objects
		 * @param hashObject1 - HashObject<Long> object 
		 * @param hashObject2 - HashObject<Long> object 
		 * @param value - Long object 
		 * @param expectedResult - boolean value 
		 */
		public static void equals(HashObject<Long> hashObject1, HashObject<Long> hashObject2, Long value, boolean expectedResult)
		{
			boolean result = hashObject1.equals(hashObject2); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests equals method for Integer objects
		 * @param hashObject1 - HashObject<Integer> object 
		 * @param hashObject2 - HashObject<Integer> object
		 * @param value - Integer object
		 * @param expectedResult - boolean value 
		 */
		public static void equals(HashObject<Integer> hashObject1, HashObject<Integer> hashObject2, Integer value, boolean expectedResult)
		{
			boolean result = hashObject1.equals(hashObject2); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests equals method for Double objects
		 * @param hashObject1 - HashObject<Double> object 
		 * @param hashObject2 - HashObject<Double> object 
		 * @param value - Long object
		 * @param expectedResult - boolean value 
		 */
		public static void equals(HashObject<Double> hashObject1, HashObject<Double> hashObject2, Double value, boolean expectedResult)
		{
			boolean result = hashObject1.equals(hashObject2); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests equals method for Float objects
		 * @param hashObject1 - HashObject<Float> object 
		 * @param hashObject2 - HashObject<Float> object 
		 * @param value - Float object
		 * @param expectedResult - boolean value 
		 */
		public static void equals(HashObject<Float> hashObject1, HashObject<Float> hashObject2, Float value, boolean expectedResult)
		{
			boolean result = hashObject1.equals(hashObject2); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests equals method for Character objects
		 * @param hashObject1 - HashObject<Character> object 
		 * @param hashObject2 - HashObject<Character> object 
		 * @param value - Character object
		 * @param expectedResult - boolean value 
		 */
		public static void equals(HashObject<Character> hashObject1, HashObject<Character> hashObject2, Character value, boolean expectedResult)
		{
			boolean result = hashObject1.equals(hashObject2); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests equals method for Boolean objects
		 * @param hashObject1 - HashObject<Boolean> object 
		 * @param hashObject2 - HashObject<Boolean> object 
		 * @param value - Boolean object
		 * @param expectedResult - boolean value 
		 */
		public static void equals(HashObject<Boolean> hashObject1, HashObject<Boolean> hashObject2, Boolean value, boolean expectedResult)
		{
			boolean result = hashObject1.equals(hashObject2); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests equals method for Byte objects
		 * @param hashObject1 - HashObject<Byte> object 
		 * @param hashObject2 - HashObject<Byte> object 
		 * @param value - Byte object
		 * @param expectedResult - boolean value 
		 */
		public static void equals(HashObject<Byte> hashObject1, HashObject<Byte> hashObject2, Byte value, boolean expectedResult)
		{
			boolean result = hashObject1.equals(hashObject2); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests hashCode method for String objects 
		 * @param hashObject - HashObject<String> object
		 * @param value - String object 
		 * @param expectedResult - long value 
		 */
		public static void hashCode(HashObject<String> hashObject, String value, long expectedResult)
		{
			int result = hashObject.hashCode(); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests hashCode method for Long objects 
		 * @param hashObject - HashObject<Long> object
		 * @param value - Long object 
		 * @param expectedResult - long value 
		 */
		public static void hashCode(HashObject<Long> hashObject, Long value, long expectedResult)
		{
			int result = hashObject.hashCode(); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests hashCode method for Integer objects 
		 * @param hashObject - HashObject<Integer> object
		 * @param value - Integer object 
		 * @param expectedResult - long value 
		 */
		public static void hashCode(HashObject<Integer> hashObject, Integer value, long expectedResult)
		{
			int result = hashObject.hashCode(); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests hashCode method for Double objects 
		 * @param hashObject - HashObject<Double> object
		 * @param value - Double object 
		 * @param expectedResult - long value 
		 */
		public static void hashCode(HashObject<Double> hashObject, Double value, long expectedResult)
		{
			int result = hashObject.hashCode(); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests hashCode method for Float objects 
		 * @param hashObject - HashObject<Float> object
		 * @param value - Float object 
		 * @param expectedResult - long value 
		 */
		public static void hashCode(HashObject<Float> hashObject, Float value, long expectedResult)
		{
			int result = hashObject.hashCode(); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests hashCode method for Character objects 
		 * @param hashObject - HashObject<Character> object
		 * @param value - Character object 
		 * @param expectedResult - long value 
		 */
		public static void hashCode(HashObject<Character> hashObject, Character value, long expectedResult)
		{
			int result = hashObject.hashCode(); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests hashCode method for Boolean objects 
		 * @param hashObject - HashObject<Boolean> object
		 * @param value - Boolean object 
		 * @param expectedResult - long value 
		 */
		public static void hashCode(HashObject<Boolean> hashObject, Boolean value, long expectedResult)
		{
			int result = hashObject.hashCode(); 
			Assert.assertEquals(result, expectedResult);
		}
		
		/**
		 * Tests hashCode method for Byte objects 
		 * @param hashObject - HashObject<Byte> object
		 * @param value - Byte object 
		 * @param expectedResult - long value 
		 */
		public static void hashCode(HashObject<Byte> hashObject, Byte value, long expectedResult)
		{
			int result = hashObject.hashCode(); 
			Assert.assertEquals(result, expectedResult);
		}
		
}
