import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.GridBagLayout.*;
/**
 * This is the battle system class.
 */
public class Battle extends JFrame implements ActionListener,Runnable
{
    Image img;
    Image img1;
    BufferedImage offScreen;
    boolean playerTurn=true;
    Player battlePlayer=new Player();
    int numOfHPotions=5;
    int numOfMPotions=5;
    //Gets stats from player to the battle
    int health;
    int mana;
    //Creates the monster you will fight in battle
    Monsters battleMonsters=new Monsters();
    MonsterStats myMonster;
    //Finds monster based on monster name
    JButton attackButton= new JButton ("ATTACK");
    JButton defendButton= new JButton ("DEFEND");
    JButton fireButton= new JButton ("FIRE");
    JButton iceButton= new JButton ("ICE");
    JButton hPotionButton= new JButton ("POTION");
    JButton mPotionButton= new JButton ("ELIXIR");
    JPanel buttonPanel = new JPanel();

    int monsterY=100;
    int monsterPic=0;
    int timesDefend=0;
    public void init()
    {        
    	//setting the layout of the battle window
        Container screen = getContentPane();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(attackButton);
        attackButton.setBackground(Color.WHITE);
        buttonPanel.add(defendButton);
        buttonPanel.add(fireButton);
        buttonPanel.add(iceButton);
        buttonPanel.add(hPotionButton);
        buttonPanel.add(mPotionButton);
        attackButton.addActionListener(this);
        defendButton.addActionListener(this);
        hPotionButton.addActionListener(this);
        mPotionButton.addActionListener(this);
        fireButton.addActionListener(this);
        iceButton.addActionListener(this);
        offScreen = new BufferedImage(500,500, BufferedImage.TYPE_INT_RGB);

        add(buttonPanel, BorderLayout.EAST);

        health=battlePlayer.endur;
        mana=battlePlayer.intel*5;
    }

    public void paint (Graphics g)
    {
        super.paint(g);        
    }

    public void actionPerformed(ActionEvent thisEvent)
    {
        Object source = thisEvent.getSource();
        if (source==attackButton)
        {
            playerAttack();
        }
        if (source==defendButton)
        {
            playerDefend();
        }
        if (source==hPotionButton)
        {
            useHPotion();
        }
        if (source==mPotionButton)
        {
            useMPotion();
        }
        if (source==fireButton)
        {
            playerFire();
        }
        if (source==iceButton)
        {
            playerIce();
        }

        //now have if statements seeing finding out where the action occured

    }

    //when someone presses the mouse button
    public void mousePressed(MouseEvent e)
    {      }

    //when someone releases the mouse button
    public void mouseReleased(MouseEvent e)
    {      }

    // when the mouse enters the applet
    public void mouseEntered(MouseEvent e)
    {      }

    //when the mouse leaves the applet
    public void mouseExited(MouseEvent e)
    {  }

    //when the mouse button is clicked
    public void mouseClicked(MouseEvent e)
    {}

    //the mouse button is pressed and the mouse makes a significantly large movement
    public void mouseDragged(MouseEvent e)
    {}

    //the mouse makes a significantly large movement
    public void mouseMoved(MouseEvent e)
    {
        int x=e.getX();
        int y=e.getY();
    }

    public Battle (String monsterName){
        if (monsterName.equals("Rat")){
            monsterPic=1;
            myMonster=battleMonsters.createRat();
        }
        if (monsterName.equals("Goblin")){
            monsterPic=2;
            myMonster=battleMonsters.createGoblin();
            //monsterPic.equals("Goblin");
        }
        if (monsterName.equals("Octopus")){
            monsterPic=3;
            myMonster=battleMonsters.createOctopus();
            //monsterPic.equals("Octopus");
        }
        if (monsterName.equals("Dragon")){
            monsterPic=4;
            myMonster=battleMonsters.createDragon();
            //monsterPic.equals("Dragon");
        }
        init();
        setSize(588,505);
        //setExtendedState (JFrame.MAXIMIZED_BOTH);  //this sets it to maximum size
        // or set the size you want p.setSize(520,520);
        setVisible(true);
        start();
    }
    //Makes the current player in adventure the same as battlePlayer.
    public void setPlayer(Player myPlayer){
        battlePlayer=myPlayer;
    }

    public void playerMove(){
        playerTurn=true;  
    }

    public void enemyMove() {
        Graphics g=getGraphics();
        if (playerTurn==false) {
            //We'll keep it simple and make it just do the amount of damage the enemy's strength is every time.
            if (myMonster.level<=2){
                health=health-myMonster.enemStr;
            }
            if (myMonster.level>2) {
                myMonster.enemHealth=myMonster.enemHealth+10;
                health=health-myMonster.enemStr;}
        }
        playerTurn=true;
    }

    public void playerWin() {
        if (myMonster.enemHealth<=0) {
            //Recieves xp from the monster you fought
            battlePlayer.xp=battlePlayer.xp+myMonster.xpGiven;
            timesDefend=0;
            setVisible(false);
            dispose();
            stop();
        }
    }

    public void playerLose() { 
        if (health<=0){
            battlePlayer.xp=0;
            timesDefend=0;
            setVisible(false);
            dispose();
            stop();
        }
    }

    //Here is where the player's attacks goes.
    public void playerAttack() {
        if (playerTurn==true) {
            myMonster.enemHealth=myMonster.enemHealth-battlePlayer.str;
        }
        playerTurn=false;
        enemyMove();
    }

    public void playerDefend() {
        if (playerTurn == true) {
            if(timesDefend<2){
                myMonster.enemStr=myMonster.enemStr/2; 
                timesDefend++;
            }
        }
        playerTurn=false;
        enemyMove();
    }

    public void useHPotion() {
        //health +5
        if (playerTurn==true && numOfHPotions>0) {
            health=health+5;
            numOfHPotions=numOfHPotions-1;
        }
        playerTurn=false;
        enemyMove();
    }

    public void useMPotion() {
        //mana +5
        if (playerTurn==true && numOfMPotions>0) {
            mana=mana+5;
            numOfMPotions=numOfMPotions-1;
        }
        playerTurn=false;
        enemyMove();
    }

    public void playerIce() {
        if (playerTurn==true) {
            mana=mana-5;
            if (myMonster.weakness.equals("ice"))
                myMonster.enemHealth=-battlePlayer.intel*2;
            else {
                myMonster.enemHealth=-battlePlayer.intel;
            }
        }
        //If the enemy is weak against ice, it will do double the damage.
        playerTurn=false;
        enemyMove();
    }

    public void playerFire() {
        if (playerTurn==true) {
            mana=mana-5;
            //If the enemy is weak against fire, it will do double the damage.
            if (myMonster.weakness.equals("fire"))
                myMonster.enemHealth=-battlePlayer.intel*2;
            else{
                myMonster.enemHealth=-battlePlayer.intel;
            }
        }
        playerTurn=false;
        enemyMove();
    }

    public void drawBattleScreen(Graphics g) {
        try
        {
            Graphics offScreenGraphics=offScreen.getGraphics();
            img1 = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/BattleScreen.png"));
            offScreenGraphics.drawImage(img1,10,70,null);
        }
        catch (Exception e){}
    }

    public void drawBackPlayer(Graphics g) {
        try
        {
            Graphics offScreenGraphics=offScreen.getGraphics();
            img = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/BattleBackPlayer.png"));
            offScreenGraphics.drawImage(img,50,280,null);
        }
        catch (Exception e){}
    }

    public void drawMonster(Graphics g) {
        if (monsterPic==1){
            try
            {
                Graphics offScreenGraphics=offScreen.getGraphics();
                img = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/Rat.png"));
                offScreenGraphics.drawImage(img,250,monsterY,null);
            }
            catch (Exception e){}
        }
        else if (monsterPic==2){
            try
            {
                Graphics offScreenGraphics=offScreen.getGraphics();
                img = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/Goblin.png"));
                offScreenGraphics.drawImage(img,250,monsterY,null);
            }
            catch (Exception e){}
        }
        else if (monsterPic==3){
            try
            {
                Graphics offScreenGraphics=offScreen.getGraphics();
                img = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/Octopus.png"));
                offScreenGraphics.drawImage(img,250,monsterY,null);
            }
            catch (Exception e){}
        }
        else if (monsterPic==4){
            try
            {
                Graphics offScreenGraphics=offScreen.getGraphics();
                img = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/Dragon.png"));
                offScreenGraphics.drawImage(img,250,monsterY,null);
            }
            catch (Exception e){}

        }

    }

    public void update()
    {
        //starting crap
        Graphics g = getGraphics();
        Graphics offScreenGraphics=offScreen.getGraphics();
        offScreenGraphics.drawImage(img,0,70,null);

        //Drawing methods
        drawBattleScreen(offScreenGraphics);
        drawMonster(offScreenGraphics);
        drawBackPlayer(offScreenGraphics);

        //winning/losing
        playerWin();
        battlePlayer.levelUp();
        playerLose();

        setPlayer(battlePlayer);

        //TEXT INFO
        offScreenGraphics.setColor(Color.white);
        //First Row
        offScreenGraphics.drawString("Player Lvl: " + battlePlayer.level, 30, 410);
        offScreenGraphics.drawString("Player Mana: "+ mana, 205,410);
        offScreenGraphics.drawString("Enemy Health: "+ myMonster.enemHealth, 395,410);
        //Second Row
        offScreenGraphics.drawString("Player exp: "+ battlePlayer.xp, 30,435);
        offScreenGraphics.drawString("Player Health: "+ health, 205,435);
        //Third Row
        offScreenGraphics.drawString("Player Str: "+ battlePlayer.str, 30,460);
        offScreenGraphics.drawString("Player Potions: "+ numOfHPotions, 205,460);
        //Fourth Row
        offScreenGraphics.drawString("Player Int: "+ battlePlayer.intel, 30,485);
        offScreenGraphics.drawString("Player Elixers: "+ numOfMPotions, 205,485);
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
