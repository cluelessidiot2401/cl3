import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class QuickSortTests {
	
	public static File inputFile;
	public static File bigInputFile;
	
	public static String inputFileName;
	public static String bigInputFileName;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		inputFileName = "input.xml";
		bigInputFileName = "bigInput.xml";

		inputFile = new File(inputFileName);
		bigInputFile = new File(bigInputFileName);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void FileNotNULL() {
		try {
			assertNotNull("File is Null", inputFile);
			assertNotNull("Big Input File is Null", bigInputFile);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testArrayLoad() {
		XMLReader.loadFile(inputFileName);
		assertNotNull("Array Is NULL",XMLReader.readArray());
		
//		XMLReader.loadFile("");
//		assertNotNull("Array Is NULL",XMLReader.readArray());
	}
	
	@Test
	public void testSort() {

		XMLReader.loadFile(inputFileName);
		ThreadQuickSort.a = XMLReader.readArray();
		ThreadQuickSort.sort();
		System.out.println(ThreadQuickSort.staticToString());

//		assertArrayEquals(new int[] {-4,0,3,55}, ThreadQuickSort.a, "Arrays are not Equal" );
	}
	
	@Test
	public void testBigSort() {

		XMLReader.loadFile(bigInputFileName);
		ThreadQuickSort.a = XMLReader.readArray();
		ThreadQuickSort.sort();
		System.out.println(ThreadQuickSort.staticToString());

//		assertArrayEquals(new int[] {-4,0,3,55}, ThreadQuickSort.a, "Arrays are not Equal" );
	}

}
