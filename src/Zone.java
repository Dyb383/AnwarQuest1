import java.awt.Image;
import java.util.ArrayList;

public class Zone {
	private Image img;
	private int defaultx;
	private int defaulty;
	private ArrayList<Place> locations = new ArrayList<Place>();
	private Zone next;
	public Zone()
	{
		defaultx=400;
		defaulty=400;
	}
	public Zone(Image imgIn, int defx, int defy, Zone nextIn)
	{
		
		img =  imgIn;
		defaultx=defx;
		defaulty=defy;
		next = nextIn;
	}
	public void setImage(String image)
	{
        try{
            img = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/"+image));}
        catch (Exception e){}
    }
	public void addLocation(Place pIn)
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
	public ArrayList<Place> getLocations()
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
	public Image getImage() {
		return img; 
	}
}
