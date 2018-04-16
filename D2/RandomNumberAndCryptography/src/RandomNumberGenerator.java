public class RandomNumberGenerator {

	int initialNumber;
	
	public RandomNumberGenerator(int seed) {
		initialNumber = seed;
	}
	
	public int generate() {
		++initialNumber;
		initialNumber *= (Math.ceil(Math.log(initialNumber)/Math.log(2))+1);
//		System.out.println(initialNumber);

		String newNum = Integer.toString(initialNumber);
		int newLength = newNum.length();
		int division = Math.max(newLength/4,1);
//		System.out.println(newLength+" "+division);
		StringBuilder random = new StringBuilder();
		for(int i=division*3;i<Math.min(division*4, newLength);++i)	random.append(newNum.charAt(i));
		for(int i=0;i<Math.min(division, newLength);++i)	random.append(newNum.charAt(i));
		for(int i=division*2;i<Math.min(division*3, newLength);++i)	random.append(newNum.charAt(i));
		for(int i=division;i<Math.min(division*2, newLength);++i)	random.append(newNum.charAt(i));
//		System.out.println(random);
		return Integer.parseInt(random.toString())%(newLength*37);
//		return -1;
		
	}

}
