import java.util.ArrayList;
public class ImitationGame {
	//public boolean isPrime()
	public static void main(String[] args){
		ArrayList<Long> encrypted = new ArrayList<Long>();
		Cipher test = new Cipher("Sausage");
		Decipher t2 = new Decipher();
		encrypted = test.encrypt();
		System.out.println(encrypted);
		String s = t2.decrypt(encrypted);
		System.out.println(s);
		//System.out.println(t2.modularExponentiation(3, 200, 50));
	}
}
