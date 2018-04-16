import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

	private static Scanner s = new Scanner(System.in);
	
	public static boolean containsUCL(String password)
	{
		Pattern p =Pattern.compile("[A-Z]");
		Matcher m = p.matcher(password);
		return m.find();
	}
	
	public static boolean containsNumber(String password)
	{
		Pattern p =Pattern.compile("[0-9]");
		Matcher m = p.matcher(password);
		return m.find();
	}
	
	public static boolean containsSpecialCharacters(String password)
	{
		Pattern p =Pattern.compile("[^A-Za-z0-9]");
		Matcher m = p.matcher(password);
		return m.find();
	}
	
	public static void main(String[] args) {
		
		System.out.println("Enter password to be encrypted:");
		
		String password = s.next();
		
		EncryptPassword encryptPassword;
		System.out.println("Number? " + containsNumber(password));
		System.out.println("Special? " + containsSpecialCharacters(password));
		System.out.println("UCL? " + containsUCL(password));
		if(!containsNumber(password) && !containsSpecialCharacters(password)
				&& !containsUCL(password)) {
			System.out.println("Calling SecurePassword Hash");
			
			encryptPassword = new EncryptPassword(new SecurePassword(password));
		}
		else {
			System.out.println("Calling UnsecurePassword Hash");
			
			encryptPassword = new EncryptPassword(new UnsecurePassword(password));
			
		}
		System.out.print("Ecrypted String : ");
		System.out.println(Arrays.toString(encryptPassword.ecryptedString));
		

	}

}
