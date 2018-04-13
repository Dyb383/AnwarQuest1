import java.awt.Image;
import java.util.ArrayList;

public class Zone {
	Image img;
	int defaultx;
	int defaulty;
	ArrayList<Integer> locations = new ArrayList<Integer>();
	Zone next;
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
}
