package test1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import au.com.bytecode.opencsv.CSVReader;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Style;

import org.apache.commons.lang3.StringUtils;

public class MakeKML {
	
	public void MakeKmlFile() throws FileNotFoundException {
		final Kml kml = new Kml();
		kml.createAndSetPlacemark()
		   .withName("London, UK").withOpen(Boolean.TRUE)
		   .createAndSetPoint().addToCoordinates(-0.126236, 51.500152);
		kml.createAndSetPlacemark()
		   .withName("London2, UK").withOpen(Boolean.TRUE)
		   .createAndSetPoint().addToCoordinates(-0.246236, 51.500152);
		kml.marshal(new File("HelloKml.kml"));
	}
	
	public void MakeKmlManyPoints() throws FileNotFoundException {
		final Kml kml = new Kml();
		Document doc = kml.createAndSetDocument().withName("JAK Example1").withOpen(true);

	    // create a Folder
	    Folder folder = doc.createAndAddFolder();
	    folder.withName("Continents with Earth's surface").withOpen(true);
	    
	    MyIconParams[] targets = new MyIconParams[1000];
	    
	    targets = MakeBeijingCoords();
	    
	    for (int x=1; x<1000;x++) {
		    // create Placemark elements
	    	
	    	System.out.println(targets[x].toString());
	    	
		    createPlacemark(doc, folder, targets[x].getLat(), targets[x].getLon(), targets[x].getIp(), 30);
	    }
	   
	    // print and save
	    kml.marshal(new File("advancedexample1.kml"));
	}
	
	public void MakeKmlFromCsv(String inFilename, String outFilename, String docName, String folderName, int latColumn, int lonColumn) throws IOException {
		final Kml kml = new Kml();
		Document doc = kml.createAndSetDocument().withName(docName).withOpen(true);

	    // create a Folder
	    Folder folder = doc.createAndAddFolder();
	    folder.withName(folderName).withOpen(true);
	    
	    // List of Lat/Lon
	    List<MyIconParams> targets = new ArrayList<MyIconParams>();
	    
	    String strFile = inFilename;
	    CSVReader reader = new CSVReader(new FileReader(strFile));
	    String [] nextLine;
	    int cnt = 0;  
	    while ((nextLine = reader.readNext()) != null) {
	    	//System.out.println("lat is " + latColumn + " lon is " + lonColumn);
	    	System.out.println("lat is " + nextLine[latColumn] + " lon is " + nextLine[lonColumn]);
	    	if (cnt > 0){
	    		
	    		System.out.println("bool is "+(StringUtils.isNotBlank(nextLine[latColumn]) || StringUtils.isNotBlank(nextLine[lonColumn])));
	    		
	    		if (StringUtils.isNotBlank(nextLine[latColumn]) || StringUtils.isNotBlank(nextLine[lonColumn])) {
		    		targets.add(new MyIconParams(nextLine[latColumn],nextLine[lonColumn],nextLine[0]));
		    		System.out.println("count is: "+cnt);
	    		}
	    	}
	    	cnt++;
	    	 
	    }
	    
	    
	    for (MyIconParams target : targets) {
		    // create Placemark elements
	    	
	    	System.out.println(target.toString());
	    	
		    createPlacemark(doc, folder, target.getLat(), target.getLon(), target.getIp(), 30);
	    }
	   
	    // print and save
	    kml.marshal(new File(outFilename));
	    
	    reader.close();
	}
	
	private static void createPlacemark(Document document, Folder folder, double longitude, double latitude, 
		    String ipName, int coveredLandmass) {

		
			Icon icon = new Icon()
			    .withHref("http://maps.google.com/mapfiles/kml/paddle/red-square.png");
			Style style = document.createAndAddStyle();
			style.withId("style_" + ipName) // set the stylename to use this style from the placemark
			    .createAndSetIconStyle().withScale(1.0).withIcon(icon); // set size and icon
			style.createAndSetLabelStyle().withColor("ff43b3ff").withScale(1.0); // set color and size of the continent name

			Placemark placemark = folder.createAndAddPlacemark();
			// use the style for each continent
			placemark.withName(ipName)
			    .withStyleUrl("#style_" + ipName)
			    .createAndSetLookAt().withLongitude(longitude).withLatitude(latitude)
			    .withAltitude(0).withRange(12000000);
			
			placemark.createAndSetPoint().addToCoordinates(latitude,longitude); // set coordinates
	}
	
	public MyIconParams[] MakeBeijingCoords() {
		
		double lon1 = 39.972286;
		double lon2 = 39.831292;
		double lat1 = 116.283747;
		double lat2 = 116.556247;
		
		double lat_diff = lat1 - lat2;
		double lon_diff = lon2 - lon1;
		
		
		
		MyIconParams[] Beijing = new MyIconParams[1000];
		
		for (int x=1; x<1000;x++) {
			Random rnd = new Random();
			
			Beijing[x] = new MyIconParams(lat2+rnd.nextDouble()*lat_diff, lon1+rnd.nextDouble()*lon_diff, "foo");
		}
		
		return Beijing;
		
	}
	
	public static void main(String[] args) {
		MakeKML kmlmaker = new MakeKML();
		try {
			kmlmaker.MakeKmlManyPoints();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
