
public class HashObjectTester
{

	public static void main(String[] args)
	{
		int key = TestCase.K_1; 
		Bag<HashObject> bag  = new Bag<HashObject>(); 
		
		bag.add(new HashObject<String>(TestCase.S_AB, key)); 
		bag.add(new HashObject<Long>(TestCase.L_12, key)); 
		bag.add(new HashObject<Integer>(TestCase.I_12, key));
		bag.add(new HashObject<Double>(TestCase.D_12, key));
		bag.add(new HashObject<Float>(TestCase.F_12, key));
		bag.add(new HashObject<Character>(TestCase.C_A, key)); 
		bag.add(new HashObject<Boolean>(TestCase.B_true, key)); 
		bag.add(new HashObject<Byte>(TestCase.B_12, key));


		for(HashObject obj : bag)
		{
			System.out.println("value: " + obj.getValue() + " code: " + obj.hashCode());
		}
	}

}
