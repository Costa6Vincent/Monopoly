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
import java.util.Locale;
import javax.sound.sampled.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    Graphics2D g;
    
    static Dimension dim;

    public static boolean animateFirstTime = true;
    
    public static int FPS=60;
    
    public static Image image1=null;
    
    public static boolean turnAnimation=false;
    public static int xAnim;
    public static int yAnim;

    public static Font font= new Font("Kabel",Font.BOLD,20);
    public static Font font2= new Font("Kabel",Font.BOLD,75);
    public static Font font3= new Font("BrushScriptMT",Font.BOLD,30);
    public static Image Board=Toolkit.getDefaultToolkit().getImage("./Pictures/BoardPieces/Board2.JPG");
   
    public static int xsize = -1;
    public static int ysize = -1;
    
    public static Time time;
    public static boolean startMenu=false;
    public static boolean startMenuAnim;
    public static int xPosStart;
    
    public static String StartGameS="Start Game";
    public static String SettingsS="Settings";
    public static String HelpS="Help";
    public static String TutorialS="Tutorial";
    public static String ExitS="Exit";
    
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
    
    public static int timeCount;
    
    public static int currentPlayer;
    
    public static boolean diceRoll;
    public static boolean decision;
    public static boolean payRent;
    public static boolean purchase;
    public static boolean upgrade;
    
    public static boolean StartGameH;
    public static boolean SettingsH;
    public static boolean HelpH;
    public static boolean TutorialH;
    public static boolean ExitH;
    
    boolean large,small;
    
    
    
    
    public static boolean gameStart;
    
    public static Property property[][];
    public static final int numPlayers=4;
    public static Player players[]=new Player[numPlayers];
    public static Dice dice;
    public static Dice dice2;
    
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
            if(gameStart)
            {


                g.setColor(Color.yellow);
                if(gameStart)
                {
                    int menuxpos=boardAlloc+YTITLE;
                    int menuypos=YTITLE+1;
                    int menuLength=windowAlloc;
                    int menuHeight=getHeight2()-2;
                    g.setColor(Color.black);
                    g.fillRect(menuxpos, menuypos, menuLength+50, menuHeight+50);
                    g.setColor(Color.red);
                    Stroke nice = g.getStroke();
                    g.setStroke(new BasicStroke(20.0f));
                    g.drawRect(menuxpos, menuypos, menuLength, menuHeight);
                    g.setStroke(nice);
                }

        //Display the objects of the board
                g.setColor(Color.gray);

                //g.fillRect(getX(0)+getWidth2()/numColumns+1, getY(0)+getHeight2()/numRows+1,(getWidth2()*(numColumns-2))/numColumns, (getHeight2()*(numRows-2))/numRows);
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
                                g.setColor(Color.green);
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
                if(time.getHours()!=0&&time.getMinutes()!=0)
                    text = ("Hours:"+time.getHours()+" Minutes: "+time.getMinutes()+" Seconds: "+time.getSeconds());
                if(time.getHours()==0&&time.getMinutes()==0&&time.getSeconds()!=0)
                    text = ("Seconds: "+time.getSeconds());
                if(time.getHours()==0&&time.getMinutes()!=0)
                    text = (" Minutes: "+time.getMinutes()+" Seconds: "+time.getSeconds());
                if(text!=null)
                    g.drawString(text,50,60);

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
            if(!gameStart)
            {
                g.setColor(Color.white);
                g.fillRect(0,0,getWidth2()*50,getHeight2()*50);
                GUIs.SplashScreen.drawSplashScreen(g,getWidth2()/2,getHeight2()/2,360-sRotation,.1+sScale,.05+sScale);
            }
            if(startMenu)
            {
                GUIs.SplashScreenMenu.drawSplashScreenMenu(g,getWidth2()/2,getHeight2()*8/12,0,.1+mScale,.05+mScale);
            }
        gOld.drawImage(image, 0, 0, null);
        
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
        
        
        purchaseX=getX(0)+(4)+boardAlloc+YTITLE*2;
        purchaseY=getY(0)+(10)*getHeight2()/numRows-YTITLE/2;
        purchaseLength=(getWidth2()/numColumns)*2;
        purchaseHeight=getHeight2()/numRows;
        
        payX=getX(0)+(4)+boardAlloc+YTITLE*2;
        payY=getY(0)+(8)*getHeight2()/numRows-YTITLE/2;
        payLength=(getWidth2()/numColumns)*2;
        payHeight=getHeight2()/numRows;
        
        endTurnX=getX(0)+(4)+boardAlloc+YTITLE*2;
        endTurnY=getY(0)+(9)*getHeight2()/numRows-YTITLE/2;
        endTurnLength=(getWidth2()/numColumns)*2;
        endTurnHeight=getHeight2()/numRows;
        
        upgradeX=getX(0)+(4)+boardAlloc+YTITLE*2;
        upgradeY=getY(0)+(7)*getHeight2()/numRows-YTITLE/2;
        upgradeLength=(getWidth2()/numColumns)*2;
        upgradeHeight=getHeight2()/numRows;
        
        large=false;
        small=false;
        
        
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
        if(turnCount%5==4)
        {
            
        }
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
class PanAndZoom {
 
    PanAndZoomCanvas canvas;
 
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
	JSlider zoomSlider = new JSlider(JSlider.HORIZONTAL, 0, 300, 100);
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
	    
	    // the size of the pan translations 
	    // are defined by the current mouse location subtracted
	    // from the reference location
	    int deltaX = e.getX() - referenceX;
	    int deltaY = e.getY() - referenceY;

	    // make the reference point be the new mouse point. 
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



