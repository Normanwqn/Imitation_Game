import java.math.BigInteger;
import java.util.ArrayList;

public class Decipher {
	static private long publicKey; //the product of two prime numbers
	static private long e = 0; //a chosen prime number
	static private long phiValue; 
	static private long privateKey;
	
	public void keyGenerator(){
		long pa = findRandomPrime(50,60);
		long pb = findRandomPrime(50,60);
		while (pa == pb){
			pa = findRandomPrime(50,60);
			pb = findRandomPrime(50,60);
		}
		publicKey = pa * pb;
		//System.out.println("Generate Public Key:" + publicKey);
		phiValue = (pa-1)*(pb-1);
		//e = 3;
		e = 3;
		privateKey = (2*phiValue+1) / e;
	}
	public String decrypt(ArrayList<Long> received){
		String result = new String();
		//long temp;
		//System.out.println("Public Key:" + publicKey);
		//System.out.println("Private Key:" + privateKey);
		for (long i: received){
			//System.out.println("i:" + i);
			//double temp = (myPow(i, privateKey)) % publicKey
			//System.out.println("Temp:" + temp);
			//System.out.println(modularExponentiation(1394, 3, publicKey));
			result = result + (char) modularExponentiation(i, privateKey, publicKey);
		}
		return result;
	}
	private long findRandomPrime(long min, long max){
		long trial = min + (long)(Math.random() * (max - min) + 1);
		while (! isPrime(trial)){
			trial = min + (long)(Math.random() * (max - min) + 1);
		}
		//System.out.println("Trial:" + trial);
		return trial;
	}
	public long modularExponentiation (long base, long exponent, Long modN){
		long actualExponent = 1;
		long currentExponent = 1;
		ArrayList<Long> modResults = new ArrayList<Long>();
		ArrayList<Long> exponentCalculated = new ArrayList<Long>();
		long currentBase = base;
		while (actualExponent <= exponent) {
			long temp = (myPow(currentBase,currentExponent));
			exponentCalculated.add(actualExponent);
			if (temp < modN){
				modResults.add(temp);
			} else{
				temp = temp % modN;
				modResults.add(temp);
				currentBase = temp;
				currentExponent = 1;
			}
			currentExponent *= 2;
			actualExponent *= 2;
		}
		BigInteger product = new BigInteger("1");
		int counter = modResults.size() - 1;
		/*System.out.println(exponentCalculated);
		System.out.println(modResults);*/
		while (exponent > 0){
			if ((exponent - exponentCalculated.get(counter)) < 0){
				counter --;
			} else{
				//System.out.println("-" + exponentCalculated.get(counter));
				exponent -= exponentCalculated.get(counter);
				//System.out.println("*" + modResults.get(counter));
				BigInteger t = BigInteger.valueOf(modResults.get(counter).intValue());
				product = product.multiply(t);
				counter --;
			}
			BigInteger t = BigInteger.valueOf(modN.intValue());
			product = product.remainder(t);
			//System.out.println ("Product: " + product);
		}
		return product.longValue();
	}
	private boolean isPrime(long a){
		boolean result = true;
		if (a == 1){
			return false;
		}
		for (long i = 2; i <= a-1; i++){
			if ( a % i == 0){
				return false;
			}
		}
		return result;
	}
	public long getPublicKey(){
		return publicKey;
	}
	public long get_e(){
		return e;
	}
	public static long myPow(long a, long b){
	    long res =1;
	    for (long i = 0; i < b; i++) {
	        res *= a;
	    } 
	    return res;
	}
}
