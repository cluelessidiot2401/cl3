import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassword {
	
	public byte[] ecryptedString;

	public byte[] getEcryptedString() {
		return ecryptedString;
	}

	public void setEcryptedString(byte[] ecryptedString) {
		this.ecryptedString = ecryptedString;
	}
	
	public EncryptPassword(SecurePassword securePassword) {
		try {
		
			MessageDigest MD5 = MessageDigest.getInstance("MD5");
			ecryptedString = MD5.digest(securePassword.password.getBytes("UTF-8"));
		
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public EncryptPassword(UnsecurePassword unsecurePassword) {
		try {
			
			MessageDigest SHA = MessageDigest.getInstance("SHA-1");
			ecryptedString = SHA.digest(unsecurePassword.password.getBytes("UTF-8"));
		
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
