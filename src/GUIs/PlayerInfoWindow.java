
package GUIs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.Toolkit;


public class PlayerInfoWindow extends monopoly.project.MonopolyProject
{
    
    public static void drawInfoWindow(Graphics2D g)
    {
        Color black=Color.black;
        if(decision)
            g.setColor(Color.white);
        else
        {
            Color color2 = new Color(Color.red.getRed()/255,Color.red.getGreen()/255,Color.red.getBlue()/255,.3f);
            g.setColor(color2);
        }
        g.setFont(new Font("Impact",Font.ITALIC,30));
        String text2 = null;
        if(decision)
        {
            if(purchase)
            {
                g.setColor(Color.white);
                g.fillRect(purchaseX, purchaseY, purchaseLength, purchaseHeight);
                g.setColor(Color.red);
                text2 = "Purchase";
                g.drawString(text2, purchaseX, purchaseY+purchaseHeight*3/4); 
            }
            if(!payRent)
            {
                g.setColor(Color.white);
                g.fillRect(endTurnX, endTurnY, endTurnLength, endTurnHeight);
                g.setColor(Color.red);
                text2 = "End Turn";
                g.drawString(text2, endTurnX, endTurnY+endTurnHeight*3/4); 
            }
            if(payRent)
            {
                g.setColor(Color.white);
                g.fillRect(payX, payY, payLength, payHeight);
                text2 = "Pay Rent";
                g.setColor(Color.red);
                g.drawString(text2, payX, payY+payHeight*3/4); 
            }
            if(upgrade)
            {
                g.setColor(Color.white);
                g.fillRect(upgradeX, upgradeY, upgradeLength, upgradeHeight);
                text2 = "Upgrade";
                g.setColor(Color.red);
                g.drawString(text2, upgradeX, upgradeY+upgradeHeight*3/4); 
            }
            if(attack)
            {
                g.setColor(Color.white);
                g.fillRect(attackX, attackY, attackLength, attackHeight);
                text2 = "Attack!";
                g.setColor(Color.red);
                g.drawString(text2, attackX, attackY+attackHeight*3/4); 
            }
            
            if(upgradeArmy&&canUpgrade)
            {
                g.setColor(Color.white);
                g.fillRect(upgradeArmyX, upgradeArmyY, upgradeArmyLength, upgradeArmyHeight);
                text2 = "Upgrade Army!";
                g.setColor(Color.red);
                g.drawString(text2, upgradeArmyX, upgradeArmyY+upgradeArmyHeight*3/4); 
            }
            
            

        }
        else
        {
            text2 = "Roll the die";
            g.setColor(Color.red);
            g.drawString(text2, purchaseX, purchaseY+purchaseHeight*3/4); 
        }
        if(players[currentPlayer].getInJail())
        {
            g.fillRect(payX, 100, payLength, payHeight);
            text2 = "In Jail";
            g.setColor(Color.red);
            g.drawString(text2, payX, payY+payHeight*3/4); 
        }
        g.setColor(players[currentPlayer].getColor());
        
        Stroke nice = g.getStroke();
        float size=13.0f;
        g.setStroke(new BasicStroke(size));
        
        g.drawRect(boardAlloc+50, 50, windowAlloc/4, windowAlloc/4);
        g.setStroke(nice);
        
        players[currentPlayer].drawonWindow(g, boardAlloc+50, 50, windowAlloc/4, windowAlloc/4, null);
        text2=players[currentPlayer].getName();
        g.setColor(Color.blue);
        g.drawString(text2, boardAlloc+40, windowAlloc/2-25);
        
        g.setColor(black);
        text2="$";
        g.drawString(text2, boardAlloc+windowAlloc/3+size, 100);
        g.setColor(black);
        text2=""+players[currentPlayer].getMoney();
        g.drawString(text2, boardAlloc+windowAlloc/3+size+20, 100);
        if(players[currentPlayer].getNumProperty()>=1)
        {
            g.setColor(Color.white);
            text2="Properties: "+players[currentPlayer].getNumProperty();
            g.drawString(text2, boardAlloc+50, windowAlloc/2+20);
            for(int index=0;index<players[currentPlayer].getNumProperty();index++)
            {
                text2=players[currentPlayer].getPropertyOwnership(index).getName();
                g.setColor(Color.white);
                g.drawString(text2, boardAlloc+50, windowAlloc/2+((40+size)*(index+1))); 
            }
        }
        if(players[currentPlayer].getTheArmy()!=null)
        {
            g.setFont(new Font("Impact",Font.ITALIC,40));
            g.setColor(black);
            text2="Army Power: "+players[currentPlayer].getTheArmy().getCurrentArmyPower();
            g.drawString(text2, boardAlloc+windowAlloc/3+size+20,150); 
        }
        if(property[players[currentPlayer].getY()][players[currentPlayer].getX()]!=null)
        {
            g.setFont(new Font("Impact",Font.ITALIC,40));
            g.setColor(black);
            text2="Property P: "+property[players[currentPlayer].getY()][players[currentPlayer].getX()].getDefense();
            g.drawString(text2, boardAlloc+windowAlloc/3+size+20,190); 
        }
        if(property[players[currentPlayer].getY()][players[currentPlayer].getX()].getThePlayer()==null)
        {
            g.setFont(new Font("Impact",Font.ITALIC,40));
            g.setColor(black);
            text2="Property Val: "+property[players[currentPlayer].getY()][players[currentPlayer].getX()].getCost();
            g.drawString(text2, boardAlloc+windowAlloc/3+size+20,230); 
        }
        
        
    }
    
}
