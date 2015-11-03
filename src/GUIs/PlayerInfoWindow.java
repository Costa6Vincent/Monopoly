
package GUIs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class PlayerInfoWindow extends monopoly.project.MonopolyProject
{
    public static void drawInfoWindow(Graphics2D g)
    {
        if(decision)
            g.setColor(Color.white);
        else
        {
            Color color2 = new Color(Color.red.getRed()/255,Color.red.getGreen()/255,Color.red.getBlue()/255,.3f);
            g.setColor(color2);
        }
        g.setFont(new Font("Impact",Font.ITALIC,40));
        String text2 = null;
        if(decision)
        {
            if(purchase)
            {
                g.fillRect(purchaseX, purchaseY, purchaseLength, purchaseHeight);
                g.fillRect(endTurnX, endTurnY, endTurnLength, endTurnHeight);
                
                
                
                g.setColor(Color.red);
                
                text2 = "Purchase";
                g.drawString(text2, purchaseX, purchaseY+purchaseHeight*3/4); 
                
                
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
            if(players[currentPlayer].getNumProperty()>=1)
            {
                for(int index=0;index<players[currentPlayer].getNumProperty();index++)
                {
                    text2=players[currentPlayer].getPropertyOwnership(index).getName();
                    g.setColor(Color.white);
                    g.drawString(text2, boardAlloc+50, YTITLE*3+index*30); 
                }
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
        
    }
    
}
