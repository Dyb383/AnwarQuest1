

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;


public class HubWorld extends JFrame implements ActionListener, Runnable {
    Image img;
    Image img1;
    BufferedImage offScreen;
    boolean playerTurn = true;
    Player hubPlayer = new Player();
    int numOfHPotions = 5;
    int numOfMPotions = 5;
    //Gets stats from player to the battle
    int health;
    int mana;
    //Finds monster based on monster name
    JButton healButton = new JButton("HEAL");
    JButton questButton = new JButton("QUEST");
    JButton leaveButton = new JButton("LEAVE");
    JPanel buttonPanel = new JPanel();

    public void init() {
        //setting the layout of the battle window
        Container screen = getContentPane();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(healButton);
        buttonPanel.add(questButton);
        buttonPanel.add(leaveButton);
        healButton.addActionListener(this);
        questButton.addActionListener(this);
        leaveButton.addActionListener(this);
        offScreen = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);

        add(buttonPanel, BorderLayout.EAST);

        health = hubPlayer.currentHP;
        mana = hubPlayer.currentMana;
    }

    public void paint (Graphics g)
    {
        super.paint(g);
    }

    public void actionPerformed(ActionEvent thisEvent)
    {
        Object source = thisEvent.getSource();
        if (source==healButton)
        {
            playerHeal();
        }
        if (source==questButton)
        {
            playerQuest(hubPlayer);
        }
        if (source==leaveButton)
        {
            setVisible(false);
            dispose();
            stop();
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

    public void setPlayer(Player myPlayer) {
        hubPlayer=myPlayer;
    }

    public HubWorld(Player myPlayer) {
        setPlayer(myPlayer);
        init();
        setSize(600,505);
        //setExtendedState (JFrame.MAXIMIZED_BOTH);  //this sets it to maximum size
        // or set the size you want p.setSize(520,520);
        setVisible(true);
        start();
    }
    //Makes the current player in adventure the same as battlePlayer.

    private void playerHeal(){
        hubPlayer.currentHP = hubPlayer.maxHP;
        hubPlayer.currentMana = hubPlayer.maxMana;
    }

    private void playerQuest(Player myPlayer){
        final JFrame parent = new JFrame();
        if (myPlayer.quest == 2){
            String quest1 = JOptionPane.showInputDialog(parent, "DAMN SON WHERE'D YOU FIND THIS??", null);
            myPlayer.quest = 3;
        }
        if (myPlayer.quest == 1){
            String quest1 = JOptionPane.showInputDialog(parent, "Well, well, well, maybe you are not so useless after all! \n" +
                    "I need you to go and defeat an evil wizard who goes by the name Anwar. If you do this you would be the hero of this whole" +
                    " 500x500 pixel world!", null);
            myPlayer.quest = 2;
        }
        if(myPlayer.quest == 0){
            String quest = JOptionPane.showInputDialog(parent, "Hello new adventurer! I have a task that I need you to take care of. \n" +
                    "Go win two battles and come back to me. Do this and you will have proven your worth!", null);
            myPlayer.quest = 1;
        }
        else{
            setVisible(false);
            dispose();
            stop();
        }

    }


    public void drawHubScreen(Graphics g) {
        try
        {
            Graphics offScreenGraphics=offScreen.getGraphics();
            img1 = javax.imageio.ImageIO.read(this.getClass().getResource("PICS/hubworld.png"));
            offScreenGraphics.drawImage(img1,10,70,null);
        }
        catch (Exception e){}
    }



    public void update()
    {
        //starting crap
        Graphics g = getGraphics();
        Graphics offScreenGraphics=offScreen.getGraphics();
        offScreenGraphics.drawImage(img,0,70,null);

        //Drawing methods
        setPlayer(hubPlayer);
        drawHubScreen(g);


        //TEXT INFO
        offScreenGraphics.setColor(Color.green);
        //First Row
        offScreenGraphics.drawString("Player Lvl: " + hubPlayer.level, 30, 410);
        offScreenGraphics.drawString("Player Mana: "+ mana, 205,410);
        //Second Row
        offScreenGraphics.drawString("Player exp: "+ hubPlayer.xp, 30,435);
        offScreenGraphics.drawString("Player Health: "+ health, 205,435);
        //Third Row
        offScreenGraphics.drawString("Player Str: "+ hubPlayer.str, 30,460);
        offScreenGraphics.drawString("Player Potions: "+ numOfHPotions, 205,460);
        //Fourth Row
        offScreenGraphics.drawString("Player Int: "+ hubPlayer.intel, 30,485);
        offScreenGraphics.drawString("Player Elixers: "+ numOfMPotions, 205,485);
        //Ending crap
        g.drawImage(offScreen,0,0,this);
    }

    //   /*********************************************************************************************/
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





