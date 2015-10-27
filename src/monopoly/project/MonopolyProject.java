/*
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
    
    int xsize = -1;
    int ysize = -1;
    
    int diceRow1=5;
    int diceColumn1=4;
    int diceRow2=5;
    int diceColumn2=6;
    
    static Property property[][];
    static Player players[]=new Player[4];
    static Dice dice;
    static Dice dice2;
    
    public static int timerTick;
    double fps=1.0/FPS;
    
    static MonopolyProject frame1;
    public static void main(String[] args) {

        frame1 = new MonopolyProject();
        frame1.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame1.setLocation(dim.width / 2 - frame1.getWidth() / 2, dim.height / 2 - frame1.getHeight() / 2);
        frame1.setVisible(true);
    }

    public MonopolyProject() 
    {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (MouseEvent.BUTTON3 == e.getButton()){
                    
                }
                
                if (MouseEvent.BUTTON1 == e.getButton()){
                    
                    int xdelta = getWidth2()/numColumns*2;
                    int ydelta = getHeight2()/numRows*2;
                    int xpos = e.getX() - getX(0);
                    int ypos = e.getY() - getY(0);
                    int column = xpos/(xdelta/2);
                    int row = ypos/(ydelta/2);
                    if(diceRow1==row&&diceColumn1==column)
                    {
                        dice=new Dice();
                    }
                    if(diceRow2==row&&diceColumn2==column)
                    {
                        dice2=new Dice();
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
        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.VK_UP == e.getKeyCode())
                {
                    System.out.println(Dice.addSides());
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
        g.fillRect(getX(0)+getWidth2()/numColumns+1, getY(0)+getHeight2()/numRows+1,(getWidth2()*(numColumns-2))/numColumns, (getHeight2()*(numRows-2))/numRows);
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
                    text2 = property[zrow][zcolumn].getName();
                    g.drawString(text2, xpos, ypos+height/4); 
                    
                }   
            }    
        }
        
        dice.draw(g,getX(0)+diceColumn1*getWidth2()/numColumns, getY(0)+diceRow1*getHeight2()/numRows, getWidth2()/numColumns, getHeight2()/numRows,this);
        dice2.draw(g,getX(0)+diceColumn2*getWidth2()/numColumns, getY(0)+diceRow2*getHeight2()/numRows, getWidth2()/numColumns, getHeight2()/numRows,this);
        
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
        
        dice=new Dice();
        dice2=new Dice();
        
        
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
    }
    
    public void Turn()
    {
        
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