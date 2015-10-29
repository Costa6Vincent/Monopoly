/*Vincent Costa, Jessica Jacinto, Tyler Cook
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.project;

import java.io.*;
import java.awt.*;
import java.io.File;
import javax.swing.*;
import java.awt.event.*;
import java.util.Locale;
import javax.sound.sampled.*;

public final class MonopolyProject extends JFrame implements Runnable {
    
    static final int numRows = 11;
    static final int numColumns = 11;
    static final int YTITLE = 30;
    static final int XBORDER = 30;
    static final int YBORDER = 30;
    static final int WINDOW_BORDER = 8;
    static final int WINDOW_WIDTH = 2*(WINDOW_BORDER + XBORDER) + 900;
    static final int WINDOW_HEIGHT = YTITLE + WINDOW_BORDER + 2 * YBORDER + 900;
    
    Image image;
    Graphics2D g;

    static Dimension dim;

    boolean animateFirstTime = true;
    
    public static int FPS=60;
    
    Image image1=null;
    
    boolean turnAnimation=false;
    int xAnim;
    int yAnim;

    
    Font font= new Font("Times_New_Roman",Font.BOLD,25);
    Font font2= new Font("Kabel",Font.BOLD,75);
    Font font3= new Font("BrushScriptMT",Font.BOLD,30);
    Image Board=Toolkit.getDefaultToolkit().getImage("./Pictures/BoardPieces/Board.GIF");
   
    int xsize = -1;
    int ysize = -1;
    
    Time time;
    boolean startMenu=false;
    boolean startMenuAnim;
    int xPosStart;
    
    String StartGameS="Start Game";
    String SettingsS="Settings";
    String HelpS="Help";
    String TutorialS="Tutorial";
    String ExitS="Exit";
    
    int diceRow1=5;
    int diceColumn1=4;
    int diceRow2=5;
    int diceColumn2=6;
    double sRotation;
    double sScale;
    
    double mRotation;
    double mScale;
    
    int turnCount;
    
    int purchaseRow=6;
    int purchaseCol=4;
    
    int timeCount;
    
    int currentPlayer;
    
    boolean diceRoll;
    boolean decision;
    
    boolean StartGameH;
    boolean SettingsH;
    boolean HelpH;
    boolean TutorialH;
    boolean ExitH;
    
    
    
    
    static boolean gameStart;
    
    static Property property[][];
    static final int numPlayers=4;
    static Player players[]=new Player[numPlayers];
    static Dice dice;
    static Dice dice2;
    
    public static int timerTick;
    double fps=1.0/FPS;
    static MonopolyProject frame1;
    public static void main(String[] args) {

        frame1 = new MonopolyProject();
        frame1.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        //dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }

    public MonopolyProject() 
    {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (MouseEvent.BUTTON3 == e.getButton()){
                    reset();
                }
                
                if (MouseEvent.BUTTON1 == e.getButton()){
                    if(!gameStart)
                    {
                        if(StartGameH)
                        {
//                            gameStart=true;
//                            startMenu=false;
//                            StartGameH=false;
                            startMenuAnim=true;
                        }
                        if(ExitH)
                        {
                            System.exit(0);
                        }
                    }
                    int numberOfSquaresNeededToMove=0;
                    int xdelta = getWidth2()/numColumns*2;
                    int ydelta = getHeight2()/numRows*2;
                    int xpos = e.getX() - getX(0);
                    int ypos = e.getY() - getY(0);
                    int column = xpos/(xdelta/2);
                    int row = ypos/(ydelta/2);
                    if(diceRoll)
                    {
                        if((diceRow1==row&&diceColumn1==column)||(diceRow2==row&&diceColumn2==column))
                        {
                            dice=new Dice();
                            dice2=new Dice();
                            numberOfSquaresNeededToMove=Dice.addSides();
                            while(numberOfSquaresNeededToMove>=1)
                            {

                                if(players[currentPlayer].getX()<numColumns-1&&players[currentPlayer].getY()==0)
                                {
                                    players[currentPlayer].setX(players[currentPlayer].getX()+1);
                                    numberOfSquaresNeededToMove--;
                                }
                                else if(players[currentPlayer].getX()==numColumns-1&&players[currentPlayer].getY()<=numRows-2)
                                {

                                    players[currentPlayer].setY(players[currentPlayer].getY()+1);
                                    numberOfSquaresNeededToMove--;
                                }
                                else if(players[currentPlayer].getX()>=1&&players[currentPlayer].getY()<=numRows-1)
                                {
                                    players[currentPlayer].setX(players[currentPlayer].getX()-1);
                                    numberOfSquaresNeededToMove--;
                                }
                                else if(players[currentPlayer].getX()==0&&players[currentPlayer].getY()>=0)
                                {
                                    players[currentPlayer].setY(players[currentPlayer].getY()-1);
                                    numberOfSquaresNeededToMove--;
                                }
                                else
                                    numberOfSquaresNeededToMove--;
                            }
                            diceRoll=false;
                            decision=true;
                        }
                    }
                    else if(decision)
                    {
                        if(purchaseRow==row&&(purchaseCol==column||purchaseCol+1==column||purchaseCol+2==column))
                        {
                            if(players[currentPlayer].getMoney()>=property[players[currentPlayer].getY()][players[currentPlayer].getX()].getCost()&&property[players[currentPlayer].getY()][players[currentPlayer].getX()].getThePlayer()==null)
                            {
                                    property[players[currentPlayer].getY()][players[currentPlayer].getX()].addPlayer(players[currentPlayer]);
                                    players[currentPlayer].setMoney(players[currentPlayer].getMoney()-property[players[currentPlayer].getY()][players[currentPlayer].getX()].getCost());
                            }
                        }   
                        players[currentPlayer].setIsTurn(false);
                        currentPlayer++;
                        if(currentPlayer>=numPlayers)
                            currentPlayer=0;
                        players[currentPlayer].setIsTurn(true);
                        decision=false;
                        diceRoll=true;
                        turnCount++;
                        xAnim=0;
                        yAnim=0;
                        turnAnimation=true;
                        
                    }
                    
                        
                    
                }
                
                repaint();
            }
        });

    addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseMoved(MouseEvent e) {
          
          if(!gameStart)
          {
                int xdelta = getWidth2()/numColumns*2;
                int ydelta = getHeight2()/numRows*2;
                int xpos = e.getX() - getX(0);
                int ypos = getYNormal(e.getY() - getY(0));
                int column = xpos/(xdelta/2);
                int row = ypos/(ydelta/2);

                if(xpos>getWidth2()/2-XBORDER&&xpos<getWidth2()/2+StartGameS.length()*25
                &&ypos<453&&ypos>413)
                    StartGameH=true;
                else
                    StartGameH=false;
                if(xpos>getWidth2()/2-XBORDER&&xpos<getWidth2()/2+SettingsS.length()*25
                &&ypos<407&&ypos>362)
                    SettingsH=true;
                else
                    SettingsH=false;
                if(xpos>getWidth2()/2-XBORDER&&xpos<getWidth2()/2+HelpS.length()*25
                &&ypos<344&&ypos>302)
                    HelpH=true;
                else
                    HelpH=false;
                if(xpos>getWidth2()/2-XBORDER&&xpos<getWidth2()/2+TutorialS.length()*25
                &&ypos<283&&ypos>249)
                    TutorialH=true;
                else
                    TutorialH=false;
                if(xpos>getWidth2()/2-XBORDER&&xpos<getWidth2()/2+ExitS.length()*25
                &&ypos<233&&ypos>190)
                    ExitH=true;
                else
                    ExitH=false;
                //System.out.println(getYNormal(e.getY() - getY(0)));
                g.drawOval(getWidth2()/2, getYNormal(e.getY() - getY(0)), 50, 50);
          }
        
        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.VK_UP == e.getKeyCode()){
                    
                }
                if(KeyEvent.VK_SPACE==e.getKeyCode()){
                    
                }
                if(KeyEvent.VK_Q==e.getKeyCode())
                {
                    
                }
                repaint();
            }
        });
        init();
        start();
    }
    
    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() 
    {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() 
    {
        
    }
////////////////////////////////////////////////////////////////////////////
    @Override
    public void paint(Graphics gOld) {
        if (image == null || xsize != getSize().width || ysize != getSize().height) 
        {
            xsize = getSize().width;
            ysize = getSize().height;
            image = createImage(xsize, ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        }

//fill background
        g.setColor(Color.cyan);
        
        g.fillRect(0, 0, xsize, ysize);

        int x[] = {getX(0), getX(getWidth2()), getX(getWidth2()), getX(0), getX(0)};
        int y[] = {getY(0), getY(0), getY(getHeight2()), getY(getHeight2()), getY(0)};
//fill border
        g.setColor(Color.white);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.red);
        g.drawPolyline(x, y, 5);

        
        
        if (animateFirstTime) 
        {
            gOld.drawImage(image, 0, 0, null);
            return;
        }
        if(gameStart)
        {

            
            g.setColor(Color.yellow);
    //horizontal lines
            for (int zi=1;zi<numRows;zi++)
            {
                g.setColor(Color.black);
                g.drawLine(getX(0) ,getY(0)+zi*getHeight2()/numRows ,
                getX(getWidth2()) ,getY(0)+zi*getHeight2()/numRows );
            }
    //vertical lines
            for (int zi=1;zi<numColumns;zi++)
            {
                g.setColor(Color.black);
                g.drawLine(getX(0)+zi*getWidth2()/numColumns ,getY(0) ,
                getX(0)+zi*getWidth2()/numColumns,getY(getHeight2())  );
            }

            

    //Display the objects of the board
            g.setColor(Color.gray);
            
            //g.fillRect(getX(0)+getWidth2()/numColumns+1, getY(0)+getHeight2()/numRows+1,(getWidth2()*(numColumns-2))/numColumns, (getHeight2()*(numRows-2))/numRows);
            g.drawImage(Board, XBORDER, YBORDER, getWidth2()+XBORDER, getHeight2()+YBORDER,this);
            for (int zrow=0;zrow<numRows;zrow++)
            {
                for (int zcolumn=0;zcolumn<numColumns;zcolumn++)
                {
                    if(property[zrow][zcolumn]!=null)
                    {
                        int xpos=getX(0)+zcolumn*getWidth2()/numColumns;
                        int ypos=getY(0)+zrow*getHeight2()/numRows;
                        int length=getWidth2()/numColumns;
                        int height=getHeight2()/numRows;
                        property[zrow][zcolumn].draw(g,xpos,
                        ypos,length,
                        height,this);

                        g.setColor(Color.black);
                        g.setFont(new Font("Impact",Font.ITALIC,20));
                        String text2 = property[zrow][zcolumn].getCost()+"";
                        g.drawString(text2, xpos+length/2, ypos+length); 

//                        if(property[zrow][zcolumn].getThePlayer()!=null)
//                        {
//                            text2 = property[zrow][zcolumn].getThePlayer().getName();
//                            g.drawString(text2, xpos, ypos+height/2); 
//                        }
//                        else if(property[zrow][zcolumn].getThePlayer()==null)
//                        {
//                            text2 = property[zrow][zcolumn].getName();
//                            g.drawString(text2, xpos, ypos+height/4); 
//                        }

                    }   
                }    
            }
            if(decision)
                g.setColor(Color.red);
            else
            {
                Color color2 = new Color(Color.red.getRed()/255,Color.red.getGreen()/255,Color.red.getBlue()/255,.3f);
                g.setColor(color2);
            }
            if(decision)
            {
                g.fillRect(getX(0)+(purchaseCol)*getWidth2()/numColumns+1, getY(0)+purchaseRow*getHeight2()/numRows, (getWidth2()/numColumns)*3, getHeight2()/numRows);

                g.setFont(new Font("Impact",Font.ITALIC,40));
                String text2 = "Purchase";
                g.setColor(Color.black);
                g.drawString(text2, getX(0)+(purchaseCol)*getWidth2()/numColumns+25, getY(0)+purchaseRow*getHeight2()/numRows+55); 
            }
            g.setFont(font);
            String text=null;
            if(time.getHours()!=0&&time.getMinutes()!=0)
                text = ("Hours:"+time.getHours()+" Minutes: "+time.getMinutes()+" Seconds: "+time.getSeconds());
            if(time.getHours()==0&&time.getMinutes()==0&&time.getSeconds()!=0)
                text = ("Seconds: "+time.getSeconds());
            if(time.getHours()==0&&time.getMinutes()!=0)
                text = (" Minutes: "+time.getMinutes()+" Seconds: "+time.getSeconds());
            if(text!=null)
                g.drawString(text,50,60);

            for(int index=0;index<numPlayers;index++)
            {
                int xpos=getX(0)+players[index].getX()*getWidth2()/numColumns+index*10;
                int ypos=getY(0)+players[index].getY()*getHeight2()/numRows;
                int length=getWidth2()/numColumns*3/4;
                int height=getHeight2()/numRows*3/4;
                players[index].draw(g, xpos, ypos, length, height, this);
                g.setColor(Color.black);
                String text2 = players[index].getMoney()+"";
                g.drawString(text2, xpos+length/2, ypos+length*3/4); 
            }

            dice.draw(g,getX(0)+diceColumn1*getWidth2()/numColumns, getY(0)+diceRow1*getHeight2()/numRows, getWidth2()/numColumns, getHeight2()/numRows,this);
            dice2.draw(g,getX(0)+diceColumn2*getWidth2()/numColumns, getY(0)+diceRow2*getHeight2()/numRows, getWidth2()/numColumns, getHeight2()/numRows,this);
        }
        if(turnAnimation)
        {
            for(int index=0;index<numPlayers;index++)
            {
                if(players[index].getIsTurn())
                {
                    g.setColor(Color.black);
                    g.setFont(new Font("Impact",Font.ITALIC,40));
                    g.drawString(players[index].getName()+"'s Turn",-50+xAnim,-50+yAnim);
                    currentPlayer=index;
                }
            }
        }
        if(!gameStart)
        {
            g.setColor(Color.white);
            g.fillRect(0,0,getWidth2()*50,getHeight2()*50);
            drawSplashScreen(getWidth2()/2,getHeight2()/2,360-sRotation,.1+sScale,.05+sScale);
        }
        if(startMenu)
        {
            drawSplashScreenMenu(getWidth2()/2,getHeight2()*8/12,0,.1+mScale,.05+mScale);
        }
        
        
        gOld.drawImage(image, 0, 0, null);
    }
    
    public void drawSplashScreen(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        
        g.setColor(Color.red);
        g.fillRect(-150, -50, 360, 100);
        g.setColor(Color.black);
        Stroke nice = g.getStroke();
        g.setStroke(new BasicStroke(3.0f));
        g.drawRect(-150, -50, 360, 100);
        g.setStroke(nice);
        g.setFont(font2);
        String red = "Monopoly";
        nice = g.getStroke();
        g.setStroke(new BasicStroke(4.0f));
        g.setColor(Color.black);
        g.drawString(red, -150,  25);
        g.setStroke(nice);
        g.setFont(font2);
        g.setColor(Color.white);
        g.drawString(red, -150+3,  25-3);
        
        red="War";
        g.setFont(font3);
        g.drawString(red, 90,  45);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    public void drawSplashScreenMenu(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        Color black=Color.black;
        Color selected=Color.yellow;
        
        
        g.setColor(black);
        String nice=StartGameS;
        g.setFont(font);
        if(StartGameH)
            g.setColor(selected);
        else
            g.setColor(black);
        g.drawString(nice, 0+xPosStart,  (int)(yscale*yscale));
        nice=SettingsS;
        if(SettingsH)
            g.setColor(selected);
        else
            g.setColor(black);
        g.drawString(nice, 0,  (int)(yscale*yscale)*7);
        nice=HelpS;
        if(HelpH)
            g.setColor(selected);
        else
            g.setColor(black);
        g.drawString(nice, 0,  (int)(yscale*yscale)*14);
        nice=TutorialS;
        if(TutorialH)
            g.setColor(selected);
        else
            g.setColor(black);
        g.drawString(nice, 0,  (int)(yscale*yscale)*21);
        nice=ExitS;
        if(ExitH)
            g.setColor(selected);
        else
            g.setColor(black);
        g.drawString(nice, 0,  (int)(yscale*yscale)*28);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    @Override
    public void run() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        while (true) {
            animate();
            repaint();
            double seconds = fps;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {
        
        property=new Property[numRows][numColumns];
        //top row
        
        Property.InitializeDataBase();
        Player.InitializeDataBase2();
        for(int index=0;index<numPlayers;index++)
        {
            players[index].setX(numColumns-1);
            players[index].setY(numRows-1);
        }
        
        dice=new Dice();
        dice2=new Dice();
        time =new Time();
        players[0].setIsTurn(true);
        diceRoll=true;
        turnCount=0;
        gameStart=false;
        timeCount=0;
        sRotation=0;
        sScale=0;
        startMenu=false;
        mRotation=0;
        mScale=0;
        turnAnimation=false;
        startMenuAnim=false;
        
        
        
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {
        
        if (animateFirstTime) 
        {
            if (xsize != getSize().width || ysize != getSize().height) 
            {
                xsize = getSize().width;
                ysize = getSize().height;
            }
            image1=Toolkit.getDefaultToolkit().getImage("b.GIF");
            
            reset();
            animateFirstTime = false;
            
            
        }
        
        
        if(!gameStart)
        {
            sScale+=.02;
            sRotation+=.2;
            if(sRotation>=20||sScale>=25)
            {
                startMenu=true;
                //timeCount++;
                sScale-=.02;
                sRotation-=.2;
            }
            if(timeCount%120==119)
            {
                startMenu=true;
            }
            if(startMenu)
            {
                mScale+=.02;
                mRotation+=.2;
                if(mRotation>=20||mScale>=15)
                {
                    startMenu=true;
                    //timeCount++;
                    mScale-=.02;
                    mRotation-=.2;
                    timeCount=0;
                }
            }
            if(startMenuAnim)
            {
                    xPosStart+=13;
                    if(xPosStart>=300)
                    {
                        gameStart=true;
                        startMenu=false;
                        StartGameH=false;
                        xPosStart=0;
                    }
            }
            return;
        }
        time.addTime();
        if(turnAnimation)
        {
            xAnim+=5;
            yAnim+=5;
            if(xAnim>getWidth2()/2-40&&yAnim>getHeight2()/2)
            {
                timeCount++;
            }
            if(xAnim>getWidth2()/2-35)
                xAnim-=5;
            if(yAnim>getHeight2()/2)
                yAnim-=5;
            
            if(timeCount%60==59)
            {
                turnAnimation=false;
                xAnim=0;
                yAnim=0;
            }
        }
        if(turnCount%5==4)
        {
            
        }
    }
    
    public void CalculateTax()
    {
        for(int index=0;index<numPlayers;index++)
        {
            for(int index2=0;index2<players[index].getNumProperties();index2++)
            {
                players[index].getPropertyOwnership();
            }
        }
    }
        
////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
/////////////////////////////////////////////////////////////////////////
    public int getX(int x) {
        return (x + XBORDER + WINDOW_BORDER);
    }
    public int getY(int y) {
        return (y + YBORDER + YTITLE );
    }
    public int getYNormal(int y) {
        return (-y + YBORDER + YTITLE + getHeight2());
    }
    public int getWidth2() {
        return (xsize - 2 * (XBORDER + WINDOW_BORDER));
    }
    public int getHeight2() {
        return (ysize - 2 * YBORDER - WINDOW_BORDER - YTITLE);
    }
}
class sound implements Runnable {
    Thread myThread;
    File soundFile;
    public boolean donePlaying = false;
    sound(String _name)
    {
        soundFile = new File(_name);
        myThread = new Thread(this);
        myThread.start();
    }
    @Override
    public void run()
    {
        try 
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = ais.getFormat();
            System.out.println("Format: " + format);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            try (SourceDataLine source = (SourceDataLine) AudioSystem.getLine(info)) 
            {
                source.open(format);
                source.start();
                int read = 0;
                byte[] audioData = new byte[999999];
                while (read > -1){
                    read = ais.read(audioData,0,audioData.length);
                    if (read >= 0) {
                        source.write(audioData,0,read);
                    }
                }
                donePlaying = true;
                source.drain();
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException exc) {
            System.out.println("error: " + exc.getMessage());
        }
    }
}