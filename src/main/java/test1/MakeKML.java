package test1;

import java.io.File;
import java.io.FileNotFoundException;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Style;

public class MakeKML {
	
/*  Upper Left

	 39¡58'20.23"N
	116¡17'1.49"E

	Lower Right

	 39¡49'52.65"N
	116¡33'22.49"E
*/
	
	
	
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
	    
	    // create Placemark elements
	    createPlacemark(doc, folder, 93.24607775062842, 47.49808862281773, "Asia", 30);
	    createPlacemark(doc, folder, 19.44601806124206, 10.13133611111111, "Africa", 20);
	    createPlacemark(doc, folder, -103.5286299241638, 41.26035225962401, "North America", 17);
	    createPlacemark(doc, folder, -59.96161780270248, -13.27347674076888, "South America", 12);
	    createPlacemark(doc, folder, 14.45531426360271, 47.26208181151567, "Europe", 7);
	    createPlacemark(doc, folder, 135.0555272486322, -26.23824399654937, "Australia", 6);
	   
	    // print and save
	    kml.marshal(new File("advancedexample1.kml"));
	}
	
	private static void createPlacemark(Document document, Folder folder, double longitude, double latitude, 
		    String continentName, int coveredLandmass) {

		
			Icon icon = new Icon()
			    .withHref("http://labs.google.com/ridefinder/images/mm_20_purple.png");
			Style style = document.createAndAddStyle();
			style.withId("style_" + continentName) // set the stylename to use this style from the placemark
			    .createAndSetIconStyle().withScale(5.0).withIcon(icon); // set size and icon
			style.createAndSetLabelStyle().withColor("ff43b3ff").withScale(5.0); // set color and size of the continent name

			Placemark placemark = folder.createAndAddPlacemark();
			// use the style for each continent
			placemark.withName(continentName)
			    .withStyleUrl("#style_" + continentName)
			    // 3D chart imgae
			    //.withDescription(
			     //   "<![CDATA[<img src=\"http://chart.apis.google.com/chart?chs=430x200&chd=t:" + coveredLandmass + "," + remainingLand + "&cht=p3&chl=" + continentName + "|remaining&chtt=Earth's surface\" />")
			    // coordinates and distance (zoom level) of the viewer
			    .createAndSetLookAt().withLongitude(longitude).withLatitude(latitude).withAltitude(0).withRange(12000000);
			
			placemark.createAndSetPoint().addToCoordinates(longitude, latitude); // set coordinates
		}
	
	public static void main(String[] args) {
		MakeKML kmlmaker = new MakeKML();
		try {
			kmlmaker.MakeKmlManyPoints();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
