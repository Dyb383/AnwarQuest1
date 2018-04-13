import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

/**
 * Overworld class. Has sprites and our character running around creating events for battle.
 */
public class Adventure extends JFrame implements ActionListener,KeyListener,Runnable
{
    Image img;
    BufferedImage offScreen;
    Places battlePlaces = new Places();
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
                Battle battleRat = new Battle("Rat");
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

    public void drawBackground1(Graphics g) {
        try{
            img = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/Backgroundnew.png"));}
        catch (Exception e){}}

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
    
    public void update(){
        //Starting crap
        Graphics g = getGraphics();
        Graphics offScreenGraphics=offScreen.getGraphics();
        offScreenGraphics.drawImage(img,0,0,null);
        //Title/Class/Beginning Background
        if (gameStarted==false && classScreen==false) {
            drawTitle(offScreenGraphics);
            }
        if (gameStarted==true) {  
            battlePlaces.drawPlaces(offScreenGraphics);
            //STATS
            //First Column
            offScreenGraphics.drawString("Player XP: "+ myPlayer.xp, 20,475);
            offScreenGraphics.drawString("Player Level: " + myPlayer.level, 20, 495);
            //Second Column
            offScreenGraphics.drawString("Player Str: "+ myPlayer.str, 150,475);
            offScreenGraphics.drawString("Player Int: "+ myPlayer.intel, 150,495);
            //Third Column
            offScreenGraphics.drawString("Player Health: "+ myPlayer.endur, 250,475);
            offScreenGraphics.drawString("Player Mana: "+ myPlayer.intel*5, 250,495);
            //TRIGGERING BATTLES
            if (battlePlaces.isCollision1(myPlayer)==true) {
                Battle battleRat = new Battle("Rat");
                battleRat.setPlayer(myPlayer);
                if (playerDirection.equals ("down")){
                    myPlayer.y-=10; }
                if (playerDirection.equals ("up")){
                    myPlayer.y+=10; }
                if (playerDirection.equals ("left")){
                    myPlayer.x+=10; }
                if (playerDirection.equals ("right")){
                    myPlayer.x-=10; }
            }
            if (battlePlaces.isCollision2(myPlayer)==true) {
                Battle battleDragon = new Battle("Dragon");
                battleDragon.setPlayer(myPlayer);
                if (playerDirection.equals ("down")){
                    myPlayer.y-=10; }
                if (playerDirection.equals ("up")){
                    myPlayer.y+=10; }
                if (playerDirection.equals ("left")){
                    myPlayer.x+=10; }
                if (playerDirection.equals ("right")){
                    myPlayer.x-=10; }
            }
            if (battlePlaces.isCollision3(myPlayer)==true) {
                Battle battleGoblin = new Battle("Goblin");
                battleGoblin.setPlayer(myPlayer);
                if (playerDirection.equals ("down")){
                    myPlayer.y-=10; }
                if (playerDirection.equals ("up")){
                    myPlayer.y+=10; }
                if (playerDirection.equals ("left")){
                    myPlayer.x+=10; }
                if (playerDirection.equals ("right")){
                    myPlayer.x-=10; }
            }
            if (battlePlaces.isCollision4(myPlayer)==true) {
                Battle battleOctopus = new Battle("Octopus");
                battleOctopus.setPlayer(myPlayer);
                if (playerDirection.equals ("down")){
                    myPlayer.y-=10; }
                if (playerDirection.equals ("up")){
                    myPlayer.y+=10; }
                if (playerDirection.equals ("left")){
                    myPlayer.x+=10; }
                if (playerDirection.equals ("right")){
                    myPlayer.x-=10; 
                    }
            }
            
            if (battlePlaces.isCollisionNext(myPlayer)==true) {
            	if (checkForNewGame()==false){
            		//in here will be where we paint the second level
            		//will probably need a new method along the lines of update2ndLevel();
            	}
            	else if (checkForNewGame()==true){
            		offScreenGraphics.drawString("Player must level up before continuing.", 200, 400);
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
            drawBackground1(offScreenGraphics);
        }
        if (classScreen==true) {
            drawClassScreen(offScreenGraphics); }

        myPlayer.levelUp();

        //Ending crap
        g.drawImage(offScreen,0,0,this); 
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
            update();
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