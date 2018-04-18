import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;

/**
 * Overworld class. Has sprites and our character running around creating events for battle.
 */
public class Adventure extends JFrame implements ActionListener,KeyListener,Runnable
{
    Image img;
    int zoneCount=0;
    ArrayList<Zone> zoneArr = populate();
    BufferedImage offScreen;
    Place battlePlace = new Place();
    //Keeps track of how far the player is
    int eventsActivated=0;
    Player myPlayer = new Player();
    //Makes sure player starts on title screen
    boolean gameStarted=false;
    boolean classScreen=false;
    String playerDirection = "down";
    public static void main(String[] args) {
        Adventure p = new Adventure();
        p.init();

        p.setSize(510,510);
        //p.setExtendedState (JFrame.MAXIMIZED_BOTH);  //this sets it to maximum size
        // or set the size you want p.setSize(520,520);
        p.setVisible(true);
        p.start();
    }

    public void init() {
        //Setup
        addKeyListener(this);
        setFocusable(true);
        offScreen = new BufferedImage(500,500, BufferedImage.TYPE_INT_RGB);
    }

    public void paint (Graphics g) {
        super.paint(g); }     

    public void keyPressed(KeyEvent e)
    {
        char theChar=e.getKeyChar();
        int theCode=e.getKeyCode();
        //ONCE THE GAME HAS STARTED
        if (gameStarted==true) {
            if (theCode == KeyEvent.VK_W)
            {
                //Makes sure he doesn't go off the island, but can still move after colliding.
                if (myPlayer.x<50)
                    myPlayer.moveRight();
                if (myPlayer.y<50)
                    myPlayer.moveDown();
                if (myPlayer.x>430)
                    myPlayer.moveLeft();
                if (myPlayer.y>420)
                    myPlayer.moveUp();
                else {
                    myPlayer.moveUp();
                    playerDirection = "up";}
            }
            if (theCode == KeyEvent.VK_S)
            {
                //Makes sure he doesn't go off the island, but can still move after colliding.
                if (myPlayer.x<50)
                    myPlayer.moveRight();
                if (myPlayer.y<50)
                    myPlayer.moveDown();
                if (myPlayer.x>430)
                    myPlayer.moveLeft();
                if (myPlayer.y>420)
                    myPlayer.moveUp();
                else{
                    myPlayer.moveDown();
                    playerDirection = "down";}
            }           
            if (theCode == KeyEvent.VK_D)
            {
                //Makes sure he doesn't go off the island, but can still move after colliding.
                if (myPlayer.x<50)
                    myPlayer.moveRight();
                if (myPlayer.y<50)
                    myPlayer.moveDown();
                if (myPlayer.x>430)
                    myPlayer.moveLeft();
                if (myPlayer.y>420)
                    myPlayer.moveUp();
                else{
                    myPlayer.moveRight();
                    playerDirection = "right";}
            }
            if (theCode == KeyEvent.VK_A)
            {
                //Makes sure he doesn't go off the island, but can still move after colliding.
                if (myPlayer.x<50)
                    myPlayer.moveRight();
                if (myPlayer.y<50)
                    myPlayer.moveDown();
                if (myPlayer.x>430)
                    myPlayer.moveLeft();
                if (myPlayer.y>420)
                    myPlayer.moveUp();
                else{
                    myPlayer.moveLeft();
                    playerDirection = "left";}
            }
            if (theCode == KeyEvent.VK_3)
            {
                Battle battleRat = new Battle("Rat", myPlayer);
                battleRat.setPlayer(myPlayer);
            }
        }
        //ONCE ON THE TITLE SCREEN
        if (gameStarted==false) {
            if (theCode == KeyEvent.VK_SPACE) 
                classScreen=true; }
        //ON THE CLASS SCREEN
        if (classScreen==true) {
            if (theCode == KeyEvent.VK_1)
            {
                classScreen=false;
                gameStarted=true;
                myPlayer.playerClass=(1);
            }
            if (theCode == KeyEvent.VK_2)
            {
                classScreen=false;
                gameStarted=true;
                myPlayer.playerClass=(2);
            }
        }
    }
    //UNEEDED KEY LISTENERS
    public void keyReleased(KeyEvent e){}

    public void keyTyped(KeyEvent e){}
    
    public void actionPerformed(ActionEvent thisEvent){
        Object source = thisEvent.getSource(); }

    //Drawing methods:
    public void drawTitle(Graphics g) {
        try{
            img = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/title.png"));}
        catch (Exception e){}}

    public void drawZone(Graphics g) {
            img = zoneArr.get(zoneCount).getImage();
            }

    public void drawClassScreen(Graphics g) {
        try{
            img = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/Class_Screen.png"));}
        catch (Exception e){}}

    public boolean checkForNewGame(){
    	if (myPlayer.level==1){
    		return true;
    	}
    	else
    		return false;
    }
    
    public void update(Zone z){
        //Starting crap
        Zone zwork = zoneArr.get(zoneCount);
        ArrayList<Place> placeW = zwork.getLocations();
        Graphics g = getGraphics();
        Graphics offScreenGraphics=offScreen.getGraphics();
        offScreenGraphics.drawImage(img,0,15,null);
        //Title/Class/Beginning Background
        if (gameStarted==false && classScreen==false) {
            drawTitle(offScreenGraphics);
            }
        if (gameStarted==true) {  


            //STATS
            //First Column
            offScreenGraphics.drawString("Gold: " + myPlayer.gold, 20, 465);
            offScreenGraphics.drawString("Player XP: "+ myPlayer.xp, 20,480);
            offScreenGraphics.drawString("Player Level: " + myPlayer.level, 20, 495);
            //Second Column
            offScreenGraphics.drawString("Player Str: "+ myPlayer.str, 150,480);
            offScreenGraphics.drawString("Player Int: "+ myPlayer.intel, 150,495);
            //Third Column
            offScreenGraphics.drawString("Player Health: "+ myPlayer.currentHP+" / "+myPlayer.endur, 250,480);
            offScreenGraphics.drawString("Player Mana: "+ myPlayer.currentMana + " / "+myPlayer.intel*5, 250,495);
            //TRIGGERING BATTLES

            
            for(int i=0; i<zwork.getLocations().size(); i++) {
	            if (battlePlace.isCollision(myPlayer, placeW.get(i))==true) {
	                if(placeW.get(i).type == 'c')
	                {
	                	Battle battle = new Battle(placeW.get(i).monster, myPlayer);
	                }
	                if(placeW.get(i).type == 'h')
	                {
	                	HubWorld hub = new HubWorld(myPlayer);
	                }
	                if(placeW.get(i).type == 's')
	                {
	                    Shop shop = new Shop(myPlayer);
	                }
	                if(placeW.get(i).type =='u')
	                {
	                	zoneCount++;
	                	myPlayer.x = zoneArr.get(zoneCount).getDefaultX();
	                	myPlayer.y = zoneArr.get(zoneCount).getDefaultY();
	                }
	                if(placeW.get(i).type=='d')
	                {
	                	zoneCount--;
	                	myPlayer.x = zoneArr.get(zoneCount).getDefaultX();
	                	myPlayer.y = zoneArr.get(zoneCount).getDefaultY();
	                }
                
                	//battleRat.setPlayer(myPlayer);
                if (playerDirection.equals ("down")){
                    myPlayer.y-=20; }
                if (playerDirection.equals ("up")){
                    myPlayer.y+=20; }
                if (playerDirection.equals ("left")){
                    myPlayer.x+=20; }
                if (playerDirection.equals ("right")){
                    myPlayer.x-=20; }
	            }
	        }
                       
            //PLAYER MOVING IN A DIRECTION
            if (playerDirection.equals ("down")){
                myPlayer.drawPlayerDown(offScreenGraphics); }
            if (playerDirection.equals ("up")){
                myPlayer.drawPlayerUp(offScreenGraphics); }
            if (playerDirection.equals ("left")){
                myPlayer.drawPlayerLeft(offScreenGraphics); }
            if (playerDirection.equals ("right")){
                myPlayer.drawPlayerRight(offScreenGraphics); }
            drawZone(offScreenGraphics);
            for(int i=0; i<zwork.getLocations().size(); i++) {
                Place pW = placeW.get(i); 
            	pW.drawPlace(offScreenGraphics);
            }
        }
        if (classScreen==true) {
            drawClassScreen(offScreenGraphics); }

        myPlayer.levelUp();

        //Ending crap
        g.drawImage(offScreen,0,0,this); 
    }
    public static ArrayList<Zone>  populate()
    {
    	ArrayList<Zone> zOut = new ArrayList<Zone>();
    	Zone z1 = new Zone();
    	Zone z2 = new Zone();
    	
    	Place z1P1 = new Place(100, 100, 'c');
    	Place z1P2 = new Place(200,200,'h');
    	Place z1P3 = new Place(250,50,'c');
    	Place z1P4 = new Place(100,430,'c');
    	Place z1P5 = new Place (300,300,'s');
    	Place z1P6 = new Place ( 429, 220, 'u');
    	z1P5.setImage("shopColl.png");
    	z1P1.setImage("Cave.png");
    	z1P2.setImage("Castle.png");
    	z1P3.setImage("Tent.png");
    	z1P4.setImage("Port.png");
    	z1P1.setMonster("Rat");
    	z1P3.setMonster("Goblin");
    	z1P4.setMonster("Octopus");
    	z1.addLocation(z1P1);
    	z1.addLocation(z1P2);
    	z1.addLocation(z1P3);
    	z1.addLocation(z1P4);
    	z1.addLocation(z1P5);
    	z1.addLocation(z1P6);
    	
    	Place z2P1 = new Place(100,100,'d');
    	Place z2P2 = new Place(250,250,'c');
    	z2P1.setImage("Tent.png");
    	z2P2.setMonster("Woof Barkington");
    	z2P2.setImage("boosHat.png");
    	z2.addLocation(z2P1);
    	z2.addLocation(z2P2);
    	z2.setImage("zone2BG.png");
    	z1.setImage("backgroundnew.png");
    	zOut.add(z1);
    	zOut.add(z2);
    	return zOut;  	
    }
    
    

    /*********************************************************************************************/
    /* BELOW IS FOR ANIMATION.  THE ONLY THING THAT YOU NEED TO CHANGE IS DELAY */

    int frame;
    int delay=50;   // this is the time of the delay in milliseconds.
    Thread animator;

    /**
     * This method is called when the applet becomes visible on
     * the screen. Create a thread and start it.
     */
    public void start()
    {
        animator = new Thread(this);
        animator.start();
    }

    /**
     * This method is called by the thread that was created in
     * the start method. It does the main animation.
     */
    public void run()
    {
        // Remember the starting time
        long tm = System.currentTimeMillis();
        while (Thread.currentThread() == animator)
        {
            // Display the next frame of animation.
            update(zoneArr.get(zoneCount));
            try
            {
                tm += delay;
                Thread.sleep(Math.max(0, tm - System.currentTimeMillis()));
            }
            catch (InterruptedException e)
            {
                break;
            }
            // Advance the frame
            frame++;
        }
    }

    /**
     * This method is called when the applet is no longer
     * visible. Set the animator variable to null so that the
     * thread will exit before displaying the next frame.
     */
    public void stop()
    {
        animator = null;
    }
}