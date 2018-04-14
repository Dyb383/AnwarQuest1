import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.GridBagLayout.*;

/*
 * Shop Class. Builds shop.
 */

public class Shop extends JFrame implements ActionListener,Runnable {

	BufferedImage offScreen;
    Player shopPlayer=new Player();
    Image img;
    Image img1;
    Button str = new Button ("New Sword: $200");
    Button armor = new Button ("New Armour: $200");
    Button intel = new Button ("Spell Books: $200");
    Button quest = new Button ("Quest");
    Button rest = new Button ("Rest");
    
	public void init() {                
		//setting the layout of the battle window
		Container screen = getContentPane();

	    Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(str, BorderLayout.EAST);
        buttonPanel.add(armor, BorderLayout.EAST);
        buttonPanel.add(intel, BorderLayout.EAST);
        buttonPanel.add(quest, BorderLayout.EAST);
        buttonPanel.add(rest, BorderLayout.EAST);
        offScreen = new BufferedImage(500,500, BufferedImage.TYPE_INT_RGB);
        add(buttonPanel, BorderLayout.EAST);
        str.addActionListener(this);
        armor.addActionListener(this);
        intel.addActionListener(this);
        quest.addActionListener(this);
        rest.addActionListener(this);
	}
	
	public Shop(Player myPlayer) {
		setPlayer(myPlayer);
        init();
        setSize(600,505);
        //setExtendedState (JFrame.MAXIMIZED_BOTH);  //this sets it to maximum size
        // or set the size you want p.setSize(520,520);
        setVisible(true);
        start();
	}
	
	public void actionPerformed(ActionEvent thisEvent) {
		Object source = thisEvent.getSource();
		if (source==str) {
            strButton();
        }
        if (source==armor) {
            armorButton();
        }
        if (source==intel) {
            intelButton();
        }
        if (source==quest) {
            quest();
        }
        if (source==rest) {
            rest();
        }
        //now have if statements seeing finding out where the action occured
    }
	
    private void rest() {
		// TODO Auto-generated method stub
		shopPlayer.endur = shopPlayer.maxHP;
		//add mana replenish after mana is persistant
	}

	private void quest() {
		// TODO Auto-generated method stub
		//method needs to print out a quest for the player
	}

	private void intelButton() {
		// TODO Auto-generated method stub
		if (shopPlayer.gold>=200){
			shopPlayer.gold=shopPlayer.gold-200;
			shopPlayer.intel=shopPlayer.intel*2;
		}
		else {
			//print you dont have enough money
		}
	}

	private void armorButton() {
		// TODO Auto-generated method stub
		if (shopPlayer.gold>=200){
			shopPlayer.gold=shopPlayer.gold-200;
			shopPlayer.endur=shopPlayer.endur*2;
		}
		else {
			//print you dont have enough money
		}
	}

	private void strButton() {
		// TODO Auto-generated method stub
		if (shopPlayer.gold>=200){
			shopPlayer.gold=shopPlayer.gold-200;
			shopPlayer.str=shopPlayer.str*2;
		}
		else {
			//print you dont have enough money
		}
	}

	public void setPlayer(Player myPlayer) {
        shopPlayer=myPlayer;
    }
	 
	public void paint (Graphics g) {
	    super.paint(g);        
	}
	
	public void shopStop() {
            setVisible(false);
            dispose();
            stop();
    }
	
	
    public void drawShopScreen(Graphics g) {
        try
        {
            Graphics offScreenGraphics=offScreen.getGraphics();
            img = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/shopWIP.png"));
            offScreenGraphics.drawImage(img,0,0,null);
        }
        catch (Exception e){}
    }

	public void update()
    {
        //starting crap
        Graphics g = getGraphics();
        Graphics offScreenGraphics=offScreen.getGraphics();
        offScreenGraphics.drawImage(img,0,0,null);

        //Drawing methods
        setPlayer(shopPlayer);
        
        drawShopScreen(g);
        //shopStop();

        //TEXT INFO
        offScreenGraphics.setColor(Color.white);
        //First Row
        offScreenGraphics.drawString("Player Lvl: " + shopPlayer.level, 30, 410);
        offScreenGraphics.drawString("Player Gold: " + shopPlayer.gold, 205,410);
        //Second Row
        offScreenGraphics.drawString("Player exp: " + shopPlayer.xp, 30,435);
        offScreenGraphics.drawString("Player Health: " + shopPlayer.endur, 205,435);
        //Third Row
        offScreenGraphics.drawString("Player Str: " + shopPlayer.str, 30,460);
        offScreenGraphics.drawString("Player Mana: " + shopPlayer.intel*5, 205, 460);
        //Fourth Row
        offScreenGraphics.drawString("Player Int: " + shopPlayer.intel, 30,485);
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