import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {
	
	public static File file ;
	
	public static void loadFile(String name) {
		file = new File(name);
	}
	
	public static int[] readArray() {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBulder;
		try {
			documentBulder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBulder.parse(file);
			
			document.getDocumentElement().normalize();
			
			System.out.println("root element = "+document.getDocumentElement().getNodeName());
			
			System.out.println("-----------------------------");
			
			
			NodeList nodeList = document.getElementsByTagName("elem");
			System.out.println("Array is :");

			int []array = new int[nodeList.getLength()];
			for(int i=0;i<nodeList.getLength();++i) {
				
				Node node = nodeList.item(i);
				
				System.out.println("current element: "+node.getNodeName());
				array[i] = Integer.parseInt(node.getAttributes().getNamedItem("value").getNodeValue());
				System.out.println("current node value: "+array[i]);
			}

			System.out.println("-----------------------------");
			
			return array;
			

		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
