
public class Main {

	public static void main(String[] args) {
		
		XMLReader.loadFile("input.xml");
		ThreadQuickSort.a = XMLReader.readArray();
		
		System.out.println("\nBefore Sorting:");
		System.out.println(ThreadQuickSort.staticToString());
		
		ThreadQuickSort.sort();

		System.out.println("\nAfter Sorting:");
		System.out.println(ThreadQuickSort.staticToString());

	}

}
