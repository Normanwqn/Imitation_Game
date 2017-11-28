import java.util.ArrayList;
public class Cipher {
	String received = new String();
	public Cipher(String s){
		received = s;
	}
	private ArrayList<Long> ConvertToASCII(String s){
		ArrayList<Long> holder = new ArrayList<Long>();
		for (int i = 0; i <= s.length()-1; i++){
			holder.add((long)s.charAt(i));
		}
		return holder;
	}
	public ArrayList<Long> encrypt(){
		ArrayList<Long> spilt = ConvertToASCII(received);
		ArrayList<Long> result = new ArrayList<Long>();
		Decipher t = new Decipher();
		t.keyGenerator();
		for (long i: spilt){
			result.add(((long)Math.pow(i,t.get_e()) ) % t.getPublicKey());
		}
		return result;
	}
}
