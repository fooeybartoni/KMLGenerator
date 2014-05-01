package test1;

import java.util.Random;

public class MyIconParams {

	public MyIconParams(double lat, double lon, String name) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.Ip = name;
		
	}
	
	public MyIconParams(String lat, String lon, String name) {
		// TODO Auto-generated constructor stub
		
		this(Double.parseDouble(lat), Double.parseDouble(lon), name);
	}
	
	private double lat;
	private double lon;
	private final static int octet = 254;
	private String Ip = new String();
	
	private String createRndIp() {
		
		Random rnd1 = new Random();
		
		StringBuilder IpNew = new StringBuilder();
		IpNew.append((int)(rnd1.nextDouble()*octet)+1);
		IpNew.append(".");
		IpNew.append((int)(rnd1.nextDouble()*octet));
		IpNew.append(".");
		IpNew.append((int)(rnd1.nextDouble()*octet));
		IpNew.append(".");
		IpNew.append((int)(rnd1.nextDouble()*octet));
		
		System.out.println(IpNew.toString());
		
		return IpNew.toString();
	}
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public String getIp() {
		return Ip;
	}
	public void setIp(String ip) {
		Ip = ip;
	}

	@Override
	public String toString() {
		return "MyIconParams [lat=" + lat + ", lon=" + lon + ", Ip=" + Ip + "]";
	}
	
	
}
