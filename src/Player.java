import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This is the player character class.
 */
public class Player 
{
    int level=1;
    int xp=0;
    int str=5;
    int intel=5;
    int endur=25;
    int gold=0;
    int maxHP = endur;
    int maxMana = intel*5;
    int currentHP = maxHP;
    int currentMana = maxMana;
    int quest = 0;

    int x=400;
    int y=400;

    int playerClass=0;

    Image img;
    public Player (){

    }

    //Draws the player in different directions
    public void drawPlayerDown(Graphics g) {
        try
        {
            img = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/PlayerDown.png"));
            g.drawImage(img,x,y,null);
        }
        catch (Exception e){}
    }

    public void drawPlayerUp(Graphics g) {
        try
        {
            img = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/PlayerUp.png"));
            g.drawImage(img,x,y,null);
        }
        catch (Exception e){}
    }

    public void drawPlayerLeft(Graphics g) {
        try
        {
            img = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/PlayerLeft.png"));
            g.drawImage(img,x,y,null);
        }
        catch (Exception e){}
    }

    public void drawPlayerRight(Graphics g) {
        try
        {
            img = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/PlayerRight.png"));
            g.drawImage(img,x,y,null);
        }
        catch (Exception e){}
    }

    public void levelUp() {
        if (xp>=Math.pow(level,2)+20){
            if (playerClass==(1)){
                level=level+1;
                str=str+5;
                intel=intel+1;
                endur=endur+7;
                xp=0;
                maxHP = endur;
                maxMana = intel*5;
                currentHP = maxHP;
                currentMana = maxMana;
            }
            if (playerClass==(2)){
                level=level+1;
                str=str+1;
                intel=intel+4;
                endur=endur+5;
                xp=0;
                maxHP = endur;
                maxMana = intel*5;
                currentHP = maxHP;
                currentMana = maxMana;
            }
        }
    }

    //How Battle gets the stats from player.
    public int getEndur(){
        return endur;
    }

    public int getIntel() {
        return intel;
    }
    

    //Deals with movement
    public void moveUp() {
        y=y-10;
    }

    public void moveDown() {
        y=y+10;
    }

    public void moveLeft() {
        x=x-10;
    }

    public void moveRight() {
        x=x+10;
    }

    

}