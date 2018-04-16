import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class App {

	private static Scanner s = new Scanner(System.in);
	public static void main(String[] args) {	
		
		System.out.println("Enter message to be sent");
		String data = s.next();
		
		System.out.println("Enter the key");
		int seed = s.nextInt();
		
		try {
			
			RandomNumberGenerator randomNumberGenerator1 = new RandomNumberGenerator(seed);
			RandomNumberGenerator randomNumberGenerator2 = new RandomNumberGenerator(seed);
			
			byte[] key = Integer.toString(randomNumberGenerator1.generate()).getBytes();
			MessageDigest SHA = MessageDigest.getInstance("SHA-1");
			key = SHA.digest(key);
			key = Arrays.copyOf(key, 16);
			SecretKeySpec SK = new SecretKeySpec(key, "AES");
			byte[] iv = new byte[16];
			for(int i=0;i<16;++i)	iv[i]=0;
			IvParameterSpec IVSpec = new IvParameterSpec(iv);
			
			Cipher encrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
			encrypt.init(Cipher.ENCRYPT_MODE, SK,IVSpec);
			byte [] encryptedString = encrypt.doFinal(data.getBytes());
			System.out.println("Encrypted String = "+Arrays.toString(encryptedString));
			
			byte[] keyDec = Integer.toString(randomNumberGenerator2.generate()).getBytes();
			MessageDigest SHA1 = MessageDigest.getInstance("SHA-1");
			keyDec = SHA1.digest(keyDec);
			keyDec = Arrays.copyOf(keyDec, 16);
			SecretKeySpec SKDec = new SecretKeySpec(keyDec, "AES");
			byte[] ivDec = new byte[16];
			for(int i=0;i<16;++i)	ivDec[i]=0;
			IvParameterSpec IVSpecDec = new IvParameterSpec(ivDec);
			
			Cipher decrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
			decrypt.init(Cipher.DECRYPT_MODE, SKDec,IVSpecDec);
			byte [] decryptedString = decrypt.doFinal(encryptedString);
			System.out.println("Decrypted String = "+ new String(decryptedString,"UTF-8"));
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
