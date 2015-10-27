/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.project;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Dice {
    
    private int Side1=1;
    private int Side2=2;
    private int Side3=3;
    private int Side4=4;
    private int Side5=5;
    private int Side6=6;
    
    private int side;
    
    private Image side1=Toolkit.getDefaultToolkit().getImage("./Pictures/Dice/diceSide1.PNG");
    private Image side2=Toolkit.getDefaultToolkit().getImage("./Pictures/Dice/diceSide2.PNG");
    private Image side3=Toolkit.getDefaultToolkit().getImage("./Pictures/Dice/diceSide3.PNG");
    private Image side4=Toolkit.getDefaultToolkit().getImage("./Pictures/Dice/diceSide4.PNG");
    private Image side5=Toolkit.getDefaultToolkit().getImage("./Pictures/Dice/diceSide5.PNG");
    private Image side6=Toolkit.getDefaultToolkit().getImage("./Pictures/Dice/diceSide6.PNG");
    
    Dice()
    {
        side=(int)(Math.random()*6+1);
    }
    
    public static int addSides()
    {
        int value;
        value=MonopolyProject.dice.getSide()+MonopolyProject.dice2.getSide();
        return(0);
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }
    public void draw(Graphics2D g,int xpos,int ypos,int length,int height,MonopolyProject image  ) 
    {
        
        Image currentImage=null;
        if(getSide()==Side1)
            currentImage=side1;
        if(getSide()==Side2)
            currentImage=side2;
        if(getSide()==Side3)
            currentImage=side3;
        if(getSide()==Side4)
            currentImage=side4;
        if(getSide()==Side5)
            currentImage=side5;
        if(getSide()==Side6)
            currentImage=side6;
        
        g.drawImage(currentImage, xpos, ypos, length, height,image);
    }
}
