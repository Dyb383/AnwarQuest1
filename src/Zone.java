import java.awt.Image;
import java.util.ArrayList;

public class Zone {
	private Image img;
	private int defaultx;
	private int defaulty;
	private ArrayList<Integer> locations = new ArrayList<Integer>();
	private Zone next;
	
	public void Zone(Image imgIn, int defx, int defy, Zone nextIn)
	{
		
		img =  imgIn;
		defaultx=defx;
		defaulty=defy;
		next = nextIn;
	}
	public void setImg(Image imgIn)
	{
		img = imgIn;
	}
	public void addLocation(int x, int y)
	{
		locations.add(x);
		locations.add(y);
	}
	public void setDefaultX(int x)
	{
		defaultx = x;
	}
	public void setDefaultY(int y)
	{
		defaulty=y;
	}
	public void setNext(Zone m)
	{
		next = m;
	}
	public Image getImg()
	{
		return img;
	}
	public ArrayList<Integer> getLocations()
	{
		return locations;
	}
	public int getDefaultX()
	{
		return defaultx;
	}
	public int getDefaultY()
	{
		return defaulty;
	}
	public Zone getNext()
	{
		return next;
	}
}
