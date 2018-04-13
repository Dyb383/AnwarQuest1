import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Image.*;
/**
 *This class places the objects on screen such as the castle and tent.
 */
public class Places
{
    Player myPlayer = new Player();
    int x1=100;
    int y1=100;
    int x2=300;
    int y2=300;
    int x3=250;
    int y3=150;
    int x4=100;
    int y4=430;
    Image img1;
    Image img2;
    Image img3;
    Image img4;
    public void drawPlaces(Graphics g) {

        try{
            img1 = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/Cave.png"));
            g.drawImage(img1,x1,y1,null);}
        catch (Exception e){}
        try{
            img2 = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/Castle.png"));
            g.drawImage(img2,x2,y2,null);}
        catch (Exception e){}
        try{
            img3 = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/Tent.png"));
            g.drawImage(img3,x3,y3,null);}
        catch (Exception e){}
        try{
            img4 = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/Port.png"));
            g.drawImage(img4,x4,y4,null);}
        catch (Exception e){}
    }

    public boolean isCollision1(Player myPlayer) //collision detection for rat
    {
        int playerX=myPlayer.x;
        int playerY=myPlayer.y;
        if (myPlayer.x+29>=x1 && myPlayer.x<=x1+56 && myPlayer.y+28>=y1 && myPlayer.y<=y1+56)
            return true;
        return false;
    }
    
    public boolean isCollision2(Player myPlayer)//dragon
    {
        int playerX=myPlayer.x;
        int playerY=myPlayer.y;
        if (myPlayer.x+29>=x2 && myPlayer.x<=x2+56 && myPlayer.y+28>=y2 && myPlayer.y<=y2+56)
            return true;
        return false;
    }
    
    public boolean isCollision3(Player myPlayer)//goblin
    {
        int playerX=myPlayer.x;
        int playerY=myPlayer.y;
        if (myPlayer.x+29>=x3 && myPlayer.x<=x3+56 && myPlayer.y+28>=y3 && myPlayer.y<=y3+56)
            return true;
        return false;
    }
    
    public boolean isCollision4(Player myPlayer)//octopus
    {
        int playerX=myPlayer.x;
        int playerY=myPlayer.y;
        if (myPlayer.x+29>=x4 && myPlayer.x<=x4+56 && myPlayer.y+28>=y4 && myPlayer.y<=y4+56)
            return true;
        return false;
    }
    public boolean isCollisionNext(Player myPlayer)//collision detection for the next window
    {
    	int playerX=myPlayer.x;
    	int playerY=myPlayer.y;
    	if (myPlayer.x>=410 && myPlayer.x<=429 && myPlayer.y>=200 && myPlayer.y<=220)
    		return true;
    	else
    		return false;
    }

}
