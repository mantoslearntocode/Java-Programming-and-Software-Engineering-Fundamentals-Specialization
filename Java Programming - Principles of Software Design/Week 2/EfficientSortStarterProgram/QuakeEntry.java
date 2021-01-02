
public class QuakeEntry implements Comparable<QuakeEntry>{
	
	private Location myLocation;
	private String title;
	private double depth;
	private double magnitude;

	public QuakeEntry(double lat, double lon, double mag, 
	                  String t, double d) {
		myLocation = new Location(lat,lon);
		
		magnitude = mag;
		title = t;
		depth = d;
	}
	
	public Location getLocation(){
		return myLocation;
	}
	
	public double getMagnitude(){
		return magnitude;
	}
	public String getInfo(){
		return title;
	}
	public double getDepth(){
		return depth;
	}

	@Override
	public int compareTo(QuakeEntry loc) {
	    //return Double.compare(magnitude, loc.getMagnitude());
	    // sort quake by magnitude first, from smallest magnitude to largest magnitude, 
	    // and then break ties (use == operator to determine whether magnitudes are equal) by depth
	    int comp = Double.compare(magnitude, loc.getMagnitude());
	    if (comp != 0)
	    {
	        return comp;
	    }
	    
	    return Double.compare(depth, loc.getDepth());
	    // Here is another way to sort by Magnitude
	    /*
	    if (magnitude < loc.getMagnitude())
	    {
	        return -1;
	    }
	    if (magnitude > loc.getMagnitude())
	    {
	        return 1;
	    }
	    return 0;
	    */
	    
	    
	   //  Below here sorts by Location
	    /*
		double difflat = myLocation.getLatitude() - loc.myLocation.getLatitude();
		if (Math.abs(difflat) < 0.001) {
			double diff = myLocation.getLongitude() - loc.myLocation.getLongitude();
			if (diff < 0) return -1;
			if (diff > 0) return 1;
			return 0;
		}
		if (difflat < 0) return -1;
		if (difflat > 0) return 1;
		
		
		// never reached
		return 0;
		*/
	}
	
	public String toString(){
		return String.format("(%3.2f, %3.2f), mag = %3.2f, depth = %3.2f, title = %s", myLocation.getLatitude(),myLocation.getLongitude(),magnitude,depth,title);
	}
	
}