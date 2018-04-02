import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class QuickSortTests {
	
	public static File inputFile;
	public static File bigInputFile;
	
	public static String inputFileName;
	public static String bigInputFileName;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		inputFileName = "input.xml";
		bigInputFileName = "input.xml";

		inputFile = new File(inputFileName);
		bigInputFile = new File(bigInputFileName);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void FileNotNULL() {
		try {
			assertNotNull("File is Null", inputFile);
			assertNotNull("Big Input File is Null", bigInputFile);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testArrayLoad() {
		XMLReader.loadFile(inputFileName);
		assertNotNull("Array Is NULL",XMLReader.readArray());
		
//		XMLReader.loadFile("");
//		assertNotNull("Array Is NULL",XMLReader.readArray());
	}
	
	@Test
	void testSort() {

		XMLReader.loadFile(inputFileName);
		ThreadQuickSort.a = XMLReader.readArray();
		ThreadQuickSort.sort();

		assertArrayEquals(new int[] {-4,0,3,55}, ThreadQuickSort.a, "Arrays are not Equal" );
	}

}
