/**
 * 
 */
package test1;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;

/**
 * @author brucegoldfeder
 *
 */
public class MakeKMLTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	  *@param precision number of decimal digits
	  */
	public  boolean areEqualDouble(double a, double b, int precision) {
	   return Math.abs(a - b) <= Math.pow(10, -precision);
	}
	
	/**
	 * Test method for {@link test1.MakeKML#MakeKmlFile()}.
	 * @throws IOException 
	 */
	@Test
	public void testMakeKmlFile() throws IOException {
		
		MakeKML mockMakeKML = new MakeKML();
		mockMakeKML.MakeKmlFile();
		
		
		Kml kml = Kml.unmarshal(new File("HelloKml.kml"));
		Placemark placemark = (Placemark) kml.getFeature();
		Point point = (Point) placemark.getGeometry();
		List<Coordinate> coordinates = point.getCoordinates();
		for (Coordinate coordinate : coordinates) {
			System.out.println(coordinate.getLatitude());
			System.out.println(coordinate.getLongitude());
			System.out.println(coordinate.getAltitude());
			
			assertTrue(areEqualDouble(coordinate.getLatitude(),51.500152, 6));
		}
		
		
	}
	
	/**
	 * Test method for {@link test1.MakeKmlFromCsv()}.
	 */
	@Test
	public void testMakeKmlFromCsv() 
			throws IOException {
		
		String inFilename = "/Users/brucegoldfeder/Sites/maxmind/file.csv";
		String outFilename = "/Users/brucegoldfeder/Sites/maxmind/honeypot.kml";
		String docName = "Honey_Pot";
		String folderName = "Originating_IPs";
		int latColumn = 7;
		int lonColumn =8;
		
		MakeKML mockMakeKML = new MakeKML();
		mockMakeKML.MakeKmlFromCsv(inFilename, outFilename, docName, folderName, latColumn, lonColumn);
		
	}


	/**
	 * Test method for {@link test1.MakeKML#MakeKmlManyPoints()}.
	 */
	//@Test
	public void testMakeKmlManyPoints() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link test1.MakeKML#MakeBeijingCoords()}.
	 */
	//@Test
	public void testMakeBeijingCoords() {
		fail("Not yet implemented"); // TODO
	}

}
