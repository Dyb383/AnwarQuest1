import java.awt.Image;
import java.util.ArrayList;

public class Zone {
	private Image img;
	private int defaultx;
	private int defaulty;
	private ArrayList<Places> locations = new ArrayList<Places>();
	private Zone next;
	
	public Zone(Image imgIn, int defx, int defy, Zone nextIn)
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
	public void addLocation(Places pIn)
	{
		locations.add(pIn);
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
	public ArrayList<Places> getLocations()
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
