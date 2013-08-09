package test1;

import java.io.File;
import java.io.FileNotFoundException;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Style;
import java.util.Random;

public class MakeKML {
	

	
	
	
	public void MakeKmlFile() throws FileNotFoundException {
		final Kml kml = new Kml();
		kml.createAndSetPlacemark()
		   .withName("London, UK").withOpen(Boolean.TRUE)
		   .createAndSetPoint().addToCoordinates(-0.126236, 51.500152);
		kml.createAndSetPlacemark()
		   .withName("London2, UK").withOpen(Boolean.TRUE)
		   .createAndSetPoint().addToCoordinates(-0.146236, 51.500152);
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
	
	private static void createPlacemark(Document document, Folder folder, double longitude, double latitude, 
		    String ipName, int coveredLandmass) {

		
			Icon icon = new Icon()
			    .withHref("http://labs.google.com/ridefinder/images/mm_20_purple.png");
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
			
			placemark.createAndSetPoint().addToCoordinates(longitude, latitude); // set coordinates
		}
	
	public MyIconParams[] MakeBeijingCoords() {
		/*  Upper Left

		 39¡58'20.23"N
		116¡17'1.49"E		39.972286  116.283747

		Lower Right

		 39¡49'52.65"N
		116¡33'22.49"E		39.831292	116.556247
	*/		
		
		double lon1 = 39.972286;
		double lon2 = 39.831292;
		double lat1 = 116.283747;
		double lat2 = 116.556247;
		
		double lat_diff = lat1 - lat2;
		double lon_diff = lon2 - lon1;
		
		
		
		MyIconParams[] Beijing = new MyIconParams[1000];
		
		for (int x=1; x<1000;x++) {
			Random rnd = new Random();
			
			Beijing[x] = new MyIconParams(lat2+rnd.nextDouble()*lat_diff, lon1+rnd.nextDouble()*lon_diff);
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
