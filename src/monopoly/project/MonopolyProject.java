/*Vincent Costa, Jessica Jacinto, Tyler Cook
 * A Standard mompoly game with armies along with a lord of the rings skin
 * 
 * 
 */
package monopoly.project;

import java.io.*;
import java.awt.*;
import java.io.File;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import javax.sound.sampled.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static monopoly.project.MonopolyProject.YTITLE;
import static monopoly.project.MonopolyProject.boardAlloc;
import static monopoly.project.MonopolyProject.currentPlayer;
import static monopoly.project.MonopolyProject.getHeight2;
import static monopoly.project.MonopolyProject.getWidth2;
import static monopoly.project.MonopolyProject.getX;
import static monopoly.project.MonopolyProject.getY;
import static monopoly.project.MonopolyProject.numColumns;
import static monopoly.project.MonopolyProject.numPlayers;
import static monopoly.project.MonopolyProject.numRows;
import static monopoly.project.MonopolyProject.players;
import network.ClientHandler;
import network.ServerHandler;

public class MonopolyProject extends JFrame implements Runnable {
    
    public static final int numRows = 11;
    public static final int numColumns = 11;
    public static final int YTITLE = 30;
    public static final int XBORDER = 0;
    public static final int YBORDER = 0;
    public static final int WINDOW_BORDER = 8;
    public static final int boardAlloc=1200;
    public static final int windowAlloc=500;
    public static final int WINDOW_WIDTH = 2*(WINDOW_BORDER + XBORDER) + boardAlloc+windowAlloc;
    public static final int WINDOW_HEIGHT = YTITLE + WINDOW_BORDER + 2 * YBORDER + 900;
    
    public static Image image;
    public static Image image2;
    Graphics2D g;
    
    public static int numSongs=5;
    sound music[]=new sound[numSongs];
    
    public static sound diceroll=null;
    public static boolean dicerollN=false;
    
    public static sound purchasesound=null;
    public static boolean purchaseN;
    
    public static sound loseSound=null;
    public static boolean loseN;
    
    public static sound warSound=null;
    public static boolean warH;
    
    public static sound upgradeArmySound=null;
    public static boolean upgradeArmyH;
    
    static Dimension dim;

    public static PanAndZoom pen = new PanAndZoom();
    
    public static boolean animateFirstTime = true;
    
    public static int FPS=60;
    public static int turns=0;
    
    public static Image image1=null;
    
    public static boolean turnAnimation=false;
    public static int xAnim;
    public static int yAnim;

    public static Font font= new Font("Kabel",Font.BOLD,20);
    public static Font font2= new Font("Kabel",Font.BOLD,75);
    public static Font font3= new Font("BrushScriptMT",Font.BOLD,30);
    public static Image Board=Toolkit.getDefaultToolkit().getImage("./Pictures/BoardPieces/Board2.JPG");
    public static Image boardMiddle=Toolkit.getDefaultToolkit().getImage("./Pictures/BoardPieces/boardMiddle.JPG");
    
   
    public static int xsize = -1;
    public static int ysize = -1;
    
    int numClicks=0;
    
    public static Time time;
    public static boolean startMenu=false;
    public static boolean startMenuAnim;
    public static int xPosStart;
    
    public static String StartGameS="Start Game";
    public static String SettingsS="Settings";
    public static String HelpS="Help";
    public static String TutorialS="Tutorial";
    public static String ExitS="Exit";
    public static String AIS="Play AI";
    
    public static int diceRow1=5;
    public static int diceColumn1=4;
    public static int diceRow2=5;
    public static int diceColumn2=6;
    public static double sRotation;
    public static double sScale;
    public static double sDir;
    
    public static double mRotation;
    public static double mScale;
    
    public static int turnCount;
    
    public static int purchaseX;
    public static int purchaseY;
    public static int purchaseLength;
    public static int purchaseHeight;
    
    public static int payX;
    public static int payY;
    public static int payLength;
    public static int payHeight;
    
    public static int endTurnX;
    public static int endTurnY;
    public static int endTurnLength;
    public static int endTurnHeight;
    
    public static int upgradeX;
    public static int upgradeY;
    public static int upgradeLength;
    public static int upgradeHeight;
    
    public static int attackX;
    public static int attackY;
    public static int attackLength;
    public static int attackHeight;
    
    public static int upgradeArmyX;
    public static int upgradeArmyY;
    public static int upgradeArmyLength;
    public static int upgradeArmyHeight;
    
    public static int timeCount;
    
    public static int currentPlayer;
    
    public static boolean diceRoll;
    public static boolean decision;
    public static boolean payRent;
    public static boolean purchase;
    public static boolean upgrade;
    public static boolean attack;
    public static boolean upgradeArmy;
    public static boolean canUpgrade;
    
    public static boolean StartGameH;
    public static boolean SettingsH;
    public static boolean HelpH;
    public static boolean TutorialH;
    public static boolean ExitH;
    public static boolean AIH;
    public static boolean AIOn;
    public static boolean StringMove;
    public static boolean drawGoodCard;
    public static boolean drawBadCard;
    public static String ipAddress = new String();
    public static boolean large,small;
    public static boolean gameStart;
    public static boolean musicOn;
    public static boolean soundsOn;
    public static boolean settings;
    
    
    
    public static int MusicX;
    public static int MusicY;
    public static int MusicLength;
    public static int MusicHeight;
    public static int musicCounter;
    
    public static int SoundsX;
    public static int SoundsY;
    public static int SoundsLength;
    public static int SoundsHeight;
    public static int soundsCounter;
    
    
    
    public static Property property[][];
    public static final int numPlayers=4;
    public static Player players[]=new Player[numPlayers];
    public static Army army[]=new Army[numPlayers];
    public static Dice dice;
    public static Dice dice2;
    
    public static int timerTick;
    double fps=1.0/FPS;
    static MonopolyProject frame1;
    
    public static boolean gameStarted = false;
    public static boolean myTurn;
    public static int serverValue = 0;
    public static int clientValue = 0;
    public static boolean isConnecting = false;
    public static boolean isClient;
    public static boolean isServer;
    
    Image background =Toolkit.getDefaultToolkit().getImage("./Pictures/BoardPieces/scroll.JPG");
    
    //System.out.println(army);
    
    public static void main(String[] args) {

        
        frame1 = new MonopolyProject();
        frame1.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame1.setLocationRelativeTo(null);
        //frame1.setExtendedState(JFrame.MAXIMIZED_BOTH); 
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
                    
                    Logic.LeftClick.Click1(e);
                        
                   
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
          
          Logic.MouseMoved.Moved(e);
        
        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {   
                if (myTurn && gameStarted && e.getKeyCode() == KeyEvent.VK_1)
                {
				if (isClient)
                                {
                                    System.out.println("sending from client");
                                    clientValue++;
					ClientHandler.sendPieceMove(clientValue);
                                }
                                else if(isServer)
                                {
                                    System.out.println("sending from server");
                                    serverValue++;
					ServerHandler.sendPieceMove(serverValue);
                                }			                    
                }
                else if (myTurn && gameStarted && e.getKeyCode() == KeyEvent.VK_2)
                {
			if (isClient)
                                {
                                    System.out.println("sending from client");
                                    clientValue+=2;
					ClientHandler.sendPieceMove(clientValue);
                                }
				else if(isServer)
                                {
                                    System.out.println("sending from server");
                                    serverValue+=2;
					ServerHandler.sendPieceMove(serverValue);
                                }	
			                    
                }                
                else if (e.getKeyCode() == KeyEvent.VK_S)
                {
                    if (!isConnecting)
                    {
                        try
                        {
                    
                            isConnecting = true;
                            System.out.println("is connecting true");
                            ServerHandler.recieveConnect(5657);
                            System.out.println("after recieveConnect");
                            if (ServerHandler.connected)
                            {
                                isClient = false;
                                isServer = true;
                                myTurn = false;
                                gameStarted = true;
                                isConnecting = false;
                            }
                        }
                        catch (IOException ex)
                        {
                            System.out.println("Cannot host server: " + ex.getMessage());
                            isConnecting = false;
                        }                        
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_C)
                {
                    if (!isConnecting)
                    {
                    
                            try
                            {
                   
                                isConnecting = true;
                                ClientHandler.connect(ipAddress, 5657);
                                if (ClientHandler.connected)
                                {
                                    isClient = true;
                                    myTurn = true;
                                    gameStarted = true;
                                    isConnecting = false;
                                }
                            }
                            catch (IOException ex)
                            {
                                System.out.println("Cannot join server: " + ex.getMessage());
                                isConnecting = false;
                            }                    
                    }
                }                
                else
                {
                    if (!gameStarted && ipAddress.length() <= 13)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_0)
                        {
                            ipAddress += "0";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_1)
                        {
                            ipAddress += "1";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_2)
                        {
                            ipAddress += "2";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_3)
                        {
                            ipAddress += "3";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_4)
                        {
                            ipAddress += "4";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_5)
                        {
                            ipAddress += "=5";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_6)
                        {
                            ipAddress += "6";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_7)
                        {
                            ipAddress += "7";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_8)
                        {
                            ipAddress += "8";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_9)
                        {
                            ipAddress += "9";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_PERIOD)
                        {
                            ipAddress += ".";
                        }
                    }
                    
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                    {
                        if(ipAddress.length()>0)
                            ipAddress = ipAddress.substring(0, ipAddress.length()-1);
                    }
                }
                
                
                
                
                if (gameStarted || isConnecting)
                {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !isConnecting)
                    {
                        if (gameStarted)

                            if (isClient)
                            {
                                ClientHandler.sendDisconnect();
                                ClientHandler.disconnect();
                            }
                            else
                            {
                                ServerHandler.sendDisconnect();
                                ServerHandler.disconnect();
                            }
                        gameStarted = false;
                    }
                }
                if(gameStart)
                {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE )
                    {
                        gameStart=false;
                        startMenu=true;
                        StartGameH=true;
                        xPosStart=0;
                        //startMenuAnim=true;
                    }
                }
                       
                
                
                

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
    public void paint(Graphics gOld) 
    {
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
        //if(gameStart)
        {


            g.setColor(Color.yellow);
            //if(gameStart)
            {
                int menuxpos=boardAlloc+YTITLE;
                int menuypos=YTITLE+1;
                int menuLength=windowAlloc;
                int menuHeight=getHeight2()-2;
                g.setColor(Color.black);
                g.drawImage(background,menuxpos, menuypos, menuLength, menuHeight,this);
                g.setColor(players[currentPlayer].getColor());
                Stroke nice = g.getStroke();
                g.setStroke(new BasicStroke(10.0f));
                g.drawRect(menuxpos, menuypos+6, menuLength-28, menuHeight-14);
                g.setStroke(nice);
            }

    //Display the objects of the board
            g.setColor(Color.gray);

            g.drawImage(boardMiddle,getX(0)+boardAlloc/numColumns-5, getY(0)+getHeight2()/numRows+1,(boardAlloc*(numColumns-2))/numColumns+5, (getHeight2()*(numRows-2))/numRows,this);
            //g.drawImage(Board, YTITLE, YTITLE*2, boardAlloc+XBORDER, getHeight2()-YTITLE,this);
            for (int zrow=0;zrow<numRows;zrow++)
            {
                for (int zcolumn=0;zcolumn<numColumns;zcolumn++)
                {
                    if(property[zrow][zcolumn]!=null)
                    {

                        int length=boardAlloc/numColumns*4/3;
                        int height=getHeight2()/numRows;
                        int xpos=getX(0)+zcolumn*boardAlloc/numColumns;
                        int ypos=getY(0)+zrow*getHeight2()/numRows;

                        property[zrow][zcolumn].draw(g,xpos,ypos,length,height,this);

                        if(property[zrow][zcolumn].getThePlayer()!=null)
                        {
                            g.setColor(property[zrow][zcolumn].getThePlayer().getColor());
                            for(int index=0;index<property[zrow][zcolumn].getUpgrade();index++)
                            {
                                g.fillOval(xpos+length/8+index*20, ypos+height/8, length/5, height/5);
                            }
                        }

                    }   
                }    
            }
            GUIs.PlayerInfoWindow.drawInfoWindow(g);
            g.setFont(font);
            String text=null;
            g.setColor(Color.red);
            if(time.getHours()!=0&&time.getMinutes()!=0)
                text = ("Hours:"+time.getHours()+" Minutes: "+time.getMinutes()+" Seconds: "+time.getSeconds());
            if(time.getHours()==0&&time.getMinutes()==0&&time.getSeconds()!=0)
                text = ("Seconds: "+time.getSeconds());
            if(time.getHours()==0&&time.getMinutes()!=0)
                text = (" Minutes: "+time.getMinutes()+" Seconds: "+time.getSeconds());
            if(text!=null)
                g.drawString(text,boardAlloc/2-50,getHeight2()/2-50);

            String text2=null;
            for(int index=0;index<numPlayers;index++)
            {
                int xpos=getX(0)+players[index].getX()*boardAlloc/numColumns+index*10;
                int ypos=getY(0)+players[index].getY()*getHeight2()/numRows-YTITLE;
                int length=getWidth2()/numColumns*3/4;
                int height=getHeight2()/numRows*3/4;
                players[index].drawonBoard(g, xpos, ypos+height/2, length, height, this);
                g.setColor(Color.black);
                text2 = players[index].getMoney()+"";
                g.drawString(text2, xpos+length/2, ypos+length*3/4); 
                if(index==currentPlayer)
                {
                    Stroke nice = g.getStroke();
                    float size=7.0f;
                    g.setStroke(new BasicStroke(size));
                    g.setColor(players[index].getColor());
                    g.drawOval(xpos-length/4,ypos-height/4,length+length/2,height*2);
                    g.setStroke(nice);
                }
                
            }
            

            dice.draw(g,getX(0)+diceColumn1*boardAlloc/numColumns, getY(0)+diceRow1*getHeight2()/numRows, boardAlloc/numColumns, getHeight2()/numRows,this);
            dice2.draw(g,getX(0)+diceColumn2*boardAlloc/numColumns, getY(0)+diceRow2*getHeight2()/numRows, boardAlloc/numColumns, getHeight2()/numRows,this);
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
        GUIs.MainMenu.drawMainMenu(g,0,0+xPosStart,0,1,1, this);

//        for(int index=0;index<players[currentPlayer].getNumProperty();index++)
//        {
//            g.setColor(Color.yellow);
//            g.fillRect(boardAlloc,boardAlloc+50,500,500);
//        }
            
//add or modify.   
//        if (!gameStarted)
//        {
//            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
//            g.setColor(Color.black);
//            g.drawString("Not Connected",100,150);
//            
//        }
//        else if (isClient)
//        {
//            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
//            g.setColor(Color.black);
//            g.drawString("The Client",100,150);
//        }
//        else
//        {
//            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
//            g.setColor(Color.black);
//            g.drawString("The Server",100,150);
//        }            
//
//
//        {
//            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
//            g.setColor(Color.black);
//            g.drawString("Client value " + clientValue,100,200);
//        }
//
//        {
//            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
//            g.setColor(Color.black);
//            g.drawString("Server value " + serverValue,100,300);
//            
//        }
//        
//            try
//            {
//                g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
//                g.setColor(Color.black);
//                g.drawString("Your IP address: " + InetAddress.getLocalHost().getHostAddress(), getX(10), getY(20));
//                g.drawString("Enter IP address: " + ipAddress, getX(10), getY(60));
//            }
//            catch (UnknownHostException e)
//            {
//                e.printStackTrace();
//            }
            if(settings)
            {
                GUIs.Settings.drawSettingsWindow(g);
            }
            
        
        gOld.drawImage(image, 0, 0, null);
        
        
        
    }
    
    
    
    
////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    @Override
    public void run() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        while (true)
        {
            animate();
            repaint();
            pen.canvas.repaint();
            double seconds = fps;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try 
            {
                Thread.sleep(miliseconds);
            } 
            catch (InterruptedException e) 
            {
                
            }
        }
    }
    
        /**
     * Updates state of game
     */
    public void update()
    {

        if (animateFirstTime)
        {
            animateFirstTime = false;
            if (xsize != getSize().width || ysize != getSize().height)
            {
                xsize = getSize().width;
                ysize = getSize().height;
            }

            reset();
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {
        
        property=new Property[numRows][numColumns];
        //top row
        
        Property.InitializeDataBase();
        Player.InitializeDataBase2();
        Army.InitializeDataBase();
        Cards.Cards.InitializeDataBase();
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
        
        
        AIOn=false;
        
        
        purchaseX=getX(0)+(4)+boardAlloc+YTITLE*2;
        purchaseY=getY(0)+(750)+getHeight2()/numRows-YTITLE/2+20;
        purchaseLength=(boardAlloc/numColumns)+20;
        purchaseHeight=(boardAlloc/numRows)/3+10;
        
        endTurnX=getX(0)+(4)+boardAlloc+YTITLE*2;
        endTurnY=getY(0)+(750-purchaseHeight-2)+getHeight2()/numRows-YTITLE/2;
        endTurnLength=(boardAlloc/numColumns);
        endTurnHeight=(boardAlloc/numRows)/3+10;
        
        payX=getX(0)+(4)+boardAlloc+YTITLE*2;
        payY=getY(0)+(750-purchaseHeight-2-endTurnHeight)+getHeight2()/numRows-YTITLE/2+20;
        payLength=(boardAlloc/numColumns);
        payHeight=(boardAlloc/numRows)/3+10;
        
        upgradeX=getX(0)+(4)+boardAlloc+YTITLE*2;
        upgradeY=getY(0)+(750-purchaseHeight-2-endTurnHeight-payHeight)+getHeight2()/numRows-YTITLE/2;
        upgradeLength=(boardAlloc/numColumns);
        upgradeHeight=(boardAlloc/numRows)/3+10;
        
        attackX=getX(0)+(4)+boardAlloc+YTITLE*2;
        attackY=getY(0)+(750-purchaseHeight-2-endTurnHeight-2-payHeight-2)+getHeight2()/numRows-YTITLE/2+20;
        attackLength=(boardAlloc/numColumns);
        attackHeight=(boardAlloc/numRows)/3+10;
        
        upgradeArmyX=getX(0)+(4)+boardAlloc+YTITLE*2;
        upgradeArmyY=getY(0)+(750-purchaseHeight-2-endTurnHeight-2-payHeight-2-attackHeight-2)+getHeight2()/numRows-YTITLE/2;
        upgradeArmyLength=(boardAlloc/numColumns)+11*8;
        upgradeArmyHeight=(boardAlloc/numRows)/3+10;
        
        MusicX=WINDOW_WIDTH/2;
        MusicY=200;
        MusicLength=0;
        MusicHeight=0;

        SoundsX=WINDOW_WIDTH/2;
        SoundsY=300;
        SoundsLength=0;
        SoundsHeight=0;
        
        large=false;
        small=false;
        diceRoll=true;
        
        canUpgrade=true;
        musicOn=true;
        
        turns=0;
        
//        music[0]=new sound("anunxepectedparty.wav");
//        music[1]=new sound("dreamingofbagend.wav");
//        music[2]=new sound("oldfriends.wav");
//        music[3]=new sound("radagastthebrown.wav");
//        music[4]=new sound("theadventurebegins.wav");
        
        
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
        
        timeCount++;
        if(!gameStart)
        {
            if(!small&&(sRotation>=20))
            {
                startMenu=true;
                //timeCount++;
                small=true;
                
            }
            else if(!small)
            {
                sScale+=.02;
                sRotation+=.2;
            }
            
            if((small&&!large))
            {
                
                if(sScale>2.3)
                {
                    if(timeCount%15==14)
                        large=true;
                }
                else
                {
                    sDir=.005;
                    sScale+=sDir;
                }
            }
            else if(large)
            {
                if(sScale<2.0)
                {
                    if(timeCount%30==29)
                        large=false;
                }
                else
                {
                    sDir=-.005;
                    sScale+=sDir;
                }
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
                    
                    mScale-=.02;
                    mRotation-=.2;
                    
                    
                }
                
                
            }
            if(startMenuAnim)
            {
                
                xPosStart-=30;
                if(xPosStart<=-getHeight2())
                {
                    gameStart=true;
                    startMenu=false;
                    StartGameH=false;
                    xPosStart=0;
                    startMenuAnim=false;
                }
            }
            return;
        }
        time.addTime();
        if(turnAnimation)
        {
            xAnim+=5;
            yAnim+=5;
            if(xAnim>boardAlloc/2-40&&yAnim>getHeight2()/2)
            {
                timeCount++;
            }
            if(xAnim>boardAlloc/2-35)
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
//        if(turns%5==4)
//        {
//            for(int index=0;index<numPlayers;index++)
//            {
//                int totalCost=0;
//                for(int index2=0;index2<players[index].getNumProperty();index2++)
//                {
//                    Property currentProp=players[index].getPropertyOwnership(index2);
//                    totalCost+=currentProp.getTax();
//                }
//                players[index].setMoney(players[index].getMoney()-totalCost);
//                boolean keepLooping=true;
//                while(keepLooping)
//                {
//                    int randomRow=(int)(Math.random()*numRows+1);
//                    int randomColumn=(int)(Math.random()*numColumns+1);
//                    if(property[randomRow][randomColumn]!=null)
//                    {
//                        keepLooping=false;
//                        players[index].setY(randomRow);
//                        players[index].setX(randomColumn);
//                    }
//                    
//                }
//                
//            }
//        }
        if(gameStart&&musicOn)
        {
            if(music[0]==null)
                music[0]=new sound("./Sounds/Music/TheHobbit/anunxepectedparty.wav");
            
            if(music[1]==null&&music[0].donePlaying)
                music[1]=new sound("./Sounds/Music/TheHobbit/dreamingofbagend.wav");
            
            if(music[1]!=null&&music[2]==null&&music[1].donePlaying)
                music[2]=new sound("./Sounds/Music/TheHobbit/oldfriends.wav");
            
            if(music[2]!=null&&music[3]==null&&music[2].donePlaying)
                music[3]=new sound("./Sounds/Music/TheHobbit/radagastthebrown.wav");
            
            if(music[3]!=null&&music[4]==null&&music[3].donePlaying)
                music[4]=new sound("./Sounds/Music/TheHobbit/theadventurebegins.wav");
            
            if(music[4]!=null&&music[4].donePlaying)
            {
                for(int index=0;index<numSongs;index++)
                {
                    music[index]=null;
                }
            }
            if(dicerollN)
            {
                diceroll=new sound("./Sounds/Tones/Dice.wav");
                dicerollN=false;
            }
            if(purchaseN)
            {
                purchasesound=new sound("./Sounds/Tones/purchase.wav");
                purchaseN=false;
            }
            if(warH)
            {
                warSound=new sound("./Sounds/Tones/war.wav");
                warH=false;
            }
            if(upgradeArmyH)
            {
                upgradeArmySound=new sound("./Sounds/Tones/upgradeArmy.wav");
                upgradeArmyH=false;
            }
            
        }
        
        for(int index=0;index<numPlayers;index++)
        {
            if(players[index].getMoney()<=0&&players[index].getInGame())
            {
                players[index].SetInGame(false);
                loseSound=new sound("./Sounds/Tones/lose.wav");
            }
        }
        for(int index=0;index<numPlayers;index++)
        {
            int count=0;
            if(!players[index].getInGame())
            {
                count++;
            }
            if(count==3)
            {
                reset();
            }
                
        }
        
    }
    
    
    public static void AITurn()
    {
        Player currentPerson=players[currentPlayer];
        Property currentProperty=property[players[currentPlayer].getY()][players[currentPlayer].getX()];
        
        if(currentProperty.getThePlayer()==null&&currentProperty.getCanPurchase()&&currentPerson.getMoney()>=currentProperty.getCost())
        {
            currentProperty.addPlayer(currentPerson);
            currentPerson.addProperty(currentProperty);
            currentPerson.setMoney(currentPerson.getMoney()-currentProperty.getCost());
        }
        else if(currentProperty.getThePlayer()!=null&&currentProperty.getThePlayer()!=currentPerson&&currentPerson.getMoney()>=currentProperty.getRent())
        {
            currentPerson.setMoney(currentPerson.getMoney()-currentProperty.getRent());
            currentProperty.getThePlayer().setMoney(currentProperty.getThePlayer().getMoney()+currentProperty.getRent());
        }
        else if (currentProperty.getThePlayer()!=null&&currentProperty.getThePlayer()!=currentPerson&&currentPerson.getMoney()<currentProperty.getRent()) 
        {
            while(currentPerson.getMoney()<currentProperty.getRent()&&currentPerson.getNumProperty()>=1)
            {
                currentPerson.deleteProperty(currentPerson.getPropertyOwnership(currentPerson.getNumProperty()-1));
            }
            if(currentPerson.getMoney()<currentProperty.getRent())
            {
                currentPerson.SetInGame(false);
            }
            else
            {
                currentPerson.setMoney(currentPerson.getMoney()-currentProperty.getRent());
                currentProperty.getThePlayer().setMoney(currentProperty.getThePlayer().getMoney()+currentProperty.getRent());
            }
            
            
            
        }
            
        return;
        
    }
    
    public void CalculateTax()
    {
        for(int index=0;index<numPlayers;index++)
        {
            //for(int index2=0;index2<players[index].getNumProperties();index2++)
            {
                //players[index].getPropertyOwnership(index2);
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
    public static int getX(int x) {
        return (x + XBORDER + WINDOW_BORDER);
    }
    public static int getY(int y) {
        return (y + YBORDER + YTITLE );
    }
    public static int getYNormal(int y) {
        return (-y + YBORDER + YTITLE + getHeight2());
    }
    public static int getWidth2() {
        return (xsize - 2 * (XBORDER + WINDOW_BORDER));
    }
    public static int getHeight2() {
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
class PanAndZoom extends MonopolyProject{
 
    PanAndZoomCanvas canvas;
    MonopolyProject image=new MonopolyProject();
 
    public static void main(String[] args) {
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    new PanAndZoom();
		}
	    });
    }

    public PanAndZoom() {
	JFrame frame = new JFrame();
        
	canvas = new PanAndZoomCanvas();
	PanningHandler panner = new PanningHandler();
	canvas.addMouseListener(panner);
	canvas.addMouseMotionListener(panner);
	canvas.setBorder(BorderFactory.createLineBorder(Color.black));

	// code for handling zooming
	JSlider zoomSlider = new JSlider(JSlider.HORIZONTAL, 0, 500, 100);
	zoomSlider.setMajorTickSpacing(25);
	zoomSlider.setMinorTickSpacing(5);
	zoomSlider.setPaintTicks(true);
	zoomSlider.setPaintLabels(true);
	zoomSlider.addChangeListener(new ScaleHandler());

	// Add the components to the canvas
	frame.getContentPane().add(zoomSlider, BorderLayout.NORTH);
	frame.getContentPane().add(canvas, BorderLayout.CENTER);
	frame.pack();
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.setVisible(true);
    }
 
    class PanAndZoomCanvas extends JComponent {
	double translateX;
	double translateY;
	double scale;
 
	PanAndZoomCanvas() {
	    translateX = 0;
	    translateY = 0;
	    scale = 1;
	}
 
	public void paintComponent(Graphics g) {
	    Graphics2D ourGraphics = (Graphics2D) g;
	    // save the original transform so that we can restore
	    // it later
	    AffineTransform saveTransform = ourGraphics.getTransform();

	    // blank the screen. If we do not call super.paintComponent, then
	    // we need to blank it ourselves
	    ourGraphics.setColor(Color.WHITE);
	    ourGraphics.fillRect(0, 0, getWidth(), getHeight());
			
	    // We need to add new transforms to the existing
	    // transform, rather than creating a new transform from scratch.
	    // If we create a transform from scratch, we will
	    // will start from the upper left of a JFrame, 
	    // rather than from the upper left of our component
	    AffineTransform at = new AffineTransform(saveTransform);

	    // The zooming transformation. Notice that it will be performed
	    // after the panning transformation, zooming the panned scene,
	    // rather than the original scene
	    at.translate(getWidth()/2, getHeight()/2);
	    at.scale(scale, scale);
	    at.translate(-getWidth()/2, -getHeight()/2);

	    // The panning transformation
	    at.translate(translateX, translateY);

	    ourGraphics.setTransform(at);

	    // draw the objects
	    ourGraphics.setColor(Color.BLACK);
	    ourGraphics.drawRect(50, 50, 50, 50);
	    ourGraphics.fillOval(100, 100, 100, 100);
            
            
	    ourGraphics.drawString("Test Affine Transform", 50, 30);
            if (animateFirstTime) 
            {
                if (xsize != getSize().width || ysize != getSize().height) 
                {
                    xsize = getSize().width;
                    ysize = getSize().height;
                }

                reset();
                animateFirstTime = false;
            
            
            }
            int length=boardAlloc/numColumns*4/3;
            int height2=getHeight2()/numRows;
            int xpos=image.getX(0)+0*boardAlloc/numColumns;
            int ypos=image.getY(0)+0*getHeight2()/numRows;
            property[0][0].draw(ourGraphics,xpos,ypos,length,height2,image);
            
            ourGraphics.drawImage(boardMiddle,image.getX(0)+boardAlloc/numColumns-5, image.getY(0)+getHeight2()/numRows+1,(boardAlloc*(numColumns-2))/numColumns+5, (getHeight2()*(numRows-2))/numRows,this);
            //g.drawImage(Board, YTITLE, YTITLE*2, boardAlloc+XBORDER, getHeight2()-YTITLE,this);
            for (int zrow=0;zrow<numRows;zrow++)
            {
                for (int zcolumn=0;zcolumn<numColumns;zcolumn++)
                {
                    if(property[zrow][zcolumn]!=null)
                    {

                        length=boardAlloc/numColumns*4/3;
                        height2=getHeight2()/numRows;
                        xpos=image.getX(0)+zcolumn*boardAlloc/numColumns;
                        ypos=image.getY(0)+zrow*getHeight2()/numRows;

                        property[zrow][zcolumn].draw(ourGraphics,xpos,ypos,length,height2,image);

                        if(property[zrow][zcolumn].getThePlayer()!=null)
                        {
                            ourGraphics.setColor(property[zrow][zcolumn].getThePlayer().getColor());
                            for(int index=0;index<property[zrow][zcolumn].getUpgrade();index++)
                            {
                                ourGraphics.fillOval(xpos+length/8+index*20, ypos+height2/8, length/5, height2/5);
                            }
                        }

                    }   
                }    
            }
            String text2=null;
            for(int index=0;index<numPlayers;index++)
            {
                xpos=getX(0)+players[index].getX()*boardAlloc/numColumns+index*10;
                ypos=getY(0)+players[index].getY()*getHeight2()/numRows-YTITLE;
                length=getWidth2()/numColumns*3/4;
                height2=getHeight2()/numRows*3/4;
                players[index].drawonBoard(ourGraphics, xpos, ypos+height2/2, length, height2, image);
                g.setColor(Color.black);
                text2 = players[index].getMoney()+"";
                g.drawString(text2, xpos+length/2, ypos+length*3/4); 
                if(index==currentPlayer)
                {
                    Stroke nice = ourGraphics.getStroke();
                    float size=7.0f;
                    ourGraphics.setStroke(new BasicStroke(size));
                    g.setColor(players[index].getColor());
                    ourGraphics.drawOval(xpos-length/4,ypos-height2/4,length+length/2,height2*2);
                    ourGraphics.setStroke(nice);
                }
                
            }

	    // make sure you restore the original transform or else the drawing
	    // of borders and other components might be messed up
	    ourGraphics.setTransform(saveTransform);
	}
	public Dimension getPreferredSize() {
	    return new Dimension(500, 500);
	}
    }
    
    class PanningHandler implements MouseListener,
				    MouseMotionListener {
	int referenceX;
	int referenceY;
 
	public void mousePressed(MouseEvent e) {
	    // capture the starting point
	    referenceX = e.getX();
	    referenceY = e.getY();
	}
	
	public void mouseDragged(MouseEvent e) {
	    int deltaX = e.getX() - referenceX;
	    int deltaY = e.getY() - referenceY;

	    referenceX = e.getX();
	    referenceY = e.getY();
	    
	    canvas.translateX += deltaX;
	    canvas.translateY += deltaY;
 
	    // schedule a repaint.
	    canvas.repaint();
	}
 
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	}
        public void mouseWheelMoved(MouseWheelEvent e) 
        {
            int notches = e.getWheelRotation();
            JSlider slider = (JSlider)e.getSource();
            if(notches<0)
                slider.setValue(slider.getValue()+5);
            else
                slider.setValue(slider.getValue()-5);
        }
 
    class ScaleHandler implements ChangeListener {
	public void stateChanged(ChangeEvent e) {
	    JSlider slider = (JSlider)e.getSource();
	    int zoomPercent = slider.getValue();
	    // make sure zoom never gets to actual 0, or else the objects will
	    // disappear and the matrix will be non-invertible.
	    canvas.scale = Math.max(0.00001, zoomPercent / 100.0);
	    canvas.repaint();
	}
    }
 }



