import java.awt.Graphics;
import java.awt.Image;

public class Place {
	int x;
	int y;
	//c for combat, h for hub, s for shop, b for boss
	char type;
	String monster;
	Image img;
	Player myPlayer = new Player();
	
	public Place(int xIn, int yIn, char tIn)
	{
		x=xIn;
		y=yIn;
		type = tIn;
	}
	
	public Place() {
		// TODO Auto-generated constructor stub
	}

	public boolean isCollision(Player myPlayer, Place myPlace)
	{
	     int playerX=myPlayer.x;
	     int playerY=myPlayer.y;
	     if (myPlayer.x+29>=myPlace.x && myPlayer.x<myPlace.x+56 && myPlayer.y+28>=myPlace.y && myPlayer.y<=myPlace.y+56)
	         return true;
	     return false;
	}
	//takes in the pictures filename i.e Castle.png, and sets the places img. DO NOT INCLUDE PICS/
	public void setImage(String image)
	{
        try{
            img = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/"+image));}
        catch (Exception e){}
    }
	public void setType(char t)
	{
		type = t;
	}
	public void setMonster(String monIn)
	{
		monster = monIn;
	}
	public void drawPlace(Graphics g) {
    try{
        g.drawImage(img,x,y,null);}
    catch (Exception e){}
	}
	

}